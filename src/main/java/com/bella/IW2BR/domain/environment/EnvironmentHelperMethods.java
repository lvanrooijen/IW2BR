package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.notecollection.NoteCollection;
import com.bella.IW2BR.domain.tag.Tag;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.exceptions.generic.IllegalActionException;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.exceptions.generic.ResourceNotInEnvironmentException;
import com.bella.IW2BR.security.AuthHelperService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** This class contains helper methods related to environments */
@Service
@RequiredArgsConstructor
public class EnvironmentHelperMethods {
  private final EnvironmentRepository environmentRepository;
  private final AuthHelperService authHelperService;

  public Environment getEnvironmentOrThrow(Long environmentId) {
    return environmentRepository
        .findById(environmentId)
        .orElseThrow(() -> new ItemNotFoundException("environment not found"));
  }

  /**
   * Verifies if the authenticated user is the owner of the environment, or has an admin role.
   *
   * @param environment environment
   * @throws IllegalActionException if the provided user is not the owner of the environment and
   *     does not have admin role
   */
  public void throwIfNotOwnerOrAdmin(Environment environment) {
      User user = authHelperService.getAuthenticatedUser();
    if (!environment.isOwner(user.getId()) && !user.isAdmin()) {
      throw new IllegalActionException(
          "Failed to create tag. creator is not the owner of the environment");
    }
  }

  /**
   * Verifies if the authenticated user is the owner of the environment, or has an admin role.
   *
   * @param environmentId ID of environment
   * @throws IllegalActionException When the user is not the owner of the environment and is not
   *     admin role
   * @throws ItemNotFoundException when the provided environment ID is not found
   */
  public void throwIfNotOwnerOrAdmin(Long environmentId) {
      User user = authHelperService.getAuthenticatedUser();
    Environment environment =
        environmentRepository
            .findById(environmentId)
            .orElseThrow(() -> new ItemNotFoundException("environment not found"));
    if (!environment.isOwner(user.getId()) && !user.isAdmin()) {
      throw new IllegalActionException(
          "Failed to create tag. creator is not the owner of the environment");
    }
  }

  /**
   * Verifies if a Tag is connected to the specified environment.
   *
   * @param tag The tag
   * @param environmentId ID of the environment Tag is supposed to be a member of
   * @throws ResourceNotInEnvironmentException if provided Tag is not a member of environment
   */
  public void throwIfNotInEnvironment(Tag tag, Long environmentId) {
    if (!Objects.equals(tag.getEnvironment().getId(), environmentId)) {
      throw new ResourceNotInEnvironmentException("Tag is not a member of this environment");
    }
  }

  /**
   * Verifies if a Note Collection is connected to the specified environment.
   *
   * @param noteCollection Note Collection
   * @param environmentId ID of the environment Tag is supposed to be a member of
   * @throws ResourceNotInEnvironmentException if Note Collection is not a member of environment
   */
  public void throwIfNotInEnvironment(NoteCollection noteCollection, Long environmentId) {
    if (!Objects.equals(noteCollection.getEnvironment().getId(), environmentId)) {
      throw new ResourceNotInEnvironmentException(
          "Note Collection is not a member of this environment");
    }
  }
}

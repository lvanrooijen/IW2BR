package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.environment.dto.*;
import com.bella.IW2BR.domain.environment.util.EnvironmentHelperMethods;
import com.bella.IW2BR.domain.user.User;
import com.bella.IW2BR.exceptions.generic.ItemNotFoundException;
import com.bella.IW2BR.security.AuthHelperService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** handles the business logic related to environments */
@Service
@RequiredArgsConstructor
public class EnvironmentService {
  private final EnvironmentRepository environmentRepository;
  private final AuthHelperService authHelperService;
  private final EnvironmentHelperMethods environmentHelperMethods;
  private final EnvironmentMapper mapper;

  public GetEnvironment create(PostEnvironment body) {
    User loggedInUser = authHelperService.getAuthenticatedUser();

    Environment createdEnvironment = mapper.fromPost(body, loggedInUser);
    environmentRepository.save(createdEnvironment);

    return mapper.toGet(createdEnvironment);
  }

  public GetEnvironment getById(Long id) {
    Environment environment = getEnvironmentOrThrow(id);
    environmentHelperMethods.throwIfNotOwnerOrAdmin(id);

    return mapper.toGet(environment);
  }

  public GetFullEnvironment getFullById(Long id) {
    Environment environment = getEnvironmentOrThrow(id);
    environmentHelperMethods.throwIfNotOwnerOrAdmin(id);

    return mapper.toGetFull(environment);
  }

  public List<GetEnvironment> getAll() {
    User loggedInUser = authHelperService.getAuthenticatedUser();

    List<Environment> environments;
    if (loggedInUser.isAdmin()) {
      environments = environmentRepository.findAll();
    } else {
      environments = environmentRepository.findAllByOwner(loggedInUser);
    }

    return environments.stream().map(mapper::toGet).toList();
  }

  public GetEnvironment update(Long id, PatchEnvironment patch) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(id);
    Environment environment = getEnvironmentOrThrow(id);

    mapper.updateFields(environment, patch);
    environmentRepository.save(environment);

    return mapper.toGet(environment);
  }

  public void delete(Long id) {
    environmentHelperMethods.throwIfNotOwnerOrAdmin(id);
    getEnvironmentOrThrow(id);

    environmentRepository.deleteById(id);
  }

  // helper methods
  public Environment getEnvironmentOrThrow(Long environmentId) {
    return environmentRepository
        .findById(environmentId)
        .orElseThrow(() -> new ItemNotFoundException("Environment not found"));
  }
}

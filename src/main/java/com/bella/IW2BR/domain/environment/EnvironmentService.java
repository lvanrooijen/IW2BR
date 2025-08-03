package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.environment.dto.EnvironmentMapper;
import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.PatchEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
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
  private final EnvironmentMapper mapper;

  public GetEnvironment createEnvironment(PostEnvironment body) {
    User loggedInUser = authHelperService.getAuthenticatedUser();

    Environment createdEnvironment = mapper.fromPostEnvironment(body, loggedInUser);
    environmentRepository.save(createdEnvironment);

    return mapper.toGetEnvironment(createdEnvironment);
  }

  public GetEnvironment getById(Long id) {
    Environment environment =
        environmentRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ItemNotFoundException(
                        String.format("environment with id: %d can not be found", id)));
    return mapper.toGetEnvironment(environment);
  }

  public List<GetEnvironment> getAll() {
    List<Environment> environments = environmentRepository.findAll();
    return environments.stream().map(mapper::toGetEnvironment).toList();
  }

  public GetEnvironment updateEnvironment(Long id, PatchEnvironment patch) {
    Environment environment =
        environmentRepository
            .findById(id)
            .orElseThrow(
                () ->
                    new ItemNotFoundException(
                        String.format(
                            "Can not update environment, and environment with id %d does not exist",
                            id)));

    mapper.updateEnvironmentFields(environment, patch);
    environmentRepository.save(environment);

    return mapper.toGetEnvironment(environment);
  }

  public void deleteEnvironmentById(Long id) {
    environmentRepository
        .findById(id)
        .orElseThrow(
            () ->
                new ItemNotFoundException(
                    String.format(
                        "Can not delete environment, environment by id %s can not be found", id)));
    environmentRepository.deleteById(id);
  }
}

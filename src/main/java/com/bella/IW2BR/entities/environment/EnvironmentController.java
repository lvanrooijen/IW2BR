package com.bella.IW2BR.entities.environment;

import com.bella.IW2BR.entities.environment.dto.GetEnvironment;
import com.bella.IW2BR.entities.environment.dto.PatchEnvironment;
import com.bella.IW2BR.entities.environment.dto.PostEnvironment;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(Endpoints.ENVIRONMENTS)
@RequiredArgsConstructor
public class EnvironmentController implements EnvironmentControllerSwaggerDocs {
  private final EnvironmentService environmentService;

  @Override
  @PostMapping
  public ResponseEntity<GetEnvironment> createEnvironment(
      @RequestBody @Valid PostEnvironment body) {
    GetEnvironment environment = environmentService.createEnvironment(body);
    URI location =
        UriComponentsBuilder.newInstance()
            .path("environments/{id}")
            .buildAndExpand(environment.id())
            .toUri();

    return ResponseEntity.created(location).body(environment);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<GetEnvironment> getEnvironmentById(@PathVariable Long id) {
    GetEnvironment environment = environmentService.getById(id);
    return ResponseEntity.ok(environment);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<GetEnvironment>> getAllEnvironments() {
    List<GetEnvironment> environments = environmentService.getAll();
    return ResponseEntity.ok(environments);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<GetEnvironment> updateEnvironment(
      @PathVariable Long id, @RequestBody PatchEnvironment patch) {
    GetEnvironment updatedEnvironment = environmentService.updateEnvironment(id, patch);
    return ResponseEntity.ok(updatedEnvironment);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEnvironment(@PathVariable Long id) {
    environmentService.deleteEnvironmentById(id);
    return ResponseEntity.ok().build();
  }
}

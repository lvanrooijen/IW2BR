package com.bella.IW2BR.domain.environment;

import com.bella.IW2BR.domain.environment.dto.GetEnvironment;
import com.bella.IW2BR.domain.environment.dto.PatchEnvironment;
import com.bella.IW2BR.domain.environment.dto.PostEnvironment;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Endpoints.ENVIRONMENTS)
@RequiredArgsConstructor
public class EnvironmentController implements EnvironmentControllerDocs {
  private final EnvironmentService environmentService;

  @Override
  @PostMapping
  public ResponseEntity<GetEnvironment> create(@Valid @RequestBody PostEnvironment body) {
    GetEnvironment environment = environmentService.create(body);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(environment.id())
            .toUri();

    return ResponseEntity.created(location).body(environment);
  }

  @Override
  @GetMapping("/{id}")
  public ResponseEntity<GetEnvironment> get(@PathVariable Long id) {
    GetEnvironment environment = environmentService.getById(id);
    return ResponseEntity.ok(environment);
  }

  @Override
  @GetMapping
  public ResponseEntity<List<GetEnvironment>> getAll() {
    List<GetEnvironment> environments = environmentService.getAll();
    return ResponseEntity.ok(environments);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<GetEnvironment> patch(
      @PathVariable Long id, @Valid @RequestBody PatchEnvironment patch) {
    GetEnvironment updatedEnvironment = environmentService.update(id, patch);
    return ResponseEntity.ok(updatedEnvironment);
  }

  @Override
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    environmentService.delete(id);
    return ResponseEntity.noContent().build();
  }
}

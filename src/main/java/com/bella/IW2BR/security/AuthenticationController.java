package com.bella.IW2BR.security;

import com.bella.IW2BR.domain.user.dto.*;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(Endpoints.AUTH)
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationAPIDocs {
  private final AuthenticationService authenticationService;

  @Override
  @PostMapping("/register")
  public ResponseEntity<GetUserWithJwtToken> register(
      @Valid @RequestBody PostUser body, HttpServletResponse response) {
    GetUserWithJwtToken user = authenticationService.registerUser(body, response);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(user.id())
            .toUri();
    return ResponseEntity.created(location).body(user);
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<GetUserWithJwtToken> login(
      @Valid @RequestBody LoginUser body, HttpServletResponse response) {
    GetUserWithJwtToken user = authenticationService.login(body, response);
    return ResponseEntity.ok(user);
  }

  @Override
  @PostMapping("/refresh-token")
  public ResponseEntity<GetUserWithJwtToken> refreshToken(
      HttpServletRequest request, HttpServletResponse response) {
    GetUserWithJwtToken user = authenticationService.refreshToken(request, response);

    return ResponseEntity.ok(user);
  }

  @Override
  @PatchMapping("/{id}")
  public ResponseEntity<GetUser> updateUser(
      @PathVariable UUID id, @RequestBody @Valid PatchUser patch) {
    GetUser customer = authenticationService.updateUser(id, patch);

    return ResponseEntity.ok(customer);
  }

  @Override
  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    authenticationService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }

  @Override
  @PostMapping("/logout")
  public ResponseEntity<Void> logoutUser(HttpServletRequest request) {
    authenticationService.logoutUser(request);
    return ResponseEntity.ok().build();
  }
}

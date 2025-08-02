package com.bella.IW2BR.security;

import com.bella.IW2BR.entities.user.dto.*;
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
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(Endpoints.AUTH)
@RequiredArgsConstructor
public class AuthenticationController implements AuthenticationControllerDocs {
  private final AuthenticationService authenticationService;

  @Override
  @PostMapping("/register")
  public ResponseEntity<GetUserWithJwtToken> registerUser(
      @RequestBody @Valid PostUser body, HttpServletResponse response) {
    GetUserWithJwtToken user = authenticationService.registerUser(body, response);

    URI location =
        UriComponentsBuilder.newInstance().path("/users/{id}").buildAndExpand(user.id()).toUri();
    return ResponseEntity.created(location).body(user);
  }

  @Override
  @PostMapping("/login")
  public ResponseEntity<GetUserWithJwtToken> login(
      @RequestBody LoginUser body, HttpServletResponse response) {
    GetUserWithJwtToken user = authenticationService.login(body, response);
    return ResponseEntity.ok(user);
  }

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
  // TODO gebruiker mag eigen account ook verwijderen. die SpeLL even uitzoeken hiervoor!
  @PreAuthorize("hasRole('ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
    authenticationService.deleteUser(id);
    return ResponseEntity.ok().build();
  }

  // TODO logout, revoke de refresh token
}

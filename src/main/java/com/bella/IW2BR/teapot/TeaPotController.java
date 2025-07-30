package com.bella.IW2BR.teapot;

import com.bella.IW2BR.security.refreshtoken.RefreshToken;
import com.bella.IW2BR.security.refreshtoken.RefreshTokenRepository;
import com.bella.IW2BR.utils.constants.routes.Endpoints;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Temporary controller for testing purposes
 *
 * <p>TODO delete this controller
 */
@RestController
@RequestMapping(Endpoints.BASE_ROUTE + "/teapot")
@RequiredArgsConstructor
public class TeaPotController {
  private final RefreshTokenRepository refreshTokenRepository;

  @GetMapping()
  public ResponseEntity<String> testing123() {
    HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
    return ResponseEntity.status(status).body("Stinging nettle tea");
  }

  @GetMapping("/refresh-tokens")
  public ResponseEntity<List<RefreshToken>> getAllRefreshTokens() {
    return ResponseEntity.ok(refreshTokenRepository.findAll());
  }
}

package com.bella.IW2BR.teapot;

import com.bella.IW2BR.utils.constants.routes.Endpoints;
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
@RequestMapping(Endpoints.BASE_ROUTE)
public class TeaPotController {
  @GetMapping("/teapot")
  public ResponseEntity<String> testing123() {
    HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
    return ResponseEntity.status(status).body("Stinging nettle tea");
  }
}

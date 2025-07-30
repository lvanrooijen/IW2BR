package com.bella.IW2BR.utils.constants.routes;

/** Contains constants holding endpoints for security configuration */
public class SecurityRoutes {
  private static final String BASE = "/api/v1";
  private static final String REGISTER_USERS = BASE + "/auth/register";
  private static final String LOGIN_USERS = BASE + "/auth/login";
  private static final String SWAGGER_UI = "/swagger-ui/**";
  private static final String SWAGGER_DOCS = "/v3/api-docs*/**";

  // TODO delete me
  private static final String TESTING_TEAPOT = BASE + "/teapot";

  /**
   * Method that returns endpoints that don't require authentication
   *
   * @return String[] containing endpoints
   */
  public static String[] getNonAuthenticatedEndpoints() {
    return new String[] {REGISTER_USERS, LOGIN_USERS, SWAGGER_UI, SWAGGER_DOCS, TESTING_TEAPOT};
  }
}

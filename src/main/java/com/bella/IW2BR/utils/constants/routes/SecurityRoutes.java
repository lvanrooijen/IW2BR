package com.bella.IW2BR.utils.constants.routes;

/** Contains constants holding endpoints for security configuration */
public class SecurityRoutes {
  private static final String BASE = "/api/v1";
  private static final String AUTH = BASE + "/auth";
  private static final String REGISTER_USERS = AUTH + "/register";
  private static final String LOGIN_USERS = AUTH + "/login";
  private static final String LOGOUT = AUTH + "/logout";
  private static final String REFRESH_TOKEN = AUTH + "/refresh-token";
  private static final String SWAGGER_UI = "/swagger-ui/**";
  private static final String SWAGGER_DOCS = "/v3/api-docs*/**";

  /**
   * Method that returns endpoints that don't require authentication
   *
   * @return String[] containing endpoints
   */
  public static String[] getNonAuthenticatedEndpoints() {
    return new String[] {
      REFRESH_TOKEN, LOGOUT, REGISTER_USERS, LOGIN_USERS, SWAGGER_UI, SWAGGER_DOCS
    };
  }
}

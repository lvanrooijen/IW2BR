package com.bella.IW2BR.utils.constants.routes;

/** Contains constant values for this apps endpoints */
public class Endpoints {
  // TODO delete me
  public static final String TESTING_TEAPOT = "/api/v1/teapots";

  public static final String BASE_ROUTE = "/api/v1";
  public static final String AUTH = BASE_ROUTE + "/auth";
  public static final String USERS = BASE_ROUTE + "/users";
  public static final String ENVIRONMENTS = BASE_ROUTE + "/environments";
  private static final String ENVIRONMENTS_WITH_ID = BASE_ROUTE + "/environments/{environmentId}";
  public static final String TAGS = ENVIRONMENTS_WITH_ID + "/tags";
  public static final String NOTE_COLLECTIONS = ENVIRONMENTS_WITH_ID + "/note_collections";
  private static final String NOTE_COLLECTIONS_WITH_ID = NOTE_COLLECTIONS + "/{noteCollectionId}";
  public static final String NOTES = NOTE_COLLECTIONS_WITH_ID + "/notes";
}

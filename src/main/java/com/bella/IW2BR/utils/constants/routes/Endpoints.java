package com.bella.IW2BR.utils.constants.routes;

/** Contains constant values for this apps endpoints */
public class Endpoints {
  public static final String BASE_ROUTE = "/api/v1";

  public static final String AUTH = BASE_ROUTE + "/auth";
  public static final String USERS = BASE_ROUTE + "/users";

  public static final String ENVIRONMENTS = BASE_ROUTE + "/environments";

  private static final String ENVIRONMENTS_WITH_ID = BASE_ROUTE + "/environments/{environmentId}";

  public static final String TAGS = ENVIRONMENTS_WITH_ID + "/tags";

  public static final String NOTE_COLLECTIONS = ENVIRONMENTS_WITH_ID + "/note_collections";
  private static final String NOTE_COLLECTIONS_WITH_ID = NOTE_COLLECTIONS + "/{noteCollectionId}";

  public static final String NOTES = NOTE_COLLECTIONS_WITH_ID + "/notes";

  public static final String FLASHCARD_DECKS = ENVIRONMENTS_WITH_ID + "/flashcard_decks";
  private static final String FLASHCARD_DECKS_WITH_ID = FLASHCARD_DECKS + "/{flashcardDeckId}";

  public static final String FLASHCARDS = FLASHCARD_DECKS_WITH_ID + "/flashcards";

  public static final String EXAMS = ENVIRONMENTS_WITH_ID + "/exams";
  private static final String EXAMS_WITH_ID = EXAMS + "/{examId}";

  public static final String QUESTIONS = EXAMS_WITH_ID + "/questions";

  public static final String EXAM_ATTEMPTS = EXAMS_WITH_ID + "/attempts";
  private static final String EXAM_ATTEMPTS_WITH_ID = EXAMS_WITH_ID + "/attempts/{attemptId}";
}

package com.bella.IW2BR.utils.constants.ApiDocs;

public class APIDocumentationConstants {
  public static final String SEPARATOR = "<br><br>";
  public static final String SUCCESS = "success";
  public static final String CREATED = "Created";
  public static final String DELETED = "Deleted";
  public static final String OWNER_ADMIN_ACCESS_ONLY = "authenticated user is not owner or ADMIN";

  // entity not a member
  public static final String EXAM_NOT_IN_ENVIRONMENT =
      "The exam specified in the path is not part of the environment specified in path";
  public static final String EXAM_ATTEMPT_NOT_IN_ENVIRONMENT =
      "The exam Attempt is not part of the environment specified in the path";
  public static final String EXAM_ATTEMPT_NOT_IN_EXAM =
      "Exam Attempt is not connected to exam specified in path";
  public static final String QUESTION_NOT_IN_EXAM =
      "Question is not connected to exam specified in path";
  public static final String FLASHCARD_DECK_NOT_IN_ENVIRONMENT =
      "The Flashcard Deck specified in the path is not part of the environment specified in path";
  public static final String FLASHCARD_NOT_IN_DECK =
      "The Flashcard specified in the path is not part of the Flashcard Deck specified in path";
  public static final String NOTE_COLLECTION_NOT_IN_ENVIRONMENT =
      "The Note Collection specified in the path is not part of the Environment specified in path";
  public static final String NOTE_NOT_IN_COLLECTION =
      "The Note specified in the path is not part of the Note Collection specified in path";
  public static final String TAG_NOT_IN_ENVIRONMENT =
      "The Tag specified in the path is not part of the Environment specified in path";
  // not found:
  public static final String ENVIRONMENT_404 = "Environment with provided ID can not be found";
  public static final String EXAM_404 = "Exam with provided ID can not be found";
  public static final String EXAM_ATTEMPT_404 = "Exam Attempt with provided ID can not be found";
  public static final String TAG_404 = "Tag with provided ID can not be found";
  public static final String QUESTION_404 = "Question with provided ID can not be found";
  public static final String FLASHCARD_404 = "Flashcard with provided ID can not be found";
  public static final String FLASHCARD_DECK_404 =
      "Flashcard Deck with provided ID can not be found";
  public static final String NOTE_404 = "Note with provided ID can not be found";
  public static final String NOTE_COLLECTION_404 =
      "Note Collection with provided ID can not be found";
  public static final String USER_404 = "User with provided ID can not be found";

  // duplicates
  public static final String DUPLICATE_ENVIRONMENT = "Environment with this name already exists";

  // Bad request
  public static final String INVALID_REQUEST_BODY = "The provided request body is invalid";
  public static final String EXAM_NOT_FINALIZED =
      "The exam is not finalized, it must be completed before performing this action";

  // public static final String NAME = "";
}

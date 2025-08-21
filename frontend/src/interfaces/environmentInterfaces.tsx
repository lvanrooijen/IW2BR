export interface PostEvironmentProps {
  title: string;
  description: string;
}

export interface EnvironmentResponseProps {
  id?: number;
  title: string;
  description: string;
  noteCollections: BaseEntity[];
  exams: BaseEntity[];
  examAttempts: BaseEntity[];
  flashcardDecks: BaseEntity[];
  tags: BaseEntity[];
}

export interface BaseEntity {
  id?: number;
  title: string;
  description: string;
}

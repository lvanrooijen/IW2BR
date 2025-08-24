export interface PostEvironmentProps {
  title: string;
  description: string;
}

export interface EnvironmentResponseProps {
  id?: number;
  title: string;
  description: string;
  noteCollections: NoteCollection[];
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

export interface NoteCollection extends BaseEntity {
  notes: Note[];
}

export interface Note {
  id: number;
  title: string;
  body: string;
  tagId: number;
}

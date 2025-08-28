export interface PostEvironmentProps {
  title: string;
  description: string;
}

export interface EnvironmentResponseProps {
  id?: number;
  title: string;
  description: string;
  noteCollections: INoteCollection[];
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

export interface INoteCollection extends BaseEntity {
  notes: INote[];
}

export interface INote {
  id: number;
  title: string;
  body: string;
  tagId: number;
}

export interface ITag {
  id: number;
  title: string;
}

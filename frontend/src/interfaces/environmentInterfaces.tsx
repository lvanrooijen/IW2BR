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
  flashcardDecks: IFlashcardDeck[];
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
  tag: ITag;
}

export interface IFlashcardDeck extends BaseEntity {
  notes: IFlashcard[];
}

export interface IFlashcard {
  id: number;
  front: string;
  back: string;
  tag: ITag;
}

export interface ITag {
  id: number;
  title: string;
}

import type { INote } from '../../interfaces/environmentInterfaces';

const Note: React.FC<NoteProps> = ({ note }) => {
  return (
    <li
      className={`bg-amber-300 rounded-sm p-3 text-base-300 h-96 overflow-ellipsis w-96`}
    >
      <h2 className="font-bold border-b-1 rounded-xs px-3 min-h-12 flex flex-col justify-end pb-3 tracking-wider">
        {note.title}
      </h2>
      <p className="p-3 italic">{note.body}</p>
      <p>{note.tagId}</p>
    </li>
  );
};

export default Note;

interface NoteProps {
  note: INote;
}

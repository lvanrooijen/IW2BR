import type { INote } from '../../interfaces/environmentInterfaces';
import TagDropdown from '../tags/TagDropdown';

const Note: React.FC<NoteProps> = ({ note }) => {
  return (
    <li
      className={`bg-amber-300 rounded-sm p-3 text-base-300 h-96 overflow-ellipsis w-96 grid grid-cols-3 grid-rows-6 gap-3`}
    >
      <h2 className="font-bold border-b-1 rounded-xs px-3 min-h-12 flex flex-col justify-end pb-3 tracking-wider col-span-3 row-span-1">
        {note.title}
      </h2>
      <p className="p-3 italic col-span-3 row-span-4">{note.body}</p>
      <TagDropdown style={'col-span-3 row-span-1'} />
    </li>
  );
};

export default Note;

interface NoteProps {
  note: INote;
}

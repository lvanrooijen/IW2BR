import { useState } from 'react';
import type { INote } from '../../interfaces/environmentInterfaces';
import TagDropdown from '../tags/TagDropdown';
import submitIcon from '../../assets/icons/submit.png';
import deleteIcon from '../../assets/icons/delete.png';
import editIcon from '../../assets/icons/edit.png';
import AxiosInstance from '../../util/services/AxiosInstance';
import { useNavigate, useParams } from 'react-router';
import { useEnvironment } from '../../util/context/EnvironmentContext';

const NoteForm: React.FC<Props> = ({ note }) => {
  const { environmentId, noteCollectionId } = useParams();
  const navigate = useNavigate();
  const { getEnvironmentData } = useEnvironment();
  const [editMode, setEditMode] = useState<boolean>(false);
  const [title, setTitle] = useState<string>(note.title);
  const [body, setBody] = useState<string>(note.body);

  const getFormValues = (e: React.FormEvent) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget as HTMLFormElement);

    const filteredForm = Object.fromEntries(
      Array.from(formData.entries()).filter(([key, value]) => value !== '')
    );

    console.log(filteredForm);
    handleSubmit(filteredForm);

    setEditMode(false);
  };

  const handleSubmit = (form) => {
    AxiosInstance.patch(
      `environments/${environmentId}/note_collections/${noteCollectionId}/notes/${note.id}`,
      form
    )
      .then((response) => console.log(response.data))
      .catch((error) => console.error(error))
      .finally(() => setEditMode(false));
  };

  const handleDelete = () => {
    AxiosInstance.delete(
      `environments/${environmentId}/note_collections/${noteCollectionId}/notes/${note.id}`
    )
      .then((response) => {
        if (response.status === 204) {
          navigate(
            `/environments/${environmentId}/note_collections/${noteCollectionId}`
          );
          getEnvironmentData(Number(environmentId));
        }
      })
      .catch((error) => console.error(error));
  };

  return (
    <div>
      <form
        onSubmit={getFormValues}
        className={`grid grid-cols-12 grid-rows-5 gap-4 bg-amber-300 rounded-sm p-3 text-base-300 h-96 overflow-ellipsis w-96`}
      >
        <input
          className="col-span-8 font-bold border-b-1 rounded-xs px-3 min-h-12 flex flex-col justify-end pb-3 tracking-wider"
          type="text"
          name="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <div className="col-span-4 col-start-10 flex items-center justify-end w-full gap-1">
          {editMode ? (
            <button
              type="submit"
              className="btn p-1.5 bg-green-800 hover:bg-green-900"
            >
              <img src={submitIcon} alt="submit" className="h-full" />
            </button>
          ) : (
            <button
              type="button"
              className="btn p-1.5 bg-amber-300 hover:bg-amber-600"
              onClick={(e) => {
                e.preventDefault();
                setEditMode(true);
              }}
            >
              <img src={editIcon} alt="edit" className="h-full" />
            </button>
          )}

          <button
            type="button"
            className="btn p-1.5 bg-amber-300 hover:bg-red-800"
            onClick={handleDelete}
          >
            <img src={deleteIcon} alt="delete" className="h-full" />
          </button>
        </div>

        <textarea
          className="col-span-12 row-span-3 row-start-2 p-3 italic resize-none"
          name="body"
          value={body}
          onChange={(e) => setBody(e.target.value)}
        />
        <TagDropdown
          selectedTagId={note.tag?.id}
          style="col-span-12 row-start-5"
        />

        <input
          type="hidden"
          onKeyDown={(e) => {
            if (e.key === 'Enter' && !editMode) {
              e.preventDefault();
            }
          }}
        />
      </form>
    </div>
  );
};

export default NoteForm;

interface Props {
  note: INote;
}

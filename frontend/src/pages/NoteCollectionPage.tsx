import { useEffect, useState } from 'react';
import { useEnvironment } from '../util/context/EnvironmentContext';
import { useParams } from 'react-router';
import type { NoteCollection } from '../interfaces/environmentInterfaces';
import NotFound from '../components/notfound/NotFound';
import CreateNewEntity from '../components/form/CreateNewEntity';
import { ClimbingBoxLoader } from 'react-spinners';
import FormInputField from '../components/form/FormInputField';
import FormTextArea from '../components/form/FormTextArea';

const NoteCollectionPage = () => {
  const { environmentId, noteCollectionId } = useParams();
  const { environment } = useEnvironment();
  const [noteCollection, setNoteCollection] = useState<NoteCollection | null>(
    null
  );

  useEffect(() => {
    if (!environment || !noteCollectionId) return;

    const collection =
      environment.noteCollections?.find(
        (c) => c.id === Number(noteCollectionId)
      ) ?? null;

    setNoteCollection(collection);
    console.log('NOTE COLLECTION:: ', collection);
  }, [environment, noteCollectionId]);

  if (!environment) return <ClimbingBoxLoader />;
  if (noteCollection == null) return <NotFound />;

  return (
    <div className="p-6 flex flex-col gap-3">
      <div className="border-b-3">
        <h1 className="font-bold tracking-wider px-3">
          {noteCollection.title}
        </h1>
        <p className="p-3 italic">{noteCollection.description}</p>
        <CreateNewEntity
          postTo={`environments/${environmentId}/note_collections/${noteCollectionId}/notes`}
          formLabel={'Note'}
        >
          <FormInputField placeholder={'Title'} name={'title'} />
          <FormTextArea name={'body'} placeholder={'body'} />
          <p>
            TODO iets van een component die alle tags van environment ophaalt,
            drop down. en selected geeft id terug.
          </p>
        </CreateNewEntity>
      </div>
      <ul className="flex flex-col gap-3 items-center">
        {noteCollection.notes.map((note) => (
          <li
            key={note.id}
            className={`bg-amber-300 rounded-sm p-3 text-base-300 h-96 overflow-ellipsis w-96`}
          >
            <h2 className="font-bold border-b-1 rounded-xs px-3">
              {note.title}
            </h2>
            <p className="p-3 italic">{note.body}</p>
            <p>{note.tagId}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default NoteCollectionPage;

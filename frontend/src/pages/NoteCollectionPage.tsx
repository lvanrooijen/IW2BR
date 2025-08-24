import { useEffect, useState } from 'react';
import { useEnvironment } from '../util/context/EnvironmentContext';
import { useParams } from 'react-router';
import type { INoteCollection } from '../interfaces/environmentInterfaces';
import NotFound from '../components/notfound/NotFound';
import CreateNewEntity from '../components/form/CreateNewEntity';
import { ClimbingBoxLoader } from 'react-spinners';
import FormInputField from '../components/form/FormInputField';
import FormTextArea from '../components/form/FormTextArea';
import Note from '../components/note/Note';

const NoteCollectionPage = () => {
  const { environmentId, noteCollectionId } = useParams();
  const { environment } = useEnvironment();
  const [noteCollection, setNoteCollection] = useState<INoteCollection | null>(
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
          <p>TagSelector (WIP)</p>
        </CreateNewEntity>
      </div>
      <ul className="flex flex-col gap-3 items-center">
        {noteCollection.notes.map((note) => (
          <Note key={note.id} note={note} />
        ))}
      </ul>
    </div>
  );
};

export default NoteCollectionPage;

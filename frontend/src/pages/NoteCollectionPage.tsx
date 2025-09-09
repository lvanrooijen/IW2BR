import { useEffect, useState } from 'react';
import { useEnvironment } from '../util/context/EnvironmentContext';
import { useParams } from 'react-router';
import type { INoteCollection } from '../interfaces/environmentInterfaces';
import NotFound from '../components/notfound/NotFound';
import CreateNewEntity from '../components/form/CreateNewEntity';
import { ClimbingBoxLoader } from 'react-spinners';
import FormInputField from '../components/form/FormInputField';
import FormTextArea from '../components/form/FormTextArea';
import NoteForm from '../components/note/NoteForm';
import TagDropdown from '../components/tags/TagDropdown';
import SectionHeaderWithDescription from '../components/generic/headers/SectionHeaderWithDescription';

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
        <SectionHeaderWithDescription
          title={noteCollection.title}
          description={noteCollection.description}
        />
        <CreateNewEntity
          postTo={`environments/${environmentId}/note_collections/${noteCollectionId}/notes`}
          formLabel={'Note'}
        >
          <FormInputField placeholder={'Title'} name={'title'} />
          <FormTextArea name={'body'} placeholder={'body'} />
          <TagDropdown create={true} />
        </CreateNewEntity>
      </div>

      <ul className="flex flex-col gap-3 items-center">
        {noteCollection.notes.map((note) => (
          <NoteForm key={note.id} note={note} />
        ))}
      </ul>
    </div>
  );
};

export default NoteCollectionPage;

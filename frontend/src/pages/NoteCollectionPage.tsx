import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import AxiosInstance from '../util/services/AxiosInstance';

const NoteCollectionPage: React.FC<NoteCollectionPageProps> = () => {
  const { noteCollectionId, environmentId } = useParams();
  const [notes, setNotes] = useState<Note[] | []>([]);

  useEffect(() => {
    AxiosInstance.get('')
      .then((response) => setNotes(response.data))
      .catch((error) => console.error);
  }, []);

  return (
    <>
      <h1>Notes</h1>
      <p>notecollection: {noteCollectionId}</p>
      <p>environment: {environmentId}</p>
    </>
  );
};

export default NoteCollectionPage;

interface NoteCollectionPageProps {}

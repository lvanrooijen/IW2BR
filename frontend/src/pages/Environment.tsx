import { useEnvironment } from '../util/context/EnvironmentContext';
import { useEffect } from 'react';
import { ClimbingBoxLoader } from 'react-spinners';
import Card from '../components/card/Card';
import CardListItems from '../components/card/CardListItems';
import SectionHeader from '../components/generic/SectionHeader';

const Environment = () => {
  const { environment } = useEnvironment();

  useEffect(() => {
    console.log(environment);
  }, []);

  if (environment === null) {
    return <ClimbingBoxLoader />;
  }
  return (
    <div>
      <div>
        <SectionHeader label={environment.title} />
      </div>

      <Card label={'Note Collections'}>
        <>
          <CardListItems
            list={environment.noteCollections}
            navTo="note_collections"
          />
          <button className="btn btn-soft btn-secondary">Create</button>
        </>
      </Card>

      <Card label={'Flashcard decks'}>
        <>
          <CardListItems
            list={environment.flashcardDecks}
            navTo="flashcard_decks"
          />
          <button className="btn btn-soft btn-secondary">Create</button>
        </>
      </Card>

      <Card label={'Exams'}>
        <>
          <CardListItems list={environment.exams} navTo="exams" />
          <button className="btn btn-soft btn-secondary">Create</button>
        </>
      </Card>

      <Card label={'Exam Attempts'}>
        <>
          <CardListItems
            list={environment.examAttempts}
            navTo="exam_attempts"
          />
        </>
      </Card>

      <Card label={'Tags'}>
        <>
          <CardListItems list={environment.tags} navTo="tags" />
          <button className="btn btn-soft btn-secondary">Create</button>
        </>
      </Card>
    </div>
  );
};

export default Environment;

/*
TODO:
1. maak context voor environment
2. laad gegevens environment op deze pagina
*/

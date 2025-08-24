import { useEnvironment } from '../util/context/EnvironmentContext';
import { useEffect, useState } from 'react';
import { ClimbingBoxLoader } from 'react-spinners';
import Card from '../components/card/Card';
import CardListItems from '../components/card/CardListItems';
import SectionHeader from '../components/generic/SectionHeader';
import { useParams } from 'react-router';
import CreateNewEntity from '../components/form/CreateNewEntity';

const EnvironmentPage = () => {
  const { environmentId } = useParams();
  const { environment } = useEnvironment();

  const createPath = (target: string) => {
    return `environments/${environmentId}/${target}`;
  };

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
          <CreateNewEntity
            formLabel="Note Collections"
            postTo={createPath('note_collections')}
          />
        </>
      </Card>

      <Card label={'Flashcard decks'}>
        <>
          <CardListItems
            list={environment.flashcardDecks}
            navTo="flashcard_decks"
          />
          <CreateNewEntity
            formLabel="Flashcard Decks"
            postTo={createPath('flashcard_decks')}
          />
        </>
      </Card>

      <Card label={'Exams'}>
        <>
          <CardListItems list={environment.exams} navTo="exams" />
          <CreateNewEntity formLabel="Exams" postTo={createPath('exams')} />
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
          <CreateNewEntity formLabel="Tags" postTo={createPath('tags')} />
        </>
      </Card>
    </div>
  );
};

export default EnvironmentPage;

/*
TODO:
1. maak context voor environment
2. laad gegevens environment op deze pagina
*/

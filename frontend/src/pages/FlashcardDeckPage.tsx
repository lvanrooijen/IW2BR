import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { useEnvironment } from '../util/context/EnvironmentContext';
import type { IFlashcardDeck } from '../interfaces/environmentInterfaces';
import SectionHeaderWithDescription from '../components/generic/SectionHeaderWithDescription';
import { ClimbingBoxLoader } from 'react-spinners';

const FlashcardDeckPage: React.FC<Props> = () => {
  const [flashcardDeck, setFlashcardDeck] = useState<IFlashcardDeck | null>(
    null
  );
  const { flashcardDeckId } = useParams();
  const { environment } = useEnvironment();

  useEffect(() => {
    if (!environment || !flashcardDeckId) return;

    const flashcardDeck =
      environment.flashcardDecks?.find(
        (c) => c.id === Number(flashcardDeckId)
      ) ?? null;

    setFlashcardDeck(flashcardDeck);
    console.log('FLASHCARD DECK:: ', flashcardDeck);
  }, [environment, flashcardDeckId]);

  if (!flashcardDeck) return <ClimbingBoxLoader />;

  return (
    <div>
      <SectionHeaderWithDescription
        title={flashcardDeck?.title}
        description={flashcardDeck?.description}
      />
    </div>
  );
};

export default FlashcardDeckPage;

interface Props {}

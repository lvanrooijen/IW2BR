import { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { useEnvironment } from '../util/context/EnvironmentContext';
import type { IFlashcardDeck } from '../interfaces/environmentInterfaces';
import SectionHeaderWithDescription from '../components/generic/headers/SectionHeaderWithDescription';
import { ClimbingBoxLoader } from 'react-spinners';
import TabContainer from '../components/tabContainer/TabContainer';
import Flashcard from '../components/flashcard/Flashcard';

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
    <div className="">
      <SectionHeaderWithDescription
        title={flashcardDeck?.title}
        description={flashcardDeck?.description}
      />
      <TabContainer
        tab1Label={'Study'}
        tab1={
          <div className="flex flex-col gap-6">
            {flashcardDeck.flashcards.map((card) => (
              <Flashcard key={card.id} flashcard={card} />
            ))}
          </div>
        }
        tab2Label={'Edit deck'}
        tab2={<div>contente B</div>}
        tab3Label={'Vieuw statistics'}
        tab3={<div>contente C</div>}
      />
    </div>
  );
};

export default FlashcardDeckPage;

interface Props {}

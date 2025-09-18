import type { IFlashcard } from '../../interfaces/environmentInterfaces';

const Flashcard: React.FC<Props> = ({ flashcard }) => {
  return (
    <div className="border-2 min-w-82 min-h-96 bg-base-100 text-center tracking-wider">
      <h1 className="border-b-2  p-3">{flashcard.frontBody}</h1>
      <p className="p-3">{flashcard.backBody}</p>
    </div>
  );
};

export default Flashcard;

interface Props {
  flashcard: IFlashcard;
}

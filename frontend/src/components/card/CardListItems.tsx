import type { BaseEntity } from '../../interfaces/environmentInterfaces';
import CatInBag from '../../assets/images/cat_in_the_bag.png';
import { useNavigate } from 'react-router';

const CardListItems: React.FC<CardListItemsProps> = ({
  list,
  navTo,
  emptyListMsg,
}) => {
  const navigate = useNavigate();
  return (
    <ul>
      {list.length > 0 ? (
        list.map((item) => (
          <li
            key={item.id}
            className="cursor-pointer"
            onClick={() => {
              if (navTo) {
                navigate(`${navTo}/${item.id}`);
              }
            }}
          >
            <h3>{item.title}</h3>
            <p>{item.description}</p>
          </li>
        ))
      ) : (
        <div className="flex items-center justify-center gap-6 my-3">
          {emptyListMsg ? (
            <span>{emptyListMsg}</span>
          ) : (
            <img className="w-6" src={CatInBag} />
          )}
        </div>
      )}
    </ul>
  );
};

export default CardListItems;

interface CardListItemsProps {
  list: BaseEntity[];
  navTo: string;
  emptyListMsg?: string;
}

//TODO overlay maken voor creation formulieren, en die dan dynamisch zien te krijgen.

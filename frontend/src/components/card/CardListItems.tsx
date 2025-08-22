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
    <ul className="w-full gap-3 pb-6">
      {list.length > 0 ? (
        list.map((item) => (
          <li
            key={item.id}
            className="grid-cols-6 grid cursor-pointer items-center place-items-start rounded-md bg-base-200 p-3 mx-3 hover:bg-base-300"
            onClick={() => {
              if (navTo) {
                navigate(`${navTo}/${item.id}`);
              }
            }}
          >
            <h3 className="col-span-2 pr-3">{item.title}</h3>
            <p className="col-span-4 pl-3 border-l-1 rounded-md min-h-9">
              {item.description}
            </p>
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

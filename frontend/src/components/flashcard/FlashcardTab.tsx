import { useState } from 'react';

const FlashcardTab: React.FC<Props> = () => {
  const [active, setActive] = useState('tab1');

  const setActiveStyle = (tabname: string) => {
    return active == tabname ? style.activeBar : '';
  };

  const style = {
    tabBarItem:
      'hover:bg-base-300 border-x-1 w-1/3 text-center cursor-pointer min-h-9 justify-center items-center flex font-bold',
    activeBar: 'bg-primary tracking-wider',
  };
  return (
    <div className="border-1 rounded-md bg-base-300 min-h-92">
      <div className="w-full flex flex-row justify-evenly items-center border-b-1 bg-base-100">
        <div
          id="tab1"
          className={`${style.tabBarItem} ${setActiveStyle('tab1')}`}
          onClick={() => setActive('tab1')}
        >
          Tab 1
        </div>
        <div
          id="tab2"
          className={`${style.tabBarItem}  ${setActiveStyle('tab2')}`}
          onClick={() => setActive('tab2')}
        >
          Tab 2
        </div>
        <div
          id="tab3"
          className={`${style.tabBarItem}  ${setActiveStyle('tab3')}`}
          onClick={() => setActive('tab3')}
        >
          Tab 3
        </div>
      </div>
      {active == 'tab1' && <div>1</div>}
      {active == 'tab2' && <div>2</div>}
      {active == 'tab3' && <div>3</div>}
    </div>
  );
};

export default FlashcardTab;

interface Props {
  // TO ADD
}

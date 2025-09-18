import { useState } from 'react';

const TabContainer: React.FC<Props> = ({
  tab1,
  tab1Label,
  tab2,
  tab2Label,
  tab3,
  tab3Label,
}) => {
  const [active, setActive] = useState('tab1');

  const setActiveStyle = (tabname: string) => {
    return active == tabname ? style.activeBar : '';
  };

  const style = {
    tabBarItem:
      'hover:bg-base-300 border-x-1 w-1/3 text-center cursor-pointer min-h-9 justify-center items-center flex font-bold',
    activeBar: 'bg-primary tracking-wider',
    container: 'p-6 flex flex-col justify-center items-center',
  };
  return (
    <div className="border-2 rounded-md bg-base-300 min-h-92 m-6">
      <div className="w-full flex flex-row justify-evenly items-center border-b-1 bg-base-100">
        <div
          id="tab1"
          className={`${style.tabBarItem} ${setActiveStyle('tab1')} `}
          onClick={() => setActive('tab1')}
        >
          {tab1Label}
        </div>
        <div
          id="tab2"
          className={`${style.tabBarItem}  ${setActiveStyle('tab2')}`}
          onClick={() => setActive('tab2')}
        >
          {tab2Label}
        </div>
        <div
          id="tab3"
          className={`${style.tabBarItem}  ${setActiveStyle('tab3')}`}
          onClick={() => setActive('tab3')}
        >
          {tab3Label}
        </div>
      </div>
      {active == 'tab1' && <div className={`${style.container}`}>{tab1}</div>}
      {active == 'tab2' && <div className={`${style.container}`}>{tab2}</div>}
      {active == 'tab3' && <div className={`${style.container}`}>{tab3}</div>}
    </div>
  );
};

export default TabContainer;

interface Props {
  tab1: React.ReactNode;
  tab1Label: string;
  tab2: React.ReactNode;
  tab2Label: string;
  tab3: React.ReactNode;
  tab3Label: string;
}

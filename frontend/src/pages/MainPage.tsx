import { Outlet } from 'react-router';
import type { UserProps } from '../interfaces/ApiServiceInterfaces';
import BasicPage from '../components/pagecomponent/BasicPage';
import Header from '../components/generic/headers/Header';

const MainPage: React.FC<MainPageProps> = ({ user }) => {
  return (
    <BasicPage>
      <div className="w-full p-6 gap-3 flex flex-col">
        <Header label={`Welcome ${user?.username}`} />
      </div>
      <div>
        <Outlet />
      </div>
    </BasicPage>
  );
};

export default MainPage;

interface MainPageProps {
  user: UserProps | null;
}

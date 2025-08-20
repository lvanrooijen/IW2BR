import { Outlet } from 'react-router';
import type { UserProps } from '../interfaces/ApiServiceInterfaces';
import BasicPage from '../components/page/BasicPage';
import Header from '../components/generic/Header';

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

interface EnvironmentProps {
  id?: number;
  title: string;
  description: string;
}

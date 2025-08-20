import { Outlet } from 'react-router';
import { EnvironmentProvider } from '../context/EnvironmentContext';

const EnvironmentLayout = () => {
  return (
    <EnvironmentProvider>
      <Outlet />
    </EnvironmentProvider>
  );
};

export default EnvironmentLayout;

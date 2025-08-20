import { createContext, useContext, useEffect, useState } from 'react';
import AxiosInstance from '../services/AxiosInstance';

const EnvironmentContext = createContext<EnvironmentContextProps | null>(null);

export const EnvironmentProvider: React.FC<EnvironmentProviderProps> = ({
  children,
}) => {
  const [environment, setEnvironment] =
    useState<EnvironmentResponseProps | null>(null);

  useEffect(() => {
    console.log('[ENVIRONMENT CONTEXT] loaded');
  }, []);

  const getEnvironmentData = (id: number) => {
    AxiosInstance.get(`/environments/${id}`)
      .then((response) => {
        setEnvironment(response.data);
        console.log('[ENVIRONMENT CONTEXT] response data: ', response.data);
      })
      .catch((error) => console.error('[ENVIRONMENT CONTEXT] error: ', error));
  };

  return (
    <EnvironmentContext.Provider value={{ getEnvironmentData, environment }}>
      {children}
    </EnvironmentContext.Provider>
  );
};

export const useEnvironment = () => {
  const context = useContext(EnvironmentContext);
  if (!context) {
    throw new Error('EnvironmentProvider context is null');
  }
  return context;
};

interface EnvironmentProviderProps {
  children: React.ReactNode;
}

interface EnvironmentContextProps {
  getEnvironmentData: (id: number) => void;
  environment: EnvironmentResponseProps | null;
}

interface EnvironmentResponseProps {
  id?: number;
  title: string;
  description: string;
}

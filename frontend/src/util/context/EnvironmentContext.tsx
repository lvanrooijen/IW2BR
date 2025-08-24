import { createContext, useContext, useEffect, useState } from 'react';
import AxiosInstance from '../services/AxiosInstance';
import { useParams } from 'react-router';
import type { EnvironmentResponseProps } from '../../interfaces/environmentInterfaces';

const EnvironmentContext = createContext<EnvironmentContextProps | null>(null);

export const EnvironmentProvider: React.FC<EnvironmentProviderProps> = ({
  children,
}) => {
  const { environmentId } = useParams();

  const [environment, setEnvironment] =
    useState<EnvironmentResponseProps | null>(null);

  useEffect(() => {
    getEnvironmentData(Number(environmentId));
  }, []);

  const getEnvironmentData = (id: number) => {
    AxiosInstance.get(`/environments/${id}`)
      .then((response) => {
        setEnvironment(response.data);
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

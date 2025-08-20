import { useParams } from 'react-router';
import { useEnvironment } from '../util/context/EnvironmentContext';
import { useEffect } from 'react';

const Environment = () => {
  const { environment, getEnvironmentData } = useEnvironment();
  const { environmentId } = useParams();

  useEffect(() => {
    getEnvironmentData(Number(environmentId));
    console.log(environment);
  }, []);

  return <div>Hello Environment {environmentId}</div>;
};

export default Environment;

/*
TODO:
1. maak context voor environment
2. laad gegevens environment op deze pagina
*/

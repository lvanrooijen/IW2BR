import { useEnvironment } from '../util/context/EnvironmentContext';
import { useEffect } from 'react';
import { ClimbingBoxLoader } from 'react-spinners';
import Card from '../components/Card';

const Environment = () => {
  const { environment } = useEnvironment();

  useEffect(() => {
    console.log(environment);
  }, []);

  if (environment === null) {
    return <ClimbingBoxLoader />;
  }
  return (
    <div>
      <div>
        <h1 className="border-2 bg-amber-900">{environment.title}</h1>
      </div>

      <Card label={'NoteCollection'}>
        <div></div>
      </Card>
    </div>
  );
};

export default Environment;

/*
TODO:
1. maak context voor environment
2. laad gegevens environment op deze pagina
*/

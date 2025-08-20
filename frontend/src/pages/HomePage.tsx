import { useNavigate } from 'react-router';
import type { UserProps } from '../interfaces/ApiServiceInterfaces';
import FormFrame from '../components/form/FormFrame';
import FormInputField from '../components/form/FormInputField';
import FormTextArea from '../components/form/FormTextArea';
import AxiosInstance from '../util/services/AxiosInstance';
import { useEffect, useState } from 'react';
import DisplayList from '../components/generic/displaylist/DisplayList';
import DisplayListItem from '../components/generic/displaylist/DisplayListItem';

const HomePage: React.FC<HomePageProps> = ({ user }) => {
  const [environments, setEnvironments] = useState<EnvironmentProps[]>([]);
  const [createForm, setCreateForm] = useState<boolean>(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchEnvironments();
  }, []);

  const fetchEnvironments = () => {
    AxiosInstance.get('/environments')
      .then((response) => {
        setEnvironments(response.data);
        console.log(response.data);
      })
      .catch((error) => console.error('Error fetching environments: ', error));
  };
  const postEnvironment = (form: EnvironmentProps) => {
    AxiosInstance.post('/environments', form)
      .then((response) => {
        console.log(response.status);
        if (response.status == 201) {
          setCreateForm(false);
          // todo display some type of success message
          // refresh page so new environment is shown
          fetchEnvironments();
        }
      })
      .catch((error) => {
        console.error('Error creating environment: ', error);
        // TODO uitzoeken hoe ik die problemdetail moet doorgeven
      });

    console.log(form);
  };

  if (!user) {
    return <div>User not logged in</div>;
  }
  return (
    <div className="w-full p-6 gap-3 flex flex-col">
      {createForm && (
        <FormFrame
          formLabel="Create new environment"
          onClose={() => setCreateForm(false)}
          handleSubmit={postEnvironment}
        >
          <FormInputField
            placeholder={'Title'}
            name={'title'}
            required={true}
          />
          <FormTextArea placeholder={'description'} name={'description'} />
        </FormFrame>
      )}
      <DisplayList
        title="Environments"
        buttonLabel="+ new"
        buttonAction={() => setCreateForm(true)}
      >
        {environments &&
          environments.map((environment: EnvironmentProps) => (
            <DisplayListItem
              key={environment.id}
              title={environment.title}
              content={environment.description}
              handleClick={() => navigate(`/environments/${environment.id}`)}
            />
          ))}
      </DisplayList>
    </div>
  );
};

export default HomePage;

interface HomePageProps {
  user: UserProps | null;
}

interface EnvironmentProps {
  id?: number;
  title: string;
  description: string;
}

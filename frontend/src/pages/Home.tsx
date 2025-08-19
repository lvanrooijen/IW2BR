import { useEffect, useState } from 'react';
import Header from '../components/generic/Header';
import DisplayList from '../components/generic/DisplayList';
import BasicPage from '../components/page/BasicPage';
import type { UserProps } from '../interfaces/ApiServiceInterfaces';
import AxiosInstance from '../util/services/AxiosInstance';
import FormFrame from '../components/form/FormFrame';
import FormInputField from '../components/form/FormInputField';
import FormTextArea from '../components/form/FormTextArea';
import type { PostEvironmentProps } from '../interfaces/RequestBodyInterfaces';
import DisplayListItem from '../components/generic/DisplayListItem';

const Home: React.FC<HomeProps> = ({ user }) => {
  const [environments, setEnvironments] = useState<EnvironmentProps[]>([]);
  const [createForm, setCreateForm] = useState<boolean>(false);

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

  const postEnvironment = (form: PostEvironmentProps) => {
    AxiosInstance.post('/environments', form)
      .then((response) => {
        console.log(response.status);
        if (response.status == 200) {
          setCreateForm(false);
          // todo display some type of success message
          // refresh page so new environment is shown
          setCreateForm(false);
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
    <BasicPage>
      <div className="w-full p-6 gap-3 flex flex-col">
        <Header label={`Welcome ${user?.username}`} />
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
              />
            ))}
        </DisplayList>
      </div>
    </BasicPage>
  );
};

export default Home;

interface HomeProps {
  user: UserProps | null;
}

interface EnvironmentProps {
  id: number;
  title: string;
  description: string;
}

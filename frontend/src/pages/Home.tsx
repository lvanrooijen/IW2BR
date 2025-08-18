import Header from '../components/generic/Header';
import ItemDisplay from '../components/generic/ItemDisplay';
import BasicPage from '../components/page/BasicPage';
import type { UserProps } from '../interfaces/ApiServiceInterfaces';

const Home: React.FC<HomeProps> = ({ user }) => {
  if (!user) {
    return <div>User not logged in</div>;
  }
  return (
    <BasicPage>
      <div className="w-full p-6 gap-3 flex flex-col">
        <Header label={`Welcome ${user?.username}`} />

        <ItemDisplay
          title="Environments"
          buttonLabel="+ new"
          buttonAction={() => console.log('clickityclack!')}
        >
          //TODO add list of environments here
        </ItemDisplay>
      </div>
    </BasicPage>
  );
};

export default Home;

interface HomeProps {
  user: UserProps | null;
}

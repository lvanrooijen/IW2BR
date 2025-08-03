import type { UserProps } from '../interfaces/ApiServiceInterfaces';

const Home: React.FC<HomeProps> = ({ user }) => {
  if (!user) {
    return <div>User not logged in</div>;
  }
  return (
    <div>
      <p>Home Page BB</p>
      <p>Welcome {user?.username}</p>
    </div>
  );
};

export default Home;

// interfaces
interface HomeProps {
  user: UserProps | null;
}

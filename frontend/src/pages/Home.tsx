const Home: React.FC<HomeProps> = ({ user }) => {
  return (
    <div>
      <p>Home Page BB</p>
      <p>Welcome {user.username}</p>
    </div>
  );
};

export default Home;

// interfaces
interface HomeProps {
  user: {
    id: string;
    email: string;
    username: string;
    role: string;
    accessToken: string;
  };
}

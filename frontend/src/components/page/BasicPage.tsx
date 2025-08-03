import NavBar from '../navigation/NavBar';

const BasicPage: React.FC<BasicPageProps> = ({ children }) => {
  return (
    <div>
      <NavBar />
      {children}
    </div>
  );
};

export default BasicPage;

// interfaces
interface BasicPageProps {
  children: React.ReactNode;
}

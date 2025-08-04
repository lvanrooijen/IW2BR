import NavBar from '../navigation/NavBar';

const BasicPage: React.FC<BasicPageProps> = ({ children }) => {
  return (
    <div>
      <NavBar />
      <div className="flex flex-row items-center">{children}</div>
    </div>
  );
};

export default BasicPage;

interface BasicPageProps {
  children: React.ReactNode;
}

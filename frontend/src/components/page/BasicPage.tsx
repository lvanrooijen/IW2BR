import NavBar from '../navigation/NavBar';

const BasicPage: React.FC<BasicPageProps> = ({ children }) => {
  return (
    <div className="min-h-screen border-2">
      <NavBar style={'grid-rows-1'} />
      <div className="grid-rows-11">{children}</div>
    </div>
  );
};

export default BasicPage;

interface BasicPageProps {
  children: React.ReactNode;
}

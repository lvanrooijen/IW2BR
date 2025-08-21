import Cat from '../../assets/images/cat.png';
const Header: React.FC<HeaderProps> = ({ label }) => {
  return (
    <div className="relative">
      <img src={Cat} className="w-12 animate-bounce absolute right-3 -top-4" />
      <div className="relative h-21 bg-green-100 bg-[url(src/assets/images/tiles.jpg)] bg-blend-multiply rounded-md">
        <h1 className="text-2xl text-right px-6 py-3">{label}</h1>
      </div>
    </div>
  );
};

export default Header;

interface HeaderProps {
  label: string;
}

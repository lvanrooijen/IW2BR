const Header: React.FC<HeaderProps> = ({ label }) => {
  return (
    <div className="h-21 bg-amber-100 bg-[url(src/assets/images/tiles.jpg)] bg-blend-multiply rounded-md">
      <h1 className="text-2xl text-right px-6 py-3">{label}</h1>
    </div>
  );
};

export default Header;

interface HeaderProps {
  label: string;
}

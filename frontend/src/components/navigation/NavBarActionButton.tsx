const NavBarActionButton: React.FC<NavBarActionButtonProps> = ({
  action,
  label,
}) => {
  return (
    <li className="cursor-pointer text-1xl">
      <button onClick={action}>{label}</button>
    </li>
  );
};

export default NavBarActionButton;

interface NavBarActionButtonProps {
  action: () => void;
  label: string;
}

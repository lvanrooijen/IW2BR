import { NavLink } from 'react-router';

const NavBarLink: React.FC<NavBarLinkProps> = ({ destination, label }) => {
  return (
    <li>
      <NavLink to={destination} className="text-1xl">
        {label}
      </NavLink>
    </li>
  );
};

export default NavBarLink;

// interfaces
interface NavBarLinkProps {
  destination: string;
  label: string;
}

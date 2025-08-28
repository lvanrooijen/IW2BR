import NavBarLink from './NavBarLink';
import ProfilePlaceholder from '../../assets/icons/profile_icon_green.png';
import { useAuth } from '../../util/context/AuthContext';
import NavBarActionButton from './NavBarActionButton';
import { useNavigate } from 'react-router';
import TextToSvgComponent from '../icon/TextToSvgComponent';

const NavBar: React.FC<NavBarProps> = ({ style }) => {
  const { logout } = useAuth();
  const nav = useNavigate();
  return (
    <div className={`${style}`}>
      <div className={`navbar bg-base-100 shadow-sm`}>
        <div className="flex-1">
          <a className="btn btn-ghost p-3 max-w-36" onClick={() => nav('/')}>
            <TextToSvgComponent fill="#ff0000" className="my-svg" width={''} />
          </a>
        </div>
        <div className="flex gap-2">
          <div className="dropdown dropdown-end">
            <div
              tabIndex={0}
              role="button"
              className="btn btn-ghost btn-circle avatar"
            >
              <div className="w-10 rounded-full">
                <img alt="profile icon" src={ProfilePlaceholder} />
              </div>
            </div>
            <ul
              tabIndex={0}
              className="menu menu-sm dropdown-content bg-base-200 rounded-box z-1 mt-3 w-52 p-2 shadow"
            >
              <NavBarLink destination="/profile" label="Profile" />
              <NavBarActionButton action={logout} label="Logout" />
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NavBar;

interface NavBarProps {
  style?: string;
}

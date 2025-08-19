import BasicPage from '../components/page/BasicPage';
import pagenotfound from '../assets/images/pagenotfound.png';

const NotFoundPage = () => {
  return (
    <BasicPage>
      <div className="flex flex-col items-center justify-center w-full h-full">
        <img src={pagenotfound} alt="404 page not found" />
      </div>
    </BasicPage>
  );
};

export default NotFoundPage;

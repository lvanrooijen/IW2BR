import pagenotfound from '../../assets/images/pagenotfound.png';

const NotFound = () => {
  return (
    <div className="flex flex-col items-center justify-center w-full h-full">
      <img src={pagenotfound} alt="404 page not found" />
    </div>
  );
};

export default NotFound;

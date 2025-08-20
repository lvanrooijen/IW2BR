const DisplayList: React.FC<DisplayListProps> = ({
  title,
  buttonLabel,
  buttonAction,
  children,
}) => {
  return (
    <>
      <div className=" bg-base-300 py-3 px-6 pb-12 rounded-md">
        <div className="flex justify-between">
          <h1 className="text-xl">{title}</h1>
          <button className="btn btn-soft btn-secondary" onClick={buttonAction}>
            {buttonLabel}
          </button>
        </div>
        <div className="flex flex-col gap-3 pt-3">{children}</div>
      </div>
    </>
  );
};

export default DisplayList;

interface DisplayListProps {
  title: string;
  children: React.ReactNode;
  buttonLabel: string;
  buttonAction: () => void;
}

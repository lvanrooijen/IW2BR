const ItemDisplay: React.FC<ItemDisplayProps> = ({
  title,
  buttonLabel,
  buttonAction,
  children,
}) => {
  return (
    <>
      <div className=" bg-accent-content py-3 px-6 rounded-md">
        <div className="flex justify-between">
          <h1 className="text-xl">{title}</h1>
          <button className="btn btn-soft btn-secondary" onClick={buttonAction}>
            {buttonLabel}
          </button>
        </div>
        <div>{children}</div>
      </div>
    </>
  );
};

export default ItemDisplay;

interface ItemDisplayProps {
  title: string;
  children: React.ReactNode;
  buttonLabel: string;
  buttonAction: () => void;
}

const DisplayListItem: React.FC<DisplayListItemProps> = ({
  title,
  content,
  handleClick,
}) => {
  return (
    <>
      <div
        className={`border-1 border-b-3 border-base-200 rounded-md shadow-2xl p-3 ${
          handleClick ? 'cursor-pointer' : ''
        }`}
        onClick={handleClick}
      >
        <h1 className="text-lg">{title}</h1>
        <div className="text-xs">{content ? content : 'No description'}</div>
      </div>
    </>
  );
};

export default DisplayListItem;

interface DisplayListItemProps {
  title: string;
  content: string;
  handleClick?: () => void;
}

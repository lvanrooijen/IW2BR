const Divider: React.FC<Props> = ({ lines }) => {
  return (
    <div className="w-full h-12 overflow-hidden flex flex-col items-center justify-center gap-3">
      {lines >= 1 && (
        <div className="border-1 border-secondary opacity-30 w-1/3"></div>
      )}
      {lines >= 2 && (
        <div className="border-1 border-primary opacity-30 w-3/4"></div>
      )}
      {lines >= 3 && (
        <div className="border-1 border-accent opacity-30 w-2/3"></div>
      )}
    </div>
  );
};

export default Divider;

interface Props {
  lines: number;
}

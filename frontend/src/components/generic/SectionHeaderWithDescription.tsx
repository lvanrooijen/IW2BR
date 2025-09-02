import Divider from '../../assets/images/divider.png';
const SectionHeaderWithDescription: React.FC<Props> = ({
  id,
  title,
  description,
}) => {
  return (
    <div className="rounded-md pb-6">
      <h1 className="font-bold tracking-wider px-3">{title}</h1>
      <p className="min-h-24 p-3 italic">{description}</p>
      <div className="w-full h-6 overflow-hidden flex items-center justify-center">
        <img
          className="invert rotate-180 opacity-5"
          src={Divider}
          alt="image of footsteps"
        />
      </div>
    </div>
  );
};

export default SectionHeaderWithDescription;

interface Props {
  id?: number;
  title: string;
  description: string;
}

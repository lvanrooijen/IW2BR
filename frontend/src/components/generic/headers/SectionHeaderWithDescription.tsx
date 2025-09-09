import Divider from './Divider';

const SectionHeaderWithDescription: React.FC<Props> = ({
  id,
  title,
  description,
}) => {
  return (
    <div className="rounded-md pb-6">
      <h1 className="font-bold tracking-wider px-3">{title}</h1>
      <p className="min-h-24 p-3 italic">{description}</p>
      <Divider lines={3} />
    </div>
  );
};

export default SectionHeaderWithDescription;

interface Props {
  id?: number;
  title: string;
  description: string;
}

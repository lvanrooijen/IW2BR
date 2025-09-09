const SectionHeader: React.FC<SectionHeaderProps> = ({ label }) => {
  return (
    <h1 className="mx-3 border-b-2 border-base-300 bg-accent-content text-2xl text-center text-content-primary p-3 rounded-t-md rounded-b-sm">
      {label}
    </h1>
  );
};

export default SectionHeader;

interface SectionHeaderProps {
  label: string;
}

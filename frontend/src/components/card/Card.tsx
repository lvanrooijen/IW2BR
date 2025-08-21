const Card: React.FC<CardProps> = ({ label, children }) => {
  return (
    <div className="border-2 p-3 rounded-md m-3">
      <h2 className="text-2xl font-medium tracking-wider text-secondary-content border-b-2 text-center mb-6">
        {label}
      </h2>
      <div className="flex flex-col justify-center items-center">
        {children}
      </div>
    </div>
  );
};

export default Card;

interface CardProps {
  label: string;
  children: React.ReactNode;
}

import { useState } from 'react';

const Card: React.FC<CardProps> = ({ label, children }) => {
  const [showForm, setShowForm] = useState<boolean>(false);

  return (
    <div className="border-2 rounded-md m-3">
      <h2 className="text-2xl font-medium tracking-wider text-secondary-content border-b-2 text-center mb-6 mx-3 p-3">
        {label}
      </h2>
      <div className="flex flex-col justify-center items-center w-full">
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

import { useEffect, useState } from 'react';

const FormInputField: React.FC<FormInputFieldProps> = ({
  placeholder,
  name,
  type,
  initialvalue,
  required,
  range,
}) => {
  const [value, setValue] = useState<string | number>('');
  const inputStyle = 'p-2 border-2 rounded-md w-full';

  useEffect(() => {
    if (initialvalue) setValue(initialvalue);
  }, []);

  return (
    <>
      {type == 'number' && range ? (
        <input
          className={inputStyle}
          type={type}
          name={name}
          value={value}
          required={required}
          min={range.min}
          max={range.max}
          placeholder={placeholder}
          onChange={(e) => setValue(e.target.value)}
        />
      ) : (
        <input
          className={inputStyle}
          type={type || 'text' || 'hidden'}
          placeholder={placeholder}
          id={name}
          name={name}
          value={value}
          required={required}
          onChange={(e) => setValue(e.target.value)}
        />
      )}
    </>
  );
};

export default FormInputField;

interface FormInputFieldProps {
  placeholder: string;
  name: string;
  type?: string;
  initialvalue?: string;
  required?: boolean;
  range?: { min: number; max: number };
}

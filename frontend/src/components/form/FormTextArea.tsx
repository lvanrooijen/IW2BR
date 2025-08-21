const FormTextArea: React.FC<FormTextAreaProps> = ({ name, placeholder }) => {
  return (
    <textarea
      className={`border-2 rounded-md w-full resize-none min-h-32 px-2 py-3`}
      name={name}
      placeholder={placeholder}
    ></textarea>
  );
};

export default FormTextArea;

interface FormTextAreaProps {
  name: string;
  placeholder: string;
}

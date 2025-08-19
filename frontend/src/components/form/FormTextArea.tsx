const FormTextArea: React.FC<FormTextAreaProps> = ({ name, placeholder }) => {
  return (
    <textarea
      className={`border-2 rounded-md p-3 m-2 w-full resize-none`}
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

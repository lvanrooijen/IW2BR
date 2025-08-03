const FormFrame: React.FC<FormFrameProps> = ({
  children,
  formLabel,
  handleSubmit,
}) => {
  const getFormValues = (e: React.FormEvent) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget as HTMLFormElement);
    const formValues = Object.fromEntries(formData);
    handleSubmit(formValues);
  };

  return (
    <div className="border-2 rounded-md flex justify-center items-center flex-col gap-6 bg-base-300">
      <h2 className="text-2xl pt-3 text-accent">{formLabel}</h2>
      <form
        className="flex flex-col justify-center items-center px-12"
        onSubmit={(e) => {
          getFormValues(e);
        }}
      >
        <div>{children}</div>
        <button className="btn btn-secondary p-3 my-3" type="submit">
          Submit
        </button>
      </form>
    </div>
  );
};

export default FormFrame;

interface FormFrameProps {
  children: React.ReactNode;
  formLabel: string;
  handleSubmit: (body: any) => void;
}

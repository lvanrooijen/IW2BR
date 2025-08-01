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
    <>
      <div>
        <h2>{formLabel}</h2>
        <form
          onSubmit={(e) => {
            getFormValues(e);
          }}
        >
          {children}
          <button>submit</button>
        </form>
      </div>
    </>
  );
};

export default FormFrame;

interface FormFrameProps {
  children: React.ReactNode;
  formLabel: string;
  handleSubmit: (body: any) => void;
}

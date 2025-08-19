const FormFrame: React.FC<FormFrameProps> = ({
  children,
  formLabel,
  style,
  handleSubmit,
  onClose,
}) => {
  const getFormValues = (e: React.FormEvent) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget as HTMLFormElement);

    const filteredForm = Object.fromEntries(
      Array.from(formData.entries()).filter(([key, value]) => value !== '')
    );

    handleSubmit(filteredForm);
  };

  return (
    <div
      className={`border-2 pb-12 rounded-md flex justify-center items-center flex-col gap-6 bg-base-300 ${style}`}
    >
      {onClose && (
        <div className="w-full flex justify-end">
          <button className="btn btn-error text-md m-3" onClick={onClose}>
            X
          </button>
        </div>
      )}
      <h2 className="text-2xl text-accent">{formLabel}</h2>
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
  style?: string;
  handleSubmit: (body: any) => void;
  onClose?: () => void;
}

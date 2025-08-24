import { useEffect } from 'react';
import { GrClose } from 'react-icons/gr';

const FormFrame: React.FC<FormFrameProps> = ({
  children,
  label,
  style,
  handleSubmit,
  onClose,
  isVisible,
  width,
}) => {
  useEffect(() => {
    console.log(isVisible);
  }, [isVisible]);

  const getFormValues = (e: React.FormEvent) => {
    e.preventDefault();

    const formData = new FormData(e.currentTarget as HTMLFormElement);

    const filteredForm = Object.fromEntries(
      Array.from(formData.entries()).filter(([key, value]) => value !== '')
    );

    handleSubmit(filteredForm);
  };

  if (!isVisible && onClose) {
    return null;
  } else {
    return (
      <div
        className={`${width} ${
          onClose ? 'backdrop-blur-sm absolute' : ''
        } left-0 right-0 bottom-0 top-0 flex flex-col justify-center items-center`}
      >
        <div
          className={`${
            onClose ? 'w-120' : 'w-full'
          } border-2 pb-6 px-6 rounded-md flex justify-center items-center flex-col gap-6 bg-base-300 ${style}`}
        >
          {onClose && (
            <div className="w-full flex justify-end">
              <button
                className="btn btn-error text-md mt-3 mr-3"
                onClick={onClose}
              >
                <GrClose />
              </button>
            </div>
          )}
          <h2 className="text-lg text-accent py-3 border-b-2 min-w-full text-center font-medium tracking-widest">
            {label}
          </h2>
          <form
            className={`flex flex-col justify-center items-center w-full`}
            onSubmit={(e) => {
              getFormValues(e);
            }}
          >
            <div className="flex flex-col gap-3 w-full">{children}</div>
            <button className="btn btn-primary mt-3" type="submit">
              Submit
            </button>
          </form>
        </div>
      </div>
    );
  }
};

export default FormFrame;

interface FormFrameProps {
  children: React.ReactNode;
  label: string;
  style?: string;
  handleSubmit: (body: any) => void;
  onClose?: () => void;
  isVisible?: boolean;
  width?: string;
}

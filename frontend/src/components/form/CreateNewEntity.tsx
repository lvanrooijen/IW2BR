import { useState } from 'react';
import FormFrame from './FormFrame';
import AxiosInstance from '../../util/services/AxiosInstance';
import FormInputField from './FormInputField';
import FormTextArea from './FormTextArea';
import { useEnvironment } from '../../util/context/EnvironmentContext';
import { useParams } from 'react-router';

const CreateNewEntity: React.FC<CreateNewEntityProps> = ({
  postTo,
  formLabel,
}) => {
  const [showForm, setShowForm] = useState<boolean>(false);
  const { getEnvironmentData } = useEnvironment();
  const { environmentId } = useParams();

  const post = (form: FormProps, endpoint: string) => {
    console.log('posting ', endpoint);
    console;
    AxiosInstance.post(endpoint, form)
      .then((response) => {
        if (response.status === 201) {
          console.log('success');
          getEnvironmentData(Number(environmentId));
        }
      })
      .catch((error) => console.error(error));
  };

  return (
    <div className="flex items-center justify-center mb-3">
      <button
        className="btn btn-soft btn-secondary"
        onClick={() => {
          setShowForm(true);
        }}
      >
        Create
      </button>
      <FormFrame
        label={formLabel}
        onClose={() => {
          setShowForm(false);
        }}
        isVisible={showForm}
        handleSubmit={(form) => {
          post(form, postTo), setShowForm(false);
        }}
      >
        <FormInputField name="title" placeholder="Title" required />
        <FormTextArea name="description" placeholder="description" />
      </FormFrame>
    </div>
  );
};

export default CreateNewEntity;

interface CreateNewEntityProps {
  postTo: string;
  formLabel: string;
}

interface FormProps {
  title: string;
  description: string;
}

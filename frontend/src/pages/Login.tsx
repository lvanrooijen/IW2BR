import apiClient from '../security/ApiClient';
import FormWrapper from '../components/FormFrame';
import FormField from '../components/FormInputField';

const loginUser = (fields: LoginForm) => {
  console.log(fields);

  apiClient
    .post('auth/login', {
      username: fields.username,
      password: fields.password,
    })
    .then((response) => {
      //TODO finish me
      console.log(response.data.accessToken);
      const accessToken = response.data.accessToken;
      localStorage.setItem('accessToken', accessToken);
    })
    .catch((err) => console.error(err));
};
const Login = () => {
  return (
    <>
      <FormWrapper formLabel="Login" handleSubmit={loginUser}>
        <FormField placeholder={'username'} name={'username'} />
        <FormField
          placeholder={'password'}
          name={'password'}
          type={'password'}
        />
      </FormWrapper>
    </>
  );
};

export default Login;

interface LoginForm {
  username: string;
  password: string;
}

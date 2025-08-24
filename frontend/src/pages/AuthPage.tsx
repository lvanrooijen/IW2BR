import { useState } from 'react';
import { useAuth } from '../util/context/AuthContext';
import FormFrame from '../components/form/FormFrame';
import FormInputField from '../components/form/FormInputField';

const AuthPage = () => {
  const [signUpMode, setSignUpMode] = useState<boolean>(false);
  const { login, register } = useAuth();
  const FormWrapperStyle = 'flex items-center justify-center h-screen';

  if (signUpMode) {
    return (
      <div className={FormWrapperStyle}>
        <FormFrame
          label={'Sign up'}
          handleSubmit={(form) => register(form)}
          width="w-2/3"
        >
          <FormInputField placeholder="email" name="email" type="email" />
          <FormInputField placeholder="first name" name="firstName" />
          <FormInputField placeholder="last name" name="lastName" />
          <FormInputField
            placeholder="password"
            name="password"
            type="password"
          />
          <input type="hidden" name="role" value={'USER'} />
          <p className="flex pb-3">
            Already have an account?{' '}
            <span
              className="underline text-primary cursor-pointer px-1"
              onClick={() => setSignUpMode(false)}
            >
              Login
            </span>
          </p>
        </FormFrame>
      </div>
    );
  } else {
    return (
      <div className={FormWrapperStyle}>
        <FormFrame
          label="Login"
          handleSubmit={(form) => login(form)}
          width="w-2/3"
        >
          <FormInputField
            placeholder={'email'}
            name={'username'}
            type="email"
          />
          <FormInputField
            placeholder={'password'}
            name={'password'}
            type={'password'}
          />
          <p className="flex pb-3">
            Don't have an account?{' '}
            <span
              className="underline text-primary cursor-pointer px-1"
              onClick={() => setSignUpMode(true)}
            >
              sign up
            </span>
          </p>
        </FormFrame>
      </div>
    );
  }
};

export default AuthPage;

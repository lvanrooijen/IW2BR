import { useState } from 'react';
import FormFrame from '../components/FormFrame';
import FormInputField from '../components/FormInputField';
import { useAuth } from '../util/context/AuthContext';

const AuthPage = () => {
  const [signUpMode, setSignUpMode] = useState<boolean>(false);
  const { login, register } = useAuth();

  if (signUpMode) {
    return (
      <>
        <FormFrame
          formLabel={'Sign up'}
          handleSubmit={(form) => register(form)}
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
        </FormFrame>
        <p>
          Already have an account?{' '}
          <span
            className="underline text-amber-400 cursor-pointer"
            onClick={() => setSignUpMode(false)}
          >
            login
          </span>
        </p>
      </>
    );
  } else {
    return (
      <>
        <FormFrame formLabel="Login" handleSubmit={(form) => login(form)}>
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
        </FormFrame>
        <p>
          Don't have an account yet?{' '}
          <span
            className="underline text-amber-400 cursor-pointer"
            onClick={() => setSignUpMode(true)}
          >
            sign up
          </span>
        </p>
      </>
    );
  }
};

export default AuthPage;

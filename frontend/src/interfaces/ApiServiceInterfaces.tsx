// interfaces
export interface LoginFormProps {
  username: string;
  password: string;
}

export interface RegisterFormProps {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: string; // todo enum of nah?
}

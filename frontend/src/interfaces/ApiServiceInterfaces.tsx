export interface LoginFormProps {
  username: string;
  password: string;
}

export interface RegisterFormProps {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  role: string;
}

export interface UserProps {
  id: string;
  username: string;
  email: string;
  role: string;
  accessToken?: string;
}

import type {
  LoginFormProps,
  RegisterFormProps,
} from '../../interfaces/ApiServiceInterfaces';
import AxiosInstance from './AxiosInstance';

const REFRESH_TOKEN = '/auth/refresh-token';

export async function refreshToken() {
  const response = await AxiosInstance.post(REFRESH_TOKEN);
  const { accessToken } = response.data;
  if (accessToken) {
    localStorage.setItem('accessToken', accessToken);
    console.log(
      '[ApiService#refreshToken] AccessToken refreshed::',
      accessToken
    );
  }

  return response.data;
}

export async function loginUser(loginForm: LoginFormProps) {
  const response = await AxiosInstance.post('auth/login', loginForm);
  const { accessToken, id, role, username } = response.data;
  localStorage.setItem('accessToken', accessToken);
  return { id: id, username: username, role: role };
}

export async function registerUser(registerForm: RegisterFormProps) {
  const response = await AxiosInstance.post('auth/register', registerForm);
  const { accessToken, id, role, username } = response.data;
  localStorage.setItem('accessToken', accessToken);
  return { id: id, username: username, role: role };
}

export async function logoutUser() {
  //todo
  console.log('MAKE THE LOGOUT FUNCTION!!!!!');
}

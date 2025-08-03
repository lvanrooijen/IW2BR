import type {
  LoginFormProps,
  RegisterFormProps,
  UserProps,
} from '../../interfaces/ApiServiceInterfaces';
import AxiosInstance from './AxiosInstance';
/**
 * API HTTP service for
 *
 * contains pre-made http requests.
 *
 *
 */

const REFRESH_TOKEN = '/auth/refresh-token';

export async function refreshToken(): Promise<UserProps> {
  const response = await AxiosInstance.post(REFRESH_TOKEN);
  const { id, email, username, role, accessToken } = response.data;
  if (accessToken) {
    localStorage.setItem('accessToken', accessToken);
    console.log('[ApiService#refreshToken]::', accessToken);
  }

  return { id, email, username, role, accessToken };
}

export async function loginUser(loginForm: LoginFormProps): Promise<UserProps> {
  const response = await AxiosInstance.post('auth/login', loginForm);
  const { id, email, username, role, accessToken } = response.data;
  localStorage.setItem('accessToken', accessToken);
  return { id, email, username, role, accessToken };
}

export async function registerUser(
  registerForm: RegisterFormProps
): Promise<UserProps> {
  const response = await AxiosInstance.post('auth/register', registerForm);
  const { id, email, username, role, accessToken } = response.data;
  localStorage.setItem('accessToken', accessToken);
  return { id, email, username, role, accessToken };
}

export async function logoutUser() {
  //todo
  console.log('MAKE THE LOGOUT FUNCTION!!!!!');
}

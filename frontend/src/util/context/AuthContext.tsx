import { createContext, useContext, useEffect, useState } from 'react';
import {
  refreshToken,
  loginUser,
  registerUser,
  logoutUser,
} from '../services/ApiService';
import type {
  LoginFormProps,
  RegisterFormProps,
  UserProps,
} from '../../interfaces/ApiServiceInterfaces';

const AuthContext = createContext<AuthContextProps | null>(null);

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
  const [user, setUser] = useState<UserProps | null>(null);
  const [authIsLoading, setAuthIsLoading] = useState<boolean>(true);

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      refreshToken()
        .then((userData) => {
          console.log(
            '[AUTH CONTEXT] loading app, attempting to refresh token'
          );
          setUser(userData);
          setIsLoggedIn(true);
        })
        .catch((error) => {
          console.error('[AUTH CONTEXT] Failed to refresh token', error);
          localStorage.removeItem('accessToken');
        })
        .finally(() => setAuthIsLoading(false));
    } else {
      setAuthIsLoading(false);
    }
  }, []);

  const login = async (loginForm: LoginFormProps) => {
    try {
      const userData = await loginUser(loginForm);
      console.log('TEST:: ', userData);
      setUser(userData);
      setIsLoggedIn(true);
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  const register = async (form: RegisterFormProps) => {
    try {
      const userData = await registerUser(form);
      console.log('TEST:: ', userData);
      setUser(userData);
      setIsLoggedIn(true);
    } catch (error) {
      console.error('Login failed:', error);
    }
  };

  const logout = async () => {
    try {
      await logoutUser();
    } catch (error) {
      console.error('Logout error', error);
    } finally {
      setUser(null);
      setIsLoggedIn(false);
      localStorage.removeItem('accessToken');
    }
  };

  return (
    <AuthContext.Provider
      value={{ authIsLoading, isLoggedIn, user, login, register, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('AuthProvider context is null');
  }
  return context;
};

interface AuthProviderProps {
  children: React.ReactNode;
}

// interfaces

interface AuthContextProps {
  authIsLoading: boolean;
  isLoggedIn: boolean;
  user: UserProps | null;
  login: (form: LoginFormProps) => Promise<void>;
  register: (form: RegisterFormProps) => Promise<void>;
  logout: () => Promise<void>;
}

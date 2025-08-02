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
} from '../../interfaces/ApiServiceInterfaces';

const AuthContext = createContext();

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState<boolean>(false);
  const [user, setUser] = useState<UserProps | null>(null);

  useEffect(() => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      refreshToken()
        .then((userData) => {
          setUser(userData);
          setIsLoggedIn(true);
        })
        .catch((error) => {
          console.error('Failed to refresh token', error);
          logout(); // of delete accessToken ??
        });
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
      localStorage.removeItem('accessToken');
      setUser(null);
      setIsLoggedIn(false);
    }
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, user, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);

// interfaces
interface UserProps {
  id: string;
  username: string;
  email: string;
  role: string;
}

interface AuthProviderProps {
  children: React.ReactNode;
}

import axios from 'axios';
/**
 * Axios HTTP Client Configuration
 *
 * adds the base url to all http requests
 *
 * if localstorage contains an accessToken that will be added to the authorization header of all requests
 *
 * if a requests returns 401 and it is the first attempt (not _retry) an attempt to refresh the token will be made.
 * if that attempt is succesfull the new accesstoken will be stored in localStorage.
 * If the refreshtoken attempt fails the error is logged, en the accessToken in localstorage is removed as it is invalid.
 *
 * {withCredentials: true} is set to include cookies (for the refresh token) in cross-origin requests.
 */
const BASE_API = 'http://localhost:8080/api/v1';
const REFRESH_TOKEN = BASE_API + '/auth/refresh-token';

const AxiosInstance = axios.create({
  baseURL: BASE_API,
  withCredentials: true,
});

AxiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('accessToken');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

AxiosInstance.interceptors.response.use(
  (response) => {
    return response;
  },
  async (error) => {
    const originalRequest = error.config;

    if (error.response?.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true;

      try {
        const response = await axios.post(REFRESH_TOKEN, null, {
          withCredentials: true,
        });

        const newAccessToken = response.data.accessToken;

        localStorage.setItem('accessToken', newAccessToken);

        originalRequest.headers['Authorization'] = `Bearer ${newAccessToken}`;

        console.log('refreshing token::');

        return AxiosInstance(originalRequest);
      } catch (refreshError) {
        console.error('Failed to refresh token:', refreshError);
        localStorage.removeItem('accessToken');

        return Promise.reject(refreshError);
      }
    }

    return Promise.reject(error);
  }
);

export default AxiosInstance;

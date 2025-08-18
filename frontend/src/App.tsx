import { Route, Routes, Navigate } from 'react-router';
import './App.css';
import AuthPage from './pages/AuthPage';
import Home from './pages/Home';
import Profile from './pages/Profile';
import { useAuth } from './util/context/AuthContext';
import { useEffect } from 'react';
import { ClimbingBoxLoader } from 'react-spinners';

function App() {
  const { authIsLoading, isLoggedIn, user } = useAuth();

  useEffect(() => {}, [isLoggedIn]);

  if (authIsLoading) {
    return <ClimbingBoxLoader />;
  }

  return (
    <Routes>
      <Route path="*" element={<Navigate to="/" replace />} />
      <Route
        path="/"
        element={isLoggedIn ? <Home user={user} /> : <AuthPage />}
      />
      {isLoggedIn && <Route path="/profile" element={<Profile />} />}
    </Routes>
  );
}

export default App;

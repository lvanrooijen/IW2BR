import { Route, Routes } from 'react-router';
import './App.css';
import AuthPage from './pages/AuthPage';
import Home from './pages/Home';
import { useAuth } from './util/context/AuthContext';
import { useEffect } from 'react';

function App() {
  const { isLoggedIn, user } = useAuth();

  useEffect(() => {}, [isLoggedIn]);

  if (!isLoggedIn) {
    return <AuthPage />;
  }

  return (
    <Routes>
      <Route path="/" element={<Home user={user} />} />
    </Routes>
  );
}

export default App;

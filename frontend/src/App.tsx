import { Route, Routes, Navigate } from 'react-router';
import './App.css';
import AuthPage from './pages/AuthPage';
import ProfilePage from './pages/ProfilePage';
import { useAuth } from './util/context/AuthContext';
import { useEffect } from 'react';
import { ClimbingBoxLoader } from 'react-spinners';
import EnvironmentPage from './pages/EnvironmentPage';
import MainPage from './pages/MainPage';
import HomePage from './pages/HomePage';
import EnvironmentLayout from './util/routing/EnvironmentLayout';
import NoteCollectionPage from './pages/NoteCollectionPage';

function App() {
  const { authIsLoading, isLoggedIn, user } = useAuth();

  useEffect(() => {}, [isLoggedIn]);

  if (authIsLoading) {
    return <ClimbingBoxLoader />;
  }

  if (isLoggedIn) {
    return (
      <Routes>
        <Route path="/" element={<MainPage user={user} />}>
          <Route index element={<HomePage user={user} />} />
          <Route path="profile" element={<ProfilePage />} />
        </Route>

        <Route path="/environments/" element={<MainPage user={user} />}>
          <Route path=":environmentId" element={<EnvironmentLayout />}>
            <Route index element={<EnvironmentPage />} />
            <Route
              path="note_collections/:noteCollectionId"
              element={<NoteCollectionPage />}
            />
          </Route>
        </Route>
      </Routes>
    );
  }

  return (
    <Routes>
      <Route path="*" element={<Navigate to={'/'} replace />} />
      <Route path="/" element={<AuthPage />} />
    </Routes>
  );
}

export default App;

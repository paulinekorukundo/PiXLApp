import { Navigate, Outlet } from "react-router-dom";
import { useAppContext } from "../context/AppContext";

export default function Layout() {
  const appState = useAppContext();

  if (!appState.isLoggedIn) {
    return <Navigate to="/auth/login" />;
  }
  return (
    <div>
      <header>Header</header>
      <Outlet />
      <footer>Footer</footer>
    </div>
  );
}

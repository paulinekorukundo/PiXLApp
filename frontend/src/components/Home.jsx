import { useAppContext } from "../context/AppContext";

export default function Home() {
  const appState = useAppContext();
  return (
    <div>
      <h1>Home</h1>
      <p>
        {appState.isLoggedIn
          ? `Welcome, ${appState.userDetails.email}`
          : "Welcome, Guest"}
      </p>
    </div>
  );
}

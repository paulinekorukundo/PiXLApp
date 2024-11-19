import "./App.css";
import "@mantine/core/styles.css";

import { MantineProvider } from "@mantine/core";
import { Routes, Route } from "react-router-dom";

import { AppProvider } from "./context/AppContext";

import PageNotFound from "./components/PageNotFound";
import Layout from "./components/Layout";
import Home from "./components/Home";

import Auth from "./components/Auth";
import Profile from "./components/Profile";
import PostsList from "./components/Posts/PostsList";
import PostForm from "./components/Posts/PostForm";

function App() {
  // const [formOpened, setFormOpened] = useState(false);
  return (
    <MantineProvider>
      <AppProvider>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
            <Route path="profile" element={<Profile />} />
          </Route>
          <Route path="auth">
            <Route path="login" element={<Auth />} />
            <Route path="register" element={<Auth isRegister />} />
          </Route>
          <Route path="*" element={<PageNotFound />} />
          {/* <Container> */}
          <Route path="/posts" element={<PostsList />} />
          <Route path="/add-post" element={<PostForm />} />
          {/* </Container> */}
        </Routes>
      </AppProvider>
    </MantineProvider>
  );
}

export default App;

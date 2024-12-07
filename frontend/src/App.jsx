import "./App.css";
import "@mantine/core/styles.css";

import { MantineProvider } from "@mantine/core";

import { Notifications } from "@mantine/notifications";

import { Routes, Route } from "react-router-dom";

import { AppProvider } from "./context/AppContext";

import PageNotFound from "./components/PageNotFound";
import Layout from "./components/Layout";

import Auth from "./components/Auth";
import Profile from "./components/Profile";
import PostsList from "./components/Posts/PostsList";
import PostForm from "./components/Posts/PostForm";
import RecipesList from "./components/Recipes/RecipesList";

function App() {
  // const [formOpened, setFormOpened] = useState(false);
  return (
    <MantineProvider withGlobalStyles withNormalizeCSS>
      <AppProvider>
      <Notifications position="top-right"/>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<PostsList />} />

            <Route path="profile" element={<Profile />} />
            <Route path="recipes" element={<RecipesList />} />
            <Route path="/add-post" element={<PostForm />} />
          </Route>
          <Route path="auth">
            <Route path="login" element={<Auth />} />
            <Route path="register" element={<Auth isRegister />} />
          </Route>
          <Route path="*" element={<PageNotFound />} />
          {/* <Container> */}
          {/* </Container> */}
        </Routes>
      </AppProvider>
    </MantineProvider>
  );
}

export default App;

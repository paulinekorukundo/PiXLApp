import "./App.css";
import "@mantine/core/styles.css";

import { MantineProvider } from "@mantine/core";
import { Routes, Route } from "react-router-dom";

import { AppProvider } from "./context/AppContext";

import PageNotFound from "./components/PageNotFound";
import Layout from "./components/Layout";
import Home from "./components/Home";

import Auth from "./components/Auth";

function App() {
  return (
    <MantineProvider>
      <AppProvider>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<Home />} />
          </Route>
          <Route path="auth">
            <Route path="login" element={<Auth />} />
            <Route path="register" element={<Auth isRegister />} />
          </Route>
          <Route path="*" element={<PageNotFound />} />
        </Routes>
      </AppProvider>
    </MantineProvider>
  );
}

export default App;

import { createContext, useContext, useState } from "react";
import PropTypes from "prop-types";

export const AppContext = createContext({
  isLoggedIn: false,
  userDetails: null,
  login: () => {},
  logout: () => {},
});

export const AppProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userDetails, setUserDetails] = useState(null);

  const login = (details) => {
    setIsLoggedIn(true);
    setUserDetails(details);
  };

  const logout = () => {
    setIsLoggedIn(false);
    setUserDetails(null);
  };

  return (
    <AppContext.Provider value={{ isLoggedIn, userDetails, login, logout }}>
      {children}
    </AppContext.Provider>
  );
};
AppProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export const useAppContext = () => {
  return useContext(AppContext);
};

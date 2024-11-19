import { createContext, useContext, useState } from "react";
import PropTypes from "prop-types";
import axios from "axios";

export const AppContext = createContext({
  isLoggedIn: false,
  userDetails: null,
  login: () => {},
  register: () => {},
  logout: () => {},
});

export const AppProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userDetails, setUserDetails] = useState(null);

  const login = async (userDetails) => {
    const response = await axios.post("http://localhost:8080/api/v1/auth/login", {
      email: userDetails.email,
      password: userDetails.password,
    })
      if (response.data.success) {
        setIsLoggedIn(true);
        setUserDetails({email: userDetails.email});
      }
      else{
        setIsLoggedIn(false);
        setUserDetails(null);
      }
  };
  const register = async (newUserDetails) => {
    const response = await axios.post("http://localhost:8080/api/v1/auth/register", newUserDetails)
      if (response.data.success) {
        setIsLoggedIn(true);
        setUserDetails({
          username: newUserDetails.username,
          email: newUserDetails.email,
        });
      }
      else{
        setIsLoggedIn(false);
        setUserDetails(null);
      }
  }
  const logout = () => {
    setIsLoggedIn(false);
    setUserDetails(null);
  };

  return (
    <AppContext.Provider value={{ isLoggedIn, userDetails, login, register, logout }}>
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

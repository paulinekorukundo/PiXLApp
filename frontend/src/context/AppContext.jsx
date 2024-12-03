import { createContext, useContext, useEffect, useState } from "react";
import PropTypes from "prop-types";
import axios from "axios";
import { useNavigate } from "react-router-dom";

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
  const navigate = useNavigate();

  const login = async (userDetails) => {
    const response = await axios.post(
      "http://localhost:8080/api/v1/auth/login",
      {
        email: userDetails.email,
        password: userDetails.password,
      }
    );

    if (response.status === 200) {
      const userDetailsResponse = await axios.get(
        `http://localhost:8080/api/v1/profiles/userid/${userDetails.email}`
      );
      setUserDetails({
        email: userDetailsResponse.data.userId,
        firstName: userDetailsResponse.data.firstName,
        lastName: userDetailsResponse.data.lastName,
        bio: userDetailsResponse.data.bio,
        profileId: userDetailsResponse.data.profileId,
      });
      setIsLoggedIn(true);
      localStorage.setItem("email", userDetails.email);
      navigate("/");
    } else {
      setIsLoggedIn(false);
      setUserDetails(null);
    }
  };

  const register = async (newUserDetails) => {
    const response = await axios.post(
      "http://localhost:8080/api/v1/auth/register",
      newUserDetails
    );
    if (response.data.success) {
      setIsLoggedIn(true);
      localStorage.setItem("email", newUserDetails.email);
      setUserDetails({
        username: newUserDetails.username,
        email: newUserDetails.email,
      });
    } else {
      setIsLoggedIn(false);
      setUserDetails(null);
    }
  };
  const logout = () => {
    localStorage.removeItem("email");
    setIsLoggedIn(false);
    setUserDetails(null);
    navigate("/auth/login");
  };

  useEffect(() => {
    const getUserDetails = async () => {
      const token = localStorage.getItem("email");
      if (token) {
        const userDetailsResponse = await axios.get(
          `http://localhost:8080/api/v1/profiles/userid/${token}`
        );
        setUserDetails({
          email: userDetailsResponse.data.userId,
          firstName: userDetailsResponse.data.firstName,
          lastName: userDetailsResponse.data.lastName,
          bio: userDetailsResponse.data.bio,
          profileId: userDetailsResponse.data.profileId,
        });
        console.log("userDetailsResponse", userDetailsResponse);
        setIsLoggedIn(true);
        navigate("/");
      }
    };

    getUserDetails();
  }, []);

  return (
    <AppContext.Provider
      value={{ isLoggedIn, userDetails, login, register, logout }}
    >
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

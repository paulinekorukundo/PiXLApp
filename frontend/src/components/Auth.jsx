import { Link, Navigate } from "react-router-dom";
import { Button, Card, Container, TextInput } from "@mantine/core";
import { hasLength, isEmail, useForm } from "@mantine/form";
import PropTypes from "prop-types";

import { useAppContext } from "../context/AppContext";

export default function Auth({ isRegister }) {
  const appState = useAppContext();

  const form = useForm({
    mode: "controlled",
    initialValues: { username:"",email: "", password: "" },
    validate: {
      email: isEmail("Invalid email"),
      password: hasLength({ min: 6, error: "Password is too short" }),
    },
  });
  if (appState.isLoggedIn) {
    return <Navigate to="/" replace />;
  }
  return (
    <Container size="xs">
      <Card shadow="sm" padding="lg" radius="md" withBorder>
        <div>
          <h1>{isRegister ? "Register" : "Login"}</h1>
        </div>
        <form
          onSubmit={form.onSubmit(() => {
            if (isRegister) {
              appState.register(form.values);
            } else {
              appState.login(form.values);
            }
          })}
        >{ isRegister         &&  <TextInput
        {...form.getInputProps("username")}
        mt="md"
        label="Username"
        placeholder="Username"
      />}
          <TextInput
            {...form.getInputProps("email")}
            mt="md"
            label="Email"
            placeholder="Email"
          />
          <TextInput
            {...form.getInputProps("password")}
            mt="md"
            label="Password"
            placeholder="password"
            type="password"
          />
          <Button type="submit" mt="md">
            {isRegister ? "Register" : "Login"}
          </Button>
          <div>
            {isRegister ? (
              <p>
                Already have an account?
                <Link to="/auth/login">Login</Link>
              </p>
            ) : (
              <p>
                Don&apos;t have an account?
                <Link to="/auth/register">Register</Link>
              </p>
            )}
          </div>
        </form>
      </Card>
    </Container>
  );
}

Auth.propTypes = {
  isRegister: PropTypes.bool,
};

import { Link, Navigate, Outlet } from "react-router-dom";
import { useAppContext } from "../context/AppContext";
import { Burger, Container, Flex, Group, UnstyledButton } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import { useEffect } from "react";
import Footer from "./Footer";

export default function Layout() {
  const appState = useAppContext();
  const [opened, { toggle }] = useDisclosure(false);

  if (!appState.isLoggedIn) {
    return <Navigate to="/auth/login" />;
  }
  return (
    <div>
      <header>
        <Container size="xl" p="sm">
          <Flex justify="space-between">
            <Link to="/">PiXL</Link>
            <Group gap={5} visibleFrom="sm">
              <Link className="menu-item" to="/recipes">
                Recipies
              </Link>
              <Link className="menu-item" to="/profile">
                Profile
              </Link>
              <UnstyledButton
                onClick={() => {
                  appState.logout();
                }}
              >
                Logout
              </UnstyledButton>
            </Group>

            <Burger
              opened={opened}
              onClick={toggle}
              size="sm"
              hiddenFrom="sm"
            />
          </Flex>
        </Container>
      </header>
      <Container size="xl">
        <Outlet />
      </Container>
      <footer>
        <Container
          size="xl"
          sx={{
            backgroundColor: "#f8f9fa",
            marginTop: "100px",
            borderTop: "1px solid #e7e7e7",
          }}
        >
          <Footer />
        </Container>
      </footer>
    </div>
  );
}

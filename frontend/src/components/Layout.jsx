import { Link, Navigate, Outlet } from "react-router-dom";
import { useAppContext } from "../context/AppContext";
import { Burger, Container, Flex, Group } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import { useEffect } from "react";

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
              <Link className="menu-item" to="/profile">
                Profile
              </Link>
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
        <Container size="xl">Footer</Container>
      </footer>
    </div>
  );
}

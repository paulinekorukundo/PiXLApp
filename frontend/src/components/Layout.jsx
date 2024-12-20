import { Link, Navigate, Outlet, useLocation } from "react-router-dom";
import { useAppContext } from "../context/AppContext";
import { Burger, Container, Flex, Group, UnstyledButton } from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import Footer from "./Footer";

/**
 * Provides the general layout used by the Recipes, Profile and Posts components
 */

export default function Layout() {
  const appState = useAppContext();
  const [opened, { toggle }] = useDisclosure(false);

  const location = useLocation();
  const isRecipesPage = location.pathname === "/recipes";
  const isProfilePage = location.pathname === "/profile";

  if (!appState.isLoggedIn) {
    return <Navigate to="/auth/login" />;
  }
  return (
    <div>
      <header>
        <Container size="xl" p="sm">
          <Flex justify="space-between">
            <Link to="/">
              <img width="180" height="130" src="/src/assets/PiXl Logo.png" />
            </Link>
            <Group gap={5} visibleFrom="sm">
              {!isProfilePage && (
                <Link
                  className="menu-item"
                  to={isRecipesPage ? "/" : "/recipes"}
                >
                  {isRecipesPage ? "Posts" : "Recipes"}
                </Link>
              )}
              {isProfilePage && (
                <Link className="menu-item" to="/">
                  Posts
                </Link>
              )}
              {isProfilePage && (
                <Link className="menu-item" to="/recipes">
                  Recipes
                </Link>
              )}
              {!isProfilePage && (
                <Link className="menu-item" to="/profile">
                  Profile
                </Link>
              )}
              {/* {isProfilePage && (
                <Link className="menu-item" to="/etl">
                  Batch Insert
                </Link>
              )} */}
              <Link
                className="menu-item"
                onClick={() => {
                  appState.logout();
                }}
              >
                Logout
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
      <footer
        style={{
          marginTop: "100px",
        }}
      >
        <Container
          size="xl"
          sx={{
            backgroundColor: "#f8f9fa",
            borderTop: "1px solid #e7e7e7",
          }}
        >
          <Footer />
        </Container>
      </footer>
    </div>
  );
}

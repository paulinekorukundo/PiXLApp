import { useAppContext } from "../context/AppContext";
import { Card, Avatar, Text, Group, Button, Flex } from "@mantine/core";
import DisplayUserPosts from "./Posts/DisplayUserPosts";
import UserPostsRecipes from "./Posts/UserPostsRecipes";
import PostForm from "./Posts/PostForm";
import "../assets/General.css";
import RecipeForm from "./Recipes/RecipeForm";
import { useState } from "react";

const stats = [
  { value: "34K", label: "Followers" },
  { value: "187", label: "Follows" },
  { value: "1.6K", label: "Posts" },
];

/**
 * Profile Component
 *
 * This component renders a user's profile page, displaying their basic information,
 * a background cover image, a profile picture, user stats (followers, follows, posts),
 * and the user's posts. It also provides options to follow the user, create new posts,
 * and create new recipes. When a new post or recipe is created, the list of the user's
 * posts is reloaded to reflect the changes.
 *
 * @component
 *
 * @example
 * // Example usage:
 * import Profile from './Profile';
 *
 * function App() {
 *   return <Profile />;
 * }
 *
 * @returns {JSX.Element} A card layout containing the user's profile info, actions, and their posts.
 *
 * Internal Details:
 * - **State Variables:**
 *   - `reloadPosts` (boolean): Controls whether the user's posts should be re-fetched. Triggered by actions like creating a new post or recipe.
 *
 * - **Data Sources:**
 *   - Uses `useAppContext()` to access the currently logged-in user's details (profileId, firstName, lastName, bio).
 *
 * - **Sub-Components:**
 *   - `DisplayUserPosts`: Shows the user's posts. It accepts a `reload` prop that, when toggled, refetches the user's posts.
 *   - `PostForm`: Allows the user to create a new post. If a new post is created, it updates `reloadPosts` to refresh the post list.
 *   - `RecipeForm`: Allows the user to create a new recipe. Similarly updates `reloadPosts` to refresh posts.
 *
 * - **UI Elements:**
 *   - Uses `Card`, `Avatar`, `Text`, `Group`, `Button`, `Flex` from `@mantine/core` for layout and styling.
 *   - Displays user stats (followers, follows, posts) as a simple, static array (`stats`).
 *
 * - **Functional Flow:**
 *   1. Displays a cover image and user avatar.
 *   2. Shows the user's name and bio retrieved from useAppContext.
 *   3. Renders user stats (currently hardcoded values).
 *   4. Includes actions to follow the user (non-functional), create new posts (PostForm), and create new recipes (RecipeForm).
 *   5. Displays the user's posts below the profile section via `DisplayUserPosts`. The `reloadPosts` state triggers a refresh when posts are created.
 */

export default function Profile() {
  const appState = useAppContext();
  // variable to keep track of if the user posts need to be reloaded
  const [reloadPosts, setReloadPosts] = useState(false);

  const items = stats.map((stat) => (
    <div key={stat.label}>
      <Text ta="left" fz="lg" fw={500}>
        {stat.value}
      </Text>
      <Text ta="left" fz="sm" c="dimmed" lh={1}>
        {stat.label}
      </Text>
    </div>
  ));

  const profileId = appState.userDetails.profileId;
  // const [opened, { open, close }] = useDisclosure(false);
  // const add_icon = <IconPlus className={classes.like} stroke={1.5} />;

  return (
    <>
      <Card>
        <Card.Section
          h={250}
          style={{
            backgroundImage:
              "url(https://canadianfoodfocus.org/wp-content/uploads/2021/03/cultural-cuisine.jpg)",
            backgroundSize: "cover",
          }}
        />
        <Flex align="center" mt="md">
          <Avatar
            src="https://raw.githubusercontent.com/mantinedev/mantine/master/.demo/avatars/avatar-9.png"
            size={200}
            radius={200}
          />
          <Flex direction="column" p="lg">
            <Text ta="left" fz="lg" fw={500}>
              {appState.userDetails.firstName} {appState.userDetails.lastName}
            </Text>
            <Text ta="left" fz="sm" c="dimmed">
              {appState.userDetails.bio}
            </Text>
            <Group mt="xs" justify="left" gap={30}>
              {items}
            </Group>

            <Group
              mt="xs"
              justify="flex-start"
              align="center"
              gap="md"
              className="custom-group"
            >
              <Button radius="md" mt="xl" size="md" variant="default">
                Follow
              </Button>

              <PostForm needsReload={setReloadPosts} />
              <RecipeForm needsReload={setReloadPosts} />
            </Group>
          </Flex>
        </Flex>
        {/* <DisplayUserPosts reload={reloadPosts} /> */}
        <UserPostsRecipes reload={reloadPosts} />
      </Card>
    </>
  );
}

import { useAppContext } from "../context/AppContext";
import { Card, Avatar, Text, Group, Button, Flex } from "@mantine/core";
import UserPostsRecipes from "./Posts/UserPostsRecipes";
import PostForm from "./Posts/PostForm";
import "../assets/General.css";
import RecipeForm from "./Recipes/RecipeForm";
import { useState } from "react";
import classes from "../assets/BadgeCard.module.css";
import { IconPlus, IconBoxMultiple7 } from "@tabler/icons-react";
import axios from "axios";
import Loading from "./Loading";

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
 * @returns {JSX.Element} A card layout containing the user's profile info, actions, and their posts.
 */

export default function Profile() {
  const appState = useAppContext();
  // variable to keep track of if the user posts need to be reloaded
  const [reloadPosts, setReloadPosts] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const add_icon = <IconBoxMultiple7 className={classes.like} stroke={1.5} />;

  const handleBatchInsert = async () => {
    setIsLoading(true);
    try {
      const response = await axios.get(
        import.meta.env.VITE_API_URL + `/api/v1/posts/batchInsert`
      );
      setReloadPosts((prev) => !prev);
    } catch (error) {
      console.error("Batch insert failed:", error);
    }
    setIsLoading(false);
  };
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

  return (
    <>
      <div>
        {isLoading && <Loading />}
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
                <Button
                  radius="md"
                  mt="xl"
                  size="md"
                  variant="default"
                  onClick={handleBatchInsert}
                  leftSection={add_icon}
                >
                  Batch Insert
                </Button>
              </Group>
            </Flex>
          </Flex>
          <UserPostsRecipes reload={reloadPosts} />
        </Card>
      </div>
    </>
  );
}

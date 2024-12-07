import { useAppContext } from "../context/AppContext";
import { Card, Avatar, Text, Group, Button, Flex } from "@mantine/core";
import DisplayUserPosts from "./Posts/DisplayUserPosts";
import PostForm from "./Posts/PostForm";
import "../assets/General.css";
import RecipeForm from "./Recipes/RecipeForm";
import { useState } from "react";

const stats = [
  { value: "34K", label: "Followers" },
  { value: "187", label: "Follows" },
  { value: "1.6K", label: "Posts" },
];

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
        <DisplayUserPosts reload={reloadPosts} />
      </Card>
    </>
  );
}

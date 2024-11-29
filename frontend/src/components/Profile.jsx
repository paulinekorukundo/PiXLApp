import { useAppContext } from "../context/AppContext";
import { Card, Avatar, Text, Group, Button, Flex } from "@mantine/core";

const stats = [
  { value: "34K", label: "Followers" },
  { value: "187", label: "Follows" },
  { value: "1.6K", label: "Posts" },
];

export default function Profile() {
  const appState = useAppContext();
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
          <Button fullWidth radius="md" mt="xl" size="md" variant="default">
            Follow
          </Button>
          
        </Flex>
      </Flex>
    </Card>
    
    </>
  );
}

import axios from "axios";
import { useEffect, useState } from "react";
import { Card, Text, Loader, Group, Image } from "@mantine/core";
import PostActions from "./PostsActions";
import classes from "../../assets/BadgeCard.module.css";
import "../../assets/General.css";
import { useAppContext } from "../../context/AppContext";
import { API_URL } from "../../config";

function DisplayUserPosts() {
  const [loading, setLoading] = useState(true);
  const [loadedImages, setLoadedImages] = useState({});
  const [userPosts, setUserPosts] = useState({});
  const { userDetails } = useAppContext();

  //View posts
  useEffect(() => {
    const loadPosts = async () => {
      const userId = userDetails.email;
      try {
        const response = await axios.get(`${API_URL}/${userId}`);
        setUserPosts(response.data.data || []);
      } catch (error) {
        if (error.response && error.response.status === 404) {
          setUserPosts([]);
          console.log("No Posts for User");
        } else {
          console.error("Error fetching posts:", error);
        }
      } finally {
        setLoading(false);
      }
    };

    loadPosts();
  }, [userDetails.email]);

  useEffect(() => {
    const loadImages = async () => {
      if (!Array.isArray(userPosts) || userPosts.length === 0) return;

      try {
        let imagePromises;
        if (Array.isArray(userPosts)) {
          imagePromises = userPosts.map(async (post) => {
            try {
              const response = await axios.get(
                `${API_URL}/api/v1/posts/media/${post.media}`,
              );
              return response.data;
            } catch (error) {
              console.error(
                `Error loading image for post ${post.postId}:`,
                error,
              );
              return null;
            }
          });
        } else if (typeof userPosts === "object") {
          imagePromises = Object.values(userPosts).map(async (post) => {
            try {
              const response = await axios.get(
                `${API_URL}/api/v1/posts/media/${post.media}`,
              );
              return response.data;
            } catch (error) {
              console.error(
                `Error loading image for post ${post.postId}:`,
                error,
              );
              return null;
            }
          });
        }

        const loadedImages = await Promise.all(imagePromises);
        setLoadedImages(
          loadedImages.reduce(
            (acc, img, index) => ({ ...acc, [index]: img }),
            {},
          ),
        );
        setLoading(false);
      } catch (error) {
        console.error("Error loading images:", error);
        setLoading(false);
      }
    };

    loadImages();
  }, [userPosts]);

  if (loading) return <Loader />;

  return (
    // <Grid gap={10}>
    <Card>
      {userPosts && userPosts?.length > 0 ? (
        userPosts.map((post) => (
          // <Grid.Col span={{ base: 12, md: 6, lg: 4 }} key={post.postId}>
          <Card
            withBorder
            radius="md"
            p="md"
            className={classes.card}
            key={post.postId}
            shadow="sm"
            padding="lg"
          >
            <Card.Section>
              <Image
                src={
                  post.media
                    ? `${API_URL}/api/v1/posts/media/${post.media}`
                    : "/coming-soon.png"
                }
                alt={`Post image for ${post.content}`}
                h={300}
                onError={(e) => {
                  e.target.src = "/coming-soon.png";
                }}
              />
            </Card.Section>
            <Group mt="md" position="apart">
              <PostActions
                postId={post.postId}
                likes={post.likesCount}
                comments={post.commentsCount}
              />
            </Group>
            <Card.Section className={classes.section}>
              <Group gap={7} mt={5}>
                <Text fw={500} fz="sm" mt="xs">
                  {post.userId}
                </Text>
                <Text weight={300} fz="sm" mt="xs">
                  {post.content}
                </Text>
              </Group>
            </Card.Section>
          </Card>
          // </Grid.Col>
        ))
      ) : (
        <Text>No posts available</Text>
      )}
    </Card>

    // </Grid>
  );
}

export default DisplayUserPosts;

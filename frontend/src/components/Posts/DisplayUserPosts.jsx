/* eslint-disable react/prop-types */
import axios from "axios";
import { useEffect, useState } from "react";
import { Card, Text, Loader, Group, Image, Grid } from "@mantine/core";
import PostActions from "./PostsActions";
import classes from "../../assets/BadgeCard.module.css";
import "../../assets/General.css";
import { useAppContext } from "../../context/AppContext";
import { API_URL } from "../../config";


/**
 * DisplayUserPosts Component
 *
 * This component fetches and displays all posts created by the currently logged-in user.
 * It shows a grid of post cards, each containing an image, textual content, and post actions
 * (like/comment). If no posts are found, it displays a "No posts available" message.
 *
 * @component
 *
 * @prop {boolean} [reload] - A prop that, when changed, triggers the component to refetch the user's posts.
 *
 * @example
 * // Example usage:
 * import { useState } from 'react';
 * import DisplayUserPosts from './DisplayUserPosts';
 *
 * function UserProfile() {
 *   const [reloadFlag, setReloadFlag] = useState(false);
 *
 *   const reloadPosts = () => setReloadFlag(prev => !prev);
 *
 *   return (
 *     <div>
 *       <button onClick={reloadPosts}>Reload User Posts</button>
 *       <DisplayUserPosts reload={reloadFlag} />
 *     </div>
 *   );
 * }
 *
 * @returns {JSX.Element} A grid of user posts or a loading indicator or a message if no posts are available.
 *
 * Internal Details:
 * - **State Variables:**
 *   - `loading` (boolean): Indicates whether the component is currently loading data.
 *   - `loadedImages` (Object): Maps indices to loaded images (currently not heavily utilized).
 *   - `userPosts` (Array|Object): The list of posts fetched for the user. Usually an array of post objects.
 *
 * - **Data Fetching:**
 *   Uses the currently logged-in user's email from the AppContext to fetch their posts from the backend.
 *   If no posts are found, sets `userPosts` to an empty array.
 *
 * - **Image Loading:**
 *   Attempts to load each post's media from the server. If any fails, a placeholder image is used.
 *
 * - **Dependencies:**
 *   - `axios` for HTTP requests.
 *   - `@mantine/core` for UI components like Card, Grid, Loader, Text, and Image.
 *   - `useAppContext` to get the current user's details.
 *
 * - **Lifecycle:**
 *   - On mount and whenever `userDetails.email` or `props.reload` changes, it refetches the user’s posts.
 *   - On `userPosts` change, attempts to load the associated images.
 *
 * @see PostActions for actions available on each post (like, unlike, edit, delete).
 */


function DisplayUserPosts(props) {
  const [loading, setLoading] = useState(true);
  const [loadedImages, setLoadedImages] = useState({});
  const [userPosts, setUserPosts] = useState({});
  const { userDetails } = useAppContext();

  //View posts
  useEffect(() => {
    const loadPosts = async () => {
      const userId = userDetails.email;
      try {
        const response = await axios.get(`${API_URL}/api/v1/posts/${userId}`);
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
  }, [userDetails.email, props.reload]);

  useEffect(() => {
    const loadImages = async () => {
      if (!Array.isArray(userPosts) || userPosts.length === 0) return;

      try {
        let imagePromises;
        if (Array.isArray(userPosts)) {
          imagePromises = userPosts.map(async (post) => {
            try {
              const response = await axios.get(
                `${API_URL}/api/v1/posts/media/${post.media}`
              );
              return response.data;
            } catch (error) {
              console.error(
                `Error loading image for post ${post.postId}:`,
                error
              );
              return null;
            }
          });
        } else if (typeof userPosts === "object") {
          imagePromises = Object.values(userPosts).map(async (post) => {
            try {
              const response = await axios.get(
                `${API_URL}/api/v1/posts/media/${post.media}`
              );
              return response.data;
            } catch (error) {
              console.error(
                `Error loading image for post ${post.postId}:`,
                error
              );
              return null;
            }
          });
        }

        const loadedImages = await Promise.all(imagePromises);
        setLoadedImages(
          loadedImages.reduce(
            (acc, img, index) => ({ ...acc, [index]: img }),
            {}
          )
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
    <Grid gap={10}>
      {userPosts && userPosts?.length > 0 ? (
        userPosts.map((post) => (
          <Grid.Col span={{ base: 12, md: 6, lg: 4 }} key={post.postId}>
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
          </Grid.Col>
        ))
      ) : (
        <Text>No posts available</Text>
      )}
    </Grid>
  );
}

export default DisplayUserPosts;

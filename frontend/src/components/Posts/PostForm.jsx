/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";
import { Modal, TextInput, Button, Group, FileInput } from "@mantine/core";
import { IconImageInPicture, IconPlus } from "@tabler/icons-react";
import axios from "axios";
import { useDisclosure } from "@mantine/hooks";
import classes from "../../assets/BadgeCard.module.css";
import { useAppContext } from "../../context/AppContext";
import { notifications } from "@mantine/notifications";

/**
 * PostForm Component
 *
 * This component provides a modal form for creating and submitting new posts.
 * Users can enter textual content, optionally add a tag, and upload an image file.
 * Once submitted, it sends a POST request to create a new post in the backend system.
 * If successful, it displays a success notification and optionally triggers a re-render
 * in the parent component. If an error occurs, it displays an error notification.
 *
 * @component
 *
 * @prop {Function} [needsReload] - An optional state setter function from the parent
 *   component. If provided, it will be called after a successful post creation to
 *   request a data reload in the parent.
 *
 * @example
 * // Example usage:
 * import PostForm from './PostForm';
 *
 * function Feed() {
 *   const [reload, setReload] = useState(false);
 *
 *   return (
 *     <div>
 *       <PostForm needsReload={setReload} />
 *       {reload && <PostsList />}
 *     </div>
 *   );
 * }
 */

function PostForm({ post, needsReload }) {
  // User
  const { userDetails } = useAppContext();
  const [formData, setFormData] = useState({
    content: "",
    media: "",
  });

  // Create a post
  const [opened, { open, close }] = useDisclosure(false);
  const [content, setContent] = useState("");
  const [tag, setTag] = useState("");
  const [media, setMedia] = useState("");
  const [editMode, setEditMode] = useState(false); // Track create/edit mode

  const icon = <IconImageInPicture className="image-icon" stroke={1.5} />;
  const add_icon = <IconPlus className={classes.like} stroke={1.5} />;

  const [message, setMessage] = useState("");

  useEffect(() => {
    if (post) {
      setFormData({
        content: post.content || "",
        media: post.media || "",
      });
    } else {
      setFormData({
        content: "",
        media: "",
      });
    }
  }, [post]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async () => {
    try {
      const formData = new FormData();
      formData.append("media", media);
      formData.append("userId", userDetails.email);
      formData.append("content", content);
      if (tag) formData.append("tag", tag);

      await axios.post(
        import.meta.env.VITE_API_URL + "/api/v1/posts/",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      // setMessage("Post created successfully!");
      notifications.show({
        title: "Success",
        message: "Post created successfully!",
        color: "green",
      });
      if (needsReload) {
        needsReload((prevState) => !prevState);
      }
      close();
    } catch (error) {
      console.error("Error saving post:", error);
      if (error.response) {
        notifications.show({
          title: "Error",
          message: error.response
            ? `Error: ${error.response.data.message}`
            : "An unexpected error occurred",
          color: "red",
        });
      } else {
        setMessage("An unexpected error occurred");
      }
    }
  };
  return (
    <>
      <Button
        radius="md"
        mt="xl"
        size="md"
        variant="default"
        onClick={() => {
          setContent("");
          setMedia(null);
          setTag("");
          open();
        }}
        leftSection={add_icon}
      >
        Create Post
      </Button>

      <Modal opened={opened} onClose={close} title={"Add Post"}>
        <TextInput disabled label="Username" value={userDetails.email} />
        <TextInput
          label="Content"
          placeholder="Post Description"
          value={content}
          onChange={(e) => setContent(e.target.value)}
        />
        <FileInput
          clearable
          rightSection={icon}
          label="Upload Media"
          placeholder="Add Media"
          accept="image/png,image/jpeg"
          onChange={(media) => setMedia(media)}
        />
        <TextInput
          label="Tag"
          placeholder="Add Tag to Post"
          value={tag}
          onChange={(e) => setTag(e.target.value)}
        />
        <Group position="right" mt="md">
          <Button onClick={handleSubmit}>{editMode ? "Update" : "Save"}</Button>
        </Group>
      </Modal>
    </>
  );
}

export default PostForm;

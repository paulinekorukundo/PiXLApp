/* eslint-disable react/prop-types */
import { useEffect, useState } from "react";
import { Modal, TextInput, Button, Group, FileInput } from "@mantine/core";
import { IconImageInPicture, IconPlus } from "@tabler/icons-react";
import axios from "axios";
import { useDisclosure } from "@mantine/hooks";
import classes from "../../assets/BadgeCard.module.css";
import { useAppContext } from "../../context/AppContext";
import { notifications } from "@mantine/notifications";

function PostForm(props) {
  // User
  const { userDetails } = useAppContext();

  // Create a post
  const [opened, { open, close }] = useDisclosure(false);
  const [content, setContent] = useState("");
  const [tag, setTag] = useState("");
  const [media, setMedia] = useState("");

  const icon = <IconImageInPicture className="image-icon" stroke={1.5} />;
  const add_icon = <IconPlus className={classes.like} stroke={1.5} />;

  const [message, setMessage] = useState("");

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
      if (props.needsReload) {
        props.needsReload((prevState) => !prevState);
      }
      close();
    } catch (error) {
      console.error("Error saving post:", error);
      if (error.response) {
        // setMessage(`Error: ${error.response.data.message}`);
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
        onClick={open}
        leftSection={add_icon}
      >
        Create Post
      </Button>

      <Modal opened={opened} onClose={close} title="Add Post">
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
          <Button onClick={handleSubmit}>Save</Button>
        </Group>
      </Modal>
    </>
  );
}

export default PostForm;

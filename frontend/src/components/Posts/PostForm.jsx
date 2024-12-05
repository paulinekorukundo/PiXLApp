import React, { useState } from "react";
import { Modal, TextInput, Button, Group, ActionIcon, FileInput } from "@mantine/core";
import { savePost, editPost } from "./api";
import { useDisclosure } from "@mantine/hooks";
import { useAppContext } from "../../context/AppContext";
import { IconImageInPicture, IconPlus } from "@tabler/icons-react";
import classes from "../../assets/BadgeCard.module.css";
import axios from "axios";
import { API_URL } from "../../config";

function PostForm() {

  // const [userId, setUserId] = useState(post?.userId || "");

  // const handleSubmit = async () => {
  //   try {
  //     const postData = { ...post, content, userId };
  //     if (isEditing) {
  //       await editPost(postData);
  //       alert("Post updated!");
  //     } else {
  //       await savePost(postData);
  //       alert("Post created!");
  //     }
  //     onClose();
  //   } catch (error) {
  //     console.error("Error saving post:", error);
  //   }
  // };

  // User
  const { userDetails } = useAppContext();

  // Create a post
  const [opened, { open, close }] = useDisclosure(false);
  const [content, setContent] = useState("");
  const [tag, setTag] = useState("");
  const [media, setMedia] = useState("");

  const icon = <IconImageInPicture className="image-icon" stroke={1.5} />;
  const [message, setMessage] = useState("");

  const handleSubmit = async () => {
    try {
      const formData = new FormData();
      formData.append("media", media);
      formData.append("userId", userDetails.email);
      formData.append("content", content);
      if (tag) formData.append("tag", tag);
      
      //"http://localhost:8080/api/v1/posts/"
      await axios.post(`${API_URL}/`, formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      setMessage("Post created successfully!");
      close();
    } catch (error) {
      console.error("Error saving post:", error);
      if (error.response) {
        setMessage(`Error: ${error.response.data.message}`);
      } else {
        setMessage("An unexpected error occurred");
      }
    }
  };

  return (
    <>
      <ActionIcon
          size={42}
          variant="default"
          aria-label="Add Post"
          onClick={open}
        >
          <IconPlus className={classes.like} stroke={1.5} />
        </ActionIcon>
      
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

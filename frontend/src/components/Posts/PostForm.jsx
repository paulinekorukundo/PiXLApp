import React, { useState } from "react";
import { Modal, TextInput, Button, Group } from "@mantine/core";
import { savePost, editPost } from "./api";

function PostForm({ opened, onClose, post, isEditing }) {
  const [content, setContent] = useState(post?.content || "");
  const [userId, setUserId] = useState(post?.userId || "");

  const handleSubmit = async () => {
    try {
      const postData = { ...post, content, userId };
      if (isEditing) {
        await editPost(postData);
        alert("Post updated!");
      } else {
        await savePost(postData);
        alert("Post created!");
      }
      onClose();
    } catch (error) {
      console.error("Error saving post:", error);
    }
  };

  return (
    <Modal opened={opened} onClose={onClose} title={isEditing ? "Edit Post" : "Add Post"}>
      <TextInput
        label="User ID"
        placeholder="Enter your User ID"
        value={userId}
        onChange={(e) => setUserId(e.target.value)}
      />
      <TextInput
        label="Content"
        placeholder="What's on your mind?"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <Group position="right" mt="md">
        <Button onClick={handleSubmit}>{isEditing ? "Update" : "Save"}</Button>
      </Group>
    </Modal>
  );
}

export default PostForm;

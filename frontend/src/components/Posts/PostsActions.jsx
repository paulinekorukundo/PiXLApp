import React from "react";
import { IconHeart, IconEdit, IconMessageChatbot, IconHeartBroken} from '@tabler/icons-react';
import classes from "../../assets/BadgeCard.module.css";
import "../../assets/General.css";
import { ActionIcon, Text } from "@mantine/core";
import axios from "axios";

function PostActions({ postId, likes, comments}) {

  const likePost = async (postId) => axios.post("http://localhost:8080/api/v1/posts/likePost", { postId });
  const unlikePost = async (postId) => axios.post("http://localhost:8080/api/v1/posts/unlikePost", { postId });

  const handleLike = async () => {
    try {
      await likePost(postId);
      alert("Post liked!");
    } catch (error) {
      console.error("Error liking post:", error);
    }
  };

  const handleUnlike = async () => {
    try {
      await unlikePost(postId);
      alert("Post unliked!");
    } catch (error) {
      console.error("Error unliking post:", error);
    }
  };

  return (
    <div className={classes.actionsContainer}>
      <div className={classes.iconTextWrapper}>
        <ActionIcon variant="light" radius="md" size={36} onClick={handleLike}>
          <IconHeart className={classes.like} stroke={1.5} />
        </ActionIcon>
        <Text className={classes.countText} size="sm" c="dimmed">
          {likes} 
        </Text>
      </div>

      <div className={classes.iconTextWrapper}>
        <ActionIcon variant="light" radius="md" size={36} onClick={handleLike}>
          <IconHeartBroken
            size={48}
            strokeWidth={1.5}
            className={classes.like}
            onClick={handleUnlike}
          />
        </ActionIcon>
      </div>

      <div className={classes.iconTextWrapper}>
        <ActionIcon variant="light" radius="md" size={36} onClick={handleLike}>
          <IconMessageChatbot
            size={48}
            strokeWidth={1.5}
            className={classes.like}
          />
        </ActionIcon>
          <Text className={classes.countText} size="sm" c="dimmed"> 
            {comments}
          </Text>
      </div>

      <div className={classes.iconTextWrapper}>
        <ActionIcon className="item" variant="light" radius="md" size={36} onClick={handleUnlike}>
          <IconEdit className={classes.like} stroke={1.5} />
        </ActionIcon>
      </div>

    </div>
  );
}

export default PostActions;

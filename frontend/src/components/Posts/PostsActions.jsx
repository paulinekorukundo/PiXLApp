import {
  IconHeart,
  IconEdit,
  IconMessageChatbot,
  IconHeartBroken,
} from "@tabler/icons-react";
import classes from "../../assets/BadgeCard.module.css";
import "../../assets/General.css";
import { ActionIcon, Text } from "@mantine/core";
import axios from "axios";

// eslint-disable-next-line react/prop-types
function PostActions({ postId, likes, comments, onLike }) {
  const handleLike = async () => {
    try {
      await axios.post(
        import.meta.env.VITE_API_URL + "/api/v1/posts/likePost",
        { postId },
      );
      onLike(postId);
    } catch (error) {
      console.error("Error liking post:", error);
    }
  };

  const handleUnlike = async () => {
    try {
      await axios.post(
        import.meta.env.VITE_API_URL + "/api/v1/posts/unlikePost",
        { postId },
      );
      onLike(postId);
    } catch (error) {
      console.error("Error unliking post:", error);
    }
  };

  return (
    <div className={classes.actionsContainer}>
      <div className={classes.iconTextWrapper}>
        <ActionIcon variant="light" radius="md" size={36}>
          <IconHeart
            className={classes.like}
            stroke={1.5}
            onClick={() => {
              handleLike();
            }}
          />
        </ActionIcon>
        <Text className={classes.countText} size="sm" c="dimmed">
          {likes}
        </Text>
      </div>

      <div className={classes.iconTextWrapper}>
        <ActionIcon variant="light" radius="md" size={36}>
          <IconHeartBroken
            size={48}
            strokeWidth={1.5}
            className={classes.like}
            onClick={handleUnlike}
          />
        </ActionIcon>
      </div>

      <div className={classes.iconTextWrapper}>
        <ActionIcon variant="light" radius="md" size={36}>
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

      {/* TODO PK: Add comment on click */}
      <div className={classes.iconTextWrapper}>
        <ActionIcon className="item" variant="light" radius="md" size={36}>
          <IconEdit className={classes.like} stroke={1.5} />
        </ActionIcon>
      </div>
    </div>
  );
}

export default PostActions;

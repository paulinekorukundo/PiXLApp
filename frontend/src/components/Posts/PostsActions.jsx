import {
  IconHeart,
  IconEdit,
  IconMessageChatbot,
  IconHeartBroken,
  IconCross,
  IconX,
} from "@tabler/icons-react";
import classes from "../../assets/BadgeCard.module.css";
import "../../assets/General.css";
import { ActionIcon, Text } from "@mantine/core";
import axios from "axios";
import { useAppContext } from "../../context/AppContext";
import { notifications } from "@mantine/notifications";

/**
 * PostActions Component
 *
 * This component displays action icons associated with a given post, including like, unlike,
 * edit, and delete actions. It also shows the current number of likes and comments. The actions
 * trigger asynchronous calls to the backend API and update the UI accordingly. Only the author
 * of the post (determined by comparing the current user's email with the post's user ID) sees
 * the edit and delete icons.
 *
 * @component
 *
 * @prop {string | number} postId - The unique identifier of the post.
 * @prop {number} likes - The current number of likes the post has.
 * @prop {number} comments - The current number of comments the post has.
 * @prop {Function} onLike - A callback function executed when the post is liked or unliked. It should
 *   handle any state updates or refresh actions related to the post's like count.
 * @prop {string} postUserId - The email or user ID of the post's author. Used to determine if the edit
 *   and delete buttons should be displayed.
 * @prop {Function} onEdit - A callback function executed when the edit action is triggered. The postId is passed
 *   to this function for identifying which post to edit.
 * @prop {Function} onDelete - A callback function executed when the delete action is triggered. The postId is passed
 *   to this function for identifying which post to remove from the UI.
 *
 * @example
 * // Example usage:
 * function Post({ id, userId, likesCount, commentsCount, refreshPosts }) {
 *   const handleLike = (postId) => {
 *     // Update local state or trigger a re-fetch of posts
 *     refreshPosts();
 *   };
 *
 *   const handleEdit = (postId) => {
 *     // Open edit modal, etc.
 *   };
 *
 *   const handleDelete = (postId) => {
 *     // Remove the deleted post from the UI
 *     refreshPosts();
 *   };
 *
 *   return (
 *     <PostActions
 *       postId={id}
 *       likes={likesCount}
 *       comments={commentsCount}
 *       onLike={handleLike}
 *       postUserId={userId}
 *       onEdit={handleEdit}
 *       onDelete={handleDelete}
 *     />
 *   );
 * }
 */

// eslint-disable-next-line react/prop-types
function PostActions({
  postId,
  likes,
  comments,
  onLike,
  postUserId,
  onEdit,
  onDelete,
}) {
  const { userDetails } = useAppContext();
  const isAuthor = userDetails.email === postUserId;
  const handleLike = async () => {
    try {
      await axios.post(
        import.meta.env.VITE_API_URL + "/api/v1/posts/likePost",
        { postId }
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
        { postId }
      );
      onLike(postId);
    } catch (error) {
      console.error("Error unliking post:", error);
    }
  };

  const handleEdit = async () => {
    try {
      await axios.post(import.meta.env.VITE_API_URL + "/api/v1/posts/edit", {
        postId,
      });
      onEdit(postId);
    } catch (error) {
      console.error("Error editing post:", error);
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(
        `${import.meta.env.VITE_API_URL}/api/v1/posts?postId=${postId}`
      );
      if (onDelete) onDelete(postId);
      notifications.show({
        title: "Success",
        message: "Post deleted!",
        color: "green",
      });
    } catch (error) {
      console.error("Error deleting post:", error);

      if (error.response) {
        notifications.show({
          title: "Error",
          message: error.response
            ? `Error: ${error.response.data.message}`
            : "An unexpected error occurred",
          color: "red",
        });
      }
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

      {isAuthor && (
        <div className={classes.iconTextWrapper}>
          <ActionIcon
            className="item "
            variant="light"
            radius="md"
            size={36}
            style={{ position: "absolute", right: 50 }}
            onClick={handleDelete}
          >
            <IconX className={classes.delete_icon} stroke={1.5} />
          </ActionIcon>
        </div>
      )}
    </div>
  );
}

export default PostActions;

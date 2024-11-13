-- User Table
INSERT INTO user (email, password, logged_in_status) VALUES ('johndoe@example.com', 'password123', TRUE);
INSERT INTO user (email, password, logged_in_status) VALUES ('janedoe@example.com', 'password456', FALSE);


-- Profile Table
INSERT INTO Profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('test1234', 'John', 'Doe', 'I love Italian and Mexican food.', 'Italian, Mexican', 'john_doe_profile.jpg');

INSERT INTO Profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('test567', 'Jane', 'Smith', 'Vegetarian foodie and baker.', 'Vegetarian, Desserts', 'jane_smith_profile.jpg');


-- AchievementBadge Table
-- INSERT INTO AchievementBadge (badgeId, name, description, criteria) VALUES ('b1', 'Top Chef', 'Awarded for sharing 50 recipes', 'Share 50 recipes');
-- INSERT INTO AchievementBadge (badgeId, name, description, criteria) VALUES ('b2', 'Popular Post', 'Awarded for getting 100 likes on a post', 'Get 100 likes on any post');

-- Following Table
-- INSERT INTO Following (followingId, userId, followerId) VALUES ('f1', 'u1', 'u2');
-- INSERT INTO Following (followingId, userId, followerId) VALUES ('f2', 'u2', 'u1');

-- Tag Table
-- INSERT INTO Tag (tag_id, name, description) VALUES ('t1', 'vegan', 'Recipes without animal products');
-- INSERT INTO Tag (tag_id, name, description) VALUES ('t2', 'keto', 'Low-carb, high-fat recipes');
INSERT INTO tag (name) VALUES ('Healthy Eating');
INSERT INTO tag (name) VALUES ('Vegan');
INSERT INTO tag (name) VALUES ('Keto');
INSERT INTO tag (name) VALUES ('Desserts');
-- INSERT INTO Tag (name) VALUES ('Travel');
-- INSERT INTO Tag (name) VALUES ('Photography');
-- INSERT INTO Tag (name) VALUES ('Fitness');
-- INSERT INTO Tag (name) VALUES ('Recipes');
-- INSERT INTO Tag (name) VALUES ('Food Photography');
-- INSERT INTO Tag (name) VALUES ('Baking');
-- INSERT INTO Tag (name) VALUES ('Low Carb');
-- INSERT INTO Tag (name) VALUES ('Animal Based');

-- Post Table
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user123', 'This is my first post!', 'https://example.com/media1.jpg', 10, 5, 1);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user456', 'Loving the weather today!', NULL, 25, 8, 2);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user789', 'Check out my new blog post!', 'https://example.com/media2.png', 15, 3, 4);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user101', 'Happy Monday, everyone!', NULL, 5, 1, 3);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user202', 'Traveling to Japan tomorrow, super excited!', 'https://example.com/media3.jpg', 100, 50, 2);



-- PostTag Table (Join Table)
-- INSERT INTO Post_Tag (post_id, tag_id) VALUES (1, 1);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (1, 2);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (1, 3);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (1, 4);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (2, 1);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (2, 2);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (2, 3);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (2, 4);
-- INSERT INTO PostTag (post_id, tag_id) VALUES (3, 3);


-- Comment Table
-- INSERT INTO Comment (commentId, authorId, postId, content, timestamp) VALUES ('c1', 'u2', 'p1', 'Looks delicious! I will try it soon.', '2024-10-17 18:45:00');
-- INSERT INTO Comment (commentId, authorId, postId, content, timestamp) VALUES ('c2', 'u1', 'p2', 'Perfect for my keto diet!', '2024-10-17 19:00:00');

-- Saves Table
-- INSERT INTO Saves (userId, postId, timestamp) VALUES ('u1', 'p2', '2024-10-17 19:15:00')
-- INSERT INTO Saves (userId, postId, timestamp) VALUES ('u2', 'p1', '2024-10-17 19:20:00');

-- -- Likes Table
-- INSERT INTO Likes (userId, postId, timestamp) VALUES ('u1', 'p2', '2024-10-17 19:30:00');
-- INSERT INTO Likes (userId, postId, timestamp) VALUES ('u2', 'p1', '2024-10-17 19:35:00');

-- -- Recipe Table
-- INSERT INTO Recipe (recipeId, ingredients, instructions, cuisineType, dietaryRestrictions, prepTime) VALUES ('r1', 'Pasta, Tomato, Olive Oil', 'Boil pasta, sauté tomatoes, mix and serve.', 'Italian', 30);
-- INSERT INTO Recipe (recipeId, ingredients, instructions, cuisineType, dietaryRestrictions, prepTime) VALUES ('r2', 'Almond Flour, Cocoa Powder, Butter', 'Mix ingredients, bake at 180C for 20 mins.', 'Dessert', 40);

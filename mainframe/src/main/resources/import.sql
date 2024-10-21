-- User Table
INSERT INTO user (user_id, username, email, password, profile_picture, bio, logged_in_status) VALUES ('u1', 'johndoe', 'johndoe@example.com', 'password123', 'pic1.jpg', 'Love cooking and exploring new cuisines', TRUE);
INSERT INTO user (user_id, username, email, password, profile_picture, bio, logged_in_status) VALUES ('u2', 'janedoe', 'janedoe@example.com', 'password456', 'pic2.jpg', 'Food blogger and chef', FALSE);

-- AchievementBadge Table
INSERT INTO AchievementBadge (badgeId, name, description, criteria) VALUES ('b1', 'Top Chef', 'Awarded for sharing 50 recipes', 'Share 50 recipes');
INSERT INTO AchievementBadge (badgeId, name, description, criteria) VALUES ('b2', 'Popular Post', 'Awarded for getting 100 likes on a post', 'Get 100 likes on any post');

-- Following Table
INSERT INTO Following (followingId, userId, followerId) VALUES ('f1', 'u1', 'u2');
INSERT INTO Following (followingId, userId, followerId) VALUES ('f2', 'u2', 'u1');

-- Post Table
INSERT INTO Post (postId, authorId, content, media, likesCount, commentsCount) VALUES ('p1', 'u1', 'Delicious Vegan Pasta Recipe', 'media link', 45, 10);
INSERT INTO Post (postId, authorId, content, media, likesCount, commentsCount) VALUES ('p2', 'u2', 'Keto Brownies Recipe', 'media link', 120, 20);

-- Tag Table
INSERT INTO Tag (tagId, name, description) VALUES ('t1', 'vegan', 'Recipes without animal products');
INSERT INTO Tag (tagId, name, description) VALUES ('t2', 'keto', 'Low-carb, high-fat recipes');

-- PostTag Table (Join Table)
INSERT INTO PostTag (postId, tagId) VALUES ('p1', 't1');
INSERT INTO PostTag (postId, tagId) VALUES ('p2', 't2');

-- Comment Table
-- INSERT INTO Comment (commentId, authorId, postId, content, timestamp) VALUES ('c1', 'u2', 'p1', 'Looks delicious! I will try it soon.', '2024-10-17 18:45:00');
-- INSERT INTO Comment (commentId, authorId, postId, content, timestamp) VALUES ('c2', 'u1', 'p2', 'Perfect for my keto diet!', '2024-10-17 19:00:00');

-- Saves Table
INSERT INTO Saves (userId, postId, timestamp) VALUES ('u1', 'p2', '2024-10-17 19:15:00');
INSERT INTO Saves (userId, postId, timestamp) VALUES ('u2', 'p1', '2024-10-17 19:20:00');

-- Likes Table
INSERT INTO Likes (userId, postId, timestamp) VALUES ('u1', 'p2', '2024-10-17 19:30:00');
INSERT INTO Likes (userId, postId, timestamp) VALUES ('u2', 'p1', '2024-10-17 19:35:00');

-- Recipe Table
INSERT INTO Recipe (recipeId, ingredients, instructions, cuisineType, dietaryRestrictions, prepTime) VALUES ('r1', 'Pasta, Tomato, Olive Oil', 'Boil pasta, sauté tomatoes, mix and serve.', 'Italian', 30);
INSERT INTO Recipe (recipeId, ingredients, instructions, cuisineType, dietaryRestrictions, prepTime) VALUES ('r2', 'Almond Flour, Cocoa Powder, Butter', 'Mix ingredients, bake at 180C for 20 mins.', 'Dessert', 40);

-- User Table
INSERT INTO user (email, password, logged_in_status) VALUES ('johndoe@example.com', 'password123', TRUE);
INSERT INTO user (email, password, logged_in_status) VALUES ('janedoe@example.com', 'password456', FALSE);

-- Profile Table
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('johndoe@example.com', 'John', 'Doe', 'I love Italian and Mexican food.', 'Italian, Mexican', 'john_doe_profile.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('janesmith@example.com', 'Jane', 'Smith', 'Vegetarian foodie and baker.', 'Vegetarian, Desserts', 'jane_smith_profile.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('testuser@example.com', 'Test', 'User', 'Non Vegetarian foodie and connoisseur of vintage wine.', 'Non-Vegetarian, Drunk', 'test_user_profile.jpg');

-- Tag Table
INSERT INTO tag (name) VALUES ('Healthy Eating');
INSERT INTO tag (name) VALUES ('Vegan');
INSERT INTO tag (name) VALUES ('Keto');
INSERT INTO tag (name) VALUES ('Desserts');


-- Post Table
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('user123', 'This is my first post!', '1732864476110_food1.jpg', 10, 5);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('user456', 'Loving the weather today!', 'food5.jpg', 25, 8);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('user789', 'Check out my new blog post!', 'food2.jpg', 15, 3);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('user101', 'Happy Monday, everyone!', 'food3.jpg', 5, 1);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('user202', 'Traveling to Japan tomorrow, super excited!', 'food4.jpg', 100, 50);


-- Recipe Table
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Basic Desert', 'Flour, Sugar, Eggs', 'Mix ingredients and bake at 350°F for 25 minutes', 'Dessert', false, true, false, true, 25.5);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Recipe 2', 'Rice, Beans, Tomatoes', 'Cook rice, add beans and tomatoes', 'Mexican', true, true, true, true, 20.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Recipe 3', 'Chickpeas, Tahini, Lemon', 'Blend chickpeas with tahini and lemon juice', 'Middle Eastern', true, true, true, true, 15.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Chicken Tikka Masala', 'Chicken, Spices, Yogurt', 'Marinate chicken and grill', 'Indian', false, false, false, true, 30.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Recipe 4', 'Lettuce, Carrots, Dressing', 'Chop vegetables and mix with dressing', 'Salad', true, true, true, true, 10.5);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Recipe 5', 'Pasta, Tomato Sauce, Basil', 'Cook pasta and add sauce', 'Italian', false, true, false, true, 20.0);


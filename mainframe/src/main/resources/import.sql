-- User Table
INSERT INTO user (email, password, logged_in_status) VALUES ('johndoe@example.com', 'password123', TRUE);
INSERT INTO user (email, password, logged_in_status) VALUES ('janedoe@example.com', 'password456', FALSE);

-- Profile Table
INSERT INTO Profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('johndoe@example.com', 'John', 'Doe', 'I love Italian and Mexican food.', 'Italian, Mexican', 'john_doe_profile.jpg');
INSERT INTO Profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('janesmith@example.com', 'Jane', 'Smith', 'Vegetarian foodie and baker.', 'Vegetarian, Desserts', 'jane_smith_profile.jpg');
INSERT INTO Profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('testuser@example.com', 'Test', 'User', 'Non Vegetarian foodie and connoisseur of vintage wine.', 'Non-Vegetarian, Drunk', 'test_user_profile.jpg');

-- Tag Table
INSERT INTO tag (name) VALUES ('Healthy Eating');
INSERT INTO tag (name) VALUES ('Vegan');
INSERT INTO tag (name) VALUES ('Keto');
INSERT INTO tag (name) VALUES ('Desserts');


-- Post Table
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user123', 'This is my first post!', 'https://example.com/media1.jpg', 10, 5, 1);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user456', 'Loving the weather today!', NULL, 25, 8, 2);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user789', 'Check out my new blog post!', 'https://example.com/media2.png', 15, 3, 4);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user101', 'Happy Monday, everyone!', NULL, 5, 1, 3);
INSERT INTO posts (user_id, content, media, likes_count, comments_count, tag_id) VALUES ('user202', 'Traveling to Japan tomorrow, super excited!', 'https://example.com/media3.jpg', 100, 50, 2);


-- Recipe Table
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Flour, Sugar, Eggs', 'Mix ingredients and bake at 350°F for 25 minutes', 'Dessert', false, true, false, true, 25.5);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Rice, Beans, Tomatoes', 'Cook rice, add beans and tomatoes', 'Mexican', true, true, true, true, 20.0);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Chickpeas, Tahini, Lemon', 'Blend chickpeas with tahini and lemon juice', 'Middle Eastern', true, true, true, true, 15.0);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Chicken, Spices, Yogurt', 'Marinate chicken and grill', 'Indian', false, false, false, true, 30.0);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Lettuce, Carrots, Dressing', 'Chop vegetables and mix with dressing', 'Salad', true, true, true, true, 10.5);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Pasta, Tomato Sauce, Basil', 'Cook pasta and add sauce', 'Italian', false, true, false, true, 20.0);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Tofu, Soy Sauce, Ginger', 'Stir-fry tofu with soy sauce and ginger', 'Asian', true, true, true, true, 15.5);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Beef, Potatoes, Carrots', 'Cook beef and add vegetables', 'American', false, false, true, true, 35.0);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Bread, Cheese, Butter', 'Grill cheese between slices of bread', 'Sandwich', false, true, false, false, 10.0);
INSERT INTO recipes (profile_id, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Quinoa, Black Beans, Corn', 'Cook quinoa and mix with beans and corn', 'Southwestern', true, true, true, true, 25.0);

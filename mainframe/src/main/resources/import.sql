-- User Table
INSERT INTO user (email, password, logged_in_status) VALUES ('johndoe@example.com', 'password123', TRUE);
INSERT INTO user (email, password, logged_in_status) VALUES ('janedoe@example.com', 'password456', FALSE);
INSERT INTO user (email, password, logged_in_status) VALUES ('sparkling.spaceship@example.com', 'password789', FALSE);
INSERT INTO user (email, password, logged_in_status) VALUES ('cosmic_cookie@fakemail.org', 'password111', FALSE);
INSERT INTO user (email, password, logged_in_status) VALUES ('gentle.giraffe@randommail.net', 'password222', FALSE);
INSERT INTO user (email, password, logged_in_status) VALUES ('whispering.willow@mailinator.com', 'password333', FALSE);

-- Profile Table
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('johndoe@example.com', 'John', 'Doe', 'I love Italian and Mexican food.', 'Italian, Mexican', 'john_doe_profile.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('janesmith@example.com', 'Jane', 'Smith', 'Vegetarian foodie and baker.', 'Vegetarian, Desserts', 'jane_smith_profile.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('testuser@example.com', 'Test', 'User', 'Non Vegetarian foodie and connoisseur of vintage wine.', 'Non-Vegetarian, Drunk', 'test_user_profile.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('gentle.giraffe@randommail.net', 'Gentle', 'Giraffe', 'Non Vegetarian foodie and connoisseur of vintage wine.', 'Non-Vegetarian, Drunk', 'pic1.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('whispering.willow@mailinator.com', 'Whispering', 'Willow', 'Non Vegetarian foodie and connoisseur of vintage wine.', 'Non-Vegetarian, Drunk', 'pic2.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('cosmic_cookie@fakemail.org', 'Cosmic', 'Cookie', 'Non Vegetarian foodie and connoisseur of vintage wine.', 'Non-Vegetarian, Drunk', 'pic3.jpg');
INSERT INTO profile (user_id, first_name, last_name, bio, food_preferences, profile_picture) VALUES ('sparkling.spaceship@example.com', 'Sparkling', 'Spaceship', 'Non Vegetarian foodie and connoisseur of vintage wine.', 'Non-Vegetarian, Drunk', 'pic4.jpg');


-- Tag Table
INSERT INTO tag (name) VALUES ('Healthy Eating');
INSERT INTO tag (name) VALUES ('Vegan');
INSERT INTO tag (name) VALUES ('Keto');
INSERT INTO tag (name) VALUES ('Desserts');


-- Post Table
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('sparkling.spaceship@example.com', 'Crispy edges, fluffy center—waffles are proof that breakfast is better with a little crunch', 'food1.jpg', 300, 55);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('sparkling.spaceship@example.com', 'Taking a bite of fresh basil feels like a tiny garden party in your mouth.', 'food5.jpg', 1002, 8);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('janedoe@example.com', 'If chocolate was a language, I’d be fluent by now!', 'food2.jpg', 350, 32);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('janedoe@example.com', 'Mango season: Nature’s way of reminding us that sunshine can be tasted', 'food3.jpg', 290, 1);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('janedoe@example.com', 'One spoonful of homemade soup is like a warm hug from your future self.', 'food4.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('janedoe@example.com', 'Sourdough bread: Because good things come to those who wait (and knead).', 'bread.jpg', 102, 5);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('johndoe@example.com', 'A ripe avocado is like a butter factory run by unicorns', 'one_pot.jpg', 25, 8);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('sparkling.spaceship@example.com', 'Crunchy veggies, zesty dressing—salads are basically your plate’s happy dance.', 'salad.jpg', 125, 3);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('sparkling.spaceship@example.com', 'Life is uncertain, but dessert always brings clarity.', 'food8.jpg', 348, 1);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('cosmic_cookie@fakemail.org', 'Taco Tuesday: Turning ordinary weeks into culinary carnivals since forever.', 'cake.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('cosmic_cookie@fakemail.org', 'A sprinkle of herbs can transform a dish from ‘meh’ to ‘mmmm.’', 'bacon.jpg', 10, 22);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('cosmic_cookie@fakemail.org', 'Fresh berries: little bursts of nature’s laughter on your tongue.', 'breakfast.jpg', 133, 221);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('johndoe@example.com', 'Dinner parties: Where old friendships get seasoned and new stories get served.', 'cook.jpg', 15, 13);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('johndoe@example.com', 'A sharp knife is the secret ingredient to stress-free cooking.', 'cookies.jpg', 300, 1);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('johndoe@example.com', 'Homemade pizza: Where creativity meets melted cheese harmony.', 'pizza.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('johndoe@example.com', 'Ice cream isn’t just a dessert; it’s an edible celebration.', 'lemon.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('gentle.giraffe@randommail.net', 'The sizzle of garlic hitting hot oil is the soundtrack of comfort cooking.', 'steak.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('gentle.giraffe@randommail.net', 'Cooking together: The original recipe for bonding beyond words.', 'banana.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('gentle.giraffe@randommail.net', 'Every kitchen experiment is just one delicious ‘oops’ away from genius.', 'chocolate.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('whispering.willow@mailinator.com', 'A handful of nuts and a drizzle of honey: Snacking just got upgraded to gourmet.', 'nuts.jpg', 100, 50);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('sparkling.spaceship@example.com', 'Crispy edges, fluffy center—waffles are proof that breakfast is better with a little crunch.', 'pancakes.jpg', 85, 20);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('cosmic_cookie@fakemail.org', 'Mango season: Nature’s way of reminding us that sunshine can be tasted.', 'dessert.jpg', 120, 35);
INSERT INTO posts (user_id, content, media, likes_count, comments_count) VALUES ('gentle.giraffe@randommail.net', 'The sizzle of garlic hitting hot oil is the soundtrack of comfort cooking.', 'garlic.jpg', 95, 10);




-- Recipe Table
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Simple Vanilla Sponge Cake', 'Gluten-free Flour, Sugar, Eggs', 'Whisk ingredients until smooth and bake at 350°F until golden (~25 min)', 'Dessert', false, true, false, true, 25.5);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Mexican Bean Pilaf', 'Rice, Beans, Fresh Tomatoes', 'Simmer rice until fluffy, then fold in beans and diced tomatoes for a hearty finish', 'Mexican', true, true, true, true, 20.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (4, 'Savory Chickpea Hummus', 'Chickpeas, Tahini, Lemon Juice', 'Blend chickpeas, tahini, and lemon juice until smooth and creamy', 'Middle Eastern', true, true, true, true, 15.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Chicken Tikka Masala', 'Chicken, Spices, Yogurt', 'Marinate chicken in yogurt and spices, grill, then simmer in a creamy sauce', 'Indian', false, false, false, true, 30.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Crisp Garden Salad', 'Lettuce, Carrots, Dressing', 'Chop fresh lettuce and carrots, toss with dressing', 'Salad', true, true, true, true, 10.5);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Tomato Basil Pasta', 'Gluten-free Pasta, Tomato Sauce, Fresh Basil', 'Cook pasta al dente, stir in warm tomato sauce and top with fresh basil leaves', 'Italian', false, true, false, true, 20.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Basic Desert', 'Flour, Sugar, Eggs', 'Mix ingredients and bake at 350°F for 25 minutes', 'Dessert', false, true, false, true, 25.5);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Salad', 'Lettuce, Carrots, Dressing', 'Chop vegetables and mix with dressing', 'Salad', true, true, true, true, 10.5);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (3, 'Pasta', 'Pasta, Tomato Sauce, Basil', 'Cook pasta and add sauce', 'Italian', false, true, false, true, 20.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (4, 'Spicy Lentil Soup', 'Lentils, Onions, Spices, Vegetable Broth', 'Simmer lentils with spices and onions in broth', 'Middle Eastern', true, true, true, true, 25.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (5, 'Quinoa & Avocado Salad', 'Quinoa, Avocado, Cherry Tomatoes, Lime Juice', 'Cook quinoa, dice avocado and tomatoes, toss with lime juice', 'Mexican', true, true, true, true, 15.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Mushroom Risotto', 'Arborio Rice, Mushrooms, White Wine, Parmesan', 'Sauté mushrooms, add rice and wine, simmer with broth, top with parmesan', 'Italian', false, true, false, false, 40.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (6, 'Sushi Rolls', 'Nori, Rice, Cucumber, Avocado', 'Roll rice and vegetables in nori sheets', 'Japanese', true, true, true, true, 30.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (7, 'Grilled Salmon with Asparagus', 'Salmon, Asparagus, Lemon, Olive Oil', 'Grill salmon and asparagus, season with lemon and oil', 'American', false, false, true, true, 20.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (2, 'Chickpea Curry', 'Chickpeas, Coconut Milk, Curry Powder, Spinach', 'Simmer chickpeas in coconut milk and spices, add spinach', 'Indian', true, true, true, true, 25.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (1, 'Greek Salad', 'Tomatoes, Cucumbers, Olives, Feta, Olive Oil', 'Chop vegetables, add olives and feta, drizzle with oil', 'Greek', false, true, false, true, 10.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (4, 'Beef Pho', 'Beef, Rice Noodles, Herbs, Broth', 'Simmer beef broth, cook noodles, add herbs', 'Vietnamese', false, false, true, true, 45.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (5, 'Baked Cod with Herbs', 'Cod, Rosemary, Thyme, Lemon', 'Season cod with herbs and lemon, bake until flaky', 'Mediterranean', false, false, true, true, 25.0);
INSERT INTO recipes (profile_id, name, ingredients, instructions, cusine_type, vegan, vegetarian, lactose_free, gluten_free, prep_time) VALUES (6, 'Fruit Salad', 'Berries, Melon, Mint, Lime Juice', 'Chop fruits, toss with mint and lime juice', 'International', true, true, true, true, 5.0);


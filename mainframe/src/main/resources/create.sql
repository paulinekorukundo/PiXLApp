-- PIXL databse
CREATE DATABASE pixl;
USE pixl;



-- User Table
CREATE TABLE user (
    -- user_id VARCHAR(36) PRIMARY KEY,
    -- username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    -- profile_picture TEXT,
    -- bio TEXT,
--    foodPreferences TEXT[],	Decide how to store this field (List of Strings, Map, etc?)
    logged_in_status BOOLEAN DEFAULT FALSE
);

-- AchievementBadge Table
CREATE TABLE AchievementBadge (
    badgeId VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    criteria TEXT
);

-- Following Table (for relationships between users)
CREATE TABLE Following (
    followingId VARCHAR(36) PRIMARY KEY,
    userId VARCHAR(36) REFERENCES User(userId) ON DELETE CASCADE,
    followerId VARCHAR(36) REFERENCES User(userId) ON DELETE CASCADE
);

-- Post Table
CREATE TABLE Post (
    postId VARCHAR(36) PRIMARY KEY,
    authorId VARCHAR(36) REFERENCES User(userId) ON DELETE CASCADE,
    content TEXT,
	-- media TEXT[],	Do we want to store a Link?
    likesCount INT DEFAULT 0,
    commentsCount INT DEFAULT 0
);

-- Tag Table
CREATE TABLE Tag (
    tagId VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT
);

-- PostTag Table (Join Table between Post and Tag)
CREATE TABLE PostTag (
    postId VARCHAR(36) REFERENCES Post(postId) ON DELETE CASCADE,
    tagId VARCHAR(36) REFERENCES Tag(tagId) ON DELETE CASCADE,
    PRIMARY KEY (postId, tagId)
);

-- Comment Table
CREATE TABLE Comment (
    commentId VARCHAR(36) PRIMARY KEY,
    authorId VARCHAR(36) REFERENCES User(userId) ON DELETE CASCADE,
    postId VARCHAR(36) REFERENCES Post(postId) ON DELETE CASCADE,
    content TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Saves Table (for tracking saved posts by users)
CREATE TABLE Saves (
    userId VARCHAR(36) REFERENCES User(userId) ON DELETE CASCADE,
    postId VARCHAR(36) REFERENCES Post(postId) ON DELETE CASCADE,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (userId, postId)
);

-- Likes Table (for tracking liked posts by users)
CREATE TABLE Likes (
    userId VARCHAR(36) REFERENCES User(userId) ON DELETE CASCADE,
    postId VARCHAR(36) REFERENCES Post(postId) ON DELETE CASCADE,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (userId, postId)
);

-- Recipe Table
CREATE TABLE Recipe (
    recipeId VARCHAR(36) PRIMARY KEY,
    ingredients TEXT,
    instructions TEXT,
    cuisineType VARCHAR(50),
    -- dietaryRestrictions TEXT[], Decide how to store this field (List of Strings, Map, etc?)
    prepTime INT
);

-- 사용자 테이블
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 사용자 즐겨찾기 테이블
CREATE TABLE user_favorites (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    folder VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(user_id, movie_id)
);

-- 사용자 시청 기록 테이블
CREATE TABLE user_view_history (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    viewed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    view_duration INTEGER
);

-- 영화 추천 테이블
CREATE TABLE movie_recommendations (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    movie_id BIGINT NOT NULL,
    score DECIMAL(3,2) NOT NULL,
    reason TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 인덱스 생성
CREATE INDEX idx_user_favorites_user_id ON user_favorites(user_id);
CREATE INDEX idx_user_favorites_movie_id ON user_favorites(movie_id);
CREATE INDEX idx_user_view_history_user_id ON user_view_history(user_id);
CREATE INDEX idx_user_view_history_movie_id ON user_view_history(movie_id);
CREATE INDEX idx_movie_recommendations_user_id ON movie_recommendations(user_id);
CREATE INDEX idx_movie_recommendations_movie_id ON movie_recommendations(movie_id); 
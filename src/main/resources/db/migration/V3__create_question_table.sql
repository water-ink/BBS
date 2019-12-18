CREATE TABLE `question`(
    Id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    title NVARCHAR(20) NOT NULL ,
    description TEXT,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator INT,
    comment_count INT DEFAULT 0,
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    tag VARCHAR(256)
)ENGINE = InnoDB DEFAULT CHAR SET utf8;
CREATE DATABASE IF NOT EXISTS playground;
USE playground;

-- playground.main definition
CREATE TABLE IF NOT EXISTS `main` (
                        `seq` bigint NOT NULL AUTO_INCREMENT,
                        `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `content` varchar(1000) COLLATE utf8mb4_unicode_ci NOT NULL,
                        `created_at` datetime NOT NULL,
                        `updated_at` datetime DEFAULT NULL,
                        PRIMARY KEY (`seq`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- playground.board definition
CREATE TABLE IF NOT EXISTS `board` (
                         `seq` bigint NOT NULL AUTO_INCREMENT COMMENT '게시글 시퀀스',
                         `title` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '게시글 제목',
                         `content` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '게시글 내용',
                         `author` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '게시글 작성자',
                         `created_at` datetime NOT NULL COMMENT '생성일자',
                         `updated_at` datetime DEFAULT NULL COMMENT '수정일자',
                         PRIMARY KEY (`seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
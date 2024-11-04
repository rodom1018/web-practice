package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("테스트_결과에_보여_줄_이름")
    void findByArticleId() {

        {
            Long articleId = 4L;

            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = new Article(4L, "enjoying1016@naver.com", "1234",true);

            Comment a = new Comment(1L, article, "Park", "굿 윌 헌팅");
            Comment b = new Comment(2L, article, "Kim", "아이 엠 샘");
            Comment c = new Comment(3L, article, "Choi", "쇼생크 탈출");
            List<Comment> expected = Arrays.asList(a,b,c);

        }

        {
            Long articleId = 1L;

            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = new Article(1L, "rodom1018@gmail.com", "1234", true);
            List<Comment> expected = Arrays.asList();

            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글이 없음");
        }

    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {

        {
            String nickname = "Park";
            List<Comment> comments = commentRepository.findByNickname(nickname);

            Comment a = new Comment(1L, new Article(4L, "enjoying1016@naver.com", "1234", true), nickname, "굿 윌 헌팅");
            Comment b = new Comment(4L, new Article(5L, "enjoying1014@naver.com", "1234", true), nickname, "치킨");
            Comment c = new Comment(7L, new Article(6L, "enjoying1012@naver.com", "1234", true), nickname, "조깅");

            List<Comment> expected = Arrays.asList(a,b,c);

            assertEquals(expected.toString(), comments.toString());


        }

    }
}
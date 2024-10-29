package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        //1. 예상 데이터
        Article a = new Article(1L, "rodom1018@gmail.com", "1234", true);
        Article b = new Article(2L, "ske04186@gmail.com", "1234", true);
        Article c = new Article(3L, "enjoying1018@cau.ac.kr", "1234", true);
        Article d = new Article(4L, "enjoying1018@naver.com", "1234", true);

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c,d));

        //2. 실제 데이터
        List<Article> articles = articleService.index();

        //3. 비교 및 검증
        System.out.println(expected.toString());
        assertEquals(expected.toString(), articles.toString());

    }

    @Test
    void show_성공_존재하는_id_입력() {
        //1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "rodom1018@gmail.com","1234", true);
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        //assertEquals(expected.toString(), article.toString());
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        //1. 예상 데이터
        Long id = -1L;
        Article expected = null;
        //2. 실제 데이터
        Article article = articleService.show(id);
        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_성공_title과_content만_있는_dto_입력(){
        //1. 예상 데이터
        String email = "sample@naver.com";
        String password = "1234";
        boolean is_checked = true;

        Article expected = new Article(5L, email, password, is_checked);

        //2. 실제 데이터
        ArticleForm dto = new ArticleForm(null, email, password, is_checked);
        Article article = articleService.create(dto);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패_title과_content만_있는_dto_입력(){
        //1. 예상 데이터
        Long id = 5L;
        String email = "abc@naver.com";
        String password = "1234";
        boolean is_checked = true;

        Article expected = null;

        //2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, email, password, is_checked);
        Article article = articleService.create(dto);

        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title_content가_있는_dto_입력(){
        //1. 예상 데이터
        Long id = 1L;
        String email = "random@sample.com";
        String password = "0000";
        boolean is_checked = true;

        Article expected = new Article(id, email, password, is_checked);

        //2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, email, password, is_checked);
        Article article = articleService.update(id, dto);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_성공_존재하는_id와_title만_있는_dto_입력(){
        //1. 예상 데이터
        Long id = 1L;
        String email = "random@sample.com";

        Article expected = new Article(id, email, "1234", true);

        //2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, email, null, null);
        Article article = articleService.update(id, dto);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    @Transactional
    void update_실패_존재하지_않는_id의_dto_입력(){
        //1. 예상 데이터
        Long id = 5L;
        String email = "hacking@sample.com";
        String password = "0000";
        boolean is_checked = true;

        Article expected = null;

        //2. 실제 데이터
        ArticleForm dto = new ArticleForm(id, email, password, is_checked);
        Article article = articleService.update(id, dto);

        //3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_성공_존재하는_id_입력(){
        //1. 예상 데이터
        Long id = 1L;

        Article expected = new Article(id, "rodom1018@gmail.com", "1234", true);

        //2. 실제 데이터
        Article article = articleService.delete(id);

        //3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_실패_존재하지_않는_id_입력(){
        //1. 예상 데이터
        Long id = 6L;
        Article expected = null;

        //2. 실제 데이터
        Article article = articleService.delete(id);

        //3. 비교 및 검증
        assertEquals(expected, article);
    }




}
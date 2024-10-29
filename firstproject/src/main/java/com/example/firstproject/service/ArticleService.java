package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    
    @Autowired
    private ArticleRepository articleRepository;

    //get
    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    //post
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();

        if (article.getId() != null){
            return null;
        }

        return articleRepository.save(article);
    }

    //patch
    public Article update(@PathVariable Long id, @RequestBody ArticleForm dto){

        Article article = dto.toEntity();
        log.info("id : {} , article : {}", id, article.toString());

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    //delete
    public Article delete(@PathVariable Long id){

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null){
            return null;
        }

        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        articleRepository.findById(-1L)
                .orElseThrow(() -> new IllegalArgumentException("조회 실패! "));

        return articleList;
    }
}

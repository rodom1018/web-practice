package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {

    private Long id;
    private String email;
    private String password;
    private Boolean is_checked;

    public Article toEntity(){
        return new Article(id, email, password, is_checked);
    }
}

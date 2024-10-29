package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Boolean is_checked;

    public void patch(Article article){
        if (article.email != null){
            this.email = article.email;
        }
        if (article.password != null){
            this.password = article.password;
        }
        if (article.is_checked != null){
            this.is_checked = article.is_checked;
        }
    }
}

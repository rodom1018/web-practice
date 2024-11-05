package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id; // 대표키

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article; // 해당 댓글의 부모 게시글

    @Column
    private String nickname; // 댓글을 단 사람

    @Column
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Article article) {

        if (dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패, 댓글의 id 가 없어야 합니다.");
        }

        if (dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패, 게시글의 id 가 잘못됐습니다. ");
        }

        return new Comment(dto.getId(), article, dto.getNickname(), dto.getBody());
    }

    public void patch(CommentDto dto) {
        if (this.id != dto.getId()){
            throw new IllegalArgumentException("댓글 수정 실패, 잘못된 id가 입력됐습니다.");
        }

        if(dto.getNickname() != null){
            this.nickname = dto.getNickname();
        }

        if(dto.getBody() != null){
            this.body = dto.getBody();
        }
    }
}

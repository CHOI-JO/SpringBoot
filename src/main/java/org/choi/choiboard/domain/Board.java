package org.choi.choiboard.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // DB 테이블 객체
@Getter
@Builder // setter역할
@AllArgsConstructor // 모든 필드를 객체로 받아 생성자 생성
@NoArgsConstructor // 기본생성자
@ToString //객체 주소가 아닌 값을 출력
public class Board extends BaseEntity{

    @Id // PK 선언 (notnull, unique, indexing)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 번호 생성
    private Long bno ;  // 게시물 번호

    @Column(length = 500, nullable = false) // 최대 문자 길이: 500, null을 허용하지 않음
    private String title;  //제목

    @Column(length = 2000, nullable = false)
    private String content; //내용 

    @Column(length = 50, nullable = false)
    private String writer; // 작성자

    public void change(String title, String content) {
        this.title=title;
        this.content=content;
    }
}

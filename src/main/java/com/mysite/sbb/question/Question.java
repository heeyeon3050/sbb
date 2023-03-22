package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    // OneToMany 자바세상에서의 편의를 위해서 필드 생성
    // OneToMany는 실제 DB 테이블에 칼럼이 생성되지 않는다.
    // DB는 배열이나 리스트를 저장할 수 없다.
    // 만들어도 되고 안만들어도 된다.
    // 만들면 해당 객체에서 관련된 답변들을 찾을 때 편하다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList = new ArrayList<>(); //직접 객체 초기화

    public void addAnswer(Answer a) {
        a.setQuestion(this);
        answerList.add(a);
    }
}

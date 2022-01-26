package com.rest.api.model;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id //primaryKey임을 알립니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // pk생성 전략을 DB에 위임한다는 의미입니다. mysql로 보면 pk필드를 auto_increasement로 설정한것과 같다.
    private long msrl;

    @Column (nullable = false, unique = true, length = 30) //uid column을 명시합니다. 필수이며 유니크한 필드 길이는 30
    private String uid;

    @Column(nullable = false, length = 100) // name column을 명시합니다. 필수이며 길이는 100이다.
    private String name;
}

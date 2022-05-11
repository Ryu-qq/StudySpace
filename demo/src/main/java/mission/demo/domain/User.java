package mission.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@Table(name ="USERS")
@Entity
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue
    private Long id;

    private String nickname;

    private String account_id;

    private boolean quit;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Likes> likes = new ArrayList<>();

    @Builder
    public User(String nickname, String account_id, boolean quit, AccountType account_type) {
        this.nickname = nickname;
        this.account_id = account_id;
        this.quit = quit;
        this.account_type = account_type;
    }

    public User(String nickname, AccountType account_type){
        this.nickname =nickname;
        this.account_type =account_type;
    }

    public void delete(boolean quit){
        this.quit = quit;
    }



}

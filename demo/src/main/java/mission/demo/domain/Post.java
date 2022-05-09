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
@Entity
public class Post extends Time {

    @Id
    @Column(name = "post_id")
    @GeneratedValue
    private Long id;

    @Column(name ="post_title",length = 500, nullable = false)
    private String title;

    @Column(name ="post_contents", columnDefinition = "TEXT", nullable = false)
    private String contents;

    private String useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Likes> likes = new ArrayList<>();

    @Builder
    public Post(String title, String contents, String useYn, User user) {
        this.title = title;
        this.contents = contents;
        this.useYn = useYn;
        this.user = user;
    }

    public void update(String title, String contents) {
        this.title =title;
        this.contents = contents;
    }

    public void delete(String useYn){
        this.useYn = useYn;
    }

}

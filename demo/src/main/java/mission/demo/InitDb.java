package mission.demo;

import lombok.RequiredArgsConstructor;
import mission.demo.domain.AccountType;
import mission.demo.domain.Likes;
import mission.demo.domain.Post;
import mission.demo.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
    static ArrayList<User> userList = new ArrayList<>();
    static ArrayList<Post> postList = new ArrayList<>();
    static ArrayList<Likes> likeList = new ArrayList<>();

    @PostConstruct
    public void init(){
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        public void dbInit(){


            User user1 = createUser("ryu", "fbtkdals2", AccountType.LESSEE, Boolean.FALSE);
            User user2 = createUser("sang", "fbtkdals3", AccountType.LESSEE, Boolean.FALSE);
            User user3 = createUser("won", "fbtkdals4", AccountType.LESSEE, Boolean.TRUE);
            User lessor1 = createUser("kim", "kim123", AccountType.LESSOR, Boolean.FALSE);
            User realtor1 = createUser("park", "park123", AccountType.REALTOR, Boolean.FALSE);


            Post post1 = createPost("용산구 월세 5000/50", "채광 좋아요","Y",realtor1);
            Post post2 = createPost("마포구 월세 3000/70", "주변 환경이 좋아요","Y",realtor1);
            Post post3 = createPost("용산구 월세 8000/100", "집 넓고 좋아요", "Y", realtor1);

            Post post4 = createPost("집 올려요", "용산구 월세 5000/50에 올려요","Y", lessor1);
            Post post5 = createPost("집 올려요", "마포구 월세 3000/70에 올려요","Y", lessor1);
            Post post6 = createPost("집 올려요", "용산구 월세 8000/100에 올려요","Y", lessor1);

            Post post7 = createPost("집 문의해요", "용산구 월세 5000/50 집 볼 수 있나요?","Y", user1);
            Post post8 = createPost("집 문의해요", "마포구 월세 3000/70 집 볼 수 있나요?","Y", user2);
            Post post9 = createPost("집 문의해요", "용산구 월세 8000/100 집 볼 수 있나요?","Y", user1);
            Post post10 = createPost("집 문의해요", "용산구 월세 8000/100 집 볼 수 있나요?","Y", user3);


            createLikes(post1,user1);
            createLikes(post1,user2);
            createLikes(post1,user3);
            createLikes(post3,user1);
            createLikes(post2,user2);
            createLikes(post2,user2);

            createLikes(post4,realtor1);
            createLikes(post5,realtor1);
            createLikes(post6,realtor1);

            createLikes(post7,lessor1);
            createLikes(post8,lessor1);
            createLikes(post9,lessor1);
            createLikes(post10,lessor1);


            for(User u : userList){
                em.persist(u);
            }

            for(Post p : postList){
                em.persist(p);
            }

            for(Likes l : likeList){
                em.persist(l);
            }

        }

        private User createUser(String nickname, String account_id, AccountType account_type, Boolean quit) {
            User user = User.builder()
                    .nickname(nickname)
                    .account_id(account_id)
                    .account_type(account_type)
                    .quit(quit)
                    .build();

            userList.add(user);

            return user;
        }

        private Post createPost(String title, String contents,String useYn, User user){
            Post post = Post.builder()
                    .title(title)
                    .contents(contents)
                    .useYn(useYn)
                    .user(user)
                    .build();

            postList.add(post);

            return post;
        }

        private Likes createLikes(Post post, User user){

            Likes likes = Likes.builder()
                    .post(post)
                    .user(user)
                    .build();

            likeList.add(likes);

            return likes;
        }
    }



}



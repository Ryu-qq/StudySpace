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
public class Member {

    @Id
    @GeneratedValue
    @Column(name ="member_id")
    private Long id;

    private String name;

    private String account_id;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    @Embedded
    private Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    public Member(String account_id, AccountType account_type) {
        this.account_id = account_id;
        this.account_type = account_type;
    }
    @Builder
    public Member(String name, String account_id, AccountType account_type, Address address) {
        this.name = name;
        this.account_id = account_id;
        this.account_type = account_type;
        this.address = address;
    }
}

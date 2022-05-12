package mission.demo.domain.item;

import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@DiscriminatorValue("L")
public class Lp extends Item{

    private String artist;
}

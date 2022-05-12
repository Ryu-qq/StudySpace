package mission.demo.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    /**
     * 재고 증가
     * @param quantity 재고 수량
     */

    public void addStock(int quantity){
        this.stockQuantity  += quantity;
    }

    /**
     * 재고 감소
     * @param quantity 재고 수량
     * @throws Exception 재고가 없는데 주문했을때 발생
     */

    public void removeStock(int quantity) throws Exception {
        int restStock = this.stockQuantity - quantity;
        if(restStock <0){
            throw new IllegalStateException("상품 재고가 없습니다!");
        }
        this.stockQuantity = restStock;
    }
}

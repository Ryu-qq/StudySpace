package mission.demo.service;


import lombok.RequiredArgsConstructor;
import mission.demo.domain.item.Item;
import mission.demo.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    /**
     * 아이템 저장
     * @param item 저장할 아이템
     */
    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    /**
     * 전체 아이템 조회
     * @return 아이템 리스트
     */
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    /**
     * 단건 아이템 조회
     * @param itemId 조회할 아이템 아이디
     * @return
     */
    public Item findItem(Long itemId){
        return itemRepository.findById(itemId).get();
    }
}

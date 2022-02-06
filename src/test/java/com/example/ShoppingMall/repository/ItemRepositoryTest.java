package com.example.ShoppingMall.repository;

import com.example.ShoppingMall.constant.ItemSellStatus;
import com.example.ShoppingMall.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yml")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    public void createItemList(){
        for (int i = 1; i <=10 ; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(100000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item saveItem = itemRepository.save(item);
        }
    }

    @AfterEach
    public void deleteItemList(){
        itemRepository.deleteAll();
    }


    @DisplayName("상품 저장 테스트")
    @Test
    public void test(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(100000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item saveItem = itemRepository.save(item);
        System.out.println(saveItem.toString());
    }

    @DisplayName("상품명 조회 테스트")
    @Test
    public void findByItemList(){
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        System.out.println("check");
        then(itemList).hasSize(1);
    }


    @DisplayName("상품명, 상품상세설명 or 테스트")
    @Test
    public void findByItemNmOrItemDetail(){
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 설명5");
        then(itemList).hasSize(2);
    }

    @DisplayName("가격 LessThan 테스트")
    @Test
    public void findByPriceLessThen(){
        List<Item> itemList = itemRepository.findByPriceLessThan(100005);
        then(itemList).hasSize(4);
    }

    @DisplayName("가격 내림차순 조회 테스트")
    @Test
    public void findByPriceLessThanOrderByPriceDesc(){
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(100005);
        then(itemList.get(0).getItemNm()).isEqualTo("테스트 상품4");
    }

    @DisplayName("@Query를 이용한 상품 조회 테스트")
    @Test
    public void findByItemDetail(){
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세");
        then(itemList).hasSize(10);
    }

    @DisplayName("nativeQuery 속성을 이용한 상품 조회 테스트")
    @Test
    public void findByItemDetailByNative(){
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 상품 상세");
        then(itemList).hasSize(10);
    }


}
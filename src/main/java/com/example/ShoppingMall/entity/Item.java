package com.example.ShoppingMall.entity;


import com.example.ShoppingMall.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name="item")
public class Item{

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String itemNm;

    @Column(name="price", nullable = false)
    private int price;

    @Column(nullable = false)
    private int stockNumber;

    //TODO: CLOB -> 사이즈가 큰 데이터를 외부 파일로 저장하기 위한 데이터 타입
    //TODO: BLOB -> CLOB와 비슷하나 바이너리 데이터를 저장하기 위한 데이터 타입
    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;


    private LocalDateTime regTime;
    private LocalDateTime updateTime;


}

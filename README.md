# ajou-dirtycode
Dirty code for refactoring

<br>

## Project Summary
본 프로젝트는 IntelliJ + Maven을 사용하여 Java 코드를 쉽게 리팩토링할 수 있는 방법에 대한 프로젝트이다. 따로 브랜치를 만들어 repository를 관리한 목적은 dirt directory 내의 코드들의 테스트 코드를 만들고 리팩토링을 진행하기 위함이다.

<br>

## Code Analysis

### DirtySample class 

-  `item[] items` 를 변수로 가짐
- `updateQuality()` 메소드를 가짐 

### item class

- `String name`
- `int sellIn`
- `int quality`
- `toString()`

### updateQuality() 분석 (rough ver.)

  - # `items` 리스트의 처음부터 끝까지 모든 `item` 에 대해
    - `item` 의 이름이 `Aged Brie` , `Backstage passes to a TAFKAL80ETC concert` 이 아니고
        - `item` 의 `quality` 가 0보다 크고,
            - 이름이 `Sulfuras, Hand of Ragnaros` 가 아니면,
                - `item` 의 `quality` 를 1 감소시킴.
    - `item`의 이름이  `Aged Brie` 이거나 `Backstage passes to a TAFKAL80ETC concert`이고
        - `item`의 `quality`가 50보다 작으면
            - `quality` 를 1 증가시키고
            - 만약 이름이 `Backstage passes to a TAFKAL80ETC concert` 이고
                - `sellIn` 이 11보다 작으면
                    - `quality` 가 50보다 작을 때
                        - `quality` 를 1 증가시킴
                - `sellIn` 이 6보다 작으면
                    - `quality` 가 50보다 작을 때
                        - `quality` 를 1 증가시킴        
        
    - 만약 `item` 의 이름이 `Sulfuras, Hand of Ragnaros`이 아니면
        - `item` 의 `sellIn` 을 1 감소시킴            
    
    - 만약 `sellIn` 이 0보다 작다면
        - 이름이 `Aged Brie`가 아니면
            - 이름이 `Backstage passes to a TAFKAL80ETC concert`가 아니면
                - `item` 의 `quality` 가 0보다 크면
                    - 이름이 `Sulfuras, Hand of Ragnaros`가 아니면
                        - `quality` 를 1 감소시킴
            - 이름이 `Backstage passes to a TAFKAL80ETC concert`이면
                - `quality` 는 0이다
        - 이름이 `Aged Brie`이면
            - `quality` 가 50보다 작을 때
                - `quality` 를 1 증가시킨다. 
                

### updateQuality() 분석 (simple ver.)

- 이름이 3가지 case가 아닌 경우
     - `sellIn` 이 0보다 큰 경우
          - `quality` 가 0보다 큰 경우
              - `quality` 를 1 감소
          - `quality` 가 0이하인 경우
              - `quality` 는 그대로
     - `sellIn` 이 0이하인 경우
          - `quality` 가 2이상인 경우
              - `quality` 를 2 감소
          - `quality` 가 1인 경우
              - `quality` = 0
          - `quality` 가 0이하인 경우
              - `quality`는 그대로
     - `sellIn` 을 1 감소
- 이름이 `Aged Brie`인 경우
     - `sellIn` 이 0보다 큰 경우
          - `quality` 가 50이상인 경우
              - `quality` 는 그대로
          - `quality` 가 50보다 작은 경우
              - `quality` 는 1 증가
     - `sellIn` 이 0이하인 경우
          - `quality` 가 50이상인 경우
              - `quality` 는 그대로
          - `quality` 가 49인 경우
              - `quality`  = 50
          - `quality` 가 49보다 작은 경우 
              - `quality` 를 2 증가
     - `sellIn` 을 1 감소
- 이름이 `Backstage passes to a TAFKAL80ETC concert`인 경우
     - `sellIn` 이 11이상인 경우
          - `quality` 가 50이상인 경우
              - `quality` 는 그대로
          - `quality` 가 50보다 작은 경우
              - `quality` 를 1 증가
     - `sellIn` 이 6이상 이고 11보다 작은 경우
          - `quality` 가 50이상인 경우
              - `quality` 는 그대로
          - `quality` 가 49인 경우 
              - `quality` = 50
          - `quality` 가 49보다 작은 경우
              - `quality` 를 2 증가
     - `sellIn` 이 6보다 작고 0보다 큰 경우
          - `quality` 가 50이상인 경우
              - `quality` 는 그대로
          - `quality` 가 48, 49인 경우
              - `quality`  = 50
          - `quality` 가 48보다 작은 경우
              - `quality` 를 3 증가
     - `sellIn` 이 0이하인 경우 
          - `quality`  = 0 
     - `sellIn` 을 1 감소

- 이름이 `Sulfuras, Hand of Ragnaros`인 경우 
     - 아무것도 실행하지 않음

<br>

## Refactoring Methodology
- 먼저, Item class에 `lombok.data`와 `lombok.builder` dependency를 추가하였다.
- 위의 DirtySample Code 분석에서 서술한대로 각 경우에 대한 모든 테스트 케이스들을 작성하였다.
- 중복되는 코드들을 `checkQualityUnderFiftyAndAddSome`와 같은 method로 delegate하였다.
- `items[i].sellIn < 0`인 케이스에 대한 코드를 빼내 다른 method에 extract하였다.
- Item 이름이 'Sulfuras'일 때와 같이 특정한 경우일때 진행되는 코드들을 method로 extract하였다.
- If문 내에서 특정한 조건에 대한 코드를 bool 타입을 리턴하는 method로 extract하였다.
- 조건문 안에 조건문이 있는 것을 방지하기 위해, 일부 로직의 순서를 바꿔주었다. 주기적으로 test Code를 통해 확인함으로써 test가 깨지는지 주기적으로 확인하였다.
- 일부 수(50, 1, ...) 등등을 따로 상수로 만들어주어 가독성을 높였다.
- Extract한 메소드들의 이름을 최대한 상세하게 적어 가독성을 높였다.
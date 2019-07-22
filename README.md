# ajou-dirtycode forked by 6조
Dirty code project for code refactoring.

<br>

## Project Summary
본 프로젝트는 IntelliJ + Maven을 사용하여 Java 코드를 쉽게 리팩토링할 수 있는 방법에 대한 프로젝트이다.
따로 브랜치를 만들어 repository를 관리한 목적은 dirt directory 내의 코드들의 테스트 코드를 만들고 리팩토링을
진행하기 위함이다. 

<br>

## DirtySample.java Code Analysis
1. `Aged Brie`, `Backstage passes to a TAFKAL80ETC concert`, `Sulfuras, Hand of Ragnaros` 가 아니고, quality가 0보다 크면 quality 1 제거. sellin 1 감소 + Sellin이 0보다 작으면 quality 1 추가 제거.
2. `Aged Brie`이고, quality가 50보다 작으면, quality 1 증가, sellin 1 제거. + Sellin이 0보다 작으면, quality 1 추가 증가. (quality는 최대 50)
3. `Backstage passes to a TAFKAL80ETC concert`이고, quality가 50보다 작으면 quality 1 증가, sellin이 11보다 작으면 quality 추가로 1 증가,  6보다 작으면 추가로 1 증가, sellin 1 감소 + sellin이 0보다 작으면 quality = 0
4. `Sulfuras, Hand of Ragnaros`이면, 이무것도 수행하지 않는다.

## Refactoring Methodology
- 먼저, Item class에 `lombok.data`와 `lombok.builder` dependency를 추가하였다.
- 위의 DirtySample Code 분석에서 서술한대로 각 경우에 대한 모든 테스트 케이스들을 작성하였다.
- Item Class에 대한 간단한 테스트를 작성하였다.
- 중복되는 코드들을 `checkQualityUnderFiftyAndAddSome`와 같은 method로 delegate하였다.
- `items[i].sellIn < 0`인 케이스에 대한 코드를 빼내 다른 method에 extract하였다.
- Item 이름이 'Sulfuras'일 때와 같이 특정한 경우일때 진행되는 코드들을 method로 extract하였다.
- If문 내에서 특정한 조건에 대한 코드를 bool 타입을 리턴하는 method로 extract하였다.
- 조건문 안에 조건문이 있는 것을 방지하기 위해, 일부 로직의 순서를 바꿔주었다. 주기적으로 test Code를 통해 확인함으로써 test가 깨지는지 주기적으로 확인하였다.
- 일부 수(50, 1, ...) 등등을 따로 상수로 만들어주어 가독성을 높였다.
- Extract한 메소드들의 이름을 최대한 상세하게 적어 가독성을 높였다.
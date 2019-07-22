package ac.kr.ajou.dirt;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

//    1. "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros" 가 아니고, quality가 0보다 크면 quality 1 제거. sellin 1 감소 + Sellin이 0보다 작으면 quality 1 추가 제거.
//    2. "Aged Brie"이고, quality가 50보다 작으면, quality 1 증가, sellin 1 제거. + Sellin이 0보다 작으면, quality 1 추가 증가. (quality는 최대 50)
//    3. "Backstage passes to a TAFKAL80ETC concert"이고, quality가 50보다 작으면 quality 1 증가, sellin이 11보다 작으면 quality 추가로 1 증가,  6보다 작으면 추가로 1 증가, sellin 1 감소 + sellin이 0보다 작으면 quality = 0
//    4. "Sulfuras, Hand of Ragnaros"이면, 이무것도 수행하지 않는다.
//    Quality 범위는 0 ~ 50
    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            if (isNotAgedBrieOrBackstage(items[i])) {
                doIfItemNameisNotThreeDefaultAndQualityIsOverZero(items[i]);
            } else {
                doIfNameisAgedBrieOrBackStage(items[i]);
            }
            isItemNameSulfurasThenMinusSellinOne(items[i]);
            if (items[i].sellIn < 0) {
                doIfSellinisUnderZero(items[i]);
            }
        }
    }

    private void doIfItemNameisNotThreeDefaultAndQualityIsOverZero(Item item) {
        if (item.quality > 0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
    }

    private void doIfNameisAgedBrieOrBackStage(Item item) {
        if (isItemNameBackstageAndSellinUnderWhat(item, 6)) {
            checkQualityUnderFiftyAndAddSome(item, 3);
            return;
        } else if (isItemNameBackstageAndSellinUnderWhat(item, 11)) {
            checkQualityUnderFiftyAndAddSome(item, 2);
            return;
        }
        checkQualityUnderFiftyAndAddSome(item, 1);
    }

    private boolean isItemNameBackstageAndSellinUnderWhat(Item item, int threshold) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.sellIn < threshold;
    }

    private void doIfSellinisUnderZero(Item item) {
        if (item.name.equals("Aged Brie")) {
            checkQualityUnderFiftyAndAddSome(item, 1);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else if (!item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void isItemNameSulfurasThenMinusSellinOne(Item item) {
        if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private boolean isNotAgedBrieOrBackstage(Item item) {
        return !item.name.equals("Aged Brie")
                && !item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private void checkQualityUnderFiftyAndAddSome(Item item, int addWhat) {
        if (checkIfQualityIsUnderPreDefinedMax(item)) {
            item.quality = item.quality + addWhat;
        }
    }

    private boolean checkIfQualityIsUnderPreDefinedMax(Item item) {
        return item.quality < 50;
    }
}
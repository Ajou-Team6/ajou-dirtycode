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
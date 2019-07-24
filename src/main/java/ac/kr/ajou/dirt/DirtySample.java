package ac.kr.ajou.dirt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class DirtySample {
    Item[] items;

    public DirtySample(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            doIfNameIsAgedBrie(item);
            doIfNameIsBackstage(item);
            doIfNameIsNot3Cases(item);
        }
    }

    private void doIfNameIsNot3Cases(Item item) {
        if (!item.isNamed3Cases()) {
            doIfQualityIsPositive(item);
            reduceSellInOne(item);
            if(item.sellIn<0) {
                doIfSellInIsNegative(item);
            }
        }
    }

    private void doIfNameIsBackstage(Item item) {
        if (item.isNamed("Backstage passes to a TAFKAL80ETC concert")) {
            doIfNameIsBackstageOrAged(item);
            reduceSellInOne(item);
            if(item.sellIn<0) {
                doIfSellInIsNegative(item);
            }
        }
    }

    private void doIfNameIsAgedBrie(Item item) {
        if (item.isNamed("Aged Brie")) {
            doIfNameIsBackstageOrAged(item);
            reduceSellInOne(item);
            if(item.sellIn<0) {
                doIfSellInIsNegative(item);
            }
        }
    }

    private void doIfSellInIsNegative(Item item) {
        if (item.name.equals("Aged Brie")) {
            checkQualityUnderFiftyAndAddSome(item, 1);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else if (!item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void reduceSellInOne(Item item) {
        if(!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn=item.sellIn-1;
        }
    }

    private void doIfNameIsBackstageOrAged(Item item) {
        if (isItemNameBackstageAndSellinIsUnderWhat(item, 6)) {
            checkQualityUnderFiftyAndAddSome(item, 3);
        } else if (isItemNameBackstageAndSellinIsUnderWhat(item, 11)) {
            checkQualityUnderFiftyAndAddSome(item, 2);
        } else {
            checkQualityUnderFiftyAndAddSome(item, 1);
        }
    }

    private void checkQualityUnderFiftyAndAddSome(Item item, int addWhat) {
        if (item.quality < 50) {
            item.quality = item.quality + addWhat;
        }
    }

    private boolean isItemNameBackstageAndSellinIsUnderWhat(Item item, int threshold) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.sellIn < threshold;
    }

    private void doIfQualityIsPositive(Item item) {
        if(item.quality>0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
    }
}


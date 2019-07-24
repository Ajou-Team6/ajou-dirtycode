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
            if (IfNameIsBackStageOrAged(item)) {
                DoIfNameIsBackstageOrAged(item);
            } else DoIfQualityIsPositiveAndNameIsNotSulfuras(item);
            ReduceSellInOneIfNameIsNotSulfuras(item);
            if(item.sellIn<0) {
                DoIfSellInIsNegative(item);
            }
        }
    }

    private boolean IfNameIsBackStageOrAged(Item item){
        return (item.name.equals("Aged Brie") ||
                item.name.equals("Backstage passes to a TAFKAL80ETC concert"));
    }


    private void DoIfSellInIsNegative(Item item) {
        if (item.name.equals("Aged Brie")) {
            checkQualityUnderFiftyAndAddSome(item, 1);
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else if (!item.name.equals("Sulfuras, Hand of Ragnaros") && item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

    private void ReduceSellInOneIfNameIsNotSulfuras(Item item) {
        if(!item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.sellIn=item.sellIn-1;
        }
    }

    private void DoIfNameIsBackstageOrAged(Item item) {
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

    private void DoIfQualityIsPositiveAndNameIsNotSulfuras(Item item) {
        if(item.quality>0 && !item.name.equals("Sulfuras, Hand of Ragnaros")) {
            item.quality = item.quality - 1;
        }
    }
}
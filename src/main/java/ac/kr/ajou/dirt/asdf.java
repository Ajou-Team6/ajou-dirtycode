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
            DoIfNameIsAged(item);
            DoIfNameIsBackStage(item);
            DoIfNameIsNotThoseThree(item);
        }
    }

    private void DoIfNameIsAged(Item item){
        if(item.name.equals("Aged Brie")){
            increaseQualityOneWhenQualityUnder50(item);
            item.sellIn--;
            DoIfSellInIsNegative(item);
        }
    }

    //sulfuras인지 체크 -> 맞으면 아무것도 안해
    //체크해서 맞지 않으면 ->
    private void DoIfNameIsBackStage(Item item){
        if(item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
            increaseQualityOneWhenQualityUnder50(item);
            if(item.sellIn<6) item.quality=item.quality+2;
            else if(item.sellIn<11) item.quality++;
            item.sellIn--;
            DoIfSellInIsNegative(item);
        }
    }

    public void DoIfNameIsNotThoseThree(Item item){
        if(!item.name.equals("Sulfuras, Hand of Ragnaros")){
            DoIfQualityIsPositive(item);
        }
    }


    private void DoIfSellInIsNegative(Item item) {
        if(item.sellIn<0){
            if (IfNameIsNotAgedBrieAndBackstage(item)) {
                DoIfQualityIsPositive(item);
            }
            else if(item.name.equals("Backstage passes to a TAFKAL80ETC concert")){
                item.quality=0;
            }
            else if(item.name.equals("Aged Brie")){
                increaseQualityOneWhenQualityUnder50(item);
            }
        }
    }

    private void DoIfQualityIsPositive(Item item) {
        if(item.quality>0) {
            item.quality = item.quality - 1;
        }
    }

    private boolean IfNameIsNotAgedBrieAndBackstage(Item item) {
        return (!item.name.equals("Aged Brie") &&
                !item.name.equals("Backstage passes to a TAFKAL80ETC concert"));
    }

    private void increaseQualityOneWhenQualityUnder50(Item item){
        if(item.quality<50) item.quality++;
    }
}
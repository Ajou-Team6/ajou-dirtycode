package ac.kr.ajou.dirt;

import org.hamcrest.core.AnyOf;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DirtySampleTest {

    // Name
    String BackStage = "Backstage passes to a TAFKAL80ETC concert";
    String Aged = "Aged Brie";
    // Loop
    int loopFifty = 50;

    // Quality
    int definedQualityMax = 50;
    int definedQualityMin = 0;
    int sellInOver1=5;
    int qualityUnder50=40;
    int qualityOver50=51;
    int negativeSellIn=-1;

    @Test
    public void 아이템_이름이_Aged이고_quality가50미만_Sellin이1이상일때_quality1증가_Sellin1감소_아니면에러(){
        Item[] items = makeNewItemLists(qualityUnder50,sellInOver1,Aged);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        dirtySample.updateQuality();
        assertThat(items[0].getQuality(), is(qualityUnder50+1));
        assertThat(items[0].getSellIn(), is(sellInOver1-1));
    }

    @Test
    public void 아이템_이름이_Aged고_quality가50이상일때_SellIn1감소_quality변화없음_아니면에러(){
        Item[] items = makeNewItemLists(qualityOver50,sellInOver1,Aged);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        dirtySample.updateQuality();
        assertThat(items[0].getQuality(), is(qualityOver50));
        assertThat(items[0].getSellIn(), is(sellInOver1-1));
    }

    @Test
    public void 아이템_이름이_Aged고_quality가50미만_SellIn이음수일때_quality2증가_혹은50으로셋_그리고SellIn1감소_아니면에러(){
        Item[] items = makeNewItemLists(qualityUnder50,negativeSellIn,Aged);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        dirtySample.updateQuality();
        assertThat(items[0].getQuality(), anyOf(is(qualityUnder50+2),is(50)));
        assertThat(items[0].getSellIn(), is(negativeSellIn-1));
    }

    @Test
    public void 아이템_이름이_Backstage이고_quality가50보다작고_Sellin이11보다클때_50번반복() {
        int initialQuality = 40;
        int initialSellin = 20;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, BackStage);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        // Step Check
        int checkQualityEachStage = initialQuality;
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            checkQualityEachStage = basicBackstageLogic(items[0], checkQualityEachStage);
            assertThat(items[0].getQuality(), equalTo(checkQualityEachStage));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(definedQualityMin));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    @Test
    public void 아이템_이름이_Backstage이고_quality가50보다크고_Sellin이11보다클때_50번반복() {
        int initialQuality = 100;
        int initialSellin = 20;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, BackStage);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        // Step Check
        int checkQualityEachStage = initialQuality;
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            checkQualityEachStage = basicBackstageLogic(items[0], checkQualityEachStage);
            assertThat(items[0].getQuality(), equalTo(checkQualityEachStage));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(definedQualityMin));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    private int basicBackstageLogic(Item item, int check) {
        if(item.getSellIn() < 0) return 0;
        else if(check > definedQualityMax) return check;
        else if(item.getSellIn() < 6) check += 3;
        else if(item.getSellIn() < 11) check += 2;
        else check += 1;
        if(check > definedQualityMax) check = definedQualityMax;
        return check;
    }

    private Item[] makeNewItemLists(int initialQuality, int initialSellin, String initialName) {
        return new Item[]{Item.builder()
                .name(initialName)
                .quality(initialQuality)
                .sellIn(initialSellin)
                .build()};
    }
}

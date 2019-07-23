package ac.kr.ajou.dirt;

import org.hamcrest.core.AnyOf;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DirtySampleTest {

    // Name
    String BackStage = "Backstage passes to a TAFKAL80ETC concert";

    // Loop
    int loopFifty = 50;

    // Quality
    int definedQualityMax = 50;
    int definedQualityMin = 0;

    @Test
    public void 아이템_이름이_Backstage이고_quality가50보다작고_Sellin이11보다클때_50번반복() {
        int initialQuality = 40;
        int initialSellin = 20;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, BackStage);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        // Step Check
        int check = initialQuality;
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            check = basicBackstageLogic(items[0], check);
            assertThat(items[0].getQuality(), equalTo(check));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(definedQualityMin));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    @Test
    public void 아이템_이름이_Backstage이고_quality가50보다클때_50번반복() {
        int initialQuality = 100;
        int initialSellin = 20;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, BackStage);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        // Step Check
        int check = initialQuality;
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            check = basicBackstageLogic(items[0], check);
            assertThat(items[0].getQuality(), equalTo(check));
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

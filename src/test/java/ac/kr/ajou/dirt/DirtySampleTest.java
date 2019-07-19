package ac.kr.ajou.dirt;

import org.hamcrest.core.AnyOf;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DirtySampleTest {

    // Name
    String NotThreeDefault = "NOTTHREEDEFAULT";
    String AgedBrie = "Aged Brie";
    String BackStage = "Backstage passes to a TAFKAL80ETC concert";
    String Sulfuras = "Sulfuras, Hand of Ragnaros";

    // Loop
    int loopFifty = 50;
    int loopTen = 10;
    int zero = 0;

    // Quality
    int definedQualityMax = 50;
    int definedQualityMin = 0;

    @Test
    public void 아이템_이름이_셋다아니고_quality가0보다크고_sellin이_0보다클때_N번반복() {
        int initialQuality = 10;
        int initialSellin = 10;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, NotThreeDefault);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            assertThat(items[0].getQuality(), anyOf(is(initialQuality - loop),
                    equalTo(definedQualityMin)));
        }
        // Result Check
        assertThat(items[0].getQuality(),greaterThanOrEqualTo(definedQualityMin));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    @Test
    public void 아이템_이름이_셋다아니고_quality가0보다크고_sellin이_0보다작을때_N번반복() {
        int initialQuality = 20;
        int initialSellin = -10;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, NotThreeDefault);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        for(int loop = 1; loop <= loopTen; loop++)
        {
            dirtySample.updateQuality();
            assertThat(items[0].getQuality(),anyOf(is(initialQuality - (loop * 2)),
                    equalTo(definedQualityMin)));
        }
        // Result Check
        assertThat(items[0].getQuality(),greaterThanOrEqualTo(definedQualityMin));
        assertThat(items[0].getSellIn(),is(initialSellin - loopTen));
    }

    @Test
    public void 아이템_이름이_AgedBrie이고_quality가50보다작고_sellin이_0보다클때_N번반복() {
        int initialQuality = 33;
        int initialSellin = 10;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, AgedBrie);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        int check = initialQuality;
       for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            check = AgedBrieBasicLogic(check, items[0].getQuality(), items[0].getSellIn());
            assertThat(items[0].getQuality(), anyOf(is(check),
                    equalTo(definedQualityMax)));
        }
        // Result Check
        assertThat(items[0].getQuality(),lessThanOrEqualTo(definedQualityMax));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    private int AgedBrieBasicLogic(int check, int quality, int sellin) {
            if(quality < 50) {
                if(sellin < 0) check += 2;
                else check += 1;
                if(quality > 50) check = definedQualityMax;
            }
        return check;
    }

    @Test
    public void 아이템_이름이_AgedBrie이고_quality가50보다작고_sellin이_0보다작을때_N번반복() {
        int initialQuality = 33;
        int initialSellin = -5;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, AgedBrie);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            assertThat(items[0].getQuality(), anyOf(is(initialQuality + (loop * 2)),
                    equalTo(definedQualityMax)));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(definedQualityMax));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    @Test
    public void 아이템_이름이_AgedBrie이고_quality가50보다클때_N번반복() {
        int initialQuality = 55;
        int initialSellin = -5;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, AgedBrie);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            assertThat(items[0].getQuality(), equalTo(initialQuality));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(initialQuality));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    @Test
    public void 아이템_이름이_Backstage이고_quality가50보다작고_Sellin이11보다클때_50번반복() {
        int initialQuality = 40;
        int initialSellin = 20;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, BackStage);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        int check = initialQuality;
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            check = basicBackstageLogic(items[0], check);
            assertThat(items[0].getQuality(), equalTo(check));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(zero));
        assertThat(items[0].getSellIn(),is(initialSellin - loopFifty));
    }

    @Test
    public void 아이템_이름이_Backstage이고_quality가50보다클때_50번반복() {
        int initialQuality = 100;
        int initialSellin = 20;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, BackStage);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        int check = initialQuality;
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            check = basicBackstageLogic(items[0], check);
            assertThat(items[0].getQuality(), equalTo(check));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(zero));
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

    @Test
    public void 아이템_이름이_Sulfuras일때_N번반복() {
        int initialQuality = 50;
        int initialSellin = 20;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, Sulfuras);
        DirtySample dirtySample = new DirtySample(items);
        // Step Check
        for(int loop = 1; loop <= loopFifty; loop++)
        {
            dirtySample.updateQuality();
            assertThat(items[0].getQuality(), is(initialQuality));
        }
        // Result Check
        assertThat(items[0].getQuality(),equalTo(initialQuality));
        assertThat(items[0].getSellIn(),is(initialSellin));
    }

    private Item[] makeNewItemLists(int initialQuality, int initialSellin, String initialName) {
        return new Item[]{Item.builder()
                .name(initialName)
                .quality(initialQuality)
                .sellIn(initialSellin)
                .build()};
    }

}

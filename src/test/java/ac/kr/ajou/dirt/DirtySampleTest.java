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
    public void 아이템이름이_세개_모두_아니고_item_Quality와Sellin_모두_0이상일때_CHECK() {

        int Quality = 3;
        int Sellin = 2;

        Item[] items = makeNewItemLists(Quality, Sellin, NotThreeDefault);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        dirtySample.updateQuality();
        assertThat(items[0].getQuality(), is(2));
        assertThat(items[0].getSellIn(), is(1));
    }

    @Test
    public void 아이템이름이_세개_모두_아니고_item_Quality가_0이상이고_item_Sellin가_0이하일때_CHECK() {
        int satisfied_Quality = 3;
        int satisfied_Sellin = -1;

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, NotThreeDefault);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(1));
        assertThat(satisfied_items[0].getSellIn(), is(-2));
    }

    @Test
    public void 아이템이름이_세개_모두_아니고_item_Quality가_0이하이고_item_Sellin가_0이상일때_CHECK() {
        int satisfied_Quality = -1;
        int satisfied_Sellin = 2;

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, NotThreeDefault);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(-1));
        assertThat(satisfied_items[0].getSellIn(), is(1));
    }

    @Test
    public void 아이템이름이_세개_모두_아니고_item_Quality와item_Sellin가_0이하일때_CHECK() {
        int satisfied_Quality = -2;
        int satisfied_Sellin = -2;

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, NotThreeDefault);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(-2));
        assertThat(satisfied_items[0].getSellIn(), is(-3));
    }

    @Test
    public void 아이템이름이_Sulfuras이라면_Sellin_AND_Quality_변화없으면_TRUE() {
        int satisfied_Quality = 3;
        int satisfied_Sellin = 3;

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, Sulfuras);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(satisfied_Quality));
        assertThat(satisfied_items[0].getSellIn(), is(satisfied_Sellin));
    }

    @Test
    public void 아이템_이름이_셋다아니고_quality가0보다크고_sellin이_0보다클때_N번반복() {
        int initialQuality = 10;
        int initialSellin = 10;
        Item[] items = makeNewItemLists(initialQuality, initialSellin, NotThreeDefault);
        // 10, 10, 해당하는 이름 아님.
        DirtySample dirtySample = DirtySample.builder().items(items).build();
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
        DirtySample dirtySample = DirtySample.builder().items(items).build();
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
        DirtySample dirtySample = DirtySample.builder().items(items).build();
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
        DirtySample dirtySample = DirtySample.builder().items(items).build();
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
        DirtySample dirtySample = DirtySample.builder().items(items).build();
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
        assertThat(items[0].getQuality(),equalTo(zero));
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
        DirtySample dirtySample = DirtySample.builder().items(items).build();
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

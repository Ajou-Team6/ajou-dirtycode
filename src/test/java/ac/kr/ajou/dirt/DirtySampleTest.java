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
    public void 아이템이름이_세개_모두_아니고_item_Quality와Sellin_모두_1이상일때_CHECK() {

        int satisfied_Quality = ((int)(Math.random()*definedQualityMax)+1); // 1 ~ 50 사이의 난수 생성
        int satisfied_Sellin = ((int)(Math.random()*definedQualityMax)+1); // 1 ~ 50 사이의 난수 생성

        Item[] items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, NotThreeDefault);
        DirtySample dirtySample = DirtySample.builder().items(items).build();
        dirtySample.updateQuality();

        assertThat(items[0].getQuality(), is(satisfied_Quality - 1));
        assertThat(items[0].getSellIn(), is(satisfied_Sellin - 1));
    }


    @Test
    public void 아이템이름이_세개_모두_아니고_item_Quality가_0이상이고_item_Sellin가_0이하일때_CHECK() {
        int satisfied_Quality = ((int)(Math.random()*definedQualityMax)+1); // 0 ~ 50 난수
        int satisfied_Sellin = ((int)(-Math.random()*definedQualityMax)+1); //-50 ~ 0 난수

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, NotThreeDefault);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(satisfied_Quality-2));
        assertThat(satisfied_items[0].getSellIn(), is(satisfied_Sellin-1));
    }

    @Test
    public void 아이템이름이_세개_모두_아니고_item_Quality가_0이하이고_item_Sellin가_0이상일때_CHECK() {
        int satisfied_Quality = ((int)(-Math.random()*definedQualityMax)+1); // -50~ 0 사이의 난수 생성
        int satisfied_Sellin = ((int)(Math.random()*definedQualityMax)+1); // 1~ 50 사이의 난수 생성

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, NotThreeDefault);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(satisfied_Quality));
        assertThat(satisfied_items[0].getSellIn(), is(satisfied_Sellin-1));
    }

    @Test
    public void 아이템이름이_세개_모두_아니고_item_Quality와item_Sellin가_0이하일때_CHECK() {
        int satisfied_Quality =  ((int)(-Math.random()*definedQualityMax)+1); // -50~ 0 사이의 난수 생성
        int satisfied_Sellin =  ((int)(-Math.random()*definedQualityMax)+1); // -50~ 0 사이의 난수 생성

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, NotThreeDefault);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(satisfied_Quality));
        assertThat(satisfied_items[0].getSellIn(), is(satisfied_Sellin-1));
    }

    @Test
    public void 아이템이름이_Sulfuras이라면_Sellin_AND_Quality_변화없으면_TRUE() {
        int satisfied_Quality = ((int)(Math.random()*2*definedQualityMax)-50);  // -50~ 50 사이의 난수 생성
        int satisfied_Sellin = ((int)(Math.random()*2*definedQualityMax)-50); // -50~ 50 사이의 난수 생성

        Item[] satisfied_items = makeNewItemLists(satisfied_Quality, satisfied_Sellin, Sulfuras);
        DirtySample satisfied_dirtySample = DirtySample.builder().items(satisfied_items).build();
        satisfied_dirtySample.updateQuality();
        assertThat(satisfied_items[0].getQuality(), is(satisfied_Quality));
        assertThat(satisfied_items[0].getSellIn(), is(satisfied_Sellin));
    }


    private Item[] makeNewItemLists(int initialQuality, int initialSellin, String initialName) {
        return new Item[]{Item.builder()
                .name(initialName)
                .quality(initialQuality)
                .sellIn(initialSellin)
                .build()};
    }

}

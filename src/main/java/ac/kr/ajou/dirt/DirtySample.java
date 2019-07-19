package ac.kr.ajou.dirt;

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
            if (!items[i].name.equals("Aged Brie")
                    && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) { // Item 이름이 Aged Brie가 아니고, 이상한 문구가 아니면
                if (items[i].quality > 0) {                                                  // item quality가 0보다 크면
                    if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {               // item 이름이 sulfuras, hand of ragnaros가 아니면
                        items[i].quality = items[i].quality - 1;                             // 아이템 quality를 1 제거
                    }
                }
            } else {                                                                          // Item 이름이 Aged Brie이거나, 이상한 문구이면
                if (items[i].quality < 50) {                                                  // Item quality가 50보다 작으면,
                    items[i].quality = items[i].quality + 1;                                  // item quality 1 증가

                    if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {  // item 이름이 이상한 문구이면
                        if (items[i].sellIn < 11) {                                           // item sellin이 11보다 작고,
                            if (items[i].quality < 50) {                                      // item quality가 50보다 작으면,
                                items[i].quality = items[i].quality + 1;                      // item quality 1 증가
                            }
                        }

                        if (items[i].sellIn < 6) {                                              // Item 이름이 이상한 문구이고
                            if (items[i].quality < 50) {                                        // sellin이 6보다 작고, quality가 50보다 작으면
                                items[i].quality = items[i].quality + 1;                        // quality 1 증가
                            }
                        }
                    }
                }
            }

            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {                          // item 이름이 sulfuras, hand of ragnaros가 아니면
                items[i].sellIn = items[i].sellIn - 1;                                          // sellin 1 제거
            }

            if (items[i].sellIn < 0) {                                                          // item sellin이 0보다 작고,
                if (!items[i].name.equals("Aged Brie")) {                                       // item 이름이 aged brie가 아니고,
                    if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {   // item 이름이 이상한 문구가 아니고
                        if (items[i].quality > 0) {                                             // quality가 0보다 크고
                            if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {          // item 이름이 sulfuras, hand of ragnaros가 아니면
                                items[i].quality = items[i].quality - 1;                        // 아이템 quality 1 감소
                            }
                        }
                    } else {                                                                     // 아이템 이름이 이상한 문구이면
                        items[i].quality = items[i].quality - items[i].quality;                 // item quality는 0이된다.
                    }
                } else {                                                                        // 아이템 이름이 aged brie이면,
                    if (items[i].quality < 50) {                                                // quality가 50보다 작으면
                        items[i].quality = items[i].quality + 1;                                // quality 1 증가
                    }
                }
            }
        }
    }
}
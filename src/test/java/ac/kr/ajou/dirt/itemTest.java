package ac.kr.ajou.dirt;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.IsAnything.anything;
import static org.junit.Assert.assertThat;

public class itemTest {
    Item item = new Item("Aged Brie", 10, 51);

    @Test
    public void itemToStringTest() {
        System.out.println(item.toString());
        assertThat(item.toString(), anything());
    }
}

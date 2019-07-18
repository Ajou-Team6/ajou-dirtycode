package ac.kr.ajou.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class StreamApiTest {
    @Test
    public void filterString_제대로_필터된_스트링을_반환() {
        StreamApi streamApi = new StreamApi();
        List<String> testStringList = new ArrayList<>();
        testStringList.add("abc1");
        testStringList.add("abc2");
        testStringList.add("abc3");
        testStringList.add("abc4");
        testStringList.add("abc6");
        testStringList.add("abc6");
        List<String> result = streamApi.filterString(testStringList, "abc6");
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).equals("abc6"));
        assertTrue(result.get(1).equals("abc6"));
    }

    @Test
    public void filterByName_주소_제대로_반환() {
        StreamApi streamApi = new StreamApi();
        List<Person> testPersonList = new ArrayList<>();
        testPersonList.add(new Person("JonginPark", "Yongin", 10));

    }

}

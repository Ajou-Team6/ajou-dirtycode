package ac.kr.ajou.duplicate;

public class DuplicateSample {

    public int calc(EventData eventData) {
        if (eventData.getPrice() > 10) {
            logPriceAndName(eventData);
            return 1;
        }
        if (eventData.getEventName().equals("test")) {
            logPriceAndName(eventData);
            return 2;
        }

        return 0;
    }

    public void logPriceAndName(EventData eventData) {
        System.out.println(eventData.getPrice());
        System.out.println(eventData.getEventName());
    }

    public void lombokBuilder() {
        EventData event = EventData.builder()
                .eventName("abc")
                .price(2)
                .Test("TestTest").build();
    }

    public boolean lombokBuilder(String parameter) {
        if (parameter.equals("a")) return true;
        return false;
    }

}

package ac.kr.ajou;

import ac.kr.ajou.dirt2.IpAddressMacAddress;
import ac.kr.ajou.dirt2.PcBangEvent;

import java.util.Set;

public class MainApplication {

    public static void main(String[] args) {

        PcBangEvent pcBangEvent = new PcBangEvent();
        pcBangEvent.setAccountId("11");
        pcBangEvent.setMac("mac1,mac2,mac5,mac6");
        pcBangEvent.setIp("10.10.10.10");

        IpAddressMacAddress process = new IpAddressMacAddress();

        Set<String> process1 = process.buildCombinedIdentification(pcBangEvent);

        process1.forEach(s -> {
            System.out.println(s);
        });
    }
}

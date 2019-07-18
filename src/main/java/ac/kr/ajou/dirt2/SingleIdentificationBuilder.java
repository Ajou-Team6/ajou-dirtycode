package ac.kr.ajou.dirt2;

import java.util.HashSet;
import java.util.Set;

public class SingleIdentificationBuilder implements IdentificationBuilder {
    @Override
    public Set<String> buildCombinedIdentification(PcBangEvent pbe) {
        Set<String> singleIdentificationStringSet = new HashSet<String>();
        if (pbe.getIp() != null) {
            singleIdentificationStringSet.add(pbe.getIp());
        }
        return singleIdentificationStringSet;
    }
}

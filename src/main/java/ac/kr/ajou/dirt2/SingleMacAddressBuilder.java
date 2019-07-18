package ac.kr.ajou.dirt2;

import java.util.HashSet;
import java.util.Set;

public class SingleMacAddressBuilder implements IdentificationBuilder {
    @Override
    public Set<String> buildCombinedIdentification(PcBangEvent pbe) {
        Set<String> singleMacAddressStringSet = new HashSet<String>();
        if (isValidMacAddress(pbe)) {
            String[] macAddresses = pbe.getMac().split(",");
            if (isMacAddressLengthUnderHundred(macAddresses)) {
                for (int i = 0; i < macAddresses.length; i++) {
                    singleMacAddressStringSet.add(macAddresses[i]);
                }
                return singleMacAddressStringSet;
            } ErrorMessageLog("Mac address too many");
            return null;
        } ErrorMessageLog("Mac address is wrong");
        return null;
    }

    public void ErrorMessageLog(String s) {
        System.out.println(s);
    }

    private boolean isMacAddressLengthUnderHundred(String[] macAddresses) {
        return macAddresses.length <= 100;
    }

    private boolean isValidMacAddress(PcBangEvent pbe) {
        return pbe.getMac() != null && !pbe.getMac().isEmpty();
    }
}

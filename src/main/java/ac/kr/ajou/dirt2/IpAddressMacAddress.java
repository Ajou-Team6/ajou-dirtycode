package ac.kr.ajou.dirt2;

import java.util.HashSet;
import java.util.Set;

public class IpAddressMacAddress {

    // single ip, single mac, accountId, ip + mac
    public Set<String> buildCombinedIdentification(PcBangEvent pbe) {
        Set<String> combinedIdentification = new HashSet<String>();
        // single IP
        buildForSingleIp(pbe, combinedIdentification);
        // single mac
        buildForSingleMac(pbe, combinedIdentification);
        // accountId
        buildForAccountId(pbe, combinedIdentification);
        // ip + mac
        buildForIpAndMac(pbe, combinedIdentification);

        return combinedIdentification;
    }

    private void buildForSingleIp(PcBangEvent pbe, Set<String> combinedIdentification) {
        if (pbe.getIp() != null) {
            combinedIdentification.add(pbe.getIp());
        }
    }

    private void buildForSingleMac(PcBangEvent pbe, Set<String> combindedIdentification) {
        if (isValidMacAddress(pbe)) {
            String[] macAddresses = pbe.getMac().split(",");
            if (isMacAddressLengthUnderHundred(macAddresses)) {
                for (int i = 0; i < macAddresses.length; i++) {
                    combindedIdentification.add(macAddresses[i]);
                }
                return;
            } ErrorMessageLog("Mac address too many");
            return;
        } ErrorMessageLog("Mac address is wrong");
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

    private void buildForIpAndMac(PcBangEvent pbe, Set<String> combindedIdentification) {
        if (isValidMacAddress(pbe)) {
            if (isValidIpAddress(pbe)) {
                String[] macAddresses = pbe.getMac().split(",");
                if (isMacAddressLengthUnderHundred(macAddresses)) {
                    for (int i = 0; i < macAddresses.length; i++) {
                        combindedIdentification.add(pbe.getIp() + macAddresses[i]);
                    }
                }
            }
        }
    }

    private boolean isValidIpAddress(PcBangEvent pbe) {
        return pbe.getIp() != null && !pbe.getIp().isEmpty();
    }

    private void buildForAccountId(PcBangEvent pbe, Set<String> combindedIdentification) {
        if (isValidAccountId(pbe)) {
            if (!pbe.getAccountId().equals("0")) {
                combindedIdentification.add(pbe.getAccountId());
            } else {
                ErrorMessageLog("Account id can't be 0");
            }
        } else {
            ErrorMessageLog("Account is is null or empty");
        }
    }

    private boolean isValidAccountId(PcBangEvent pbe) {
        return pbe.getAccountId() != null && !pbe.getAccountId().isEmpty();
    }
}

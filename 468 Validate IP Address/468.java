class Solution {
    public String validIPAddress(String IP) {
        if (IP == null || IP.length() == 0) {
            return "Neither";
        }
        String[] ipv4 = IP.split("\\.", -1);
        String[] ipv6 = IP.split("\\:", -1);
        boolean flag = false;
        if (ipv4.length == 4) {
            flag = true;
            for (String s : ipv4) {
                if (!validIpv4(s)) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            return "IPv4";
        }
        if (ipv6.length == 8) {
            flag = true;
            for (String s : ipv6) {
                if (!validIpv6(s)) {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean validIpv4(String s) {
        try {
            int intVal = Integer.parseInt(s);
            return String.valueOf(Integer.valueOf(s)).equals(s) && intVal >= 0 && intVal <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validIpv6(String s) {
        if (s.length() > 4) {
            return false;
        }
        try {
            return Integer.parseInt(s, 16) >= 0 && s.charAt(0) != '-';
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
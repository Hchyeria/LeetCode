class Solution {
    public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) {
            return logs;
        }
        
        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String s1, String s2) {
                int firstEmpty1 = s1.indexOf(' ');
                int firstEmpty2 = s2.indexOf(' ');
                
                boolean isS1Digit = isDigit(s1.charAt(firstEmpty1 + 1));
                boolean isS2Digit = isDigit(s2.charAt(firstEmpty2 + 1));
                
                if (isS1Digit && isS2Digit) {
                    return 0;
                } else if (isS1Digit) {
                    return 1;
                } else if (isS2Digit) {
                    return -1;
                } else {
                    int comp = s1.substring(firstEmpty1 + 1).compareTo(s2.substring(firstEmpty2 + 1));
                    if (comp == 0) {
                        return s1.substring(0, firstEmpty1).compareTo(s2.substring(0, firstEmpty2));
                    } else {
                        return comp;
                    }
                }
                
            }
        };
        
        Arrays.sort(logs, comparator);
        return logs;
    }
    
    private boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
}
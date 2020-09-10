class Solution {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        if (S == null || S.length() == 0
            || sources == null || sources.length ==0
            || targets == null || targets.length == 0
            || indexes == null || indexes.length == 0) {
            return S;
        }
        int[] match = new int[S.length()];
        Arrays.fill(match, -1);
        
        for (int i = 0; i < indexes.length; ++i) {
            int index = S.indexOf(sources[i], indexes[i]);
            if (index == indexes[i]) {
                match[indexes[i]] = i;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int left = 0;
        
        for (int i = 0; i < match.length; ++i) {
            if (match[i] >= 0) {
                sb.append(S, left, i).append(targets[match[i]]);
                left = i + sources[match[i]].length();
            }
        }
        
        sb.append(S, left, S.length());
        return sb.toString();
        
    }
}
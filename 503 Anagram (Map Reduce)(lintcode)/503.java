/* 
    503. Anagram (Map Reduce)
    Use Map Reduce to find anagrams in a given list of words.

    Example
    Example 1:

    Input: "lint lint lnit ln"
    Output: 
    ["lint", "lint", "lnit"]
    ["ln"]
    Example 2:

    Input: "ab ba cab"
    Output:
    ["ab", "ba"]
    ["cab"] 
*/
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class Anagram {

    public static class Map {
        public void map(String key, String value,
                        OutputCollector<String, String> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, String value);
            StringTokenizer token = new StringTokenizer(value);
            while (token.hasMoreTokens()) {
                String word = token.nextToken();
                char[] input = word.toCharArray();
                Arrays.sort(input);
                output.collect(new String(input), word);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values,
                           OutputCollector<String, List<String>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<String> value);
            List<String> res = new ArrayList<>();
            while (values.hasNext()) {
                res.add(values.next());
            }
            output.collect(key, res);
        }
    }
}

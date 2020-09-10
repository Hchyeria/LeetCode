/* 
    504. Inverted Index (Map Reduce)
    Use map reduce to build inverted index for given documents.

    Example
    Look at the program. 
    Input Data:
    [{"id":1,"content":"This is the content of document1"}, {"id":2,"content":"This is the content of document2"}]
*/

/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
public class InvertedIndex {

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            StringTokenizer token = new StringTokenizer(value.content);
            while (token.hasMoreTokens()) {
                String word = token.nextToken();
                output.collect(word, value.id);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);
            List<Integer> res = new ArrayList<>();
            int left = -1;
            while (values.hasNext()) {
                int right = values.next();
                if (right != left) {
                    res.add(right);
                }
                left = right;
            }
            output.collect(key, res);
        }
    }
}

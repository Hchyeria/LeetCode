/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {

    // @param nestedList a list of NestedInteger
    // @return a list of integer

    // recursion
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        if (nestedList == null) {
            return res;
        }
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                res.add(n.getInteger());
            } else {
                res.addAll(flatten(n.getList()));
            }
        }
        return res;
    }

    // iterative
    public List<Integer> flatten2(List<NestedInteger> nestedList) {
        // Write your code here
        List<Integer> res = new ArrayList<>();
        if (nestedList == null) {
            return res;
        }
        
        Deque<NestedInteger> stack = new LinkedList<>();
        
        int size = nestedList.size();
        for (int i = size - 1; i >= 0; --i) {
            stack.offerFirst(nestedList.get(i));
        }
        while (!stack.isEmpty()) {
            NestedInteger n = stack.pollFirst();
            if (n.isInteger()) {
                res.add(n.getInteger());
            } else {
                List<NestedInteger> list = n.getList();
                int curSize = list.size();
                for (int i = curSize - 1; i >= 0; --i) {
                    stack.offerFirst(list.get(i));
                }          
            }
        }
        
        return res;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return serializer(new StringBuilder(), root).toString();
    }
    
    private StringBuilder serializer(StringBuilder sb, TreeNode root) {
        if (root == null) return sb.append("#");
        sb.append(root.val).append(",");
        serializer(sb, root.left).append(",");
        serializer(sb, root.right);
        return sb;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        return deserializer(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    private TreeNode deserializer(Queue<String> q) {
        String s = q.poll();
        if (s.equals("#")) return null;
        TreeNode root = new TreeNode(Integer.valueOf(s));
        root.left = deserializer(q);
        root.right = deserializer(q);
        return root;
    }

    // Time = O(n)
    // Space = O(height)
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
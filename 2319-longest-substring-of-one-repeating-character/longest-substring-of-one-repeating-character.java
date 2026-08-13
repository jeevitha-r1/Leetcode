class Solution {
    
    class Node {
        char left, right;
        int prefix, suffix, best, len;

        Node(char c) {
            left = right = c;
            prefix = suffix = best = len = 1;
        }
    }

    Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        tree = new Node[4 * n];

        build(s, 1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int index = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, index, ch);

            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build segment tree
    private void build(String s, int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(s.charAt(start));
            return;
        }

        int mid = (start + end) / 2;

        build(s, node * 2, start, mid);
        build(s, node * 2 + 1, mid + 1, end);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Update one character
    private void update(int node, int start, int end, int index, char ch) {
        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, end, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two segments
    private Node merge(Node a, Node b) {
        Node res = new Node(a.left);

        res.len = a.len + b.len;
        res.left = a.left;
        res.right = b.right;

        // Prefix
        res.prefix = a.prefix;

        if (a.prefix == a.len && a.right == b.left) {
            res.prefix = a.len + b.prefix;
        }

        // Suffix
        res.suffix = b.suffix;

        if (b.suffix == b.len && a.right == b.left) {
            res.suffix = b.len + a.suffix;
        }

        // Best
        res.best = Math.max(a.best, b.best);

        if (a.right == b.left) {
            res.best = Math.max(res.best, a.suffix + b.prefix);
        }

        return res;
    }
}
package graph;

/**
 * Description: https://leetcode.com/problems/graph-valid-tree
 * Difficulty: Medium
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
public class GraphValidTree {

    public boolean validTreeViaUnionFind(int n, int[][] edges) {
        if (edges.length < n - 1) return false; // graph is not fully connected

        int[] parents = new int[n];
        int[] ranks = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }

        for (int[] edge : edges) {
            if (!unite(parents, ranks, edge[0], edge[1])) {
                return false; // cycle was found
            }
        }

        return true;
    }

    private boolean unite(int[] parents, int[] ranks, int node1, int node2) {
        int parent1 = findParent(parents, node1);
        int parent2 = findParent(parents, node2);

        if (parent1 == parent2) return false;

        if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
            ranks[parent1] += ranks[parent2];
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += ranks[parent1];
        }

        return true;
    }

    private int findParent(int[] parents, int node) {
        while (node != parents[node]) {
            parents[node] = parents[parents[node]];
            node = parents[node];
        }

        return node;
    }
}

package cses;
import java.io.*;
import java.util.*;

public class miniQuery {
    static class SegmentTree {
        int[] tree;
        int n;

        SegmentTree(int[] input) {
            this.n = input.length;
            tree = new int[4 * n];
            build(input, 0, 0, n - 1);
        }

        void build(int[] arr, int node, int left, int right) {
            if (left == right) {
                tree[node] = arr[left];
            } else {
                int mid = (left + right) / 2;
                build(arr, 2 * node + 1, left, mid);
                build(arr, 2 * node + 2, mid + 1, right);
                tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
            }
        }

        int query(int node, int left, int right, int l, int r) {
            if (r < left || right < l) return Integer.MAX_VALUE;
            if (l <= left && right <= r) return tree[node];
            int mid = (left + right) / 2;
            int leftMin = query(2 * node + 1, left, mid, l, r);
            int rightMin = query(2 * node + 2, mid + 1, right, l, r);
            return Math.min(leftMin, rightMin);
        }

        int rangeMin(int l, int r) {
            return query(0, 0, n - 1, l, r);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        SegmentTree segTree = new SegmentTree(arr);

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            out.println(segTree.rangeMin(l, r));
        }

        out.flush();
    }
}

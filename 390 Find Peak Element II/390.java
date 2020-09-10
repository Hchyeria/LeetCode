import java.util.List;

/*
    LintCode 390
    There is an integer matrix which has the following features:
    The numbers in adjacent positions are different.
    The matrix has n rows and m columns.
    For all i < m, A[0][i] < A[1][i] && A[n - 2][i] > A[n - 1][i].
    For all j < n, A[j][0] < A[j][1] && A[j][m - 2] > A[j][m - 1].
    We define a position P is a peek if:
    A[j][i] > A[j+1][i] && A[j][i] > A[j-1][i] && A[j][i] > A[j][i+1] && A[j][i] > A[j][i-1]
    Find a peak element in this matrix. Return the index of the peak.
    Have you met this question in a real interview? Yes
    Example
    Given a matrix:
    [
    [1 ,2 ,3 ,6 ,5],
    [16,41,23,22,6],
    [15,17,24,21,7],
    [14,18,19,20,10],
    [13,14,11,10,9]
    ]
    return index of 41 (which is [1,1]) or index of 24 (which is [2,2])
    Note
    The matrix may contains multiple peeks, find any of them.
    Challenge
    Solve it in O(n+m) time.
    If you come up with an algorithm that you thought it is O(n log m) or O(m log n), 
    can you prove it is actually O(n+m) or propose a similar but O(n+m) algorithm?
*/

class Solution {
    public List<Integer> findPeakII(int[][] A) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0 || A[0].length == 0) {
            return res;
        }
        int m = A.length, n = A[0].length;

        // according the description of question, the peak element will never appear in the boundary
        binarySearch(A, res, 1, m - 2, 1, n - 2);
        return res;
    }

    private void binarySearch(int[][] A, List<Integer> res, int rStart, int rEnd, int cStart, int cEnd) {
        int rMid = rStart + (rEnd - rStart) / 2;
        int cMid = cStart + (cEnd - cStart) / 2;

        int maxVal = Integer.MIN_VALUE;
        int c = 0, r = 0;

        for (int i = rStart; i <= rEnd; ++i) {
            if (A[i][cMid] > maxVal) {
                maxVal = A[i][cMid];
                c = cMid;
                r = i;
            }
        }

        for (int i = cStart; i <= cEnd; ++i) {
            if (A[rMid][i] > maxVal) {
                maxVal = A[rMid][i];
                c = i;
                r = rMid;
            }
        }

        if (A[r - 1][c] > A[r][c]) {
            r--;
        } else if (A[r + 1][c] > A[r][c]) {
            r++;
        } else if (A[r][c - 1] > A[r][c]) {
            c--;
        } else if (A[r][c + 1] > A[r][c]) {
            c++;
        } else {
            res.add(r);
            res.add(c);
            return;
        }

        if (r >= rStart && r < rMid && c >= cStart && c < cMid) {
            binarySearch(A, res, rStart, rMid - 1, cStart, cMid - 1);
        } else if (r >= rStart && r < rMid && c > cMid && c <= cEnd) {
            binarySearch(A, res, rStart, rMid - 1, cMid + 1, cEnd);
        } else if (r > rMid && r <= rEnd && c >= cStart && c < cMid) {
            binarySearch(A, res, rMid + 1, rEnd, cStart, cMid - 1);
        } else {
            binarySearch(A, res, rMid + 1, rEnd, cMid + 1, cEnd);
        }

    }
}
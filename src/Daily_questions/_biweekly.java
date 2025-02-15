package Daily_questions;

public class _biweekly {

    static class GoodNums {
        public int sumGood(int[] arr, int gap) {
            int tot = 0, n = arr.length;
            for (int i = 0; i < n; i++) {
                boolean ok = true;
                if (i - gap >= 0) {
                    if (arr[i] <= arr[i - gap]) ok = false;
                }
                if (i + gap < n) {
                    if (arr[i] <= arr[i + gap]) ok = false;
                }
                if (ok) tot += arr[i];
            }
            return tot;
        }
    }

    static class SqArea {
        static class Sq {
            double b, t, s;
            Sq(int y, int l) {
                b = y; s = l; t = y + (double) l;
            }
        }
        public double findLine(int[][] sq) {
            int n = sq.length;
            Sq[] a = new Sq[n];
            double tot = 0.0, mY = 0.0;
            for (int i = 0; i < n; i++) {
                int y = sq[i][1], l = sq[i][2];
                a[i] = new Sq(y, l);
                tot += (double) l * l;
                if (a[i].t > mY) mY = a[i].t;
            }
            double tgt = tot / 2.0;
            Sq[] bArr = a.clone();
            java.util.Arrays.sort(bArr, (x, y2) -> Double.compare(x.b, y2.b));
            Sq[] tArr = a.clone();
            java.util.Arrays.sort(tArr, (x, y2) -> Double.compare(x.t, y2.t));
            double[] preS = new double[n+1];
            double[] preB = new double[n+1];
            for (int i = 0; i < n; i++) {
                preS[i+1] = preS[i] + bArr[i].s;
                preB[i+1] = preB[i] + bArr[i].b * bArr[i].s;
            }
            double[] preF = new double[n+1];
            double[] preST = new double[n+1];
            double[] preBT = new double[n+1];
            for (int i = 0; i < n; i++) {
                preF[i+1] = preF[i] + tArr[i].s * tArr[i].s;
                preST[i+1] = preST[i] + tArr[i].s;
                preBT[i+1] = preBT[i] + tArr[i].b * tArr[i].s;
            }
            double lo = 0.0, hi = mY, eps = 1e-7;
            for (int i = 0; i < 60; i++) {
                double mid = (lo + hi) / 2.0;
                double cur = compArea(mid, bArr, preS, preB, tArr, preF, preST, preBT);
                if (cur < tgt) lo = mid; else hi = mid;
            }
            return Math.round(hi * 1e5) / 1e5;
        }
        private double compArea(double y, Sq[] bArr, double[] preS, double[] preB,
                                Sq[] tArr, double[] preF, double[] preST, double[] preBT) {
            int n = bArr.length;
            int i1 = upB(bArr, y);
            double sumS = preS[i1], sumB = preB[i1];
            int i2 = upT(tArr, y);
            double full = preF[i2], sumST = preST[i2], sumBT = preBT[i2];
            double part = y * (sumS - sumST) - (sumB - sumBT);
            return full + part;
        }
        private int upB(Sq[] bArr, double y) {
            int lo = 0, hi = bArr.length;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (bArr[mid].b < y) lo = mid + 1; else hi = mid;
            }
            return lo;
        }
        private int upT(Sq[] tArr, double y) {
            int lo = 0, hi = tArr.length;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tArr[mid].t <= y) lo = mid + 1; else hi = mid;
            }
            return lo;
        }
    }

    static class SubStr {
        public int minSub(String s, String p) {
            String[] pts = p.split("\\*", -1);
            if (pts.length != 3) return -1;
            String p1 = pts[0], p2 = pts[1], p3 = pts[2];
            int best = Integer.MAX_VALUE;
            java.util.List<Integer> l1 = p1.isEmpty() ? null : occ(s, p1);
            java.util.List<Integer> l2 = p2.isEmpty() ? null : occ(s, p2);
            java.util.List<Integer> l3 = p3.isEmpty() ? null : occ(s, p3);
            if (!p1.isEmpty() && l1.isEmpty()) return -1;
            if (!p2.isEmpty() && l2.isEmpty()) return -1;
            if (!p3.isEmpty() && l3.isEmpty()) return -1;
            if (!p1.isEmpty()) {
                for (int pos1 : l1) {
                    int end1 = pos1 + p1.length();
                    int pos2 = p2.isEmpty() ? end1 : find(l2, end1);
                    if (!p2.isEmpty() && pos2 == -1) continue;
                    int end2 = pos2 + (p2.isEmpty() ? 0 : p2.length());
                    int pos3 = p3.isEmpty() ? end2 : find(l3, end2);
                    if (!p3.isEmpty() && pos3 == -1) continue;
                    int cur = (pos3 + (p3.isEmpty() ? 0 : p3.length())) - pos1;
                    best = Math.min(best, cur);
                }
            } else if (!p2.isEmpty()) {
                for (int pos2 : l2) {
                    int end2 = pos2 + p2.length();
                    int pos3 = p3.isEmpty() ? end2 : find(l3, end2);
                    if (!p3.isEmpty() && pos3 == -1) continue;
                    int cur = (pos3 + (p3.isEmpty() ? 0 : p3.length())) - pos2;
                    best = Math.min(best, cur);
                }
            } else if (!p3.isEmpty()) {
                best = p3.length();
            } else {
                best = 0;
            }
            return best == Integer.MAX_VALUE ? -1 : best;
        }
        private java.util.List<Integer> occ(String s, String p) {
            java.util.List<Integer> list = new java.util.ArrayList<>();
            int[] lps = buildLPS(p);
            int i = 0, j = 0, sl = s.length(), pl = p.length();
            while (i < sl) {
                if (s.charAt(i) == p.charAt(j)) {
                    i++; j++;
                    if (j == pl) {
                        list.add(i - j);
                        j = lps[j - 1];
                    }
                } else {
                    if (j > 0) j = lps[j - 1]; else i++;
                }
            }
            return list;
        }
        private int[] buildLPS(String p) {
            int m = p.length();
            int[] lps = new int[m];
            int len = 0, i = 1;
            while (i < m) {
                if (p.charAt(i) == p.charAt(len)) {
                    len++;
                    lps[i] = len;
                    i++;
                } else {
                    if (len != 0) len = lps[len - 1]; else { lps[i] = 0; i++; }
                }
            }
            return lps;
        }
        private int find(java.util.List<Integer> list, int pos) {
            int lo = 0, hi = list.size() - 1, ans = -1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                if (list.get(mid) >= pos) { ans = list.get(mid); hi = mid - 1; }
                else lo = mid + 1;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        _biweekly obj = new _biweekly();
        GoodNums gn = new GoodNums();
        int[] arr = {1, 3, 2, 1, 5, 4};
        int gap = 2;
        int res1 = gn.sumGood(arr, gap);
        System.out.println(res1);
        SqArea sq = new SqArea();
        int[][] sa = {{0, 0, 1}, {2, 2, 1}};
        double res2 = sq.findLine(sa);
        System.out.println(res2);
        SubStr ss = new SubStr();
        String s = "abaacbaecebce";
        String pat = "ba*c*ce";
        int res3 = ss.minSub(s, pat);
        System.out.println(res3);
    }
}

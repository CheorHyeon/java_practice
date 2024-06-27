class Solution {
    public long solution(int w, int h) {
        long gcd = 0;
        if(w >= h) {
            gcd = gcd(w, h);
        }
        else {
            gcd = gcd(h, w);
        }
        return ((long) w * h) - (((w / gcd) + (h / gcd) - 1) * gcd);
    }
    
    public int gcd(int a, int b) {
        if(a % b == 0) {
            return b;
        }
        return gcd(b, a%b);
    }
}
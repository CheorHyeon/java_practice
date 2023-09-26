class Solution {
    public int solution(int n) {
        return fibo(n);
    }
    
    public int fibo(int index) {
        int[] fi = new int[index+1];
        
        fi[0] = 0;
        fi[1] = 1;
        
        for(int i=2; i<fi.length; i++) {
            fi[i] = fi[i-2] % 1234567 + fi[i-1] % 1234567;
        }
        
        return fi[index] % 1234567;
    }
}
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 1. 철수의 경우를 저장
        int c = findNumber(arrayA, arrayB);
        // 2. 영희의 경우 저장
        int y = findNumber(arrayB, arrayA);
        // 3. 최대값 반환
        int answer = Math.max(c, y);
        return answer;
    }
    
    public int findNumber(int[] commonDivisorArr, int[] nonCommonDivisorArr){
        // 1. 최대 공약수 구하기
        // 배열 원소 1개라면 자기 자신이 최대공약수
        int commonDivisor = commonDivisorArr[0];
        if(commonDivisorArr.length >= 2) {
           commonDivisor = gcd(commonDivisorArr[0], commonDivisorArr[1]);
            for(int i = 2; i < commonDivisorArr.length; i++) {
                commonDivisor = gcd(commonDivisor, commonDivisorArr[i]);
            }
        }
        // 2. 두번째 인자 배열 나눠지는지 확인
        int result = commonDivisor;
        for(int number : nonCommonDivisorArr) {
            if(number % commonDivisor == 0) {
                result = 0;
                break;
            }
        }
        // 3. 나눠 떨어지면 0, 나눠지지 않는다면 해당 최대공약수 반환하기
        return result;
    }
    
    public int gcd(int a, int b) {
        if(a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        if(b !=0) {
            return gcd(b, a%b);
        }
        else {
            return a;
        }
    }
}
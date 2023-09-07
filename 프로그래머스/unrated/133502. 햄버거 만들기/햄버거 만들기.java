import java.util.Arrays;

class Solution {
    public int solution(int[] ingredient) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (int i : ingredient) {
            sb.append(i);

            if (sb.length() >= 4) {
                int lastIndex = sb.length() - 1;

                if (sb.charAt(lastIndex - 3) == '1' && sb.charAt(lastIndex - 2) == '2' && sb.charAt(lastIndex - 1) == '3' && sb.charAt(lastIndex) == '1') {
                    sb.delete(lastIndex - 3, lastIndex + 1);
                    count++;
                }
            }
        }

        return count;
    }
}
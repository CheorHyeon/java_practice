class Solution {
    public int solution(int sticker[]) {
        if(sticker.length == 1) return sticker[0];
        
        // case 1 : 첫 번째 스티커를 먼저 선택한 경우 -> 마지막 스티커 선택 불가
        // dp 배열 : 해당 스티커 선택 or 선택x일 때 최대 점수
        int[] firstStickerSelectDP = new int[sticker.length];
        
        // 첫번째 스티커 선택 시 2번째 선택 불가 -> 2번째 값은 첫 번째 값이 최대값
        firstStickerSelectDP[0] = sticker[0];
        firstStickerSelectDP[1] = sticker[0];
        
        for(int i = 2; i < firstStickerSelectDP.length; i++) {
            // 현재 위치에서의 최대는 이전 위치를 써서 현재껄 못쓰는 것과 2번째 전꺼에 현재 스티커(이전 스티커 사용 안함) 중 최대
            firstStickerSelectDP[i] = Math.max(firstStickerSelectDP[i-1], firstStickerSelectDP[i-2] + sticker[i]);
        }
        
         // case 2 : 두 번째 스티커를 먼저 선택한 경우 -> 첫번째 스티커 선택 불가
        // dp 배열 : 해당 스티커 선택 or 선택x일 때 최대 점수
        int[] secondStickerSelectDP = new int[sticker.length];
        
        // 두번째 스티커 선택 시 처음 스티커 선택 불가 -> 2번째 값은 첫 번째 값이 최대값
        secondStickerSelectDP[1] = sticker[1];
        
        for(int i = 2; i < firstStickerSelectDP.length; i++) {
            // 현재 위치에서의 최대는 이전 위치를 써서 현재껄 못쓰는 것과 2번째 전꺼에 현재 스티커(이전 스티커 사용 안함) 중 최대
            secondStickerSelectDP[i] = Math.max(secondStickerSelectDP[i-1], secondStickerSelectDP[i-2] + sticker[i]);
        }
        
        // 3. 두개 중 최대 값 구하기
        // first : 마지막 원소 값 사용 불가(마지막 스티커 사용 불가)
        // second : 마지막 원소 값 사용 가능(맨 처음 스티커 사용 안했으니 가능)
        return Math.max(firstStickerSelectDP[firstStickerSelectDP.length - 2], secondStickerSelectDP[secondStickerSelectDP.length - 1]);
    }
}
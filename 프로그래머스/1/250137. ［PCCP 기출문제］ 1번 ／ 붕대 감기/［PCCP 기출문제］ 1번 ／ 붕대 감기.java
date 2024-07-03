class Solution {
	int curTime = 0;
	int curHealth;
	int continuitySkillCount = 0;
	public int solution(int[] bandage, int health, int[][] attacks) {
		this.curHealth = health;
		int skillTime = bandage[0];
		int recoveryPerSec = bandage[1];
		int recoveryAdd = bandage[2];
		// 몬스터 공격 처리
		for(int i = 0; i < attacks.length; i++) {
			int attackTime = attacks[i][0];
			int attackDamage = attacks[i][1];
			// 최초 초기값 세팅 0초 & 공격 당하고 시점 조정안했으므로 시점 조정
			curTime++;
			// 공격 전 까지 체력 회복
			while (curTime < attackTime) {
				// 체력 증가
				curHealth += recoveryPerSec;
				// 연속 증가횟수 증가
				continuitySkillCount++;
				// 연속 증가 횟수가 스킬 횟수 이상만큼이라면 초기화 및 추가 회복
				if(continuitySkillCount == skillTime) {
					continuitySkillCount = 0;
					curHealth += recoveryAdd;
				}
				// 최대 체력 초과 시 최대 체력으로 조정
				if(curHealth > health) {
					curHealth = health;
				}
				// 시간 증가
				curTime++;
			}
			// 공격 처리
			curHealth = curHealth - attackDamage;
			continuitySkillCount = 0;
			// 죽었으면 -1 반환
			if(curHealth <= 0) {
				return -1;
			}
		}
		// 최종 체력 반환
		return curHealth;
	}
}
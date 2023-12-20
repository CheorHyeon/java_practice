class Solution {
	char[] skillArr;

	public int solution(String skill, String[] skill_trees) {
		int result = 0;
		// 1. 순서를 뽑아낸다.
		skillArr = skill.toCharArray();

		// 2. 각 스킬트리 하나당 순서를 지키는지 파악한다.(인덱스 추출, 순서 비교)
		for (String tree : skill_trees) {
			// 2-1. 지키면 +1 / 안지키면 넘어가기
			if (correctSkills(tree)) {
				result++;
			}
		}
		return result;
	}

	/*
		SkillTree를 입력받고 선행스킬 순서대로 찍었는지 검사하는 메서드
	 */
	private boolean correctSkills(String skillTree) {
		// 길이 1일땐 선행 스킬을 찍든 말든 그냥 통과
		if (skillArr.length == 1) {
			return true;
		}
		// 길이 2부터는 순서 맞게 했는지 검사하기 위한 StringBuilder로 구성해보기
		StringBuilder tempTreeBuilder = new StringBuilder();
		for (int i = 0; i < skillTree.length(); i++) {
			String skill = skillTree.charAt(i) + "";
			// 순서열에 포함되어 있다면 추가하기
			if (containCorrectSkillSet(skill)) {
				tempTreeBuilder.append(skill);
			}
		}
		// 검사한 Tree와 선행 주어진 스킬셋과 순서 비교
		String tempTree = tempTreeBuilder.toString();
		String correctTree = new String(skillArr);
		for (int i = 0; i < tempTree.length(); i++) {
			// 순서가 틀리면 바로 종료시키기
			if (tempTree.charAt(i) != correctTree.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	/*
		올바른 순서열에 포함되어 있는지 검사
	 */
	private boolean containCorrectSkillSet(String skill) {
		// 순서셋이 있으면 true 없으면 false
		for (int i = 0; i < skillArr.length; i++) {
			if (String.valueOf(skillArr[i]).equals(skill))
				return true;
		}
		return false;
	}
}
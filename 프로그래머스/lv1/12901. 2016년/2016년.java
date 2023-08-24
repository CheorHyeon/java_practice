import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

class Solution {
	public String solution(int a, int b) {
		// 입력으로 받은 월(a)과 일(b)로 LocalDate 객체 생성
		LocalDate targetDate = LocalDate.of(2016, a, b);

		// 해당 날짜의 요일을 가져옴 (DayOfWeek 열거형)
		DayOfWeek dayOfWeek = targetDate.getDayOfWeek();

		// 요일의 이름을 짧은 형식으로 가져와 대문자로 변환하여 반환
		return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US).toUpperCase();
	}
}
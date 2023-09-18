-- 코드를 입력하세요
# 7월 아이스크림 총 주문량 + 상반기 순 3개 맛 조회
# 상반기 - 맛이 기본키, 운송번호 외래키
# 7월 - 운송번호 기본키, 맛이 외래키
# 7월은 출하량이 많아 같은 맛의 아이스크림이라도 다른 출하번호를 가짐
SELECT F.FLAVOR as `FLAVOR`
from FIRST_HALF as F
join JULY as J
on F.FLAVOR = J.FLAVOR
group by F.FLAVOR
order by sum(F.TOTAL_ORDER + J.TOTAL_ORDER) DESC
limit 3;
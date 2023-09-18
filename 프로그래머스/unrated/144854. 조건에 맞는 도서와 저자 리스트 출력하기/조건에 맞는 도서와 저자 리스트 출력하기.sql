-- 코드를 입력하세요
# 저자 ID로 참고 가능
# '경제' 카테고리에 속하는 도서들의 도서 ID(BOOK_ID), 저자명(AUTHOR_NAME), 출판일(PUBLISHED_DATE) 리스트를 출력하는 SQL문을 작성해주세요. 결과는 출판일을 기준으로 오름차순 정렬해주세요.

SELECT B.BOOK_ID as `BOOK_ID`,
A.AUTHOR_NAME as `AUTHOR_NAME`,
DATE_FORMAT(B.PUBLISHED_DATE, '%Y-%m-%d') as `PUBLISHED_DATE`
from BOOK as B
inner join AUTHOR as A
on B.AUTHOR_ID = A.AUTHOR_ID
and B.category = '경제'
order by B.PUBLISHED_DATE;
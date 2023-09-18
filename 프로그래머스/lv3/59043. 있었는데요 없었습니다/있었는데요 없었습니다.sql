-- 코드를 입력하세요
# 보호 시작일 > 입양일, 보호 시작일이 빠른 순 조회

SELECT I.ANIMAL_ID as `ANIMAL_ID`, O.NAME as `NAME`
from ANIMAL_INS as I
inner join ANIMAL_OUTS as O
on I.ANIMAL_ID = O.ANIMAL_ID
and I.DATETIME > O.DATETIME
order by I.DATETIME;

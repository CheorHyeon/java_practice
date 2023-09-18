-- 코드를 입력하세요
SELECT I.NAME as `NAME`, I.DATETIME as `DATETIME`
from ANIMAL_INS as I
left join ANIMAL_OUTS as O
on I.ANIMAL_ID = O.ANIMAL_ID
where O.DATETIME is null
order by I.DATETIME
limit 3;
-- 코드를 입력하세요
# 보호소에 들어올 당시에는 중성화 되지 않았지만, 보호소를 나갈 당시에는 중성화된 동물의 아이디와 생물 종, 이름을 조회하는 아이디 순으로 조회하는 SQL 문을 작성해주세요.
SELECT I.ANIMAL_ID as `ANIMAL_ID`, I.ANIMAL_TYPE as `ANIMAL_TYPE`, I.NAME as `NAME`
from ANIMAL_INS as I join ANIMAL_OUTS as O
on I.ANIMAL_ID = O.ANIMAL_ID
where I.SEX_UPON_INTAKE <> O.SEX_UPON_OUTCOME
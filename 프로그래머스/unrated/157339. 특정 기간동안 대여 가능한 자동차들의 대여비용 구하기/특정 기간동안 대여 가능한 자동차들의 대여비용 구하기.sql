select C.car_ID,
C.CAR_TYPE,
truncate((C.daily_fee * (1-discount_rate/100)) * 30, 0) as FEE
from CAR_RENTAL_COMPANY_RENTAL_HISTORY as H
join CAR_RENTAL_COMPANY_CAR as C
on H.CAR_ID = C.CAR_ID
join CAR_RENTAL_COMPANY_DISCOUNT_PLAN as P
on C.CAR_TYPE = P.CAR_TYPE

where P.duration_type = '30일 이상'
     and (C.car_type = '세단' or C.car_type = 'SUV')
     # 11월에 빌린 차 빼기
     and C.car_id not in (
        select CAR_ID
         from CAR_RENTAL_COMPANY_RENTAL_HISTORY
         where END_DATE >= '2022-11-01'
            And START_DATE <= '2022-11-30'
     )
group By C.car_id
having FEE between 500000 and 2000000
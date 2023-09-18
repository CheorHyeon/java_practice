-- 코드를 입력하세요
# 정보 / 주문 정보
# 제품 ID 연관
SELECT P.PRODUCT_ID as `PRODUCT_ID`, P.PRODUCT_NAME as `PRODUCT_NAME`, sum(P.price * O.AMOUNT) as `TOTAL_SALES`
from FOOD_PRODUCT as P
join FOOD_ORDER as O
on P.PRODUCT_ID = O.PRODUCT_ID
where O.PRODUCE_DATE between "2022-05-01" and "2022-05-31" 
group by P.PRODUCT_ID
order by `TOTAL_SALES` DESC, `PRODUCT_ID`
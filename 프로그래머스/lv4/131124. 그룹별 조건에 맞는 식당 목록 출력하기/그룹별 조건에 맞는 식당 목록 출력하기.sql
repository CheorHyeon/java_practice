SELECT P.MEMBER_name, R.REVIEW_TEXT, DATE_FORMAT(R.REVIEW_DATE, '%Y-%m-%d') AS `REVIEW_DATE`
FROM MEMBER_PROFILE AS P
JOIN REST_REVIEW AS R
ON P.MEMBER_ID = R.MEMBER_ID
WHERE P.MEMBER_ID IN
        (
        select MEMBER_ID
        FROM REST_REVIEW
        GROUP BY MEMBER_ID
        HAVING COUNT(REVIEW_TEXT) =
                                    (
                                        SELECT COUNT(REVIEW_TEXT)
                                        FROM REST_REVIEW
                                        GROUP BY MEMBER_ID
                                        ORDER BY COUNT(REVIEW_TEXT) DESC
                                        LIMIT 1
                                    )
        )
order by `REVIEW_DATE` asc, R.REVIEW_TEXT asc
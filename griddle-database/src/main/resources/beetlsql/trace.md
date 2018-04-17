orderByDealDesc
===
    SELECT *
    #use("orderBy")#
    dealImprove desc
    #use("limit")#


orderByPriceScale
===
    SELECT *
    #use("orderBy")#
    priceImprove / (price - priceImprove)
    #use("limit")#
    
getProductTrace
===
    select *
    from trace
    where
        productId = #productId#
    and
        date_sub(curdate(), INTERVAL #interval# DAY) <= date(`createTime`)
    order by createTime


orderBy
===
    FROM
        trace
    WHERE
        date_sub(curdate(), INTERVAL #interval# DAY) <= date(`createTime`)
    GROUP BY productId
    ORDER BY

limit
===
    limit #start#,#count#
    
takeYesterdayTrace
===
    SELECT
    	#page("*")#
    FROM
    	trace t1,
    	(
    		SELECT
    			max(id) AS maxId,
    			min(id) AS minId
    		FROM
    			trace
    		WHERE
    			createTime > DATE_ADD(CURDATE(),interval -1 day)
    		AND createTime < CURDATE()
    	) t2
    WHERE
    	t1.id < t2.maxId
    AND t1.id > t2.minId
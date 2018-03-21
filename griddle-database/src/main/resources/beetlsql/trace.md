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
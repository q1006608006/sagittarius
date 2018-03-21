byIds
===

    SELECT
        *
    FROM
        ProductPreview
    WHERE
        id in (#join(ids)#)
        

searchTitle
===
    SELECT
    	*
    FROM
    	productpreview
    WHERE
    	MATCH (title) AGAINST (#title#)
    limit
        #start#,#count#
        
        
like
===
    select *
    from productpreview
    where
        category = #product.category#
    and
        match(title) against (#product.title#)
    limit
        #start#,#count#
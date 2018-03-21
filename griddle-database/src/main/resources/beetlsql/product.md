searchProbable
===
    SELECT
        *
    FROM
        Product
    WHERE
        MATCH (#use("againstCols")#) AGAINST (#keyWords#)
	
   
againstCols
===
    productName, brand, type, modelNo, artNo

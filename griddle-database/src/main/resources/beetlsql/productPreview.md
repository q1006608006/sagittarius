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
    select #page("*")#
    FROM
    	productpreview
    WHERE
    	MATCH (title) AGAINST (#title#)
    @if(!isEmpty(maxPrice)){
    and viewPrice <= #maxPrice#
    @}
    @if(!isEmpty(minPrice)){
    and viewPrice >= #minPrice#
    @}
        
        
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
        
searchTitleByKeyWords
===
    select #page("*")#
    from productpreview
    where
        MATCH(title) AGAINST ('
    @for(word in keyWords){
        +#text(word)# 
    @}
     ' IN BOOLEAN MODE)
    @if(!isEmpty(maxPrice)){
    and viewPrice <= #maxPrice#
    @}
    @if(!isEmpty(minPrice)){
    and viewPrice >= #minPrice#
    @}
     
containsTitle
===
    SELECT #page("*")# 
    FROM productpreview 
    WHERE LOCATE(#title#,title)
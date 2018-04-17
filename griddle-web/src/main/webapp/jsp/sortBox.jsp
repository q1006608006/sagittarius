<%--
  Created by IntelliJ IDEA.
  User: IVAN
  Date: 2018/4/9
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="spulist-sortbar" class="spulist-sortbar"><div class="m-sortbar">
    <div class="sort-row">
        <div class="sort-inner">
            <ul class="sorts">

                <li class="sort">
                    <a class="link" href="javascript:void(0)" onclick="sortBy(null,null)">默认</a>
                </li>

                <li class="sort">
                    <a class="link"  href="javascript:void(0)" onclick="sortBy('comment',true)">评价↓</a>
                </li>


                <li class="sort">
                    <a class="link" href="javascript:void(0)" onclick="sortBy('price',true)">价格从高到低</a>
                </li>

                <li class="sort">
                    <a class="link" href="javascript:void(0)" onclick="sortBy('price',false)">价格从低到高</a>
                </li>

            </ul>
            <div class="price-range common-dp J_LaterHover" data-hover-cls="common-dp-active" data-leave-timer="75">
                <div class="dp-panel J_hoverBtn">
                    <div class="range-default">
                        <input placeholder="¥" type="text" class="price-txt J_SortbarPriceInput" id="start_price" title="按价格区间筛选 最低价">
                        <span class="divider">-</span>
                        <input placeholder="¥" type="text" class="price-txt J_SortbarPriceInput" id="end_price" title="按价格区间筛选 最高价">
                        <a class="submit-btn J_PriceRangeSubmit" href="javascript:void(0)" onclick="sortByPrice()">确定</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    var LocString = String(window.document.location.href);
    if(getQueryStr("minPrice") !== '') {
        document.getElementById('start_price').value = getQueryStr("minPrice");
    }
    if(getQueryStr('maxPrice') !== '') {
        document.getElementById('end_price').value = getQueryStr("maxPrice");
    }
    function getQueryStr(str) {
        var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
        if (tmp = rs) {
            return tmp[2];
        }
        return "";
    }

    function getUrlParam(url,str) {
        var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(url), tmp;
        if (tmp = rs) {
            return tmp[2];
        }
        return "";
    }

    function setParam(url,key,value) {
        var regex = new RegExp('([&?]' + key + '=)[^&]*');
        if( null === value || 'undefined' === typeof(value) || '' === value) {
            return url.replace(regex,'');
        }
        if(getUrlParam(url,key) !== '') {
            return url.replace(regex,"$1" + value);
        } else {
            return url + (url.indexOf('?') > -1 ? '&' : '?') + key + "=" + value;
        }
    }

    function sortBy(orderPoint,inDescOrder) {
        var newHref = LocString;
        newHref = setParam(newHref,"orderPoint",orderPoint);
        newHref = setParam(newHref,"inDescOrder",inDescOrder);
        window.location.href =  newHref;
    }

    function sortByPrice() {
        var maxPrice = document.getElementById('end_price').value;
        var minPrice =  document.getElementById('start_price').value;
        console.log(maxPrice);
        console.log(minPrice);
        var url = LocString;
        url = setParam(url, 'maxPrice', maxPrice);
        url = setParam(url,'minPrice',minPrice);
        console.log(url);
        window.location.href = url;
    }
</script>
</body>
</html>

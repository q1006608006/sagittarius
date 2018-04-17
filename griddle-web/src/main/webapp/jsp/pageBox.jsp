<%--
  Created by IntelliJ IDEA.
  User: IVAN
  Date: 2018/4/9
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div style="width: 100%;text-align: center;">
    <div id='page'></div>
</div>
<script type="text/javascript" src="javascript/initPage.js"></script>
<script>
    var LocString = String(window.document.location.href);
    function getQueryStr(str) {
        var rs = new RegExp("(^|)" + str + "=([^&]*)(&|$)", "gi").exec(LocString), tmp;
        if (tmp = rs) {
            return tmp[2];
        }
// parameter cannot be found
        return "";
    }
    var pageNum = getQueryStr("pageNum");
    var totalCount = ${requestScope.totalCount};
    totalCount = totalCount > 100 * 20 ? 100*20 : totalCount;
    pageUtil.initPage('page',{
        totalCount:totalCount,//总页数，一般从回调函数中获取。如果没有数据则默认为1页
        curPage:pageNum === '' ? 1 : parseInt(pageNum),//初始化时的默认选中页，默认第一页。如果所填范围溢出或者非数字或者数字字符串，则默认第一页
        showCount:9,//分页栏显示的数量
        pageSizeList:[20],//自定义分页数，默认[5,10,15,20,50]
        defaultPageSize:20,//默认选中的分页数,默认选中第一个。如果未匹配到数组或者默认数组中，则也为第一个
        isJump:true,//是否包含跳转功能，默认false
        isPageNum:false,//是否显示分页下拉选择，默认false
        isPN:true,//是否显示上一页和下一面，默认true
        isFL:true,//是否显示首页和末页，默认true
        jump:function(curPage,pageSize){//跳转功能回调，传递回来2个参数，当前页和每页大小。如果没有设置分页下拉，则第二个参数永远为0。这里的this被指定为一个空对象，如果回调中需用到this请自行使用bind方法
            var href;
            if(pageNum === "") {
                if(LocString.indexOf('?') > -1) {
                    href = LocString + "&pageNum=" + curPage;
                } else {
                    href = LocString + "?pageNum=" + curPage;
                }
            } else {
                href = LocString.replace(/([&?])pageNum=\d*/,"$1pageNum=" + curPage);
            }
            window.location.href = href;
        }
    });
</script>
</body>
</html>

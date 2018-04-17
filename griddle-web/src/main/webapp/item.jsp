<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="el" uri="/el-addition" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html style="height: 100%" xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN"><!--<![endif]-->
<head>
    <!-- Basic Page Needs -->
    <meta charset="UTF-8">
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <title>item</title>

    <!-- Mobile Specific Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">

    <!-- Boostrap style -->
    <link rel="stylesheet" type="text/css" href="stylesheets/bootstrap.min.css">

    <!-- Theme style -->
    <link rel="stylesheet" type="text/css" href="stylesheets/style_bak.css">

    <!-- Reponsive -->
    <link rel="stylesheet" type="text/css" href="stylesheets/responsive.css">

</head>
<body>


<jsp:include page="jsp/header.jsp"/>

<jsp:include page="jsp/itemBox.jsp"/>

<jsp:include page="jsp/footer.jsp"/>

<!-- Javascript -->
<script type="text/javascript" src="javascript/jquery.min.js"></script>
<script type="text/javascript" src="javascript/tether.min.js"></script>
<script type="text/javascript" src="javascript/bootstrap.min.js"></script>
<script type="text/javascript" src="javascript/waypoints.min.js"></script>
<script type="text/javascript" src="javascript/jquery.circlechart.js"></script>
<script type="text/javascript" src="javascript/easing.js"></script>
<script type="text/javascript" src="javascript/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="javascript/owl.carousel.js"></script>
<script type="text/javascript" src="javascript/smoothscroll.js"></script>
<script type="text/javascript" src="javascript/jquery-ui.js"></script>
<script type="text/javascript" src="javascript/jquery.mCustomScrollbar.js"></script>
<script type="text/javascript" src="http://ditu.google.cn/maps/api/js?key=AIzaSyBtRmXKclfDp20TvfQnpgXSDPjut14x5wk&region=GB"></script>
<script type="text/javascript" src="javascript/gmap3.min.js"></script>
<script type="text/javascript" src="javascript/waves.min.js"></script>
<script type="text/javascript" src="javascript/jquery.countdown.js"></script>

<script type="text/javascript" src="javascript/main.js"></script>

<!-- charts-echarts.js -->
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>

<script>
    Date.prototype.Format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt))
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o){
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    };
    var previews = ${el:toJson(requestScope.preview)};
    var traces = ${el:toJson(requestScope.traceList)};
    console.log(traces);
    var xData = [],priceList = [],commentList = [], saleList = [];
    if(null != traces) {
        for (var i = 0; i < traces.length; i++) {
            var traceInfo = traces[i];
            xData.push(new Date(traceInfo.date).Format("yyyy-MM-dd"));
            priceList.push(traceInfo.price);
            commentList.push(traceInfo.commentCount);
            saleList.push(traceInfo.saleCount > 0 ? traceInfo.saleCount : 0);
        }
    }
    var dom = document.getElementById("container");
    if(traces != null) {
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            noDataLoadingOption: {
                text: '暂无数据',
                effect: 'bubble',
                effectOption: {
                    effect: {
                        n: 10
                    }
                }
            },
            title: {
                text: '历史信息'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['价格', '评论', '销售量']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                data: xData
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name: '价格',
                    type: 'line',
                    stack: '总量',
                    data: priceList
                },
                {
                    name: '销售量',
                    type: 'line',
                    stack: '总量',
                    data: saleList
                },
                {
                    name: '评论',
                    type: 'line',
                    stack: '总量',
                    data: commentList
                }
            ]
        };
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    }

</script>
</body>
</html>

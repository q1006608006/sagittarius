<%--
  Created by IntelliJ IDEA.
  User: IVAN
  Date: 2018/4/6
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="el" uri="/el-addition" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<section class="flat-product-detail">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="flexslider">
                    <ul class="slides">
                        <li data-thumb="images/product/flexslider/thumb/2.jpg" class="flex-active-slide">
                            <a href="${requestScope.preview.detailUrl}" target="_blank"><img src="${requestScope.preview.picUrl}" alt="image slider" /></a>
                        </li>
                    </ul><!-- /.slides -->
                </div><!-- /.flexslider -->
            </div><!-- /.col-md-6 -->
            <div class="col-md-6">
                <div class="product-detail">
                    <div class="header-detail">
                        <h4 class="name"><a href="${requestScope.preview.detailUrl}" target="_blank">${requestScope.preview.title}</a></h4>
                        <br>
                        <div class="category">
                            <span>来源：</span>${el:fixLocation(requestScope.preview.location)}
                        </div>
                    </div><!-- /.header-detail -->
                    <div class="footer-detail">
                        <div class="price quanlity-box">
                            <div class="quanlity">
                                <span>参考价格:&nbsp;</span><span class="sale">￥${requestScope.preview.viewPrice}</span>
                            </div>
                            <div class="quanlity">
                                运费: &nbsp;<span>${requestScope.preview.viewFee == null ? '':  requestScope.preview.viewFee}</span>
                            </div>
                        </div><!-- /.box-cart -->

                        <br>
                        <div class="quanlity-box">
                            <div class="quanlity">
                                评论数量：<span>${requestScope.preview.commentCount == null ? '暂无数据':requestScope.preview.commentCount}</span>
                            </div>
                            <div class="quanlity">
                                交易数量：<span>${(requestScope.preview.viewSales == null || requestScope.preview.viewSales == '-1')? '暂无数据':requestScope.preview.viewSales}</span>
                            </div>
                        </div><!-- /.quanlity-box -->

                        <div class="social-single">
                            店铺/卖家：&nbsp;
                            <a href="${requestScope.preview.shopLink}" target="_blank">
                                ${requestScope.preview.nick}
                            </a>
                        </div><!-- /.social-single -->
                    </div><!-- /.footer-detail -->
                </div><!-- /.product-detail -->
                <div id="container" style="height: 85%;width: 100%;">
                    暂无数据
                </div>
            </div><!-- /.col-md-6 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</section><!-- /.flat-product-detail -->
</body>
</html>

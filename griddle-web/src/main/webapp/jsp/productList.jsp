<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
</head>
<body>
<section class="flat-wishlist">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="wishlist">
                    <div class="title">
                        <h3>
                            <c:choose>
                                <c:when test="${requestScope.fromSearch}">
                                    以下是 '${requestScope.search}' 的搜索内容
                                </c:when>
                            </c:choose>
                        </h3>
                    </div>
                    <jsp:include page="sortBox.jsp"/>
                    <div class="wishlist-content">
                        <table class="table-wishlist">
                            <thead>
                            <tr>
<%--                                <th><img src="images/icons/list-1.png"></th>
                                &lt;%&ndash;<th>评论数</th>&ndash;%&gt;
                                <th><img src="images/icons/cart.png"></th>
                                <th><img src="images/icons/menu/03.png"></th>
                                <th></th>--%>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${requestScope.productList}" begin="0" end="19" step="1">
                                <tr>
                                    <td>
                                        <div class="delete">
                                            <a href="${item.locationUrl}" target="_blank" title="${item.location}">
                                                <img src="${item.locationPicUrl}" alt="" width="16px" height="16px">
                                            </a>
                                        </div>
                                        <div class="product">
                                            <a href="${item.preview.detailUrl}" target="_blank" title="${item.preview.title}">
                                                <div class="image">
                                                    <img src="${item.preview.picUrl}">
                                                </div>
                                                <div class="name"  style='width: 150px;'>
                                                        ${item.subTitle}
                                                </>
                                            </a>
<%--                                            <div class="info-text">
                                                <span>${item.preview.viewSales}</span>
                                            </div>--%>
                                            <div class="status-product">
                                                <span>${item.preview.commentCount}人评论</span>
                                            </div>
                                        </div>
                                    </td>
<%--                                    <td>
                                        <div>
                                                ${item.commentCount}人评论
                                        </div>
                                    </td>--%>
                                    <td>
                                        <div class="price">
                                            &yen;${item.preview.viewPrice}
                                        </div>
                                    </td>
                                    <td>
                                        <div class="status-product" style='width: 150px;'>
                                            <a href="${item.preview.shopLink}" target="_blank" title="${item.preview.nick}">
                                                <span>${item.preview.nick}</span>
                                            </a>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="add-cart" style='width: 150px;'>
                                            <a href="item?id=${item.preview.id}" title="">
                                                <img src="images/icons/add-cart.png" alt="">查看详情
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table><!-- /.table-wishlist -->
                    </div>
                </div><!-- /.wishlist -->
            </div><!-- /.col-md-12 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</section><!-- /.flat-wishlish -->
</body>
</html>




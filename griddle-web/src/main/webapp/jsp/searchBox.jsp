<%--
  Created by IntelliJ IDEA.
  User: IVAN
  Date: 2018/3/9
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<div class="header-middle">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <div id="logo" class="logo">
                    <a href="index.html" title="">
                        <img src="images/logos/logo.png" alt="">
                    </a>
                </div><!-- /#logo -->
            </div><!-- /.col-md-3 -->
            <div class="col-md-6">
                <div class="top-search">
                    <form action="search" method="get" class="form-search" accept-charset="utf-8">
                        <div class="cat-wrap">
                            <select name="model">
                                <option value="MaxMatch">模糊查找</option>
                                <option value="AutoSegWord">智能分词</option>
                                <option value="ForceKeyWord">关键字</option>
                            </select>
                        </div><!-- /.cat-wrap -->
                        <div class="box-search">
                            <input type="text" name="searchLine" placeholder="Search what you looking for ?" value="${requestScope.searchLine}">
                            <span class="btn-search">
                                <button type="submit" class="waves-effect"><img src="images/icons/search.png" alt=""></button>
                            </span>
                        </div><!-- /.box-search -->
                    </form><!-- /.form-search -->
                </div><!-- /.top-search -->
            </div><!-- /.col-md-6 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</div><!-- /.header-middle -->
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: IVAN
  Date: 2018/3/11
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<section class="flat-row flat-banner-box">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <div class="banner-box one-half">
                    <div class="inner-box">
                        <a href="${requestScope.events[0].link}" title="${requestScope.events[0].title}">
                            <img src="${requestScope.events[0].picUrl}" alt="" id="event_1">
                        </a>
                    </div><!-- /.inner-box -->
                    <div class="inner-box">
                        <a href="${requestScope.events[1].link}" title="${requestScope.events[1].title}">
                            <img src="${requestScope.events[1].picUrl}" alt="" id="event_2">
                        </a>
                    </div><!-- /.inner-box -->
                    <div class="clearfix"></div>
                </div><!-- /.box -->
                <div class="banner-box">
                    <div class="inner-box">
                        <a href="${requestScope.events[2].link}" title="${requestScope.events[2].title}">
                            <img src="${requestScope.events[2].picUrl}" alt="" id="event_3">
                        </a>
                    </div>
                </div><!-- /.box -->
            </div><!-- /.col-md-8 -->
            <div class="col-md-4">
                <div class="banner-box">
                    <div class="inner-box">
                        <a href="${requestScope.events[3].link}" title="${requestScope.events[3].title}">
                            <img src="${requestScope.events[3].picUrl}" alt="" id="event_4">
                        </a>
                    </div><!-- /.inner-box -->
                </div><!-- /.box -->
            </div><!-- /.col-md-4 -->
        </div><!-- /.row -->
    </div><!-- /.container -->
</section><!-- /.flat-banner-box -->
</body>
</html>

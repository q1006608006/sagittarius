<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<!--[if IE 8 ]><html class="ie" xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-CN" lang="zh-CN"><!--<![endif]-->
<head>
    <!-- Basic Page Needs -->
    <meta charset="UTF-8">
    <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
    <title>AddEdit</title>

    <!-- Mobile Specific Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

    <!-- Boostrap style -->
    <link rel="stylesheet" type="text/css" href="stylesheets/bootstrap.min.css">

    <!-- Theme style -->
    <link rel="stylesheet" type="text/css" href="stylesheets/style_bak.css">

    <!-- Reponsive -->
    <link rel="stylesheet" type="text/css" href="stylesheets/responsive.css">

    <link rel="stylesheet" type="text/css" href="stylesheets/edit.css">

</head>
<body>
<div class="boxed">
    <jsp:include page="jsp/header.jsp"/>

    <section class="flat-row flat-banner-box">
        <div class="container" id="xmTanDiv">
            <div class="row">
                <div class="col-md-8">
                    <div class="banner-box one-half">
                        <div class="inner-box">
                            <a href="${requestScope.events[0].link}" title="${requestScope.events[0].title}">
                                <img src="${requestScope.events[0].picUrl}" alt="" id="event_1">
                            </a>
                            <div class="edit-box">
                                <form enctype='multipart/form-data'>
                                    <h2>编辑</h2>
                                    <input type="text" name="eventImgUrl" placeholder="图片地址" id="event_url_1" value="${requestScope.events[0].picUrl}"/>
                                    <button type="button" onclick="previewByUrl(1)">预览</button>
                                    <input type="file" name="eventImgFile" accept="image/*" onchange="xmTanUploadImg('event_1',this)">
                                    <input name="eventTitle" type="text" placeholder="图片标题" value="${requestScope.events[0].title}"/>
                                    <input name="eventLink" type="text" placeholder="链接地址" value="${requestScope.events[0].link}"/>
                                    <button type="button" class="onsave">保存</button>
                                    <input type="hidden" name="eventIndex" value="1"/>
                                </form>
                            </div>
                        </div><!-- /.inner-box -->
                        <div class="inner-box">
                            <a href="${requestScope.events[1].link}" title="${requestScope.events[1].title}">
                                <img src="${requestScope.events[1].picUrl}" alt="" id="event_2">
                            </a>
                            <div class="edit-box">
                                <form enctype='multipart/form-data'>
                                    <h2>编辑</h2>
                                    <input type="text" name="eventImgUrl" placeholder="图片地址" id="event_url_2" value="${requestScope.events[1].picUrl}"/>
                                    <button type="button" onclick="previewByUrl(2)">预览</button>
                                    <input type="file" name="eventImgFile" accept="image/*" onchange="xmTanUploadImg('event_2',this)">
                                    <input name="eventTitle" type="text" placeholder="图片标题" value="${requestScope.events[1].title}"/>
                                    <input name="eventLink" type="text" placeholder="链接地址" value="${requestScope.events[1].link}"/>
                                    <button type="button" class="onsave">保存</button>
                                    <input type="hidden" name="eventIndex" value="2"/>
                                </form>
                            </div>
                        </div><!-- /.inner-box -->
                        <div class="clearfix"></div>
                    </div><!-- /.box -->
                    <div class="banner-box">
                        <div class="inner-box">
                            <a href="${requestScope.events[2].link}" title="${requestScope.events[2].title}">
                                <img src="${requestScope.events[2].picUrl}" alt="" id="event_3">
                            </a>
                            <div class="edit-box">
                                <form enctype='multipart/form-data'>
                                    <h2>编辑</h2>
                                    <input type="text" name="eventImgUrl" placeholder="图片地址" id="event_url_3" value="${requestScope.events[2].picUrl}"/>
                                    <button type="button" onclick="previewByUrl(3)">预览</button>
                                    <input type="file" name="eventImgFile" accept="image/*" onchange="xmTanUploadImg('event_3',this)">
                                    <input name="eventTitle" type="text" placeholder="图片标题" value="${requestScope.events[2].title}"/>
                                    <input name="eventLink" type="text" placeholder="链接地址" value="${requestScope.events[2].link}"/>
                                    <button type="button" class="onsave">保存</button>
                                    <input type="hidden" name="eventIndex" value="3"/>
                                </form>
                            </div>
                        </div>
                    </div><!-- /.box -->
                </div><!-- /.col-md-8 -->
                <div class="col-md-4">
                    <div class="banner-box">
                        <div class="inner-box">
                            <a href="${requestScope.events[3].link}" title="${requestScope.events[3].title}">
                                <img src="${requestScope.events[3].picUrl}" alt="" id="event_4">
                            </a>
                            <div class="edit-box">
                                <form enctype='multipart/form-data'>
                                    <h2>编辑</h2>
                                    <input type="text" name="eventImgUrl" placeholder="图片地址" id="event_url_4" value="${requestScope.events[3].picUrl}"/>
                                    <button type="button" onclick="previewByUrl(4)">预览</button>
                                    <input type="file" name="eventImgFile" accept="image/*" onchange="xmTanUploadImg('event_4',this)">
                                    <input name="eventTitle" type="text" placeholder="图片标题" value="${requestScope.events[3].title}"/>
                                    <input name="eventLink" type="text" placeholder="链接地址" value="${requestScope.events[3].link}"/>
                                    <button type="button" class="onsave">保存</button>
                                    <input type="hidden" name="eventIndex" value="4"/>
                                </form>
                            </div>
                        </div><!-- /.inner-box -->
                    </div><!-- /.box -->
                </div><!-- /.col-md-4 -->
            </div><!-- /.row -->
        </div><!-- /.container -->
    </section><!-- /.flat-banner-box -->

    <jsp:include page="jsp/footer.jsp"/>

</div>

<!-- Javascript -->
<script type="text/javascript" src="javascript/jquery.min.js"></script>
<script type="text/javascript" src="javascript/tether.min.js"></script>
<script type="text/javascript" src="javascript/bootstrap.min.js"></script>
<script type="text/javascript" src="javascript/waypoints.min.js"></script>
<!-- <script type="text/javascript" src="javascript/jquery.circlechart.js"></script> -->
<script type="text/javascript" src="javascript/easing.js"></script>
<script type="text/javascript" src="javascript/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="javascript/owl.carousel.js"></script>
<script type="text/javascript" src="javascript/smoothscroll.js"></script>
<!-- <script type="text/javascript" src="javascript/jquery-ui.js"></script> -->
<script type="text/javascript" src="javascript/jquery.mCustomScrollbar.js"></script>
<script type="text/javascript" src="http://ditu.google.cn/maps/api/js?key=AIzaSyBtRmXKclfDp20TvfQnpgXSDPjut14x5wk&region=GB"></script>
<script type="text/javascript" src="javascript/gmap3.min.js"></script>
<script type="text/javascript" src="javascript/waves.min.js"></script>
<script type="text/javascript" src="javascript/jquery.countdown.js"></script>

<script type="text/javascript" src="javascript/main.js"></script>


<script type="text/javascript">
    //判断浏览器是否支持FileReader接口
    if (typeof FileReader == 'undefined') {
        document.getElementById("xmTanDiv").InnerHTML = "<h1>当前浏览器不支持FileReader接口</h1>";
    }

    function previewByUrl(id) {
        var elId = '#event_' + id;
        var imgLabel = $(elId);
        elId = '#event_url_' + id;
        var url = $(elId).val();
        imgLabel.attr('src',url);
    }

    //选择图片，马上预览
    function xmTanUploadImg(id,obj) {
        $('#event_url_' + id.replace('event_','')).val('');
        var file = obj.files[0];
        var reader = new FileReader();
        reader.onload = function (e) {

            var img = document.getElementById(id);
            img.src = e.target.result;
            //或者 img.src = this.result;  //e.target == this
        }

        reader.readAsDataURL(file)
    }

    $(function () {
        $('.inner-box .onsave').click(function () {
            var formElm = $(this).parent()[0];
            $.ajax({
                url:  "api/resource",
                type: 'POST',
                data: new FormData(formElm),
                processData: false,
                contentType: false,
                dataType:"json",
                success : function(data) {
                    alert(data.msg);
                },
                error: function() {
                    alert('上传过程中出现异常，请稍后再试');
                }

            })
        });
    })
</script>

</body>
</html>

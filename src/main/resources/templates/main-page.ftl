<#ftl encoding='UTF-8'>
<html>
<head>
    <title>Водоканал мистера Ланштейна</title>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">Водоканал господина Ланштейна</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/registration/"><span class="glyphicon glyphicon-user"></span> Регистрация</a></li>
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Вход</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container main-box">
    <h2 class="title-center">Новости о которых говорят</h2>
    <hr>
    <#list model.news as newsElem>
        <div class="row">
            <div class="col-md-2">
                <h4>${newsElem.date}</h4>
            </div>
            <div class="col-md-6">
                <h3>${newsElem.title}</h3>
                <p>${newsElem.text}</p>
            </div>
            <div class="col-md-4">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <#list newsElem.newsImages as image>
                            <#if image?index == 0>
                                <li data-target="#myCarousel" data-slide-to="${image?index}" class="active"></li>
                            <#else>
                                <li data-target="#myCarousel" data-slide-to="${image?index}"></li>
                            </#if>
                        </#list>
                    </ol>

                    <div class="carousel-inner">
                        <#list newsElem.newsImages as image>
                            <#if image?index == 0>
                            <div class="item active">
                            <#else>
                            <div class="item">
                            </#if>
                                <img src="/file/image/${image.id}" height="200" width="3500">
                            </div>
                        </#list>
                    </div>

                    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>
        <hr>
    </#list>
</div>
</body>
</html>
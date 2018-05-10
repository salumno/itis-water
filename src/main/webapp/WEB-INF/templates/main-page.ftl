<#ftl encoding='UTF-8'>
<html>
<head>
    <title>Водоканал мистера Ланштейна</title>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Здесь будет Ваша реклама</h2>
    <div class="row">
        <div class="col-md-6">
            <h3></h3>
            <div>
                <#list model.news as newsElem>
                    <div>
                        <div class="row">
                            <div class="col-md-8">
                                <h3>${newsElem.title}</h3>
                            </div>
                            <div class="col-md-4">
                                <h4>${newsElem.date}</h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <p>${newsElem.text}</p>
                            </div>
                        </div>
                        <div class="row">
                            <#list newsElem.newsImages as image>
                                <div class="col-md-4">
                                    <img src="/file/image/${image.id}" height="100" width="100">
                                </div>
                            </#list>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
        <div class="col-md-6">

        </div>
    </div>
</div>
</body>
</html>
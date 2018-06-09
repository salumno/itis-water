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
                <li><a href="/user/tickets">Заявки</a></li>
                <li class="active"><a href="/user/appointment-form">Запись на прием</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/profile"><span class="glyphicon glyphicon-home"></span> Личный кабинет</a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="appointment-form">
        <h2 class="title-center">Запишитесь на прием</h2>
        <#if error??>
        <div class="alert alert-success alert-dismissible">
            <a href="" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Что-то пошло не так!</strong> ${error}.
        </div>
        </#if>
        <form method="post" action="/user/appointment-form">
            <div class="form-group">
                <select class="form-control" name="depId">
                <#list model.departments as department>
                    <option value="${department.id}">${department.name}</option>
                </#list>
                </select>
            </div>
            <div class="form-group">
                <textarea class="form-group" cols="62" wrap="hard" name="description" placeholder="Опишите цель записи на прием"></textarea>
            </div>
            <div class="form-group">
                <button class="btn btn-default" type="submit">Записаться на прием</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
<html>
<head>
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
            <a class="navbar-brand" href="/admin/">Админ-панель господина Ланштейна</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/">На главную</a></li>
                <li class="active"><a href="/admin/appointments">Записи на прием</a></li>
                <li><a href="/admin/employees">Сотрудники</a></li>
                <li><a href="/admin/news">Новости</a></li>
                <li><a href="/admin/tickets">Заявки граждан</a></li>
                <li><a href="/admin/departments">Отделы</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h2 class="title-center">Заявки</h2>
    <hr>
    <#list model.appointments as appointment>
        <div class="row">
            <div class="col-md-1">
                <p>${appointment.code}</p>
            </div>
            <div class="col-md-2">
                <p>${appointment.user.surname} ${appointment.user.name}</p>
            </div>
            <div class="col-md-3">
                <p>${appointment.department.name}</p>
            </div>
            <div class="col-md-3">
                <p>${appointment.description}</p>
            </div>
            <div class="col-md-3">
                <p>${appointment.dateTime}</p>
            </div>
        </div>
        <hr>
    </#list>
</div>
</body>
</html>
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
            <a class="navbar-brand" href="/">Водоканал господина Ланштейна</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/user/tickets">Заявки</a></li>
                <li><a href="/user/appointment-form">Запись на прием</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <#if model.isNotUser>
                    <li><a href="/admin/"><span class="glyphicon glyphicon-alert"></span> Админ-панель</a></li>
                </#if>
                <li class="active"><a href="/user/profile"><span class="glyphicon glyphicon-home"></span> Личный кабинет</a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h2 class="title-center">Здравствуйте, ${model.user.surname} ${model.user.name}</h2>
    <hr>
    <h3>Отправленные заявки:</h3>
    <#list model.user.tickets as ticket>
    <div class="row">
        <div class="col-md-11">
            <div class="panel">
                <div class="panel-heading">
                    <div class="pull-left">
                        <p>${ticket.date}</p>
                    </div>
                    <div class="pull-right">
                        <#if ticket.status == 'NOT_VIEWED'>
                            <p>Не просмотрено</p>
                        <#elseif ticket.status == 'VIEWED'>
                            <p>Просмотрено</p>
                        <#elseif ticket.status == 'SOLVED'>
                            <p>Решено</p>
                        <#elseif ticket.status == 'CLOSED'>
                            <p>Закрыто</p>
                        </#if>
                    </div>
                </div>
                <div class="panel-body">
                    <p>${ticket.text}</p>
                </div>
            </div>
        </div>
        <div class="col-md-1">
            <a href="/user/tickets/${ticket.id}" class="btn btn-lg">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
    </div>
    </#list>
</div>
</body>
</html>
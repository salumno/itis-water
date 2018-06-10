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
    <div class="appointment-table">
        <h2 class="title-center">Вы успешно записались на прием!</h2>
        <table class="table">
            <tbody>
            <tr>
                <td>Код приема: </td>
                <td>${appointment.code}</td>
            </tr>
            <tr>
                <td>Ваше имя: </td>
                <td>${appointment.user.surname} ${appointment.user.name}</td>
            </tr>
            <tr>
                <td>Описание: </td>
                <td>${appointment.description}</td>
            </tr>
            <tr>
                <td>Отдел: </td>
                <td>${appointment.department.name}</td>
            </tr>
            <tr>
                <td>Адрес: </td>
                <td>${appointment.department.address}</td>
            </tr>
            <tr>
                <td>Дата и время: </td>
                <td>${appointment.dateTime}</td>
            </tr>
            </tbody>
        </table>
        <a href="/file/appointment/${appointment.id}" class="btn btn-default" role="button" download><span class="glyphicon glyphicon-download"></span></a>
        <a class="btn btn-default" href="/user/profile">В личный кабинет</a>
    </div>
</div>
</body>
</html>
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
                <li class="active"><a href="/user/tickets">Заявки</a></li>
                <li><a href="/user/appointment-form">Запись на прием</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/profile"><span class="glyphicon glyphicon-home"></span> Личный кабинет</a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="ticket-form">
        <h2 class="title-center">Опишите свои жалобы,просьбы или пожелания здесь</h2>
        <form action="/user/tickets" method="post">
            <div class="form-group">
                <textarea class="form-control" name="text" placeholder="Начните писать здесь" cols="30" wrap="hard" rows="15" required></textarea>
            </div>
            <button class="btn btn-default" type="submit">Отправить</button>
        </form>
    </div>
</div>
</body>
</html>
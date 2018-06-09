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
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <#if model.isLoggedIn>
                    <li><a href="/user/profile"><span class="glyphicon glyphicon-home"></span> Личный кабинет</a></li>
                    <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
                <#else>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> Вход</a></li>
                </#if>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="registration-form">
        <#if error??>
            <div class="alert alert-danger">${error}</div>
        </#if>
        <h2 class="title-center">Регистрация на портале</h2>
        <form action="/registration/" method="post">
            <div class="form-group">
                <input class="form-control" name="surname" placeholder="Фамилия" type="text" required>
            </div>
            <div class="form-group">
                <input class="form-control" name="name" placeholder="Имя" type="text" required>
            </div>
            <div class="form-group">
                <input class="form-control" name="login" placeholder="Логин" type="text" required>
            </div>
            <div class="form-group">
                <input class="form-control" name="password" placeholder="Пароль" type="password" required>
            </div>
            <div class="form-group">
                <input class="form-control" name="passwordCheck" placeholder="Повторите пароль" type="password" required>
            </div>
            <button class="btn btn-default" type="submit">Регистрация</button>
        </form>
    </div>
</div>
</body>
</html>
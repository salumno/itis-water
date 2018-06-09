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
                <li><a href="/registration/"><span class="glyphicon glyphicon-user"></span> Регистрация</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="login-form">
        <#if model.error??>
        <div class="alert alert-danger">Неверный логин или пароль</div>
        </#if>
        <h2 class="title-center">Вход на портал</h2>
        <form  action="/login" method="post">
            <div class="form-group">
                <input class="form-control" name="login" placeholder="логин" type="text">
            </div>
            <div class="form-group">
                <input class="form-control" name="password" placeholder="пароль" type="password">
            </div>
            <button class="btn btn-default" type="submit">Вход</button>
        </form>
    </div>

</div>
</body>
</html>
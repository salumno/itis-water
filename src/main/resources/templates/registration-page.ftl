<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">
    <#if error??>
        <div class="alert alert-danger">${error}</div>
    </#if>
    <form action="/registration/" method="post">
        <div class="form-group">
            <input class="form-control" name="surname" placeholder="фамилия" type="text">
        </div>
        <div class="form-group">
            <input class="form-control" name="name" placeholder="имя" type="text">
        </div>
        <div class="form-group">
            <input class="form-control" name="login" placeholder="логин" type="text">
        </div>
        <div class="form-group">
            <input class="form-control" name="password" placeholder="пароль" type="password">
        </div>
        <div class="form-group">
            <input class="form-control" name="passwordCheck" placeholder="повторите пароль" type="password">
        </div>
        <button class="btn btn-default" type="submit">Регистрация</button>
    </form>
</body>
</html>
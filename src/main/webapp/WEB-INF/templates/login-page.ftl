<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
    <#if model.error??>
        <div class="alert alert-danger">Неверный логин или пароль</div>
    </#if>
    <form action="/login" method="post">
        <div class="form-group">
            <input class="form-control" name="login" placeholder="логин" type="text">
        </div>
        <div class="form-group">
            <input class="form-control" name="password" placeholder="пароль" type="password">
        </div>
        <button class="btn btn-default" type="submit">Вход</button>
    </form>
</body>
</html>
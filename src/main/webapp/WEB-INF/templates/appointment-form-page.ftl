<html>
<head>
    <title>Водоканал мистера Ланштейна</title>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <form method="post" action="/user/appointment-form">
        <div class="form-group">
            <select class="form-control" name="depId">
                <#list model.departments as department>
                    <option value="${department.id}">${department.name}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <textarea class="form-group" cols="25" wrap="hard" name="description"></textarea>
        </div>
        <div class="form-group">
            <button class="btn btn-default" type="submit">Записаться на прием</button>
        </div>
    </form>
</div>
</body>
</html>
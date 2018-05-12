<html>
<head>
    <title>Водоканал мистера Ланштейна</title>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Вы успешно записались на прием!</h2>
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
</body>
</html>
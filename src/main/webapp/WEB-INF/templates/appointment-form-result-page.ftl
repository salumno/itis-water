<html>
<head>
    <title>Водоканал мистера Ланштейна</title>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
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
</div>
</body>
</html>
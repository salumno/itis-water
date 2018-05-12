<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">
<h2>Заявки</h2>
<#list model.appointments as appointment>
    <div class="row">
        <div class="col-md-1">
            <p>${appointment.code}</p>
        </div>
        <div class="col-md-2">
            <p>${appointment.user.surname} ${appointment.user.name}</p>
        </div>
        <div class="col-md-3">
            <p>${appointment.department.name}</p>
        </div>
        <div class="col-md-3">
            <p>${appointment.description}</p>
        </div>
        <div class="col-md-3">
            <p>${appointment.dateTime}</p>
        </div>
    </div>
    <hr>
</#list>
</body>
</html>
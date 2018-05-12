<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">
<h2>Здравствуйте, ${model.user.surname} ${model.user.name}</h2>
<hr>
<h3>Отправленные заявки:</h3>
<#list model.user.tickets as ticket>
<div class="panel">
    <div class="panel-heading">
        <div class="pull-left">
            <p>${ticket.date}</p>
        </div>
        <div class="pull-right">
            <a href="/user/tickets/${ticket.id}">${ticket.status}</a>
        </div>
    </div>
    <div class="panel-body">
        <p>${ticket.text}</p>
    </div>
</div>
</#list>
</body>
</html>
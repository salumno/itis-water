<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">
<h3>Присланные заявки:</h3>
<#list model.tickets as ticket>
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
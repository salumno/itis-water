<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">

<#if model.ticket.isPresent()>

    <p>${model.ticket.text}</p>
    <#list model.ticket.messages as message>
        <div class="panel">
            <div class="panel-heading">
                <div class="pull-left">
                    <p>${message.author.surname} ${message.author.name}</p>
                </div>
                <div class="pull-right">
                    <p>${message.date}</p>

                </div>
            </div>
            <div class="panel-body">
                <p>${message.text}</p>
            </div>
        </div>
    </#list>

<#else>

    <h2>Данной заявки не существует</h2>

</#if>
</body>
</html>
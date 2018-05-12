<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
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
            <select id="select-status" onchange="statusChanged(${ticket.id})">
                <#list model.statuses as status>
                    <#if status = ticket.status>
                        <option value="${status}" selected>${status}</option>
                    <#else>
                        <option value="${status}">${status}</option>
                    </#if>
                </#list>
            </select>
        </div>
    </div>
    <div class="panel-body">
        <div class="row">
            <div class="col-md-11">
                <p>${ticket.text}</p>
            </div>
            <div class="col-md-1">
                <a href="/user/tickets/${ticket.id}"><span class="glyphicon glyphicon-chevron-right"></span></a>
            </div>
        </div>
    </div>
</div>
</#list>

<script>
    function statusChanged(ticketId) {
        var data = new FormData();
        data.append("ticketId", ticketId);
        data.append("status", $('#select-status').val());

        $.ajax({
            url: '/api/admin/tickets/status',
            type: 'POST',
            data: data,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            error: function () {
                console.log('statusChanged method has produced this error.')
            }
        });
    }
</script>
</body>
</html>
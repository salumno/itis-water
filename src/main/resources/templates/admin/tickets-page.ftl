<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/admin/">Админ-панель господина Ланштейна</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/">На главную</a></li>
                <li><a href="/admin/appointments">Записи на прием</a></li>
                <li><a href="/admin/employees">Сотрудники</a></li>
                <li><a href="/admin/news">Новости</a></li>
                <li class="active"><a href="/admin/tickets">Заявки граждан</a></li>
                <li><a href="/admin/departments">Отделы</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h3 class="title-center">Присланные заявки:</h3>
    <select id="status-filter" onchange="statusFilterChanged()">
        <option value="undefined" selected>Выберите статус</option>
         <#list model.statuses as status>
             <option value="${status}">${status}</option>
         </#list>
    </select>
    <hr>
    <div id="ticket-list">
    <#list model.tickets as ticket>
        <div class="panel">
            <div class="panel-heading">
                <div class="pull-left">
                    <p>${ticket.date}</p>
                </div>
                <div class="pull-right">
                    <select id="select-status-${ticket.id}" onchange="statusChanged(${ticket.id})">
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
    </div>
</div>
<script>
    var statuses;

    loadTicketStatuses();

    function loadTicketStatuses() {
        $.ajax({
            url: '/api/admin/tickets/status',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                statuses = data;
            },
            error: function () {
                console.log('loadTicketStatuses method has produced this error.')
            }
        });
    }

    function statusChanged(ticketId) {
        var data = new FormData();
        data.append("ticketId", ticketId);
        data.append("status", $('#select-status-' + ticketId).val());

        $.ajax({
            url: '/api/admin/tickets/status/update',
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

    function statusFilterChanged() {
        var currentStatus = $('#status-filter').val();
        $.ajax({
            url: '/api/admin/tickets/status/' + currentStatus,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                fillTicketList(data);
            },
            error: function () {
                console.log('statusFilterChanged method has produced this error.')
            }
        });
    }

    function fillTicketList(data) {
        $('#ticket-list').html('');
        var tickets = data;
        for (var i = 0; i < tickets.length; i++) {
            var ticket = tickets[i];
            $('#ticket-list').append(
                '<div class="panel">' +
                    '<div class="panel-heading">' +
                        '<div class="pull-left">' +
                            '<p>' + ticket.date + '</p>' +
                        '</div>' +
                        '<div class="pull-right">' +
                            createTicketSelect(ticket) +
                        '</div>' +
                    '</div>' +
                    '<div class="panel-body">' +
                        '<div class="row">' +
                            '<div class="col-md-11">' +
                                '<p>' + ticket.text + '</p>' +
                            '</div>' +
                            '<div class="col-md-1">' +
                                '<a href="/user/tickets/' + ticket.id + '"><span class="glyphicon glyphicon-chevron-right"></span></a>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>'
            );
        }
    }

    function createTicketSelect(ticket) {
        var options = '<select id="select-status-' + ticket.id + '" onchange="statusChanged(' + ticket.id + ')">';
        for (var i = 0; i < statuses.length; i++) {
            var currentStatus = statuses[i];
            if (currentStatus === ticket.status) {
                options = options + '<option value="' + currentStatus + '" selected>' + currentStatus + '</option>';
            } else {
                options = options + '<option value="' + currentStatus + '">' + currentStatus + '</option>';
            }
        }
        options = options + '</select>';
        return options;
    }
</script>
</body>
</html>
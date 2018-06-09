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
            <a class="navbar-brand" href="/">Водоканал господина Ланштейна</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="/user/tickets">Заявки</a></li>
                <li><a href="/user/appointment-form">Запись на прием</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/user/profile"><span class="glyphicon glyphicon-home"></span> Личный кабинет</a></li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h3 class="title-center">Заявка от ${model.ticket.date}</h3>
    <#if model.ticket.status == 'NOT_VIEWED'>
        <h4 class="title-center">Текущий статус: Не просмотрено</h4>
    <#elseif model.ticket.status == 'VIEWED'>
        <h4 class="title-center">Текущий статус: Просмотрено</h4>
    <#elseif model.ticket.status == 'SOLVED'>
        <h4 class="title-center">Текущий статус: Решено</h4>
    <#elseif model.ticket.status == 'CLOSED'>
        <h4 class="title-center">Текущий статус: Закрыто</h4>
    </#if>
    <#if model.ticket??>
        <div class="ticket-edit-form">
            <div id="ticket-text-wrap">
                <div class="row">
                    <div class="col-md-10">
                        <textarea id="ticket-text" cols="67" rows="3" wrap="hard" disabled>${model.ticket.text}</textarea>
                    </div>
                    <div class="col-md-2">
                        <div id="ticket-text-edit-button">
                            <button class="btn btn-warning" type="button" onclick="editTicketMessage(${model.ticket.id})"><span class="glyphicon glyphicon-edit"></span></button>
                        </div>
                        <div id="ticket-text-edit-button-group"></div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div id="ticket-messages" class="pre-scrollable ">
        <#list model.ticket.messages as message>
            <div class="panel">
                <#if message.author.role == 'ADMIN'>
                <div class="panel-heading panel-warning">
                    <div class="pull-right">
                        <p>${message.author.surname} ${message.author.name} (Admin)</p>
                    </div>
                    <div class="pull-left">
                        <p>${message.date}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <#else>
                <div class="panel-heading panel-success">
                    <div class="pull-left">
                        <p>${message.author.surname} ${message.author.name}</p>
                    </div>
                    <div class="pull-right">
                        <p>${message.date}</p>
                    </div>
                    <div class="clearfix"></div>
                </div>
                </#if>
                <div class="panel-body">
                    <p>${message.text}</p>
                </div>
            </div>
        </#list>
        </div>
        <#if model.ticket.status == "SOLVED">
            <div class="alert alert-success">
                Ваша проблема успешно решена. Спасибо, что написали нам!
            </div>
        <#elseif model.ticket.status == "CLOSED">
            <div class="alert alert-danger">
                Заявка закрыта. Спасибо, что написали нам!
            </div>
        <#else>
            <div class="ticket-message-form">
                <form id="ticket-message-form" method="post">
                    <div class="form-group">
                        <label for="ticket-message-text">Ваше сообщение:</label>
                        <textarea id="ticket-message-text" class="form-control" name="text" rows="4" cols="60" wrap="hard"></textarea>
                        <button type="button" class="btn btn-default form-control" onclick="sendTicketMessageToServer(${model.ticket.id})">Отправить сообщение</button>
                    </div>
                </form>
            </div>
        </#if>
    <#else>
    <h2 class="title-center">Данной заявки не существует</h2>
    </#if>
</div>

<script>
    var oldTicketText;

    function editTicketMessage(ticketId) {
        oldTicketText = $('#ticket-text').val();
        closeEditButton();
        openEditButtonGroup(ticketId);
        changeTicketTextDisabledProp(false);
    }

    function closeEditButton() {
        $('#ticket-text-edit-button').html('');
    }

    function openEditButton(ticketId) {
        var area = $('#ticket-text-edit-button');
        area.html('');
        area.append(
            '<button class="btn btn-warning" type="button" onclick="editTicketMessage(' + ticketId + ')"><span class="glyphicon glyphicon-edit"></span></button>'
        );
    }

    function openEditButtonGroup(ticketId) {
        var group = $('#ticket-text-edit-button-group');
        group.html('');
        group.append(
            '<button class="btn btn-success" type="button" onclick="saveEditChanges(' + ticketId + ')"><span class="glyphicon glyphicon-ok"></span></button>' +
            '<button class="btn btn-danger" type="button" onclick="cancelEditChanges(' + ticketId + ')"><span class="glyphicon glyphicon-remove"></span></button>'
        );
    }

    function closeEditButtonGroup() {
        $('#ticket-text-edit-button-group').html('');
    }

    function saveEditChanges(ticketId) {
        var data = new FormData();
        data.append("text", $('#ticket-text').val());

        $.ajax({
            url: '/api/tickets/' + ticketId + '/update',
            type: 'POST',
            data: data,
            contentType: false,
            processData: false,
            success: function () {
                closeEditButtonGroup();
                openEditButton(ticketId);
                changeTicketTextDisabledProp(true);
            },
            error: function () {
                console.log('ticket-page saveEditChanges method failed')
            }
        })
    }

    function cancelEditChanges(ticketId) {
        closeEditButtonGroup();
        openEditButton(ticketId);
        changeTicketTextDisabledProp(true);
        $('#ticket-text').val(oldTicketText);
    }

    function changeTicketTextDisabledProp(isDisabled) {
        $('#ticket-text').prop("disabled", isDisabled);
    }

    function sendTicketMessageToServer(ticketId) {
        var form = $("#ticket-message-form")[0];
        var data = new FormData(form);

        $.ajax({
            url: '/api/tickets/' + ticketId + '/message',
            type: 'POST',
            data: data,
            cache: false,
            dataType: 'json',
            contentType: false,
            processData: false,
            success: function (data) {
                form.reset();
                updateTicketMessages(data);
            },
            error: function () {
                console.log('admin ticket-page sendTicketMessageToServer method failed')
            }
        })
    }

    function updateTicketMessages(data) {
        $('#ticket-messages').html("");
        var ticketMessages = data;
        for (var i = 0; i < ticketMessages.length; i++) {
            var message = ticketMessages[i];
            var role = message.author.role;
            if (role === 'ADMIN') {
                $('#ticket-messages').append(
                        '<div class="panel">' +
                            '<div class="panel-heading panel-info">' +
                                '<div class="pull-right">' +
                                    '<p>' + message.author.surname + ' ' + message.author.name + ' (Admin)</p>' +
                                '</div>' +
                                '<div class="pull-left">' +
                                    '<p>' + message.date + '</p>' +
                                '</div>' +
                            '</div>' +
                            '<div class="panel-body">' +
                                '<p>' + message.text + '</p>' +
                            '</div>' +
                        '</div>'
                );
            } else {
                $('#ticket-messages').append(
                        '<div class="panel">' +
                            '<div class="panel-heading panel-success">' +
                                '<div class="pull-left">' +
                                    '<p>' + message.author.surname + ' ' + message.author.name + '</p>' +
                                '</div>' +
                                '<div class="pull-right">' +
                                    '<p>' + message.date + '</p>' +
                                '</div>' +
                            '</div>' +
                            '<div class="panel-body">' +
                                '<p>' + message.text + '</p>' +
                            '</div>' +
                        '</div>'
                );
            }

        }
    }
</script>
</body>
</html>
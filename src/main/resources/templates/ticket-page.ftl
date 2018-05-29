<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">

<#if model.ticket??>

    <div id="ticket-text-wrap">

        <div id="ticket-text-edit-button">
            <button class="btn btn-warning" type="button" onclick="editTicketMessage(${model.ticket.id})">изменить</button>
        </div>
        <textarea id="ticket-text" disabled>${model.ticket.text}</textarea>
        <div id="ticket-text-edit-button-group"></div>
    </div>

    <div id="ticket-messages" class="pre-scrollable">
        <#list model.ticket.messages as message>
            <div class="panel">
                <#if message.author.role == 'ADMIN'>
                <div class="panel-heading panel-info">
                    <div class="pull-right">
                        <p>${message.author.surname} ${message.author.name} (Admin)</p>
                    </div>
                    <div class="pull-left">
                        <p>${message.date}</p>
                    </div>
                </div>
                <#else>
                <div class="panel-heading panel-success">
                    <div class="pull-left">
                        <p>${message.author.surname} ${message.author.name}</p>
                    </div>
                    <div class="pull-right">
                        <p>${message.date}</p>
                    </div>
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
        <form id="ticket-message-form" method="post">
            <div class="form-group">
                <label for="ticket-message-text">Ваше сообщение:</label>
                <textarea id="ticket-message-text" class="form-control" name="text" rows="10"></textarea>
                <button type="button" class="btn btn-default form-control" onclick="sendTicketMessageToServer(${model.ticket.id})">Отправить сообщение</button>
            </div>
        </form>
    </#if>
<#else>

    <h2>Данной заявки не существует</h2>

</#if>

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
            '<button class="btn btn-warning" type="button" onclick="editTicketMessage(' + ticketId + ')">изменить</button>'
        );
    }

    function openEditButtonGroup(ticketId) {
        var group = $('#ticket-text-edit-button-group');
        group.html('');
        group.append(
            '<button class="btn btn-success" type="button" onclick="saveEditChanges(' + ticketId + ')">сохранить</button>' +
            '<button class="btn btn-danger" type="button" onclick="cancelEditChanges(' + ticketId + ')">отмена</button>'
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
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/resources/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/resources/js/jquery.js"></script>
    <script src="/resources/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">

<#if model.ticket??>

    <p>${model.ticket.text}</p>
    <div id="ticket-messages">
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
    </div>
    <form id="ticket-message-form" method="post">
        <div class="form-group">
            <label for="ticket-message-text">Ваше сообщение:</label>
            <textarea id="ticket-message-text" class="form-control" name="text" rows="10"></textarea>
            <button type="button" class="btn btn-default form-control" onclick="sendTicketMessageToServer(${model.ticket.id})">Отправить сообщение</button>
        </div>

    </form>
<#else>

    <h2>Данной заявки не существует</h2>

</#if>

<script>
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
            $('#ticket-messages').append(
                '<div class="panel">' +
                    '<div class="panel-heading">' +
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
</script>
</body>
</html>
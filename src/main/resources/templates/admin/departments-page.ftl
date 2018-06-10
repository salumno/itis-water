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
                <li><a href="/admin/tickets">Заявки граждан</a></li>
                <li class="active"><a href="/admin/departments">Отделы</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <h2 class="title-center">Отделы</h2>
    <div>
        <h3>Добавить новый отдел</h3>
        <form id="department-add-form">
            <div class="form-group">
                <input class="form-control" name="name" placeholder="название" type="text">
            </div>
            <div class="form-group">
                <input class="form-control" name="address" placeholder="адрес" type="text">
            </div>
            <button class="btn btn-success" type="button" onclick="uploadDepartmentToServer()">добавить</button>
        </form>
    </div>
    <div id="department-list">
    <#list model.departments as department>
        <div class="row">
            <div class="col-md-3">
                <input id="department-name-${department.id}" type="text" value="${department.name}" disabled>
            </div>
            <div class="col-md-4">
                <input id="department-address-${department.id}" type="text" value="${department.address}" disabled>
            </div>
            <div class="col-md-3">
                <div id="department-edit-${department.id}">
                    <button id="department-edit-button-${department.id}" class="btn btn-warning" type="button" onclick="editDepartment(${department.id})">изменить</button>
                </div>
            </div>
            <div class="col-md-2">
                <button class="btn btn-danger" type="button" onclick="deleteDepartment(${department.id})">удалить</button>
            </div>
        </div>
        <hr>
    </#list>
    </div>
</div>
<script>
    var oldName;
    var oldAddress;

    function uploadDepartmentToServer() {
        var form = $('#department-add-form')[0];
        var data = new FormData(form);
        $.ajax({
            url: '/api/departments',
            type: 'POST',
            dataType: 'json',
            data: data,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function (data) {
                fillDepartmentList(data);
                form.reset();
            },
            error: function () {
                console.log("uploadDepartmentToServer produced the error.");
            }
        })
    }

    function editDepartment(departmentId) {
        enableInputFields(departmentId);
        enableEditProcessButtons(departmentId);
    }

    function submitEditDepartment(departmentId) {
        var updatedName = $('#department-name-' + departmentId).val();
        var updatedAddress = $('#department-address-' + departmentId).val();
        var data = new FormData();
        data.append("updatedName", updatedName);
        data.append("updatedAddress", updatedAddress);
        $.ajax({
            url: '/api/departments/' + departmentId + "/update",
            type: 'POST',
            data: data,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function () {
                disableInputFields(departmentId);
                disableEditProcessButtons(departmentId);
            },
            error: function () {
                console.log("submitEditDepartment produced the error.");
            }
        });
    }

    function enableInputFields(departmentId) {
        changeDisabledProp(departmentId, false)
    }

    function enableEditProcessButtons(departmentId) {
        var editButtonGroup = $('#department-edit-' + departmentId);
        oldName = $('#department-name-' + departmentId).val();
        oldAddress = $('#department-address-' + departmentId).val();
        editButtonGroup.html('');
        editButtonGroup.append(
                '<button id="department-edit-button-ok-' + departmentId + '" class="btn btn-success" type="button" onclick="submitEditDepartment(' + departmentId + ')">ok</button>' +
                '<button id="department-edit-button-cancel-' + departmentId + '" class="btn btn-danger" type="button" onclick="cancelEditDepartment(' + departmentId + ')">отмена</button>'
        );
    }

    function disableInputFields(departmentId) {
        changeDisabledProp(departmentId, true);
    }

    function disableEditProcessButtons(departmentId) {
        var editButtonGroup = $('#department-edit-' + departmentId);
        editButtonGroup.html('');
        editButtonGroup.append(
            '<button id="department-edit-button-' + departmentId + '" class="btn btn-warning" type="button" onclick="editDepartment(' + departmentId + ')">изменить</button>'
        );
    }

    function changeDisabledProp(departmentId, isDisabled) {
        $('#department-name-' + departmentId).prop("disabled", isDisabled);
        $('#department-address-' + departmentId).prop("disabled", isDisabled);
    }

    function cancelEditDepartment(departmentId) {
        $('#department-name-' + departmentId).val(oldName);
        $('#department-address-' + departmentId).val(oldAddress);
        disableInputFields(departmentId);
        disableEditProcessButtons(departmentId);
    }

    function deleteDepartment(departmentId) {
        $.ajax({
            url: '/api/departments/' + departmentId + '/delete',
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                fillDepartmentList(data);
            },
            error: function () {
                console.log("deleteDepartment produced the error.");
            }
        })
    }

    function fillDepartmentList(data) {
        $('#department-list').html('');
        var departments = data;
        for (var i = 0; i < departments.length; i++) {
            var department = departments[i];
            $('#department-list').append(
                '<div class="row">' +
                    '<div class="col-md-3">' +
                        '<input id="department-name-' + department.id + '" type="text" value="' + department.name + '" disabled>' +
                    '</div>' +
                    '<div class="col-md-4">' +
                        '<input id="department-address-' + department.id + '" type="text" value="' + department.address + '" disabled>' +
                    '</div>' +
                    '<div class="col-md-3">' +
                        '<div id="department-edit-' + department.id + '">' +
                            '<button id="department-edit-button-' + department.id + '" class="btn btn-warning" type="button" onclick="editDepartment(' + department.id + ')">изменить</button>' +
                        '</div>' +
                    '</div>' +
                    '<div class="col-md-2">' +
                        '<button class="btn btn-danger" type="button" onclick="deleteDepartment(' + department.id + ')">удалить</button>' +
                    '</div>' +
                '</div>' +
                '<hr>'
            );
        }
    }
</script>
</body>
</html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">
    <div class="row">
        <div class="col-md-10"></div>
        <div class="col-md-2">
            <button class="btn btn-danger" onclick="fadeAddForm()">Добавить сотрудника</button>
        </div>
    </div>
    <form id="add-form" style="display: none">
        <div class="form-group">
            <label for="surname">Фамилия</label>
            <input class="form-control" id="surname" name="surname" type="text" required>

            <label for="name">Имя</label>
            <input class="form-control" id="name" name="name" type="text" required>

            <label for="patro">Отчество</label>
            <input class="form-control" id="patro" name="patro" type="text" required>

            <label for="email">Email</label>
            <input class="form-control" id="email" name="email" type="email" required>

            <label for="inn">ИНН</label>
            <input class="form-control" id="inn" name="inn" type="number" required>

            <label for="salary">Зарплата</label>
            <input class="form-control" id="salary" name="salary" type="number" required>

            <label for="department">Отдел</label>
            <select class="form-control" id="department" name="departmentId">
                <#list model.departments as department>
                <option value="${department.id}">${department.name}</option>
                </#list>
            </select>

            <label for="role">Роль</label>
            <select class="form-control" id="role" name="role">
                <#list model.roles as role>
                <option value="${role}">${role}</option>
                </#list>
            </select>

            <label for="comment">Комментарий</label>
            <textarea class="form-control" id="comment" name="comment" rows="5" cols="20" wrap="hard" required></textarea>

            <button class="btn btn-success form-control" type="button" onclick="addEmployee()">Добавить</button>
        </div>
    </form>
    <hr>
    <input id="employee-name-filter" type="text" placeholder="Поиск по имени" onchange="employeeFilterChange()">
    <hr>
    <div id="employee-list">
        <#list model.employees as employee>
            <form id="employee-form-${employee.id}">
                <div class="row">
                    <div class="col-md-10"></div>
                    <div class="col-md-2">
                        <button id="edit-button-${employee.id}" class="btn btn-danger" type="button" onclick="editEmployee(${employee.id})">Изменить</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <input id="surname-${employee.id}" name="surname" type="text" value="${employee.surname}" disabled>
                        <input id="name-${employee.id}" name="name" type="text" value="${employee.name}" disabled>
                        <input id="patro-${employee.id}" name="patro" type="text" value="${employee.patro}" disabled>
                    </div>
                    <div class="col-md-4">
                        <input id="email-${employee.id}" name="email" type="email" value="${employee.email}" disabled>
                        <input id="inn-${employee.id}" name="inn" type="number" value="${employee.inn}" disabled>
                        <input id="salary-${employee.id}" name="salary" type="text" value="${employee.salary}" disabled>
                    </div>
                    <div class="col-md-4">
                        <div id="wrap-department-${employee.id}">
                            <input id="department-${employee.id}" name="department" type="text" value="${employee.department.name}" disabled>
                        </div>
                        <div id="wrap-role-${employee.id}">
                            <input id="role-${employee.id}" name="role" type="text" value="${employee.role}" disabled>
                        </div>
                        <div id="wrap-status-${employee.id}">
                            <input id="status-${employee.id}" name="status" type="text" value="${employee.status}" disabled>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <textarea id="comment-${employee.id}" name="comment" wrap="hard" cols="20" disabled>${employee.comment}</textarea>
                    </div>
                </div>
                <button id="edit-submit-button-${employee.id}" class="btn btn-success" type="button" onclick="submitEdit(${employee.id})" style="display: none">Сохранить изменения</button>
                <button id="edit-cancel-button-${employee.id}" class="btn btn-danger" type="button" onclick="cancelEdit(${employee.id})" style="display: none">Отмена</button>
                <hr>
            </form>
        </#list>
    </div>
</body>
<script>
    var statuses;
    var roles;
    var departments;

    loadRolesFromServer();
    loadStatusesFromServer();
    loadDepartmentsFromServer();

    function loadStatusesFromServer() {
        $.ajax({
            url: '/api/admin/employees/statuses',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                statuses = data;
            },
            error: function () {
                console.log('loadStatusesFromServer function produced the error.');
            }
        });
    }

    function loadRolesFromServer() {
        $.ajax({
            url: '/api/admin/employees/roles',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                roles = data;
            },
            error: function () {
                console.log('loadRolesFromServer function produced the error.');
            }
        });
    }

    function loadDepartmentsFromServer() {
        $.ajax({
            url: '/api/departments',
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                departments = data;
            },
            error: function () {
                console.log('loadDepartmentsFromServer function produced the error.');
            }
        });
    }

    function employeeFilterChange() {
        var name = $('#employee-name-filter').val();
        if (name.length === 0) {
            name = "undefined";
        }
        $.ajax({
            url: '/api/admin/employees/filter/' + name,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                fillEmployeeList(data);
            },
            error: function () {
                console.log('employeeFilterChange function produced the error.');
            }
        });
    }

    function fadeAddForm() {
        $('#add-form').fadeToggle();
    }

    function addEmployee() {
        var form = $('#add-form')[0];
        var data = new FormData(form);

        $.ajax({
            url: '/api/admin/employees',
            type: 'POST',
            data: data,
            dataType: 'json',
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function (data) {
                fillEmployeeList(data);
                resetAddForm();
            },
            error: function () {
                console.log('addEmployee function produced the error.');
            }
        })
    }

    function fillEmployeeList(data) {
        var employees = data;
        $('#employee-list').html('');
        for (var i = 0; i < employees.length; i++) {
            var employee = employees[i];
            $('#employee-list').append(
            '<form id="employee-form-' + employee.id + '">' +
                '<div class="row">' +
                    '<div class="col-md-10"></div>' +
                    '<div class="col-md-2">' +
                        '<button id="edit-button-' + employee.id +'" class="btn btn-danger" type="button" onclick="editEmployee(' + employee.id + ')">Изменить</button>' +
                    '</div>' +
                '</div>' +
                '<div class="row">' +
                    '<div class="col-md-4">' +
                        '<input id="surname-' + employee.id + '" name="surname" type="text" value="' + employee.surname + '" disabled>' +
                        '<input id="name-' + employee.id + '" name="name" type="text" value="' + employee.name + '" disabled>' +
                        '<input id="patro-' + employee.id + '" name="patro" type="text" value="' + employee.patro + '" disabled>' +
                    '</div>' +
                    '<div class="col-md-4">' +
                        '<input id="email-' + employee.id + '" name="email" type="email" value="' + employee.email + '" disabled>' +
                        '<input id="inn-' + employee.id + '" name="inn" type="number" value="' + employee.inn + '" disabled>' +
                        '<input id="salary-' + employee.id + '" name="salary" type="text" value="' + employee.salary + '" disabled>' +
                    '</div>' +
                    '<div class="col-md-4">' +
                        '<div id="wrap-department-' + employee.id + '">' +
                            '<input id="department-' + employee.id + '" name="department" type="text" value="' + employee.department.name + '" disabled>' +
                        '</div>' +
                        '<div id="wrap-role-' + employee.id + '">' +
                            '<input id="role-' + employee.id + '" name="role" type="text" value="' + employee.role + '" disabled>' +
                        '</div>' +
                        '<div id="wrap-status-' + employee.id + '">' +
                            '<input id="status-' + employee.id + '" name="status" type="text" value="' + employee.status + '" disabled>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
                '<div class="row">' +
                    '<div class="col-md-12">' +
                        '<textarea id="comment-' + employee.id + '" name="comment" wrap="hard" cols="20" disabled>' + employee.comment + '</textarea>' +
                    '</div>' +
                '</div>' +
                '<button id="edit-submit-button-' + employee.id +'" class="btn btn-success" type="button" onclick="submitEdit(' + employee.id + ')" style="display: none">Сохранить изменения</button>' +
                '<button id="edit-cancel-button-' + employee.id +'" class="btn btn-danger" type="button" onclick="cancelEdit(' + employee.id + ')" style="display: none">Отмена</button>' +
                '<hr>' +
            '</form>'
            );
        }
    }

    function resetAddForm() {
        $('#add-form')[0].reset();
    }

    function editEmployee(employeeId) {
        showEditSubmitButton(employeeId);
        showEditCancelButton(employeeId);
        hideEditButton(employeeId);
        changeDisabledProp(employeeId, false);

        var userRole = $('#role-' + employeeId).val();
        var userStatus = $('#status' + employeeId).val();
        var userDepartment = $('#department-' + employeeId).val();

        prepareDepartmentSelectElement(employeeId, userDepartment);
        prepareRoleSelectElement(employeeId, userRole);
        prepareStatusSelectElement(employeeId, userStatus);
    }

    function showEditSubmitButton(employeeId) {
        $('#edit-submit-button-' + employeeId).show();
    }

    function hideEditSubmitButton(employeeId) {
        $('#edit-submit-button-' + employeeId).hide();
    }

    function showEditCancelButton(employeeId) {
        $('#edit-cancel-button-' + employeeId).show();
    }

    function hideEditCancelButton(employeeId) {
        $('#edit-cancel-button-' + employeeId).hide();
    }

    function showEditButton(employeeId) {
        $('#edit-button-' + employeeId).show();
    }

    function hideEditButton(employeeId) {
        $('#edit-button-' + employeeId).hide();
    }

    function submitEdit(employeeId) {
        var form = $('#employee-form-' + employeeId)[0];
        var data = new FormData(form);

        $.ajax({
            url: '/api/admin/employees/' + employeeId + '/update',
            type: 'POST',
            data: data,
            enctype: 'multipart/form-data',
            processData: false,
            contentType: false,
            success: function () {
                prepareFieldsAfterEdit(employeeId);
            },
            error: function () {
                console.log('submitEdit function produced the error.');
            }
        })
    }

    function cancelEdit(employeeId) {
        prepareFieldsAfterEdit(employeeId);

        $.ajax({
            url: '/api/admin/employees/' + employeeId,
            type: 'GET',
            dataType: 'json',
            success: function (data) {
                fillEmployee(data);
            },
            error: function () {
                console.log('cancelEdit function produced the error.');
            }
        })
    }

    function fillEmployee(data) {
        var employeeId = data.id;
        $('#surname-' + employeeId).val(data.surname);
        $('#name-' + employeeId).val(data.name);
        $('#patro-' + employeeId).val(data.patro);
        $('#email-' + employeeId).val(data.email);
        $('#inn-' + employeeId).val(data.inn);
        $('#salary-' + employeeId).val(data.salary);
        $('#comment-' + employeeId).val(data.comment);
        $('#status-' + employeeId).val(data.status);
        $('#role-' + employeeId).val(data.role);
        $('#department-' + employeeId).val(data.department.name);
    }

    function prepareFieldsAfterEdit(employeeId) {
        hideEditSubmitButton(employeeId);
        hideEditCancelButton(employeeId);
        showEditButton(employeeId);
        changeDisabledProp(employeeId, true);

        var userRole = $('#role-select-' + employeeId).val();
        var userStatus = $('#status-select-' + employeeId).val();
        var userDepartment = $('#department-select-' + employeeId + ' :selected').text();

        prepareDepartmentInputElement(employeeId, userDepartment);
        prepareRoleInputElement(employeeId, userRole);
        prepareStatusInputElement(employeeId, userStatus);
    }

    function changeDisabledProp(employeeId, isDisabled) {
        $('#surname-' + employeeId).prop("disabled", isDisabled);
        $('#name-' + employeeId).prop("disabled", isDisabled);
        $('#patro-' + employeeId).prop("disabled", isDisabled);
        $('#email-' + employeeId).prop("disabled", isDisabled);
        $('#inn-' + employeeId).prop("disabled", isDisabled);
        $('#salary-' + employeeId).prop("disabled", isDisabled);
        $('#comment-' + employeeId).prop("disabled", isDisabled);
    }


    function prepareRoleSelectElement(employeeId, userRole) {
        var wrapper = $('#wrap-role-' + employeeId);
        wrapper.html('');
        var select = '<select id="role-select-' + employeeId + '" name="role">';
        for (var i = 0; i < roles.length; i++) {
            var role = roles[i];
            if (role === userRole) {
                select = select + '<option value="' + role + '" selected>' + role + '</option>';
            } else {
                select = select + '<option value="' + role + '">' + role + '</option>'
            }
        }
        select = select + '</select>';
        wrapper.append(select);
    }

    function prepareStatusSelectElement(employeeId, userStatus) {
        var wrapper = $('#wrap-status-' + employeeId);
        wrapper.html('');
        var select = '<select id="status-select-' + employeeId + '" name="status">';
        for (var i = 0; i < statuses.length; i++) {
            var status = statuses[i];
            if (status === userStatus) {
                select = select + '<option value="' + status + '" selected>' + status + '</option>';
            } else {
                select = select + '<option value="' + status + '">' + status + '</option>'
            }
        }
        select = select + '</select>';
        wrapper.append(select);
    }

    function prepareDepartmentSelectElement(employeeId, userDepartment) {
        var wrapper = $('#wrap-department-' + employeeId);
        wrapper.html('');
        var select = '<select id="department-select-' + employeeId + '" name="departmentId">';
        for (var i = 0; i < departments.length; i++) {
            var department = departments[i];
            if (department.name === userDepartment) {
                select = select + '<option value="' + department.id + '" selected>' + department.name + '</option>';
            } else {
                select = select + '<option value="' + department.id + '">' + department.name + '</option>'
            }
        }
        select = select + '</select>';
        wrapper.append(select);
    }

    function prepareRoleInputElement(employeeId, userRole) {
        var wrapper = $('#wrap-role-' + employeeId);
        wrapper.html('');
        var input = '<input id="role-' + employeeId + '" name="role" value="' + userRole + '" disabled>';
        wrapper.append(input);
    }

    function prepareStatusInputElement(employeeId, userStatus) {
        var wrapper = $('#wrap-status-' + employeeId);
        wrapper.html('');
        var input = '<input id="status-' + employeeId + '" name="status" value="' + userStatus + '" disabled>';
        wrapper.append(input);
    }

    function prepareDepartmentInputElement(employeeId, userDepartment) {
        var wrapper = $('#wrap-department-' + employeeId);
        wrapper.html('');
        var input = '<input id="department-' + employeeId + '" name="department" value="' + userDepartment + '" disabled>';
        wrapper.append(input);
    }

</script>
</html>
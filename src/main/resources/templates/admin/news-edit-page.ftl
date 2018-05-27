<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body class="container">
    <div id="alert-message"></div>
    <form id="news-edit-form">
        <input name="title" type="text" value="${model.news.title}">
        <h3>${model.news.date}</h3>
        <textarea name="text">${model.news.text}</textarea>
        <button class="btn btn-success" type="button" onclick="saveNewsChanges(${model.news.id})">сохранить изменения</button>
        <a href="/admin/news" class="btn btn-danger">к списку новостей</a>
    </form>
    <hr>
    <form id="news-image-form">
        <div class="form-group">
            <input id="files" type="file" multiple="multiple" name="files" accept="image/*"/>
        </div>
        <button class="btn btn-default" type="button" onclick="uploadNewsImages(${model.news.id})">добавить фото</button>
    </form>
    <div id="news-image-list">
        <#list model.news.newsImages as image>
            <img src="/file/image/${image.id}" height="100" width="100">
            <button type="button" onclick="deleteNewsImage(${image.newsId}, ${image.id})">удалить</button>
            <hr>
        </#list>
    </div>
</body>
<script>
    function saveNewsChanges(newsId) {
        var form = $("#news-edit-form")[0];
        var data = new FormData(form);

        $.ajax({
            url: '/api/news/' + newsId + '/update',
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            success: function () {
                createAlert()
            },
            error: function () {
                console.log('admin news-page saveNewsChanges method failed')
            }
        })
    }

    function createAlert() {
        $('#alert-message').html('');
        $('#alert-message').append(
            '<div class="alert alert-success">' +
                'Данные успешно изменены!' +
            '</div>'
        );
    }

    function deleteNewsImage(newsId, imageId) {
        $.ajax({
            url: '/api/news/' + newsId + '/image/' + imageId + "/delete",
            type: 'POST',
            dataType: 'json',
            success: function (data) {
                updateImageList(data);
            },
            error: function () {
                console.log('admin news-page deleteNewsImage method failed')
            }
        })
    }

    function uploadNewsImages(newsId) {
        var form = $("#news-image-form")[0];
        var data = new FormData(form);

        $.ajax({
            url: '/api/news/' + newsId + '/image',
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            cache: false,
            dataType: 'json',
            contentType: false,
            processData: false,
            success: function (data) {
                form.reset();
                updateImageList(data);
            },
            error: function () {
                console.log('admin news-page uploadNewsImages method failed')
            }
        })
    }

    function updateImageList(data) {
        var images = data;
        $('#news-image-list').html('');
        for (var i = 0; i < images.length; i++) {
            var image = images[i];
            $('#news-image-list').append(
                '<img src="/file/image/' + image.id + '" height="100" width="100">' +
                '<button type="button" onclick="deleteNewsImage(' + image.newsId + ', ' + image.id + ')">удалить</button>' +
                '<hr>'
            );
        }
    }
</script>
</html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/css/main.css"/>
</head>
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
                <li class="active"><a href="/admin/news">Новости</a></li>
                <li><a href="/admin/tickets">Заявки граждан</a></li>
                <li><a href="/admin/departments">Отделы</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> Выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<body>
    <div class="container">
        <div id="news-form-block">
            <form method="post" enctype="multipart/form-data" id="news-form">
                <div class="form-group">
                    <input type="text" name="title" placeholder="заголовок" required/>
                </div>
                <div class="form-group">
                    <textarea name="text" class="form-control" placeholder="текст новости" rows="6" required></textarea>
                </div>
                <div class="form-group">
                    <input id="files" type="file" multiple="multiple" name="files" accept="image/*"/>
                </div>
                <button class="btn btn-default" type="button" onclick="uploadNewsOnServer()">Опубликовать</button>
                <div class="clearfix"></div>
            </form>
        </div>
        <h2 class="title-center">Текущие новости портала</h2>
        <hr>
        <div id="news-list">
        </div>
    </div>

    <script>
        loadNewsFromServer();

        function uploadNewsOnServer() {
            var form = $("#news-form")[0];
            var data = new FormData(form);

            $.ajax({
                url: '/api/news',
                type: 'POST',
                enctype: 'multipart/form-data',
                data: data,
                cache: false,
                dataType: 'json',
                contentType: false,
                processData: false,
                success: function (data) {
                    form.reset();
                    updateNewsList(data);
                },
                error: function () {
                    console.log('admin news-page uploadNewsOnServer method failed')
                }
            })
        }

        function loadNewsFromServer() {
            $.ajax({
                url: '/api/news',
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    updateNewsList(data);
                },
                error: function () {
                    console.log("admin news-page loadNewsFromServer method failed")
                }
            });
        }

        function deleteNews(newsId) {
            $.ajax({
                url: '/api/news/' + newsId,
                type: 'DELETE',
                dataType: 'json',
                success: function (data) {
                    updateNewsList(data);
                },
                error: function () {
                    console.log("admin news-page deleteNews method failed")
                }
            });
        }

        function updateNewsList(data) {
            var news = data;
            $("#news-list").html("");
            for (var i = 0; i < news.length; i++) {
                var currentNews = news[i];
                $("#news-list").append(
                    '<div>' +
                        '<div class="row">' +
                            '<div class="col-md-8">' +
                                '<h3>' + currentNews.title + '</h3>' +
                            '</div>' +
                            '<div class="col-md-2">' +
                                '<h4>' + currentNews.date + '</h4>' +
                            '</div>' +
                            '<div class="col-md-2">' +
                                '<a href="" class="btn btn-sm" onclick="deleteNews(' + currentNews.id + ')"><span class="glyphicon glyphicon-remove"></span></a>' +
                                '<a href="/admin/news/' + currentNews.id + '" class="btn btn-sm"><span class="glyphicon glyphicon-edit"></span></a>' +
                            '</div>' +
                        '</div>' +
                        '<div class="row">' +
                            '<div class="col-md-12">' +
                                '<p>' + currentNews.text + '</p>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                    '<hr>'
                );
            }
        }
    </script>
</body>
</html>
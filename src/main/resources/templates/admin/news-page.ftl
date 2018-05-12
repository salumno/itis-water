<html>
<head>
    <link rel="stylesheet" type="text/css" href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>

    <div class="container">
        <div id="news-form-block">
            <form method="post" enctype="multipart/form-data" id="news-form">
                <div class="form-group">
                    <input type="text" name="title" placeholder="заголовок"/>
                </div>
                <div class="form-group">
                    <textarea name="text" class="form-control" placeholder="текст новости" rows="6" required></textarea>
                </div>
                <div class="form-group">
                    <input id="files" type="file" multiple="multiple" name="files" accept="image/*"/>
                </div>
                <button class="btn btn-default" type="button" onclick="uploadNewsOnServer()">оставить отзыв</button>
                <div class="clearfix"></div>
            </form>
        </div>
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
                                '<span class="glyphicon glyphicon-remove" onclick="deleteNews(' + currentNews.id +')"></span>' +
                            '</div>' +
                        '</div>' +
                        '<div class="row">' +
                            '<div class="col-md-12">' +
                                '<p>' + currentNews.text + '</p>' +
                            '</div>' +
                        '</div>' +
                    '</div>'
                );
            }
        }
    </script>
</body>
</html>
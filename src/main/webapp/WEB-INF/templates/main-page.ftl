<html>
<head>
    <title>Водоканал мистера Ланштейна</title>
</head>
<body>
<h2>Здесь будет Ваша реклама</h2>
<#list model.news as elem>
    <div>
        <h4>${elem.text}</h4>
        <p>${elem.date}</p>
    </div>
</#list>
</body>
</html>
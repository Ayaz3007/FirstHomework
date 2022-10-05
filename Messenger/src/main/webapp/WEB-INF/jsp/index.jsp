<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>

<!-- форма для ввода имени и кода чата-->
<form action="/chat" method="post">
    <label>Your name:
        <input type = "text" name = "clientName">
    </label>
    <p>      </p>
    <label>Input chatcode:
        <input type="text" name = "chatCode">
    </label>
    <button type="submit">Submit</button>
</form>
<p>    </p>
<!-- Поле для отображения сгенерированного кода -->
<label>Get chatcode:
    <input id ="genCode" type="text" name = "generatedCode">
</label>
<button onclick="generateCode(5)">Generate</button>
</body>
<script>
    function generateCode(length) {
        var result           = '';
        var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        var charactersLength = characters.length;
        for ( var i = 0; i < length; i++ ) {
            result += characters.charAt(Math.floor(Math.random() *
                charactersLength));
        }
        document.getElementById("genCode").value = String(result);
    }
</script>
</html>
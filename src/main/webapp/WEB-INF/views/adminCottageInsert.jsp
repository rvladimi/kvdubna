<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
    <title>
        Ввод коттеджей
    </title>
    <style>
        table {
            margin: auto;
        }
        .select1 {
            background: #b1ca00;
            color: black;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 22px;
        }
        select {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 18px;
        }
        td {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 20px;
        }
        input, textarea {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 16px;
        }
        .bluebatton {
            border-radius: 10px;
            background: lightsteelblue;
            color: black;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: 300;
            font-size: 22px;
        }
    </style>
</head>
<body>

<table width="1250" border="0" cellspacing="0" cellpadding="10" onmousedown="return false">
    <tr>
        <form action="allobjects" method="GET">
            <td width="180">
                <button type="submit" style="border: 0; background: rgba(54, 54, 54, 0); cursor: pointer;">
                    <img src="/resources/images/kvadratfull.jpg" width="80%" alt="главная страница">
                </button>
            </td>
            <input type="hidden" name="forsale" value="true">
        </form>

        <form action="allobjects" method="GET">
            <td valign="center" width="180">
                <input type="submit" class="select1" style="cursor: pointer;" value="главная страница">
            </td>
            <input type="hidden" name="forsale" value="true">
        </form>
        <form action="admin" method="POST">
            <td valign="center">
                <input type="submit" class="select1" style="cursor: pointer;" value="панель администратора">
            </td>
            <input type="hidden" name="adminAction" value="showAdminPanel">
        </form>
    </tr>
</table>

<form action="cottageInsert" method="post" enctype="multipart/form-data">

    <table width="1000" cellspacing="10" cellpadding="4">
            <tr>
                <td align="left" width="250"></td>
                <td>
                    <input name="forsale" type="radio" value="yes" checked> Продажа
                    <input name="forsale" type="radio" value="no"> Аренда
                </td>
            </tr>

        <tr>
            <td align="left" width="200">Адрес</td>
            <td><input type="text" name="address" size="50"></td>
        </tr>

        <tr>
            <td align="left" width="200">Цена</td>
            <td><input type="text" name="price" placeholder=" строка из чисел, пробелов и букв" size="50"></td>
        </tr>

        <tr>
            <td align="left" width="200">Площадь дома (кв.м)</td>
            <td><input type="text" name="homeArea" size="3"></td>
        </tr>

        <tr>
            <td align="left" width="200">Площадь участка (сотки)</td>
            <td><input type="text" name="landArea" size="3"></td>
        </tr>

        <tr>
            <td align="left" width="100">Электричество</td>
            <td>
                <select name="electricity">
                    <option>есть</option>
                    <option>нет</option>
                    <option>есть возможность подключения</option>
                    <option>нет данных</option>
                </select>
            </td>
        </tr>

        <tr>
            <td align="left" width="100">Вода</td>
            <td>
                <select name="water">
                    <option>центральное водоснабжение</option>
                    <option>колодец</option>
                    <option>нет</option>
                    <option>есть возможность подключения</option>
                    <option>нет данных</option>
                </select>
            </td>
        </tr>

        <tr>
            <td align="left" width="100">Газ</td>
            <td>
                <select name="gas">
                    <option>есть</option>
                    <option>нет</option>
                    <option>есть возможность подключения</option>
                    <option>нет данных</option>
                </select>
            </td>
        </tr>

        <tr>
            <td align="left" width="100">Канализация</td>
            <td>
                <select name="sewerage">
                    <option>центральная</option>
                    <option>септик</option>
                    <option>есть возможность подключения</option>
                    <option>нет</option>
                    <option>нет данных</option>
                </select>
            </td>
        </tr>

        <tr>
            <td align="left" valign="center">Описание</td>
            <td><textarea name="description" cols="60" rows="7"></textarea>
            </td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 1</td>
            <td><input type="file" name="photo1" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 2</td>
            <td><input type="file" name="photo2" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 3</td>
            <td><input type="file" name="photo3" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 4</td>
            <td><input type="file" name="photo4" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 5</td>
            <td><input type="file" name="photo5" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 6</td>
            <td><input type="file" name="photo6" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 7</td>
            <td><input type="file" name="photo7" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 8</td>
            <td><input type="file" name="photo8" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 9</td>
            <td><input type="file" name="photo9" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td align="left" width="200">Добавить фото 10</td>
            <td><input type="file" name="photo10" size="60" accept="image/*,image/jpeg"></td>
        </tr>

        <tr>
            <td></td>
            <td height="15">
                <input type="submit" class="bluebatton" name="adminAction" value="добавить объект в базу">
            </td>
        </tr>
    </table>

</form>
</body>
</html>

<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
    <title>
        Редактирование агентства
    </title>
    <style>
        table {
            margin: auto;
        }

        .select1 {
            background: #b1ca00;
            color: black; /* Цвет текста */
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal; /* Жирное начертание */
            font-size: 22px;
        }

        td {
            color: #363636; /* Цвет текста */
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal; /* Жирное начертание */
            font-size: 20px;
        }

        input, textarea {
            color: #363636; /* Цвет текста */
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal; /* Жирное начертание */
            font-size: 16px;
        }

        .bluebatton {
            border-radius: 10px;
            background: lightsteelblue; /* Синий цвет фона */
            color: black; /* Цвет текста */
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: 300; /* Жирное начертание */
            font-size: 22px;
        }
    </style>
</head>
<body>
    <table width="1250" border="0" cellspacing="0" cellpadding="10" onmousedown="return false">
        <tr>
            <form action="/allobjects" method="GET">
                <td width="180">
                    <button type="submit" style="border: 0; background: rgba(54, 54, 54, 0); cursor: pointer;">
                        <img src="/resources/images/kvadratfull.jpg" width="80%" alt="главная страница">
                    </button>
                </td>
                <input type="hidden" name="forsale" value="true">
            </form>

            <form action="/allobjects" method="GET">
                <td valign="center" width="180">
                    <input type="submit" class="select1" style="cursor: pointer;" value="главная страница">
                </td>
                <input type="hidden" name="forsale" value="true">
            </form>
            <form action="/admin" method="POST">
                <td valign="center">
                    <input type="submit" class="select1" style="cursor: pointer;" value="панель администратора">
                </td>
                <input type="hidden" name="adminAction" value="showAdminPanel">
                <input type="hidden" name="secretWord" value="02081927">
            </form>
        </tr>
    </table>

    <hr width="1200" align="center" noshade size="2">

    <form action="/agency" enctype="multipart/form-data" method="POST">

    <table width="1000" border="0" cellspacing="15" cellpadding="0">

        <tr>
            <td align="left" width="250">Адрес офиса</td>
            <td><input type="text" name="address" value="${agency.address}" size="50"></td>
        </tr>

        <tr>
            <td align="left" width="250">Телефон рабочий</td>
            <td><input type="text" name="phone1" value="${agency.phone1}" size="30"></td>
        </tr>

        <tr>
            <td align="left" width="250">Телефон мобильный</td>
            <td><input type="text" name="phone2" value="${agency.phone2}" size="30"></td>
        </tr>

        <tr>
            <td align="left" width="250">Email</td>
            <td><input type="text" name="email" value="${agency.email}" size="30"></td>
        </tr>

        <tr>
            <td align="left" valign="center">Описание агентства</td>
            <td><textarea name="description" cols="60" rows="7">${agency.description}</textarea>
            </td>
        </tr>

        <tr>
            <td align="left" valign="center">Текст о руководителе</td>
            <td><textarea name="director" cols="60" rows="7">${agency.directorAbout}</textarea>
            </td>
        </tr>

        <c:if test="${not empty agency.directorPhoto}">
            <tr>
                <td align="left" width="400">Фото руководителя</td>
                <td>
                    <img src="/resources/images/${agency.directorPhoto}" width="370">
                    <p>Изменить <input type="file" name="directorPhoto" size="370" accept="image/*,image/jpeg"><br>
                        <input type="hidden" name="photoName" value="${agency.directorPhoto}">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty agency.directorPhoto}">
            <tr>
                <td align="left">Добавить фото руководителя</td>
                <td><input type="file" name="directorPhoto" size="370" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <tr>
            <td></td>
            <td><input type="submit" class="bluebatton" value="сохранить изменения"></td>
        </tr>
    </table>

</form>
</body>
</html>

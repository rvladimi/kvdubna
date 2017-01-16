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
        Список комнат
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
        .select2 {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 17px;
        }
        td {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 20px;
        }
        h2 {
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            color: #363636;
        }
    </style>
</head>
<body>
<table width="1200" border="0" cellspacing="0" cellpadding="10" onmousedown="return false">
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

    <tr>
        <td colspan="3" valign="bottom">
            <h2>Cписок комнат:</h2>
        </td>
    </tr>
</table>

<c:forEach var="room" items="${roomList}">
    <table width="1200" style="background : #FFFDD0;" width="100%" border="0" cellspacing="10" cellpadding="0">
        <tr>
            <td colspan="4">
                    ${room.forSale ? "Продажа" : "Аренда"},
                     Адрес: <b>${room.address}</b>,
                     ${room.hide ? "Объявление снято" : "Объявление активно"}

            </td>
        </tr>
        <tr>
            <form action="/adminroom" method="GET">
                <td width="9%">
                    <input type="submit" class="select2" style="cursor: pointer;" name="adminAction"
                           value="изменить">
                    <input type="hidden" name="id" value="${room.id}">
                </td>
            </form>
            <form action="/adminroom" method="GET">
                <td width="13%">
                    <input type="submit" class="select2" style="cursor: pointer;" name="adminAction"
                           value="${room.hide ? "активировать" : "снять"}">
                    <input type="hidden" name="id" value="${room.id}">
                </td>
            </form>
            <td></td>
            <form action="/adminroom" method="GET">
                <td width="70%">
                    <input type="submit" class="select2" style="cursor: pointer;" name="adminAction"
                           value="удалить">
                    <input type="hidden" name="id" value="${room.id}">
                </td>
            </form>
        </tr>

        <table>
            <tr>
                <td height="15"></td>
            </tr>
        </table>
    </table>
</c:forEach>
</body>
</html>
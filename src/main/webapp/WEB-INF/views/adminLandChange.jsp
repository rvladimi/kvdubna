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
        Изменение дачи/участка
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

        select {
            color: #363636; /* Цвет текста */
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal; /* Жирное начертание */
            font-size: 18px;
        }

        .bluebatton {
            border-radius: 10px;
            background: lightsteelblue; /* Синий цвет фона */
            color: black; /* Цвет текста */
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: 300; /* Жирное начертание */
            font-size: 22px;
        }

        input, textarea {
            color: #363636; /* Цвет текста */
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal; /* Жирное начертание */
            font-size: 18px;
        }
    </style>
</head>
<body>

<table width="1250" border="0" cellspacing="0" cellpadding="10" onmousedown="return false">
    <tr>
        <form action="allobjects" method="GET">
            <td width="130">
                <button type="submit" style="border: 0; background: rgba(54, 54, 54, 0); cursor: pointer;">
                    <img src="/resources/images/kvadratfull.jpg" width="130" alt="главная страница">
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

        <form action="admin" method="POST">
            <td valign="center">
                <input type="submit" class="select1" style="cursor: pointer;" value="вернуться к списку объектов">
            </td>
            <input type="hidden" name="adminAction" value="showList">
            <input type="hidden" name="realtyType" value="дача/участок">
        </form>
    </tr>
</table>

<form action="landSaveChanges" method="post" enctype="multipart/form-data">

    <table width="1000" border="0" cellspacing="0" cellpadding="15">
        <tr>
            <td align="left" width="250"></td>
            <td>
                <c:choose>
                    <c:when test="${land.forSale}">
                        <input name="forsale" type="radio" value="yes" checked> Продажа
                        <input name="forsale" type="radio" value="no"> Аренда
                    </c:when>
                    <c:otherwise>
                        <input name="forsale" type="radio" value="yes"> Продажа
                        <input name="forsale" type="radio" value="no" checked> Аренда
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>

        <tr>
            <td align="left" width="250">Адрес</td>
            <td><input type="text" name="address" value="${land.address}" size="50"></td>
        </tr>

        <tr>
            <td align="left" width="250">Цена</td>
            <td><input type="text" name="price" value="${land.price}" size="50"></td>
        </tr>

        <tr>
            <td align="left" width="250">Площадь участка (сотки)</td>
            <td><input type="text" name="area" value="${land.area}" size="10"></td>
        </tr>

        <tr>
            <td align="left" width="100">Электричество</td>
            <td>
                <select name="electricity">
                    <option>${land.electricity}</option>
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
                    <option>${land.water}</option>
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
                    <option>${land.gas}</option>
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
                    <option>${land.sewerage}</option>
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
            <td><textarea name="description" cols="50" rows="5">${land.description}</textarea>
            </td>
        </tr>

        <c:if test="${not empty land.photo1}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 1</td>
                <td>
                    <img src="/resources/images/${land.photo1}" width="150">
                    <p>Изменить <input type="file" name="photo1" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo1Delete" value="yes"></p>
                    <input type="hidden" name="photo1Name" value="${land.photo1}">
                    <input type="hidden" name="changePhoto1" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo1}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 1</td>
                <td><input type="file" name="photo1" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty land.photo2}">
            <tr>
                <td align="left" width="100">Фото 2</td>
                <td>
                    <img src="/resources/images/${land.photo2}" width="150">
                    <p>Изменить <input type="file" name="photo2" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo2Delete" value="yes"></p>
                    <input type="hidden" name="photo2Name" value="${land.photo2}">
                    <input type="hidden" name="changePhoto2" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo2}">
            <tr>
                <td align="left" width="250">Добавить фото 2</td>
                <td><input type="file" name="photo2" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>


        <c:if test="${not empty land.photo3}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 3</td>
                <td>
                    <img src="/resources/images/${land.photo3}" width="150">
                    <p>Изменить <input type="file" name="photo3" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo3Delete" value="yes"></p>
                    <input type="hidden" name="photo3Name" value="${land.photo3}">
                    <input type="hidden" name="changePhoto3" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo3}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 3</td>
                <td><input type="file" name="photo3" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty land.photo4}">
            <tr>
                <td align="left" width="100">Фото 4</td>
                <td>
                    <img src="/resources/images/${land.photo4}" width="150">
                    <p>Изменить <input type="file" name="photo4" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo4Delete" value="yes"></p>
                    <input type="hidden" name="photo4Name" value="${land.photo4}">
                    <input type="hidden" name="changePhoto4" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo4}">
            <tr>
                <td align="left" width="250">Добавить фото 4</td>
                <td><input type="file" name="photo4" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>


        <c:if test="${not empty land.photo5}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 5</td>
                <td>
                    <img src="/resources/images/${land.photo5}" width="150">
                    <p>Изменить <input type="file" name="photo5" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo5Delete" value="yes"></p>
                    <input type="hidden" name="photo5Name" value="${land.photo5}">
                    <input type="hidden" name="changePhoto5" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo5}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 5</td>
                <td><input type="file" name="photo5" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty land.photo6}">
            <tr>
                <td align="left" width="100">Фото 6</td>
                <td>
                    <img src="/resources/images/${land.photo6}" width="150">
                    <p>Изменить <input type="file" name="photo6" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo6Delete" value="yes"></p>
                    <input type="hidden" name="photo6Name" value="${land.photo6}">
                    <input type="hidden" name="changePhoto6" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo6}">
            <tr>
                <td align="left" width="250">Добавить фото 6</td>
                <td><input type="file" name="photo6" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty land.photo7}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 7</td>
                <td>
                    <img src="/resources/images/${land.photo7}" width="150">
                    <p>Изменить <input type="file" name="photo7" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo7Delete" value="yes"></p>
                    <input type="hidden" name="photo7Name" value="${land.photo7}">
                    <input type="hidden" name="changePhoto7" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo7}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 7</td>
                <td><input type="file" name="photo7" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty land.photo8}">
            <tr>
                <td align="left" width="100">Фото 8</td>
                <td>
                    <img src="/resources/images/${land.photo8}" width="150">
                    <p>Изменить <input type="file" name="photo8" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo8Delete" value="yes"></p>
                    <input type="hidden" name="photo8Name" value="${land.photo8}">
                    <input type="hidden" name="changePhoto8" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo8}">
            <tr>
                <td align="left" width="250">Добавить фото 8</td>
                <td><input type="file" name="photo8" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty land.photo9}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 9</td>
                <td>
                    <img src="/resources/images/${land.photo9}" width="150">
                    <p>Изменить <input type="file" name="photo9" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo9Delete" value="yes"></p>
                    <input type="hidden" name="photo9Name" value="${land.photo9}">
                    <input type="hidden" name="changePhoto9" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo9}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 9</td>
                <td><input type="file" name="photo9" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty land.photo10}">
            <tr>
                <td align="left" width="100">Фото 10</td>
                <td>
                    <img src="/resources/images/${land.photo10}" width="150">
                    <p>Изменить <input type="file" name="photo10" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo10Delete" value="yes"></p>
                    <input type="hidden" name="photo10Name" value="${land.photo10}">
                    <input type="hidden" name="changePhoto10" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty land.photo10}">
            <tr>
                <td align="left" width="250">Добавить фото 10</td>
                <td><input type="file" name="photo10" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>


        <tr>
            <td></td>
            <td>
                <input type="hidden" name="id" value="${land.id}">
                <input type="submit" class="bluebatton" name="adminAction" value="сохранить изменения">
            </td>
        </tr>
    </table>
</form>

</body>
</html>

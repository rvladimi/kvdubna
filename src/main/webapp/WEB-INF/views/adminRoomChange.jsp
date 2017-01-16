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
        Изменение комнаты
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
        td {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 20px;
        }
        select {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
            font-size: 18px;
        }
        .bluebatton {
            border-radius: 10px;
            background: lightsteelblue;
            color: black;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: 300;
            font-size: 22px;
        }
        input, textarea {
            color: #363636;
            font-family: Verdana, Helvetica, Tahoma, sans-serif;
            font-weight: normal;
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
            <td valign="center" width="120">
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
            <input type="hidden" name="realtyType" value="комната">
        </form>
    </tr>
</table>

<form action="roomSaveChanges" method="post" enctype="multipart/form-data">

    <table width="1000" border="0" cellspacing="0" cellpadding="15">
        <tr>
            <td align="left" width="250"></td>
            <td>
                <c:choose>
                    <c:when test="${room.forSale}">
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
            <td><input type="text" name="address" value="${room.address}" size="50"></td>
        </tr>

        <tr>
            <td align="left" width="250">Цена</td>
            <td><input type="text" name="price" value="${room.price}" size="50"></td>
        </tr>

        <tr>
            <td align="left" width="250">Площадь комнаты (м.кв)</td>
            <td><input type="text" name="roomArea" value="${room.roomArea}" size="10"></td>
        </tr>

        <tr>
            <td align="left" width="250">Количество комнат в квартире</td>
            <td><input type="text" name="roomsNumber" value="${room.roomsNumber}" size="3"></td>
        </tr>


        <tr>
            <td align="left" width="250">Общая площадь квартиры (м.кв)</td>
            <td><input type="text" name="fullArea" value="${room.fullHomeArea}" size="10"></td>
        </tr>

        <tr>
            <td align="left" width="250">Жилая площадь квартиры (м.кв)</td>
            <td><input type="text" name="livingArea" value="${room.livingArea}" size="10"></td>
        </tr>

        <tr>
            <td align="left" width="250">Площадь кухни (м.кв)</td>
            <td><input type="text" name="kitchenArea" value="${room.kitchenArea}" size="10"></td>
        </tr>

        <tr>
            <td align="left" width="200">Этаж</td>
            <td><input type="text" name="floor" value="${room.floor}" size="2"></td>
        </tr>

        <tr>
            <td align="left" width="200">Количество этажей</td>
            <td><input type="text" name="floors" value="${room.floorsNumber}" size="2"></td>
        </tr>

        <tr>
            <td align="left" width="100">Балкон</td>
            <td>
                <select name="balcony">
                    <option>${room.balcony}</option>
                    <option>есть</option>
                    <option>нет</option>
                    <option>нет данных</option>
                </select>
            </td>
        </tr>

        <tr>
            <td align="left" width="100">Тип дома</td>
            <td>
                <select name="buildingType">
                    <option>${room.buildingType}</option>
                    <option>кирпичный</option>
                    <option>панельный</option>
                    <option>кирпично-монолитный</option>
                    <option>деревянный</option>
                    <option>нет данных</option>
                </select>
            </td>
        </tr>

        <tr>
            <td align="left" valign="center">Описание</td>
            <td><textarea name="description" cols="50" rows="5">${room.description}</textarea>
            </td>
        </tr>

        <c:if test="${not empty  room.photo1}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 1</td>
                <td>
                    <img src="/resources/images/${room.photo1}" width="150">
                    <p>Изменить <input type="file" name="photo1" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo1Delete" value="yes"></p>
                    <input type="hidden" name="photo1Name" value="${room.photo1}">
                    <input type="hidden" name="changePhoto1" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo1}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 1</td>
                <td><input type="file" name="photo1" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty room.photo2}">
            <tr>
                <td align="left" width="100">Фото 2</td>
                <td>
                    <img src="/resources/images/${room.photo2}" width="150">
                    <p>Изменить <input type="file" name="photo2" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo2Delete" value="yes"></p>
                    <input type="hidden" name="photo2Name" value="${ room.photo2}">
                    <input type="hidden" name="changePhoto2" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo2}">
            <tr>
                <td align="left" width="250">Добавить фото 2</td>
                <td><input type="file" name="photo2" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>


        <c:if test="${not empty room.photo3}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 3</td>
                <td>
                    <img src="/resources/images/${room.photo3}" width="150">
                    <p>Изменить <input type="file" name="photo3" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo3Delete" value="yes"></p>
                    <input type="hidden" name="photo3Name" value="${room.photo3}">
                    <input type="hidden" name="changePhoto3" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo3}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 3</td>
                <td><input type="file" name="photo3" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty room.photo4}">
            <tr>
                <td align="left" width="100">Фото 4</td>
                <td>
                    <img src="/resources/images/${room.photo4}" width="150">
                    <p>Изменить <input type="file" name="photo4" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo4Delete" value="yes"></p>
                    <input type="hidden" name="photo4Name" value="${room.photo4}">
                    <input type="hidden" name="changePhoto4" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo4}">
            <tr>
                <td align="left" width="250">Добавить фото 4</td>
                <td><input type="file" name="photo4" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>


        <c:if test="${not empty room.photo5}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 5</td>
                <td>
                    <img src="/resources/images/${room.photo5}" width="150">
                    <p>Изменить <input type="file" name="photo5" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo5Delete" value="yes"></p>
                    <input type="hidden" name="photo5Name" value="${room.photo5}">
                    <input type="hidden" name="changePhoto5" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo5}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 5</td>
                <td><input type="file" name="photo5" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty room.photo6}">
            <tr>
                <td align="left" width="100">Фото 6</td>
                <td>
                    <img src="/resources/images/${room.photo6}" width="150">
                    <p>Изменить <input type="file" name="photo6" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo6Delete" value="yes"></p>
                    <input type="hidden" name="photo6Name" value="${room.photo6}">
                    <input type="hidden" name="changePhoto6" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo6}">
            <tr>
                <td align="left" width="250">Добавить фото 6</td>
                <td><input type="file" name="photo6" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty room.photo7}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 7</td>
                <td>
                    <img src="/resources/images/${room.photo7}" width="150">
                    <p>Изменить <input type="file" name="photo7" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo7Delete" value="yes"></p>
                    <input type="hidden" name="photo7Name" value="${room.photo7}">
                    <input type="hidden" name="changePhoto7" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo7}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 7</td>
                <td><input type="file" name="photo7" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty room.photo8}">
            <tr>
                <td align="left" width="100">Фото 8</td>
                <td>
                    <img src="/resources/images/${room.photo8}" width="150">
                    <p>Изменить <input type="file" name="photo8" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo8Delete" value="yes"></p>
                    <input type="hidden" name="photo8Name" value="${room.photo8}">
                    <input type="hidden" name="changePhoto8" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo8}">
            <tr>
                <td align="left" width="250">Добавить фото 8</td>
                <td><input type="file" name="photo8" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty room.photo9}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="100">Фото 9</td>
                <td>
                    <img src="/resources/images/${room.photo9}" width="150">
                    <p>Изменить <input type="file" name="photo9" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo9Delete" value="yes"></p>
                    <input type="hidden" name="photo9Name" value="${room.photo9}">
                    <input type="hidden" name="changePhoto9" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo9}">
            <tr bgcolor="#FFFDD0">
                <td align="left" width="250">Добавить фото 9</td>
                <td><input type="file" name="photo9" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <c:if test="${not empty room.photo10}">
            <tr>
                <td align="left" width="100">Фото 10</td>
                <td>
                    <img src="/resources/images/${room.photo10}" width="150">
                    <p>Изменить <input type="file" name="photo10" size="60" accept="image/*,image/jpeg"><br>
                        Удалить <input type="checkbox" name="photo10Delete" value="yes"></p>
                    <input type="hidden" name="photo10Name" value="${room.photo10}">
                    <input type="hidden" name="changePhoto10" value="yes">
                </td>
            </tr>
        </c:if>

        <c:if test="${empty room.photo10}">
            <tr>
                <td align="left" width="250">Добавить фото 10</td>
                <td><input type="file" name="photo10" size="60" accept="image/*,image/jpeg"></td>
            </tr>
        </c:if>

        <tr>
            <td></td>
            <td>
                <input type="hidden" name="id" value="${room.id}">
                <input type="submit" class="bluebatton" value="сохранить изменения">
            </td>
        </tr>
    </table>
</form>

</body>
</html>

<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Дача/участок</title>
    <link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/resources/css/ad_style.css" type="text/css">
</head>
<body>

<%@ include file='detailed_header.jsp'%>

<h1 align="center">${ad.forSale ? "Продажа" : "Аренда"} дачи/участка</h1>

<table width="900" cellspacing="10" cellpadding="5">
    <c:if test="${not empty ad.address}">
        <tr>
            <td align="left" width="40%"><b>Адрес</b></td>
            <td>${ad.address}</td>
        </tr>
    </c:if>

    <c:if test="${not empty ad.price}">
        <tr>
            <td align="left" width="40%"><b>Цена</b></td>
            <td>${ad.price}</td>
        </tr>
    </c:if>

    <c:if test="${ad.landArea ne 0}">
    <tr>
        <td align="left" width="40%"><b>Площадь (сотки)</b></td>
        <td>${ad.landArea}</td>
    </tr>
    </c:if>

    <c:if test="${not empty ad.description}">
    <tr>
        <td align="left" width="40%"><b>Описание</b></td>
        <td>${ad.description}</td>
    </tr>
    </c:if>

    </table>

<c:if test="${not empty ad.photo2}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo2}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo3}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo3}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo4}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo4}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo5}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo5}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo6}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo6}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo7}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo7}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo8}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo8}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo9}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo9}" height="500"></td>
        </tr>
    </table>
</c:if>

<c:if test="${not empty ad.photo10}">
    <table width="905" border="0" cellspacing="20" cellpadding="0">
        <tr>
            <td align="left"><img src="/resources/images/${ad.photo10}" height="500"></td>
        </tr>
    </table>
</c:if>


</body>
</html>
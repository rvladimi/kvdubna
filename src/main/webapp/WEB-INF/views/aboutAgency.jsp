<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
    <title>
        Об агентстве
    </title>
    <link rel="stylesheet" href="/resources/css/agency_style.css" type="text/css">
</head>
<body>

<table width="1200" border="0" cellspacing="0" cellpadding="10" onmousedown="return false">
    <tr>
        <form action="allobjects" method="GET">
            <td width="50">
                <button type="submit" style="border: 0; background: rgba(54, 54, 54, 0); cursor: pointer;">
                    <img src="/resources/images/${agency.logo}" width="100%" alt="главная страница">
                </button>
            </td>
        </form>

        <form action="allobjects" method="GET">
            <td valign="center" width="180">
                <input type="submit" class="select1" style="cursor: pointer;" value="главная страница">
            </td>
        </form>
    </tr>
</table>
<hr width="1200" align="center" noshade size="2">

<table width="1250" height="30" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table style="background: linear-gradient(to bottom, #DCDCDC, white);" width="1170" height="5%" border="0" class="menu"
       cellspacing="0" cellpadding="5">
    <tr>
        <td width="10"></td>
        <td align="left">Услуги</td>
    </tr>
</table>

<table width="1250" height="30" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table width="1170" height="30" class="list" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="left">
            <ul>
                ${agency.description}
            </ul>
        </td>
    </tr>
</table>

<table width="1250" height="30" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table style="background: linear-gradient(to bottom, #DCDCDC, white);" width="1170" height="5%" border="0" class="menu"
       cellspacing="0" cellpadding="5">
    <tr>
        <td width="10"></td>
        <td align="left">Сотрудники</td>
    </tr>
</table>

<table width="1250" height="30" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table width="1170" border="0" cellspacing="0" cellpadding="20">
    <tr>
        <td align="left" width="410"><img src="/resources/images/${agency.directorPhoto}" height="300" alt=""></td>
        <td align="left" valign="top"> <span class="staff"><b>Татьяна Рушай</b>, руководитель агентства. <br>
					${agency.directorAbout}</span></td>
    </tr>
</table>


<table width="1250" height="30" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table style="background: linear-gradient(to bottom, #DCDCDC, white);" width="1170"  border="0" class="menu"
       cellspacing="0" cellpadding="5">
    <tr>
        <td width="10"></td>
        <td align="left">Контакты</td>
    </tr>
</table>

<table width="1250" height="20" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table width="1170" class="list" border="0" cellspacing="10" cellpadding="0">
    <tr>
        <td align="left">
            Тел. офис: ${agency.phone1}, &nbsp; Моб.: ${agency.phone2} <br> E-mail: ${agency.email}
        </td>
    </tr>
</table>

<table width="1250" height="50" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table style="background: linear-gradient(to bottom, #DCDCDC, white);" width="1170" height="5%" border="0" class="menu"
       cellspacing="0" cellpadding="5">
    <tr>
        <td width="10"></td>
        <td>Местоположение офиса</td>
    </tr>
</table>



<table width="1170" class="list" border="0" cellspacing="0" cellpadding="20">
    <tr>
        <td align="center">${agency.address}</td>
    </tr>
</table>

<table width="1170" border="0" cellspacing="0" cellpadding="20">
    <tr>
        <td align="center">
            <script type="text/javascript" charset="utf-8" src="https://api-maps.yandex.ru/services/constructor/1.0/js/?sid=M-KpqqkTtaOyMaXKos7rTcQMJSzOTnWy&width=924&height=617&lang=ru_RU&sourceType=constructor"></script>
        </td>
    </tr>
</table>

<table width="1250" height="30" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

</body>
</html>
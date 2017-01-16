<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Недвижимость</title>
    <link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/resources/css/realty_style.css" type="text/css">
</head>
<body>

<%@ include file='header.jsp' %>

<table style="background: linear-gradient(to bottom, #DCDCDC, white);" width="1170" height="5%" border="0" class="menu"
       onmousedown="return false"
       cellspacing="0" cellpadding="5">
    <tr>
        <td></td>
        <c:choose>
            <c:when test="${pageContext.request.getParameter('forsale') eq 'true' or pageContext.request.getParameter('forsale') eq null}">
                <td width="13%" align="left">
                    <form action="allobjects" method="GET">
                        <input type="submit" class="menu"
                               style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                               value="Продажа">
                        <input type="hidden" name="forsale" value="true">
                    </form>
                </td>
                <td width="11%" align="right">
                    <form action="allobjects" method="GET">
                        <input type="submit" class="menu" style="cursor: pointer;" value="Аренда">
                        <input type="hidden" name="forsale" value="false">
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td width="13%" align="left">
                    <form action="allobjects" method="GET">
                        <input type="submit" class="menu" style="cursor: pointer;" value="Продажа">
                        <input type="hidden" name="forsale" value="true">
                    </form>
                </td>
                <td width="11%" align="right">
                    <form action="allobjects" method="GET">
                        <input type="submit" class="menu"
                               style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                               value="Аренда">
                        <input type="hidden" name="forsale" value="false">
                    </form>
                </td>
            </c:otherwise>
        </c:choose>
        <td width="28%"></td>
    </tr>
</table>

<table width="1260" height="5" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table style="table-layout: fixed;" width="1210" cellspacing="20" cellpadding="0" onmousedown="return false">
    <tr>
        <td rowspan="2" width="20%">
            <table height="430" style="background: linear-gradient(to right, #DCDCDC, white);" class="menu"
                   align="left " cellspacing="0" cellpadding="10">
                <tr>
                    <td>
                        <form action="allflats" method="GET">
                            <input type="submit" class="menu" style="cursor: pointer;" value="Квартиры">
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="room" method="GET">
                            <input type="submit" class="menu" style="cursor: pointer;" value="Комнаты">
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="garage" method="GET">
                            <input type="submit" class="menu" style="cursor: pointer;" value="Гаражи">
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="land" method="GET">
                            <input type="submit" class="menu" style="cursor: pointer;" value="Дачи/участки">
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="cottage" method="GET">
                            <input type="submit" class="menu" style="cursor: pointer;" value="Коттеджи">
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="townhouse" method="GET">
                            <input type="submit" class="menu" style="cursor: pointer;" value="Таунхаусы">
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="commercial" method="GET">
                            <input type="submit" class="menu" style="cursor: pointer;" value="Коммерческая">
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
            </table>
        </td>

        <c:choose>
        <c:when test="${adsList.size() eq 0}">
            <td rowspan="2" colspan="2" align="center" style="background: #E6E6E6;" class="menu">
                Нет объектов
            </td>
        </c:when>
        <c:otherwise>
        <!--  -->
        <!-- first ad -->
        <td width="450" height="203">
            <form action="detailedAdInfo" method="GET">
                <button type="submit"
                        style="color: #363636; border:0; background: rgba(54, 54, 54, 0); font-family: Verdana, Helvetica, Tahoma, sans-serif;
                        cursor: pointer; font-weight: normal; font-size: 110%;">
                    <table width="450" height="203" cellspacing="0" cellpadding="10" style="background: #E6E6E6;">
                        <tr>
                            <td rowspan="3" align="right"><img class="picture"
                                                               src="/resources/images/${adsList[0].photo1}"
                                                               width="180"
                                                               height="180"
                                                               alt=""></td>
                            <td valign="top" width="100%" height="20%"><span class="type">${adsList[0].type}</span></td>
                        </tr>
                        <tr>
                            <td valign="middle" width="100%" height="20%"><span class="price">${adsList[0].price}</span>
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" width="100%"><span class="address">${adsList[0].address}</span></td>
                        </tr>
                    </table>
                </button>
                <input type="hidden" name="adRealtyType" value="${adsList[0].type}">
                <input type="hidden" name="id" value="${adsList[0].id}">
                <input type="hidden" name="source" value="main">
            </form>
        </td>

        <c:choose>
            <c:when test="${adsList.size() gt 1}">
                <!-- second ad -->
                <td width="450" height="203">
                    <form action="detailedAdInfo" method="GET">
                        <button type="submit"
                                style="color: #363636; border:0; background: rgba(54, 54, 54, 0); font-family: Verdana, Helvetica, Tahoma, sans-serif;
                        cursor: pointer; font-weight: normal; font-size: 110%;">
                            <table width="450" height="203" cellspacing="0" cellpadding="10"
                                   style="background: #E6E6E6;">
                                <tr>
                                    <td rowspan="3" align="right"><img class="picture"
                                                                       src="/resources/images/${adsList[1].photo1}"
                                                                       width="180"
                                                                       height="180"
                                                                       alt=""></td>
                                    <td valign="top" width="100%" height="20%"><span
                                            class="type">${adsList[1].type}</span></td>
                                </tr>
                                <tr>
                                    <td valign="middle" width="100%" height="20%"><span
                                            class="price">${adsList[1].price}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" width="100%"><span class="address">${adsList[1].address}</span>
                                    </td>
                                </tr>
                            </table>
                        </button>
                        <input type="hidden" name="adRealtyType" value="${adsList[1].type}">
                        <input type="hidden" name="id" value="${adsList[1].id}">
                        <input type="hidden" name="source" value="main">
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td width="450" height="203" style="background: #E6E6E6;"></td>
            </c:otherwise>
        </c:choose>

    </tr>
    <tr>
        <c:choose>
            <c:when test="${adsList.size() gt 2}">
                <!-- third ad -->
                <td width="450" height="203">
                    <form action="detailedAdInfo" method="GET">
                        <button type="submit"
                                style="color: #363636; border:0; background: rgba(54, 54, 54, 0); font-family: Verdana, Helvetica, Tahoma, sans-serif;
                        cursor: pointer; font-weight: normal; font-size: 110%;">
                            <table width="450" height="203" cellspacing="0" cellpadding="10"
                                   style="background: #E6E6E6;">
                                <tr>
                                    <td rowspan="3" align="right"><img class="picture"
                                                                       src="/resources/images/${adsList[2].photo1}"
                                                                       width="180"
                                                                       height="180"
                                                                       alt=""></td>
                                    <td valign="top" width="100%" height="20%"><span
                                            class="type">${adsList[2].type}</span></td>
                                </tr>
                                <tr>
                                    <td valign="middle" width="100%" height="20%"><span
                                            class="price">${adsList[2].price}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" width="100%"><span class="address">${adsList[2].address}</span>
                                    </td>
                                </tr>
                            </table>
                        </button>
                        <input type="hidden" name="adRealtyType" value="${adsList[2].type}">
                        <input type="hidden" name="id" value="${adsList[2].id}">
                        <input type="hidden" name="source" value="main">
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td width="450" height="203" style="background: #E6E6E6;"></td>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${adsList.size() gt 3}">
                <!-- fourth ad -->
                <td width="450" height="203">
                    <form action="detailedAdInfo" method="GET">
                        <button type="submit"
                                style="color: #363636; border:0; background: rgba(54, 54, 54, 0); font-family: Verdana, Helvetica, Tahoma, sans-serif;
                        cursor: pointer; font-weight: normal; font-size: 110%;">
                            <table width="450" height="203" cellspacing="0" cellpadding="10"
                                   style="background: #E6E6E6;">
                                <tr>
                                    <td rowspan="3" align="right"><img class="picture"
                                                                       src="/resources/images/${adsList[3].photo1}"
                                                                       width="180"
                                                                       height="180"
                                                                       alt=""></td>
                                    <td valign="top" width="100%" height="20%"><span
                                            class="type">${adsList[3].type}</span></td>
                                </tr>
                                <tr>
                                    <td valign="middle" width="100%" height="20%"><span
                                            class="price">${adsList[3].price}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" width="100%"><span class="address">${adsList[3].address}</span>
                                    </td>
                                </tr>
                            </table>
                        </button>
                        <input type="hidden" name="adRealtyType" value="${adsList[3].type}">
                        <input type="hidden" name="id" value="${adsList[3].id}">
                        <input type="hidden" name="source" value="main">
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td width="450" height="203" style="background: #E6E6E6;"></td>
            </c:otherwise>
        </c:choose>

        </c:otherwise>
        </c:choose>

    </tr>
</table>

<table style="background: linear-gradient(to top, #DCDCDC, white);" width="1170" height="5%" border="0"
       cellspacing="0" cellpadding="5" onmousedown="return false">
    <tr>
        <td valign="center" align="left"><a href="http://naidinakarte.ru/"><img valign="center"
                                                                                src="/resources/images/NNKlogo.png"
                                                                                height="30" alt=""></a>
        </td>
        <c:choose>
            <c:when test="${pageContext.session.getAttribute('pageCounter') ge 1}">
                <td align="right">
                    <form action="allobjects" method="GET">
                        <input type="submit" class="bottom" style="cursor: pointer;" name="pagination" value="Назад">
                        <input type="hidden" name="forsale" value="${pageContext.request.getParameter('forsale')}">
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td align="right">
                </td>
            </c:otherwise>
        </c:choose>
        <c:if test="${totalPages gt 1}">
            <td width="18%" class="bottom" align="center">
                Страница ${pageContext.session.getAttribute('pageCounter') + 1} из ${totalPages}
            </td>
        </c:if>
        <c:choose>
            <c:when test="${(pageContext.session.getAttribute('pageCounter') + 1) < totalPages}">
                <td width="32%" align="left">
                    <form action="allobjects" method="GET">
                        <input type="submit" class="bottom" style="cursor: pointer;" name="pagination" value="Вперед">
                        <c:choose>
                            <c:when test="${pageContext.request.getParameter('forsale') eq null}">
                                <input type="hidden" name="forsale" value="true">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="forsale" value="${pageContext.request.getParameter('forsale')}">
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td width="32%" align="left">
                </td>
            </c:otherwise>
        </c:choose>
    </tr>
</table>

</body>
</html>


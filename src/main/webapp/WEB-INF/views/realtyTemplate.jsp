<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Yandex.Metrika counter -->
<script type="text/javascript">
    (function (d, w, c) {
        (w[c] = w[c] || []).push(function () {
            try {
                w.yaCounter41724604 = new Ya.Metrika({
                    id: 41724604,
                    clickmap: true,
                    trackLinks: true,
                    accurateTrackBounce: true
                });
            } catch (e) {
            }
        });

        var n = d.getElementsByTagName("script")[0],
                s = d.createElement("script"),
                f = function () {
                    n.parentNode.insertBefore(s, n);
                };
        s.type = "text/javascript";
        s.async = true;
        s.src = "https://mc.yandex.ru/metrika/watch.js";

        if (w.opera == "[object Opera]") {
            d.addEventListener("DOMContentLoaded", f, false);
        } else {
            f();
        }
    })(document, window, "yandex_metrika_callbacks");
</script>
<noscript>
    <div><img src="https://mc.yandex.ru/watch/41724604" style="position:absolute; left:-9999px;" alt=""/></div>
</noscript>
<!-- /Yandex.Metrika counter -->

<table width="1170" border="0" cellspacing="0" cellpadding="0" onmousedown="return false">
    <tr>
        <td rowspan="2">
            <a href="allobjects"><img src="/resources/images/kvadratfull.jpg"
                                       height="130px" border="0" alt="главная страница"></a>

        </td>
        <td width="35%"><span class="contact">Тел. (49621) 222 98, +7 903 254 41 10<br> kvdubna@yandex.ru</span>
        </td>
    </tr>
    <tr>
        <td width="35%" align="left">
            <form action="agency" method="GET">
                <input type="submit" class="about" style="cursor: pointer;" value="О нашем агентстве">
            </form>
        </td>
    </tr>
</table>

<table width="1260" height="30" cellspacing="0" cellpadding="0">
    <tr>
        <td></td>
    </tr>
</table>

<table style="background: linear-gradient(to bottom, #DCDCDC, white);" width="1170" height="5%" border="0" class="menu"
       onmousedown="return false"
       cellspacing="0" cellpadding="5">
    <tr>
        <td></td>
        <c:choose>
            <c:when test="${pageContext.request.getParameter('forsale') eq 'true'}">
                <td width="13%" align="left">
                    <form action="${param.action}" method="GET">
                        <input type="submit" class="menu"
                               style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                               value="Продажа">
                        <input type="hidden" name="forsale" value="true">
                    </form>
                </td>
                <td width="11%" align="right">
                    <form action="${param.action}" method="GET">
                        <input type="submit" class="menu" style="cursor: pointer;" value="Аренда">
                        <input type="hidden" name="forsale" value="false">
                    </form>
                </td>
            </c:when>
            <c:otherwise>
                <td width="13%" align="left">
                    <form action="${param.action}" method="GET">
                        <input type="submit" class="menu" style="cursor: pointer;" value="Продажа">
                        <input type="hidden" name="forsale" value="true">
                    </form>
                </td>
                <td width="11%" align="right">
                    <form action="${param.action}" method="GET">
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
                            <c:choose>
                                <c:when test="${param.action eq 'allflats'}">
                                    <input type="submit" class="menu"
                                           style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                                           value="Квартиры">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="menu" style="cursor: pointer;" value="Квартиры">
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="room" method="GET">
                            <c:choose>
                                <c:when test="${param.action eq 'room'}">
                                    <input type="submit" class="menu"
                                           style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                                           value="Комнаты">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="menu" style="cursor: pointer;" value="Комнаты">
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="garage" method="GET">
                            <c:choose>
                                <c:when test="${param.action eq 'garage'}">
                                    <input type="submit" class="menu"
                                           style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                                           value="Гаражи">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="menu" style="cursor: pointer;" value="Гаражи">
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="land" method="GET">
                            <c:choose>
                                <c:when test="${param.action eq 'land'}">
                                    <input type="submit" class="menu"
                                           style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                                           value="Дачи/участки">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="menu" style="cursor: pointer;" value="Дачи/участки">
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="cottage" method="GET">
                            <c:choose>
                                <c:when test="${param.action eq 'cottage'}">
                                    <input type="submit" class="menu"
                                           style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                                           value="Коттеджи">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="menu" style="cursor: pointer;" value="Коттеджи">
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="townhouse" method="GET">
                            <c:choose>
                                <c:when test="${param.action eq 'townhouse'}">
                                    <input type="submit" class="menu"
                                           style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                                           value="Таунхаусы">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="menu" style="cursor: pointer;" value="Таунхаусы">
                                </c:otherwise>
                            </c:choose>
                            <input type="hidden" name="forsale" value="true">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form action="commercial" method="GET">
                            <c:choose>
                                <c:when test="${param.action eq 'commercial'}">
                                    <input type="submit" class="menu"
                                           style="cursor: pointer; color: white; font-weight: normal; background: #6E6E6E;"
                                           value="Коммерческая">
                                </c:when>
                                <c:otherwise>
                                    <input type="submit" class="menu" style="cursor: pointer;" value="Коммерческая">
                                </c:otherwise>
                            </c:choose>
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
                                                               src="/resources/images/${adsList[0].photo1}" width="180"
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
                <input type="hidden" name="source" value="${adsList[0].type}">
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
                        <input type="hidden" name="source" value="${adsList[1].type}">
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
                        <input type="hidden" name="source" value="${adsList[2].type}">
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
                        <input type="hidden" name="source" value="${adsList[3].type}">
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
                    <form action="${param.action}" method="GET">
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
                    <form action="${param.action}" method="GET">
                        <input type="submit" class="bottom" style="cursor: pointer;" name="pagination" value="Вперед">
                        <input type="hidden" name="forsale" value="${pageContext.request.getParameter('forsale')}">
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
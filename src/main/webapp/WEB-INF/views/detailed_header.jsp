<?xml version="1.0" encoding="utf-8"?>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ page pageEncoding="utf-8" %>

<table width="1200" border="0" cellspacing="0" cellpadding="10" onmousedown="return false">
    <tr>
        <form action="allobjects" method="GET">
            <td width="230">
                <button type="submit" style="border: 0; background: rgba(54, 54, 54, 0); cursor: pointer;">
                    <img src="/resources/images/kvadratfull.jpg" width="100%" alt="главная страница">
                </button>
            </td>
            <input type="hidden" name="forsale" value="true">
        </form>

        <form action="allobjects" method="GET">
            <td valign="center" width="230">
                <input type="submit" class="select1" style="cursor: pointer;" value="главная страница">
            </td>
            <input type="hidden" name="forsale" value="true">
        </form>

        <form action="returnToObjectsList" method="GET">
            <td valign="center">
                <input type="submit" class="select1" style="cursor: pointer;" value="вернуться к списку объектов">
            </td>
            <input type="hidden" name="source" value="${source}">
            <input type="hidden" name="forsale" value="${ad.forSale}">
        </form>
    </tr>
</table>

<hr width="1200" align="center" noshade size="2">

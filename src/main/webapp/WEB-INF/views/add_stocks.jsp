<%--
  Created by IntelliJ IDEA.
  User: phoehne
  Date: 6/16/14
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
        <link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.css'/>" type="text/css"/>
    </head>
    <body>
        <h1>Add Stock</h1>

        <form method="POST" action="/add-stock">
            <fieldset>
                <legend>New Stock</legend>
                <div>
                    <label for="ticker">Ticker</label>
                    <input type="text" id="ticker">
                </div>
                <div>
                    <label for="start-date">Start Date</label>
                    <input type="date" id="start-date">
                </div>
                <div>
                    <label for="end-date">End Date</label>
                    <input type="date" id="end-date">
                </div>
            </fieldset>

        </form>
        <sript src="<c:url value='/resources/js/bootstrap.js'/>"></sript>
    </body>
</html>

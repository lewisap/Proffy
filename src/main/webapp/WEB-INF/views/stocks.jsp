<%--
  Created by IntelliJ IDEA.
  User: phoehne
  Date: 6/13/14
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/bootstrap.css'/>"/>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <h1>Stocks</h1>

                <ul class="list-unstyled">
                    <c:forEach var="stock" items="${stocks.stocks}">
                        <li><c:out value="${stock}"/> </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="row">
                <form class="form" method="post" action="/example/stocks">
                    <div>
                        <label for="ticker">Ticker</label>
                        <input type="text" name="ticker" id="ticker" placeholder="Ticker"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Ticker</button>
                </form>
            </div>
        </div>

        <script src="<c:url value='/resources/js/bootstrap.js'/>"/>
    </body>
</html>

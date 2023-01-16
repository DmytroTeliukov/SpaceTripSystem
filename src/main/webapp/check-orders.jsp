<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ваші заяви</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <jsp:include page="component/operator-menu-navigation.jsp" />
</header>
<div class="body">
    <div class="list">
        <c:forEach var="order" items="${orders}">
            <div class="list-item">
                <div class="item-header">
                    <p>Order №${order.id}</p><hr>
                </div>
                <div class="item">
                    <div class="item-information">
                        <div class="item-text">
                            <p>Count ordered places: ${order.orderedSeats}</p>
                            <p>Client: ${order.client.lastname} ${order.client.firstname}</p>
                        </div>
                    </div>
                    <div class="item-status">
                        <p>Status:</p>
                        <p>${order.status}</p>
                    </div>
                    <c:if test="${order.status == 'ORDERED'}">
                        <div class="item-select">
                            <a href='<c:url value="/accept?id=${order.id}" />'>Accept</a>
                            <a href='<c:url value="/deny?id=${order.id}" />'>Deny</a>
                        </div>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
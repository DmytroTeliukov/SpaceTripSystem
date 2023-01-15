<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trips</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <jsp:include page="component/operator-menu-navigation.jsp" />
</header>
<div class="body">
    <div class="list">
        <c:forEach var="trip" items="${trips}">
            <div class="list-item">
                <div class="item-header">
                    <p>Trip №${trip.id}</p><hr>
                </div>
                <div class="item">
                    <div class="item-information">
                        <div class="item-image">
                            <c:if test="${trip.planet == 'MERCURY'}">
                                <img src="image/mini-Mercury.png"/>
                            </c:if>
                            <c:if test="${trip.planet == 'VENERA'}">
                                <img src="image/mini-Venera.png"/>
                            </c:if>
                            <c:if test="${trip.planet == 'EARTH'}">
                                <img src="image/mini-Earth.png"/>
                            </c:if>
                            <c:if test="${trip.planet == 'MARS'}">
                                <img src="image/mini-Mars.png"/>
                            </c:if>
                            <c:if test="${trip.planet == 'JUPITER'}">
                                <img src="image/mini-Jupiter.png"/>
                            </c:if>
                            <c:if test="${trip.planet == 'SATURN'}">
                                <img src="image/mini-Saturn.png"/>
                            </c:if>
                            <c:if test="${trip.planet == 'URANUS'}">
                                <img src="image/mini-Uranus.png"/>
                            </c:if>
                            <c:if test="${trip.planet == 'NEPTUNE'}">
                                <img src="image/mini-Neptune.png"/>
                            </c:if>
                        </div>
                        <div class="item-text">
                            <p>Planet: ${trip.planet}</p>
                            <p>Started: ${trip.started}</p>
                            <p>Status: ${trip.status}</p>
                        </div>
                        <div class="item-select">
                            <a href='<c:url value="/check-orders?id=${order.id}" />'>Check orders</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
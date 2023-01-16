<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>Z
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
    <c:if test="${role == 'CLIENT'}">
        <jsp:include page="component/user-menu-navigation.jsp" />
    </c:if>
    <c:if test="${role == 'ADMIN'}">
        <jsp:include page="component/admin-menu-navigation.jsp" />
    </c:if>
    <c:if test="${empty role}">
        <jsp:include page="component/base-menu-navigation.jsp" />
    </c:if>
</header>
<div class="body">
    <c:if test="${role == 'ADMIN'}">
        <div class="profile">
            <div class="profile-header">
                <a href='<c:url value="/add-trip"/>' class="edit-button">Add trip</a>
            </div>
            <hr>
        </div>
    </c:if>
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

                    <c:if test="${role == 'CLIENT'}">
                    <div class="item-select">
                        <form method="post" action='<c:url value="/trips" />'>
                            <input type="hidden" name="id" value=${trip.id}>

                            <button type="submit" >Order it</button>

                        </form>
                    </div>
                        </c:if>

                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
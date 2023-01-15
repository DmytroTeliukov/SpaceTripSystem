<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Order history</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
  <jsp:include page="component/user-menu-navigation.jsp" />
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
            <div class="item-image">
              <c:if test="${order.trip.planet == 'MERCURY'}">
                <img src="image/mini-Mercury.png"/>
              </c:if>
              <c:if test="${order.trip.planet == 'VENERA'}">
                <img src="image/mini-Venera.png"/>
              </c:if>
              <c:if test="${order.trip.planet == 'EARTH'}">
                <img src="image/mini-Earth.png"/>
              </c:if>
              <c:if test="${order.trip.planet == 'MARS'}">
                <img src="image/mini-Mars.png"/>
              </c:if>
              <c:if test="${order.trip.planet == 'JUPITER'}">
                <img src="image/mini-Jupiter.png"/>
              </c:if>
              <c:if test="${order.trip.planet == 'SATURN'}">
                <img src="image/mini-Saturn.png"/>
              </c:if>
              <c:if test="${order.trip.planet == 'URANUS'}">
                <img src="image/mini-Uranus.png"/>
              </c:if>
              <c:if test="${order.trip.planet == 'NEPTUNE'}">
                <img src="image/mini-Neptune.png"/>
              </c:if>
            </div>
            <div class="item-text">
              <p>Trip №{order.trip.id}</p>
              <p>Planet: ${order.trip.planet}</p>
              <p>Started: ${order.trip.started}</p>
              <p>Status: ${order.trip.status}</p>
            </div>
          </div>
          <div class="item-status">
            <p>Order status:</p>
            <p>${order.status}</p>
          </div>
          <c:if test="${order.status == 'ORDERED'}">
            <div class="item-select">
              <form action='<c:url value="/history"/>' method="post">
                <input type="hidden" name="id" value=${order.id}>
                <button type="submit" >Cancel</button>
              </form>
            </div>
          </c:if>
        </div>
      </div>
    </c:forEach>
  </div>
</div>
</body>
</html>

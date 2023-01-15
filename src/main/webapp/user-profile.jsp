<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Profile</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
  <c:if test="${role == 'CLIENT'}">
    <jsp:include page="component/user-menu-navigation.jsp" />
  </c:if>
  <c:if test="${role == 'OPERATOR'}">
    <jsp:include page="component/operator-menu-navigation.jsp" />
  </c:if>
  <c:if test="${role == 'ADMIN'}">
    <jsp:include page="component/admin-menu-navigation.jsp" />
  </c:if>
</header>
<div class="body">
  <div class="profile">

    <div class="profile-information">
      <p>User: ${lastname} ${firstname}</p>
      <p>E-mail: ${email}</p>
      <p>Phone: ${phone}</p>
    </div>
  </div>
</div>
</body>
</html>
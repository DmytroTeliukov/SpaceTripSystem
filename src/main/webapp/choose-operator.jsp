<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose operator</title>
</head>
<body>
<header>
    <jsp:include page="component/admin-menu-navigation.jsp" />
</header>
<div class="body">
    <div class="list">
        <c:forEach var="operator" items="${operators}">
            <div class="list-item">
                <div class="item">
                    <div class="item-information">
                        <div class="item-text">
                            <p>Operator: ${operator.lastname} ${operator.firstname}</p>
                            <p>E-mail: ${operator.email}</p>
                            <p>Phone: ${operator.phone}</p>
                        </div>
                    </div>
                    <div class="item-select">
                        <form method="post" action='<c:url value="/operators" />'>
                            <input type="hidden" name="email" value=${operator.email}>
                            <button type="submit" >Fire</button>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
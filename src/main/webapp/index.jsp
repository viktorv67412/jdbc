<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body style="margin:0">
<div style="width: 100%; height: calc(100% - 80%); background: #16DB8B;background-size: cover;text-align: center; padding-top:50px">
    <form method="POST" action="<%=request.getContextPath()%>/users">
        Name<br>
        <input type="text" name="name_ui"/><br>
        Age<br>
        <input type="text" name="age_ui"/><br>

        <input type="submit" value="add"/><br>
    </form>
</div>
<div style="width: 100%; height: 80%; background: #16DB8B; background-size: cover; display: inline-block; overflow: scroll;">
    <form method="POST">
        <table align="center" cellpadding="10px" border="1" bgcolor="#E62EB5">
            <%--@elvariable id="users" type="java.util.List"--%>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td >
                        <c:out value="${user}"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>

</body>
</html>

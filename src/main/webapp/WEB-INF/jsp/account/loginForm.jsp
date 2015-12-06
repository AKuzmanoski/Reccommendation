<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/2/2015
  Time: 6:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Login</title>
</head>
<body>
  <div>
    <h2>Login</h2>
    <sf:form method="POST" modelAttribute="user">
      <fieldset>
        <table cellspacing="0">
          <tr>
            <th><label for="name">Name: </label></th>
            <td><sf:input path="name" size="15" id="name"/></td>
          </tr>
          <tr>
            <th><label for="url">URL: </label></th>
            <td><sf:input path="url" size="15" id="url"/></td>
          </tr>
          <tr>
            <th><label for="countryName">Country Name: </label></th>
            <td><sf:input path="country.name" size="15" id="countryName"/></td>
          </tr>
          <tr>
            <th><label for="lastFMId">LastFm Id: </label></th>
            <td><sf:input path="lastFMId" size="15" id="lastFMId"/></td>
          </tr>
          <tr>
            <th><label for="lastFMId">LastFm Id: </label></th>
            <td><input type="submit" value="Register"/></td>
          </tr>
        </table>
      </fieldset>
    </sf:form>
  </div>
</body>
</html>

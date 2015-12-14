<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/12/2015
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add new Track</title>
</head>
<body>
<h2>Add new Track</h2>
<sf:form method="POST" modelAttribute="track">
    <fieldset>
        <table cellspacing="0">
            <tr>
                <th><label for="name">Name: </label></th>
                <td><sf:input path="name" size="15" id="name"/></td>
            </tr>
            <tr>
                <th><label for="artist">Artist: </label></th>
                <td><sf:input path="artist" size="15" id="artist"/></td>
            </tr>
            <tr>
                <th><label for="url">URL: </label></th>
                <td><sf:input path="url" size="15" id="url"/></td>
            </tr>
            <tr>
                <th><label for="mbid">LastFm Id: </label></th>
                <td><sf:input path="mbid" size="15" id="mbid"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Save"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>
</body>
</html>

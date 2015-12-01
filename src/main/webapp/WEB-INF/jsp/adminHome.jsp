<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 12/1/2015
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <form action="/admin/crawl" method="post">
    <table>
      <tr>
        <td>Number of Songs</td>
        <td><input type="text" name="numberOfSongs" value="1000" /></td>
      </tr>
      <tr>
        <td>Number of Users</td>
        <td><input type="text" name="numberOfUsers" value="1000" /></td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Crawl"></td>
      </tr>
    </table>
  </form>

  <form action="/admin/users" method="post">
    <table>
      <tr>
        <td>Number of Users</td>
        <td><input type="text" name="count" value="100"/> </td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Get Users"/></td>
      </tr>
    </table>
  </form>

  <form action="/admin/songs" method="post">
    <table>
      <tr>
        <td>Number of Songs</td>
        <td><input type="text" name="count" value="100"/> </td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Get Songs"/></td>
      </tr>
    </table>
  </form>

  <form action="/admin/countries" method="post">
    <table>
      <tr>
        <td>Number of Countries</td>
        <td><input type="text" name="count" value="100"/> </td>
      </tr>
      <tr>
        <td></td>
        <td><input type="submit" value="Get Countries"/></td>
      </tr>
    </table>
  </form>
</body>
</html>

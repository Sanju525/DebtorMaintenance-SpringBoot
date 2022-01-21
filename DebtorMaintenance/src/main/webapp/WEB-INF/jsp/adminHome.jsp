<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" %>
<html>
<head>
    <%@ include file="include/head_tag.html"%>
    <title>Admin Home</title>
    <style>
        body {
            background-color:hsla(89, 43%, 51%, 0.3);
        }
    </style>
</head>
<body>
<%@ include file="include/admin_nav.html"%>
<div class="container">
    <p>Username: <strong><%= session.getAttribute("username")%></strong></p>
    <p>Admin ID: <strong><%= session.getAttribute("uid")%></strong></p>
    <p>Pending Forms: <strong><%= session.getAttribute("pending")%></strong></p>
</div>

<%@ include file="include/script_tag.html"%>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <%@ include file="include/head_tag.html"%>
    <style>
        <%@ include file="include/styles.css"%>
    </style>
</head>
<body>
<div class="wrapper">
    <header>
        <article class="col-md-4 mx-auto">
            <h1 class=" text-center text-uppercase text-primary pb-2 pt-2">Admin Login</h1>
        </article>
    </header>
    <form class="form-signin shadow-lg p-3 mb-5 bg-body rounded" action = "${pageContext.request.contextPath}/" method="post">
        <div class="body">
            <article class="col-md-8 mx-auto">
                <div class="form-group">
                    <br />
                    <label>Username</label>
                    <input class="form-control" type="text" name="username" placeholder="Username" autofocus required/><br>
                    <label>Password</label>
                    <input class="form-control" type="password" name="password" placeholder="Password"required/><br />
                    <input type="text" name="who" value="adm" hidden>
                    <% String error;%>
                    <%
                    error = (String) session.getAttribute("UserNotFound");
                    session.removeAttribute("UserNotFound");
                    if(error!=null) {
                    %>
                    <p style="color: red">User Not Found! Check with admin</p>

                    <% } %>
                    <%
                    error = (String) session.getAttribute("IncorrectPassword");
                    session.removeAttribute("IncorrectPassword");
                    if(error != null) {
                    %>
                    <p style="color: red">Incorrect Password!</p>

                    <% } %>

                    <input  class="btn btn-success" type="submit" value="Login"/>&nbsp;&nbsp;

                    <input  class="btn btn-warning" type="button" id="debtor" value="Debtor Login"/>
                    <br><br>
            </article>
        </div>
    </form>

    <div class="container">
    <hr>
    <span class="text-muted">Debtor Maintenance</span>
    <br><br>
    </div>
</div></div>
<script>
    document.querySelector("#debtor").addEventListener("click", function (){
        location.href="/"; // todo debtor login page redirect
    })
</script>
<%@ include file="include/script_tag.html"%>
</body>
</html>
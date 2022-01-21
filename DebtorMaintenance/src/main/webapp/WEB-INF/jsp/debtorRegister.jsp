<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Debtor Registration</title>
<%--    <jsp:include page="include/head_tag.html"/>--%>
    <%@ include file="include/head_tag.html"%>
    <style>
<%--        <jsp:include page="include/styles.css"/>--%>
        <%@ include file="include/styles.css"%>
    </style>
</head>
<body>
<div class="wrapper">
    <header>
        <article class="col-md-8 mx-auto">
            <h1 class=" text-center text-uppercase text-primary pb-2 pt-2">Create Your Account</h1>
        </article>
    </header>
    <form class="form-signin shadow-lg p-3 mb-5  bg-body rounded" action = "${pageContext.request.contextPath}/debtor/register" method="post">
        <%--        todo add action--%>
        <div class="body">
            <article class="col-md-8 mx-auto">
                <div class="form-group">
                    <label>Username</label>
                    <input class="form-control" type="text" name="username" placeholder="Username" autofocus required/><br>
                    <label>Password</label>
                    <input class="form-control" type="password" name="password" placeholder="Password"required/><br />

                    <label>Confirm Password</label>
                    <input class="form-control" type="password" name="confirmPassword" placeholder="Confirm Password"required/><br />

                    <% String error; %>
                    <%
                        error = (String) session.getAttribute("error");
                        session.removeAttribute("error");
                        if(error!=null) {
                    %>
                    <p style="color: red">Password Mismatch</p>

                    <% } %>
                    <%
                        error = (String) session.getAttribute("userRegistered");
                        session.removeAttribute("userRegistered");
                        if(error!=null) {
                    %>
                    <p style="color: red">User Already Registered</p>

                    <% } %>

                    <input  class="btn btn-success" type="submit" value="Register"/>&nbsp;&nbsp;
                    <br><br>
                    <p class="text-muted">Already have an account? <a href="/" class="link-info">Login here</a></p>

                </div>
            </article>
        </div>
    </form>

    <div class="container">
        <hr>
        <span class="text-muted">Debtor Maintenance</span>
        <br>
    </div>
</div></div>

<%--<jsp:include page="include/script_tag.html"/>--%>
<%@ include file="include/script_tag.html"%>
</body>
</html>
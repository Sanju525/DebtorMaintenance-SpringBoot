<div class="wrapper">
    <% if (session.getAttribute("message") != null) { %>
    <script type="text/javascript">
        alertFunc("<%=session.getAttribute("message")%>");
    </script>
    <% session.removeAttribute("message"); %>
    <%}%>
    <header>
        <article class="col-md-4 mx-auto">
            <h1 class=" text-center text-uppercase text-primary pb-2 pt-2">Login</h1>
        </article>
    </header>
    <form class="form-signin shadow-lg p-3 mb-5  bg-body rounded" action = "${pageContext.request.contextPath}/" method="post">
        <div class="body">
            <article class="col-md-8 mx-auto">
                <div class="form-group">
                    <label>Username</label>
                    <input class="form-control" type="text" name="username" placeholder="Username" autofocus required/><br>
                    <label>Password</label>
                    <input class="form-control" type="password" name="password" placeholder="Password"required/><br />
                    <input type="text" name="who" value="deb" hidden>
                    <% String error;%>
                    <%
                    error = (String) session.getAttribute("UserNotFound");
                    session.removeAttribute("UserNotFound");
                    if(error!=null && error.equals("true")) {
                    %>
                    <p style="color: red">User Not Found! Please register to Login</p>
                    <% } %>
                    <%
                    error = (String) session.getAttribute("IncorrectPassword");
                    session.removeAttribute("IncorrectPassword");
                    if(error != null && error.equals("true")) {
                    %>
                    <p style="color: red">Incorrect Password!</p>
                    <% } %>

                    <input  class="btn btn-success" type="submit" value="Login"/>&nbsp;&nbsp;


                    <input  class="btn btn-warning" type="button" id="admin" value="Admin Login"/>
                    <br><br>
                    <p class="text-muted">Don't have an account? <a href="${pageContext.request.contextPath}/debtor/register" class="link-info">Register here</a></p>
<%--                    todo link href with register--%>
            </article>
        </div>
    </form>
    <script>
        document.querySelector("#admin").addEventListener("click", function (){
            location.href="/admin/login"; // todo admin page redirect
        })
    </script>
</div></div>
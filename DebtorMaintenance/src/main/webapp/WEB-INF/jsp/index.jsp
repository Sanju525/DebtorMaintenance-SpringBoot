<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Debtor Maintenance</title>
  <%@include file="include/head_tag.html"%>
  <style>
    <%@include file="include/styles.css"%>
  </style>

  <script type="text/javascript">
    function alertFunc(msg) {
      window.alert(msg);
    }
  </script>
</head>
<body>


<%@ include file="login.jsp"%>

<div class="container">
<hr>
<span class="text-muted">Debtor Maintenance</span>
<br><br>
</div>
<%@include file="include/script_tag.html"%>
</body>
</html>

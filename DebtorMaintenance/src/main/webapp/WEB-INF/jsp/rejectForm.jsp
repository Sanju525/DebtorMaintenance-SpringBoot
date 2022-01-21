<!doctype html>
<html>
<head>
<title>
    Reject Reason
</title>
    <%@ include file="include/head_tag.html"%>
    <style>
        body{
            background-color:hsla(89, 43%, 51%, 0.3);
        }
        .rejectForm {
            width: 100%;
            max-width: 1000px;
            padding: 15px;
            margin: auto;
            border-radius: 10px;
            background-color: whitesmoke;
        }
    </style>
</head>
<body>
<div class="container">

    <form class="rejectForm shadow-lg bg-body rounded" action="${pageContext.request.contextPath}/admin/debtor/reject" method="post">
        <input name="txnId" value="${txnId}" hidden>
        <div class="form-group">
            <label for="rejectReason">Reject Reason</label>
            <textarea class="form-control" id="rejectReason" name="rejectReason" rows="3" autofocus required></textarea>
        </div>
        <br>
        <br>
        <input type="submit" value="Reject" class="btn btn-danger">
        <input type="button" value="Cancel" class="btn btn-secondary" onclick="history.back()">
    </form>

</div>
<%@ include file="include/script_tag.html"%>
</body>
</html>
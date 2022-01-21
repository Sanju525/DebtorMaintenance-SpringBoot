<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="include/head_tag.html"%>
    <title>Debtor Form</title>
    <style>
        body{
            background-color:hsla(89, 43%, 51%, 0.3);
        }
        .debtorform {
            width: 100%;
            max-width: 80%;
            padding: 15px;
            margin: auto;
            border-radius: 10px;
            background-color: whitesmoke;
        }
    </style>

</head>
<body>
<%@ include file="include/debtor_nav.html"%>

<div class="container">
    <h3 style="text-align: center">Debtor Form</h3>
    <form class="debtorform shadow-lg p-3 mb-5 bg-body rounded" method="post" action="${pageContext.request.contextPath}/debtor/transaction">
<%--        request--{parameter}--%>
        <h4>Personal Details</h4>
        <hr>
        <div class="row mb-3">
            <label for="debtorId" class="col-sm-2 col-form-label">DebtorID</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="debtorId" name="debtorId"  value="<%= session.getAttribute("uid")%>" readonly>
            </div>
        </div>
        <div class="row mb-3">
            <label for="name" class="col-sm-2 col-form-label">Name</label>
            <div class="col-sm-10">
                <input type="text" name="name" class="form-control" id="name" autofocus required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="address1" class="col-sm-2 col-form-label">Address 1</label>
            <div class="col-sm-10">
                <input type="text" name="address1" class="form-control" id="address1" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="address2" class="col-sm-2 col-form-label">Address 2</label>
            <div class="col-sm-10">
                <input type="text" name="address2" class="form-control" id="address2">
            </div>
        </div>
        <div class="row mb-3">
            <label for="fax" class="col-sm-2 col-form-label">Fax</label>
            <div class="col-sm-10">
                <input type="text" name="fax" class="form-control" id="fax">
            </div>
        </div>
        <div class="row mb-3">
            <label for="phone" class="col-sm-2 col-form-label">Phone</label>
            <div class="col-sm-10">
                <input type="number" name="phone" class="form-control" id="phone" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="email" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input type="email" name="email" class="form-control" id="email" required>
            </div>
        </div>
        <h4>Bank Details</h4>
        <hr>
        <div class="row mb-3">
            <label for="accountNumber" class="col-sm-2 col-form-label">Account Number</label>
            <div class="col-sm-10">
                <input type="number" name="number" class="form-control" id="accountNumber" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="bankName" class="col-sm-2 col-form-label">Bank Name</label>
            <div class="col-sm-10">
                <input type="text" name="bankName" class="form-control" id="bankName" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="branchName" class="col-sm-2 col-form-label">Branch Name</label>
            <div class="col-sm-10">
                <input type="text" name="branchName" class="form-control" id="branchName">
            </div>
        </div>
        <div class="row mb-3">
            <label for="ifsc" class="col-sm-2 col-form-label">IFSC</label>
            <div class="col-sm-10">
                <input type="text" name="swiftAddress" class="form-control" id="ifsc" required>
            </div>
        </div>
        <div class="row mb-3">
            <label for="currency" class="col-sm-2 col-form-label">Currency</label>
            <div class="col-sm-10">
                <select class="form-select" name="accountCurrency" aria-label="Default select example" id="currency" required>
                    <option value="INR" selected>&#8377;</option>
                    <option value="EURO">&euro;</option>
                    <option value="USD">&#36;</option>
                </select>
            </div>
        </div>
        <input name="fromForm" value="true" hidden>
        <button type="submit" class="btn btn-primary">Submit</button>
        <button type="reset" class="btn btn-secondary">Clear</button>
    </form>

    <hr>
    <span class="text-muted">Debtor Maintenance</span>
    <br><br>
</div>

<%@ include file="include/script_tag.html"%>
</body>
</html>

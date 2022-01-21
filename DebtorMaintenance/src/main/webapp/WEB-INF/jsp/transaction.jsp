<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="include/head_tag.html"%>
    <title>Transaction Details</title>
    <style>
        body{
            background-color: hsla(89, 43%, 51%, 0.3);
        }
        .debtorform {
            width: 100%;
            max-width: 1000px;
            padding: 15px;
            margin: auto;
            border-radius: 10px;
            border-style: outset;
            background-color: whitesmoke;
        }
    </style>
</head>
<body>
<%@ include file="include/debtor_nav.html"%>

<form class="debtorform shadow-lg bg-body rounded" action="${pageContext.request.contextPath}/debtor/form" method="post">
<%--    bank | debtorForm || transaction --%>
    <h4>Transaction Details</h4>
    <hr>
    <div class="row mb-3">
        <label for="transactionId" class="col-sm-2 col-form-label"><strong>Transaction ID</strong></label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionId" name="transactionId"  value="${transaction.transactionId}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="transactionDateTime" class="col-sm-2 col-form-label">Date/Time</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionDateTime" name="dateTime"  value="${transaction.dateTime}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="transactionStatus" class="col-sm-2 col-form-label">Status</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionStatus" name="status"  value="${transaction.status}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="transactionInfo" class="col-sm-2 col-form-label">Information</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="transactionInfo" name="information"  value="${transaction.information}" readonly>
        </div>
    </div>
    <hr>
    <h3 style="text-align: center;">Confirm Details</h3>
    <hr>
    <h5>Personal Details</h5>
    <hr>
    <div class="row mb-3">
        <label for="debtorId" class="col-sm-2 col-form-label"><strong>DebtorID</strong></label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="debtorId" name="debtorId"  value="${debtorForm.debtorId}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="name" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
            <input type="text" name="name" class="form-control" id="name" value="${debtorForm.name}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="address1" class="col-sm-2 col-form-label">Address 1</label>
        <div class="col-sm-10">
            <input type="text" name="address1" class="form-control" id="address1" value="${debtorForm.address1}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="address2" class="col-sm-2 col-form-label">Address 2</label>
        <div class="col-sm-10">
            <input type="text" name="address2" class="form-control" value="${debtorForm.address2}" id="address2" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="fax" class="col-sm-2 col-form-label">Fax</label>
        <div class="col-sm-10">
            <input type="text" name="fax" class="form-control" value="${debtorForm.fax}" id="fax" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="phone" class="col-sm-2 col-form-label">Phone</label>
        <div class="col-sm-10">
            <input type="number" name="phone" class="form-control" id="phone" value="${debtorForm.phone}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="email" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
            <input type="email" name="email" class="form-control" id="email" value="${debtorForm.email}" readonly>
        </div>
    </div>
    <h5>Bank Details</h5>
    <hr>
    <div class="row mb-3">
        <label for="accountNumber" class="col-sm-2 col-form-label">Account Number</label>
        <div class="col-sm-10">
            <input type="number" name="number" class="form-control" id="accountNumber" value="${bankAccount.number}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="bankName" class="col-sm-2 col-form-label">Bank Name</label>
        <div class="col-sm-10">
            <input type="text" name="bankName" class="form-control" id="bankName" value="${bankAccount.bankName}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="branchName" class="col-sm-2 col-form-label">Branch Name</label>
        <div class="col-sm-10">
            <input type="text" name="branchName" class="form-control" id="branchName" value="${bankAccount.branchName}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="ifsc" class="col-sm-2 col-form-label">IFSC</label>
        <div class="col-sm-10">
            <input type="text" name="swiftAddress" class="form-control" id="ifsc" value="${bankAccount.swiftAddress}" readonly>
        </div>
    </div>
    <div class="row mb-3">
        <label for="currency" class="col-sm-2 col-form-label">Currency</label>
        <div class="col-sm-10">
            <input type="text" name="accountCurrency" class="form-control" id="currency" value="${bankAccount.accountCurrency}" readonly>
        </div>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>
    <button type="button" class="btn btn-secondary" onclick="history.back()">Back</button>
    <button type="button" class="btn btn-alert" onclick="myFunc()">Cancel</button>

</form>
<script>
    function myFunc() {
        window.location.href = "/debtor/form";
    }
</script>
<%@ include file="include/script_tag.html"%>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>


<!DOCTYPE html>
 
<html>
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
            />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Bill Manager</title>
        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
            rel="stylesheet"
            />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body>
        <%@include file="panner.jsp" %>
        <h1 class="font-weight-semi-bold text-uppercase mb-3 text-center">
            My Orders
        </h1>

        <c:if test="${not empty billDetailForUsers}">
            <div class="col-lg-12 table-responsive mb-5">
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                        <tr>
                            <th style="background-color: #bfd1ec">ID</th>
                            <th style="background-color: #bfd1ec">Name of Customer</th>
                            <th style="background-color: #bfd1ec">Created Date</th>
                            <th style="background-color: #bfd1ec">Address</th>
                            <th style="background-color: #bfd1ec">Email</th>
                            <th style="background-color: #bfd1ec">Phone</th>
                            <th style="background-color: #bfd1ec">Total</th>
                            <th style="background-color: #bfd1ec">Status</th>
                        </tr>
                    </thead>                                       
                    <tbody class="align-middle">
                        <c:forEach items="${billDetailForUsers}" var="b">
                            <tr>
                                <td class="align-middle">${b.id}</td>
                                <td class="align-middle">${b.nameOfCustomer}</td>
                                <td class="align-middle">${b.created_date}</td>
                                <td class="align-middle">${b.address}</td>
                                <td class="align-middle">${b.email}</td>
                                <td class="align-middle">${b.phone}</td>
                                <td class="align-middle">$${Math.round(b.total)*1.0}</td>
                                <td class="align-middle">${b.status}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>


   

        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <%@include file="footer.jsp" %>
    </body>
</html>

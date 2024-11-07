<%-- footer.jsp --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Footer</title>
        <style>
            /* CSS for footer */
            .footer {
                background-color:  #3f51b5; /* Màu nền */
                color: #ffffff; /* Màu chữ */
                padding: 30px 0; /* Padding */
                text-align: center; /* Center align text */
            }
           .footer-text {
            margin-bottom: 10px; /* Bottom margin for text */
            font-family: Arial, sans-serif; /* Specify your desired font family */
        }

        .footer-text strong {
            font-weight: bold; /* Make text inside <strong> tags bold */
        }
    </style>
</head>
<body>

<!-- Footer -->
<footer class="footer">
    <div class="footer-content">
        <h3>Contact Us</h3>
        <p class="footer-text"><strong>Designed by:</strong> Nguyen Van Huy</p>
        <p class="footer-text"><strong>Phone:</strong> 123456789</p>
        <p class="footer-text"><strong>Email:</strong> huy123@gmail.com</p>
        <p class="footer-text"><strong>Address:</strong> 123 FU Hola Town, Viet Nam</p>
        <p class="footer-text"><strong>Copyright &copy; Your Website 2024</strong></p>

            </div>
        </footer>

    </body>
</html>

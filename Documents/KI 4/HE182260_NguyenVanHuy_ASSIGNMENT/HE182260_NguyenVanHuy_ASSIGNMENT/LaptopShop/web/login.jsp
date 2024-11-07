<%-- 
    Document   : login
    Created on : May 5, 2024, 7:58:15 PM
    Author     : DungTT
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-..." crossorigin="anonymous" />

        <style>
            body {
                background-color: #f5f5f5;
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }

            #login-form-wrap {
                background-color: #fff;
                width: 350px;
                border-radius: 8px;
                box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
                padding: 40px;
                text-align: center;
            }

            h2 {
                font-weight: bold;
                color: #333;
                margin-bottom: 20px;
            }

            label {
                display: block;
                text-align: left;
                font-size: 16px; /* Increased font size */
                color: #333;
                margin-bottom: 5px;
            }

            input[type="text"],
            input[type="password"] {
                width: 100%;
                padding: 12px;
                margin: 8px 0 16px 0; /* Adjusted margin for better spacing */
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                font-size: 16px;
            }

            input[type="submit"] {
                background-color: #007bff;
                color: #fff;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s ease;
                width: 100%;
                margin-top: 20px;
            }

            input[type="submit"]:hover {
                background-color: #0056b3;
            }

            #create-account-wrap {
                margin-top: 20px;
                font-size: 14px;
                color: #666;
            }

            #create-account-wrap a {
                color: #007bff;
                text-decoration: none;
            }

            #create-account-wrap a:hover {
                text-decoration: underline;
            }

            .error-message {
                color: red;
                margin-top: 10px;
            }

            #login-with-google {
                background-color: #dd4b39;
                color: #fff;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
                margin-top: 20px;
                width: 100%;
                transition: background-color 0.3s ease;
            }

            #login-with-google:hover {
                background-color: #c23321;
            }

            #forgot-password-link {
                display: block;
                margin-top: 10px;
                color: #007bff;
                text-decoration: none;
                font-size: 14px;
            }
            .password-container {
                position: relative;
                width: 100%;
            }

            .password-input {
                width: calc(100% - 30px);
                padding-right: 30px;
            }

            .toggle-password {
                position: absolute;
                top: 50%;
                right: 0;
                transform: translateY(-50%);
                cursor: pointer;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <div id="login-form-wrap">
            <h2>LAPTOP SHOP Login</h2>
            <form id="login-form" action="login" method="POST">
                <input type="hidden" name="returnUrl" value="${param.returnUrl}">
                <div>
                    <label for="username" style="font-weight: bolder">Enter Username or Email or Phone:</label>
                    <input type="text" id="username" name="username" value="${param.username}" required>
                </div>
                <div class="password-container">
                    <label for="username" style="font-weight: bolder">Enter Password:</label>
                    <input type="password" id="password" name="password" class="password-input" required>
                    <!-- Font Awesome eye icon -->
                    <span class="toggle-password" onclick="togglePasswordVisibility()">
                        <i class="fas fa-eye"></i>
                    </span>
                </div>

                <p class="error-message">${requestScope.usernameOrPasswordWrong}</p>

                <input type="submit" id="login" value="Login">
            </form>
            <div id="create-account-wrap">
                <p>Not a member? <a href="register.jsp">Create Account</a></p>
            </div><!--create-account-wrap-->

            <!-- New Elements -->
            <button onclick="handleGoogleLogin()" id="login-with-google">Login with Google</button>
            <a href="forgotpassword.jsp" id="forgot-password-link">Forgot Password?</a>
        </div><!--login-form-wrap-->
        <script>
            function togglePasswordVisibility() {
                var passwordInput = document.getElementById('password');
                var toggleIcon = document.querySelector('.toggle-password');

                if (passwordInput.type === 'password') {
                    passwordInput.type = 'text';
                    toggleIcon.innerHTML = '<i class="fas fa-eye-slash"></i>';
                } else {
                    passwordInput.type = 'password';
                    toggleIcon.innerHTML = '<i class="fas fa-eye"></i>';
                }
            }

            function handleGoogleLogin() {
                var returnUrl = document.getElementsByName('returnUrl')[0].value;
                /*
                 document.getElementsByName('returnUrl'): Lấy tất cả các phần tử có thuộc tính name là 'returnUrl'.
                 [0]: Chọn phần tử đầu tiên trong danh sách các phần tử tìm được.
                 .value: Lấy giá trị của phần tử đầu tiên đó.
                 */
                var redirectUrl = 'https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/LaptopShop/logingoogle&response_type=code&client_id=363633922350-eo4nt45e1l2mj7cjktc5g7qskvgglme3.apps.googleusercontent.com&approval_prompt=force';
                if (returnUrl) {
                    redirectUrl += '&state=' + encodeURIComponent(returnUrl);
                }
                window.location.href = redirectUrl;
            }
        </script>
    </body>
</html>






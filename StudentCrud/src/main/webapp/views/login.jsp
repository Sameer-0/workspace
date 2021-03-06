<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
  <%@page session="false" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1" />
      <title>Student Login</title>
      <link rel="stylesheet" href="/views/style.css" />
    </head>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <body>
      <h2 class="msg-error" id="msg">${errorMsg}</h2>
      <div class="container" style="width: 600px;">
        <h2>Student Login</h2>
        <form id="login-form" action="/dashboard" method="post">
          <div class="input-name">
            <label for="id">Student Id</label>
            <span id="id-message" class="error"></span> <br /><input type="text" id="id" class="id" name="id" />
          </div>

          <div class="input-name">
            <label for="password">password</label>
            <span id="password-message" class="error"></span> <br /><input type="text" id="password" class="password"
              name="password" />
          </div>

          <div class="button1">
            <input type="submit" id="button" class="button" value="Login" />
          </div>
        </form>
      </div>
      <div id="btn-div">
        Create New Account
        <a href="/views/index.jsp" id="a-tag">
          <button id="login-btn" type="button">Register</button>
        </a>
      </div>
    </body>

    </html>
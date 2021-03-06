<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="ISO-8859-1" />
    <title>Student Registration</title>
    <link rel="stylesheet" href="/views/style.css" />
  </head>

  <body>
    <h2 class="msg-error" id="msg"></h2>
    <div class="container" style="width: 600px">
      <h2>Student Registration form</h2>
      <div class="form-container">
        <form
          method="post"
          id="myform"
          onclick="formValidation(event)"
          action="student-login"
        >
          <label for="name">Name</label>
          <span id="name-message" class="error"></span>
          <input type="text" class="name" name="name" id="name" /><br />

          <label for="id">Student Id</label
          ><span id="id-message" class="error"></span>
          <input type="text" class="name" name="id" id="id" /><br />

          <label for="email">Email Id</label
          ><span id="email-message" class="error"></span><br />
          <input type="text" class="email" name="email" id="email" /><br />

          <label for="password">Password</label
          ><span id="password-message" class="error"></span><br />
          <input
            type="text"
            class="password"
            name="password"
            id="password"
          /><br />

          <input type="submit" id="button" class="button" value="Register" />
        </form>
      </div>
    </div>
    <div id="btn-div">
      Already a user?
      <a href="/views/login.jsp" id="a-tag">
        <button id="login-btn" type="button">Login</button>
      </a>
    </div>

    <script src="/views/index.js"></script>
  </body>
</html>

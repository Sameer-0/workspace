<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
      <meta charset="ISO-8859-1" />
      <title>Edit Student</title>
      <link rel="stylesheet" href="/views/style.css" />
    </head>

    <body>
      <div id="student-editDiv">
        <div class="container">
          <h2>Edit Details</h2>
          <div class="form-container">
            <form method="post" id="myform" action="/student-update">
              <label for="name">Name</label>
              <span id="name-message" class="error"></span>
              <input type="text" class="name" name="name" id="name" value="${name}" /><br />

              <label for="id">Student Id</label><span id="id-message" class="error"></span>
              <input type="text" class="name" name="id" id="id" value="${id}" /><br />

              <label for="email">Email Id</label><span id="email-message" class="error"></span><br />
              <input type="text" class="email" name="email" id="email" value="${email}" /><br />

              <label for="password">Password</label><span id="password-message" class="error"></span><br />
              <input type="text" class="password" name="password" id="password" value="${password}" /><br />

              <input type="hidden" class="studentNo" name="studentNo" id="studentNo" value="${studentNo}">

              <input type="submit" id="button" class="button" value="Edit" />
            </form>
          </div>
        </div>
      </div>
    </body>

    </html>
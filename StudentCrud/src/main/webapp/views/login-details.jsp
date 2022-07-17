<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html>
  <title>Details</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <body>
    <div class="w3-sidebar w3-bar-block w3-light-grey w3-card" style="width:160px;">
      <button class="w3-button w3-block w3-left-align" onclick="myAccFunc()">
        Profile <i class="fa fa-caret-down"></i>
      </button>
      <div id="menu" class="w3-hide w3-white w3-card">
        <c:forEach var="list" items="${lists}">
          <a href="${list.link}/${id}" class="w3-bar-item w3-button">${list.name}</a><br>
        </c:forEach>
      </div>
    </div>

    <script>
      function myAccFunc() {
        var x = document.getElementById("menu");
        if (x.className.indexOf("w3-show") == -1) {
          x.className += " w3-show";
          x.previousElementSibling.className += " w3-orange";
        } else {
          x.className = x.className.replace(" w3-show", "");
          x.previousElementSibling.className =
          x.previousElementSibling.className.replace(" w3-orange", "");
        }
      }
    </script>

  </body>

  </html>
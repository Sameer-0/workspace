<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@page session="false" %>
    <!DOCTYPE html>
    <html>
    <title>Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/views/style.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

    <body>
      <div id="complete-div" style="margin-top: 0px;">
        <header>
          <div class="w3-sidebar w3-bar-block w3-light-grey w3-card" style="width:160px;">
            <button class="w3-button w3-block w3-left-align" onclick="myAccFunc()">
              Profile <i class="fa fa-caret-down"></i>
            </button>
            <div id="menu" class="w3-hide w3-white w3-card">
              <c:forEach var="list" items="${lists}">
                <a id="${list.id}" class="w3-bar-item w3-button">${list.name}</a><br>
              </c:forEach>
            </div>
          </div>
        </header>
        <div id="main-area">
          <p id="para-id" style="font-size: 30px;">Welcome ${id}</p>
        </div>
      </div>
      <script>
        function myAccFunc() {
          var x = document.getElementById("menu");
          if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
            x.previousElementSibling.className += " w3-indigo";
          } else {
            x.className = x.className.replace(" w3-show", "");
            x.previousElementSibling.className =
              x.previousElementSibling.className.replace(" w3-indigo", "");
          }
        }

        $(document).ready(function () {
          $('#2').on('click', function () {
            var id = '${id}'
            console.log(id)
            $.ajax({
              url: '/view-student',
              type: 'POST',
              dataType: 'json',
              data: { "id": id },
              success: function (response) {
                console.log(response)

                let table = `<table id=" table-display">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Student No</th>
                    </tr>
                </thead>
                <tbody>
                `;
                response.forEach(element => {

                  table += `<tr><td>\${element.id}</td>\<td>\${element.name}\</td>\<td>\${element.email}\</td>\<td>\${element.studentNo}\</td></tr>`
                })

                table += `</tbody> </table>`;
                $('#main-area').html(table)
              },
              error: function (err) {
                console.log(("Error", err))
              }
            })
          })

          $('#4').on('click', function () {
            var id = '${id}'
            $.ajax({
              url: '/view-student',
              type: 'POST',
              dataType: 'json',
              data: { "id": id },
              success: function (response) {
                console.log(response);
                let table = '';
                response.forEach(element => {
                  console.log(element)
                  table += `
                <div class="container">
                  <h2>Edit Details</h2>
                  <div class="form-container">
                    <form method="post" id="myform" action="/student-update">
                      <label for="name">Name</label>
                      <span id="name-message" class="error"></span>
                      <input type="text" class="name" name="name" id="name" value=\${element.name}\><br />

                      <label for="id">Student Id</label><span id="id-message" class="error"></span>
                      <input type="text" class="name" name="id" id="id" value=\${element.id}\><br />

                      <label for="email">Email Id</label><span id="email-message" class="error"></span><br />
                      <input type="text" class="email" name="email" id="email" value=\${element.email}\><br />

                      <label for="password">Password</label><span id="password-message" class="error"></span><br />
                      <input type="text" class="password" name="password" id="password" value=\${element.password}\><br />

                      <input type="hidden" class="studentNo" name="studentNo" id="studentNo" value=\${element.studentNo}\>

                        <input type="submit" id="update-button" class="button" value="Edit" />
                    </form>
                  </div>
                </div>`
                });
                $('#main-area').html(table)
              },
              error: function (err) {
                console.log("Error", err)
              }
            })
          })

          $('#3').on('click', function () {
            let confirmDelete = confirm('Are you sure you want to delete your account?');
            if (confirmDelete) {
              console.log("Entered")
              var id = '${id}';
              $.ajax({
                url: '/delete-student',
                type: 'POST',
                data: { "id": id },
                success: function (response) {
                  if (response == "deleted") {
                    window.location = '/views/login.jsp'
                  }
                },
                error: function (err) {
                  window.location = '/views/login.jsp'
                  console.log('Error', err);
                }
              })

            } else {
              console.log("Canceled")
              return false;
            }
          })

          $('#6').on('click', function () {
            console.log("Entered")
            $.ajax({
              url: '/logout-student',
              type: 'GET',
              success: function () {

                let x = confirm('Are you sure you want to Logout?');
                if (x) {
                  window.location = '/views/login.jsp'
                } else {
                  console.log("Canceled");
                  return false;
                }
              },
              error: function (error) {
                console.log("Error", error)
              }
            })
          })

          $('#5').on('click', function () {

            console.log('Entered')
            let form = `
            <div class="container">
                  <h2>Faculty Application</h2>
                  <div class="form-container">
                    <form id="faculty-form" enctype="multipart/form-data" method="POST" action="/faculty-submission">

                      <label for="photo">Passport Size Photo</label><span class="error"></span><br />
                      <input accept="image/jpeg, image/png" type="file" class="photo" name="photo" id="photo" onchange="document.getElementById('preview').src = window.URL.createObjectURL(this.files[0])">
                      <img id="preview" alt="your image" width="100" height="100" style="border: 1px solid #ced4da; margin-left:100px;"/><br>

                      <label for="name">Name</label>
                      <span id="name-message" class="error"></span>
                      <input type="text" class="name" name="name" id="faculty-name"><br />

                      <label for="email">Email</label><span id="email-message" class="error"></span><br />
                      <input type="text" class="email" name="email" id="faculty-email"><br />
                      
                      <label for="contactNo">Contact No.</label><span id="contact-message" class="error"></span>
                      <input type="text" class="contactNo" name="contactNo" id="faculty-contact" ><br />

                      <label for="aadhar">Aadhar number</label><span id="aadhar-message" class="error"></span><br />
                      <input type="text" class="aadhar" name="aadhar" id="aadhar"><br />

                      <label for="pan">Pan number</label><span id="pan-message" class="error"></span><br />
                      <input type="text" class="pan" name="pan" id="pan"><br />

                      <input type="submit" id="next-button" class="button" value="Next" />
                    </form>
                </div>
            </div>
            `
            $('#main-area').html(form)
          })

          $('#faculty-form').submit(function (e) {

            e.preventDefault();
            var facultyDetails = new FormData($("#faculty-form")[0]);

            $.ajax({
              type: 'POST',
              url: '/faculty-submission',
              data: facultyDetails,
              cache: false,
              contentType: false,
              processData: false,
              success: function (data) {

                console.log(data);
                let message = `
                  <h1>Done</h1>
                `
                $('#main-area').html(message)

              },
              error: function (error) {
                console.log("ERROR", error);
              }
            })
          })
        })
      </script>
      <!-- <script src="/views/faculty.js"></script> -->
    </body>

    </html>
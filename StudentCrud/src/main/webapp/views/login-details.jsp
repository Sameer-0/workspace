<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@page session="false" %>
    <!DOCTYPE html>
    <html>
    <title>Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"> -->
    <link rel="stylesheet" href="/views/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"
      integrity="sha512-1sCRPdkRXhBV2PBLUdRb4tMg1w2YPf37qatUFeS7zlBy7jJI8Lf4VHwWfZZfpXtYSLy85pkm9GaYVYMfw5BC1A=="
      crossorigin="anonymous" referrerpolicy="no-referrer" />

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

      <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
      <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
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
          $(document).on('click', '#2', function () {
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

          $(document).on('click', '#4', function () {
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
                <div class="container" style="max-width:600px;">
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

          $(document).on('click', '#3', function () {
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

          $(document).on('click', '#6', function () {
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

          $(document).on('click', '#5', function () {

            console.log('Entered')
            let form = `
            <div class="container" style="max-width:600px;">
                  <h2>Faculty Application</h2>
                  <div class="form-container">
                    <div id="faculty-form" enctype="multipart/form-data">

                      <label for="image">Passport Size Photo</label><span class="error"></span><br />
                      <input accept="image/jpeg, image/jpg, image/png" type="file" class="image" name="image" id="image" onchange="document.getElementById('preview').src = window.URL.createObjectURL(this.files[0])">
                      <img id="preview" alt="your image" width="100" height="100" style="border: 1px solid #ced4da; margin-left:100px;"/><br>

                      <label for="name">Name</label>
                      <span id="name-message" class="error"></span>
                      <input type="text" class="name" name="name" id="name"><br />

                      <label for="email">Email</label><span id="email-message" class="error"></span><br />
                      <input type="text" class="email" name="email" id="email"><br />
                      
                      <label for="contactNo">Contact No.</label><span id="contact-message" class="error"></span>
                      <input type="text" class="contactNo" name="contactNo" id="contactNo" ><br />

                      <label for="aadhar">Aadhar number</label><span id="aadhar-message" class="error"></span><br />
                      <input type="text" class="aadhar" name="aadhar" id="aadhar"><br />

                      <label for="pan">Pan number</label><span id="pan-message" class="error"></span><br />
                      <input type="text" class="pan" name="pan" id="pan"><br />

                      <input type="submit" id="next-button" class="button" value="Next" />
                    </div>
                </div>
            </div>
            `
            $('#main-area').html(form)

          })

          $(document).on('click', '#next-button', function (e) {
            e.preventDefault();

            let personalDetailsForm = new FormData();

            personalDetailsForm.append('image', $('#image')[0].files[0]);
            personalDetailsForm.append('name', $('#name').val())
            personalDetailsForm.append('email', $('#email').val())
            personalDetailsForm.append('contactNo', $('#contactNo').val())
            personalDetailsForm.append('aadhar', $('#aadhar').val())
            personalDetailsForm.append('pan', $('#pan').val())

            $.ajax({
              type: 'POST',
              url: '/faculty-submission',
              data: personalDetailsForm,
              contentType: false,
              processData: false,
              success: function (data) {

                console.log(data);
                let message = `
                <div class="container" >
                              <div class="table-responsive" id="form-container">
                      <table id="mytable" class="table table-hover">
                        <div class="d-flex justify-content-between">
                          <h4>Teaching Experience</h4>
                              <button id="add-btn"class="btn btn-primary bi bi-plus-circle"></button>
                        </div>
                          <thead>
                              <tr>
                                  <th>University</th>
                                  <th>Subject</th>
                                  <th>Years of Experience</th>
                                  <th>From</th>
                                  <th>to</th>
                                  <th>Action</th>
                              </tr>
                          </thead>
                          <tbody id="table-body">
                              <tr>
                                  <td>
                                      <input type="text" name="university" class="university">
                                  </td>
                                  <td>
                                      <input type="text" name="subject" class="subject">
                                  </td>
                                  <td>
                                      <input type="text" name="experience" class="experience">
                                  </td>
                                  <td>
                                      <input type="date" name="from-date" class="from-date">
                                  </td>
                                  <td>
                                      <input type="date" name="to-date" class="to-date">
                                  </td>
                                  <td>
                                      <input type="hidden">
                                  </td>
                              </tr>
                          </tbody>
                      </table>
                      <div class=" d-flex justify-content-end">
                          <button id="experience-submit" class="btn btn-success">Submit</button>
                      </div>
                  </div>
                </div>
                `
                $('#main-area').html(message)
              },
              error: function (error) {
                console.log("ERROR", error);
              }
            })
          })

          $(document).on('click', '#add-btn', function () {
            let count = 1
            console.log("clicked")
            let table = `
            <tr>
              <td>
                  <input type="text" name="university" class="university">
              </td>
              <td>
                  <input type="text" name="subject" class="subject">
              </td>
              <td>
                  <input type="text" name="experience" class="experience">
              </td>
              <td>
                  <input type="date" name="from-date" class="from-date">
              </td>
              <td>
                  <input type="date" name="to-date" class="to-date">
              </td>
              <td>
                <button class="delete-btn btn btn-danger bi bi-trash"></button>
              </td>
          </tr>
            `
            $('#table-body').append(table)
          })

          $(document).on('click', '.delete-btn', function () {
            let confirmDelete = confirm("Do you really want to delete this row?")
            if (confirmDelete) {
              $(this).closest('tr').remove()
              // $(this).parent().parent().remove()
            }
          })

          $(document).on('click', '#experience-submit', function (e) {
            e.preventDefault()
            console.log("Experience enteredddddd")

            let expArr = []

            $('#mytable tbody tr').each((idx, elem) => {
              let expObj = {}
              expObj.university = $(elem).find('.university').val()
              expObj.subject = $(elem).find('.subject').val()
              expObj.yearsOfExperience = $(elem).find('.experience').val()
              expObj.startDate = $(elem).find('.from-date').val()
              expObj.endDate = $(elem).find('.to-date').val()

              expArr.push(expObj)

            })
            $.ajax({
              url: '/experience-submission',
              type: 'POST',
              contentType: 'application/json',
              data: JSON.stringify(expArr),
              success: function (response) {
                console.log("SUCCESS EXPERIENCE")
                console.log(response)
                let previewForm = `
                <div class="form-preview">
                      <div class="personal-preview">
                          <div class="d-flex">
                              <h3>Personal Information</h3>
                              <div class="justify-content-end">
                                  <i class="fa-solid fa-pen-to-square" style="color: blue;"></i>
                              </div>
                          </div>
                          <div class="d-flex justify-content-between">
                              <div class="name d-flex flex-column justify-content-evenly flex-fill">
                                  <div class="border flex-fill">
                                    <h5>Full Name:</h5>
                                      <input type="text" readonly class="form-control-plaintext" id="faculty-details-name"
                                          value="Sameer Shaikh">
                                  </div>
                                  <div class="border flex-fill">
                                    <h5>Contact no:</h5>
                                      <input type="text" readonly class="form-control-plaintext" id="faculty-details-contactNo"
                                          value="8879338546">
                                  </div>
                                  <div class="border flex-fill">
                                    <h5>Email Id:</h5>
                                      <input type="text" readonly class="form-control-plaintext" id="faculty-details-email"
                                          value="Hello@gmail.com">
                                  </div>
                              </div>
                              <div class="photo border border-3" style="width: 130px; height:160px;">
                                  <img src="" style="max-width: 100%;">
                              </div>
                          </div>
                          <div class="pan-details">
                              <div class="border">
                                <h5>PAN Number:</h5>
                                  <input type="text" readonly class="form-control-plaintext" id="faculty-details-name"
                                      value="AAAAA1111A">
                              </div>
                          </div>
                          <div class="aadhar-details">
                              <div class="border">
                                <h5>Aadhar Number:</h5>
                                  <input type="text" readonly class="form-control-plaintext" id="faculty-details-name"
                                      value="112233445566">
                              </div>
                          </div>
                      </div>

                      <div class="experience-preview">
                          <div class="table-responsive">
                              <h5>Teaching Experience:</h5>
                              <table class="table-display table table-bordered experience-details" id="teachingDetails-table">
                                  <thead>
                                      <th>University</th>
                                      <th>Subject</th>
                                      <th>Years Of Experience</th>
                                      <th>From</th>
                                      <th>To</th>
                                      <th>Edit</th>
                                  </thead>
                                  <tbody>
                
                `
                response.forEach(element => {
                  previewForm += `
                                      <tr>
                                          <td>\${element.university}\</td>
                                          <td>\${element.subject}\</td>
                                          <td>\${element.yearsOfExperience}\</td>
                                          <td>\${element.startDate}\</td>
                                          <td>\${element.endDate}\</td>
                                          <td>
                                              <i class="fa-solid fa-pen-to-square" style="color: blue;"></i>
                                          </td>
                                      </tr>
                `
                })
                previewForm += `
              </tbody>
                              </table>
                          </div>
                      </div>
                  </div>
                  <footer class="mt-5">
                      <button class="btn btn-success" style="display: block; margin: auto; width: 50%;">Submit</button>
                  </footer>
                  </div>
                  </div>
                `

                $('#main-area').html(previewForm)
              },
              error: function (err) {
                console.log("ERROR", err);
              }
            })
          })
        })
      </script>
      <!-- <script src="/views/faculty.js"></script> -->
    </body>

    </html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <!DOCTYPE html>
  <html>
  <title>Details</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

  <style>
    table,
    th,
    /* td {
      border-bottom: 1px solid #ddd;
      text-align: center;
    } */

    table {
      width: 50%;
      border-collapse: collapse;
    }

    th {
      height: 30px;
      padding: 15px;
    }

    /* td {
      height: 50px;
      vertical-align: bottom;
      padding: 15px;
    } */
  </style>

  <body>
    <header>
      <div class="w3-sidebar w3-bar-block w3-light-grey w3-card" style="width:160px;">
        <button class="w3-button w3-block w3-left-align" onclick="myAccFunc()">
          Profile <i class="fa fa-caret-down"></i>
        </button>
        <div id="menu" class="w3-hide w3-white w3-card">
          <c:forEach var="list" items="${lists}">
            <a href="${list.link}/${id}" id="${list.id}" class="w3-bar-item w3-button">${list.name}</a><br>
          </c:forEach>
        </div>
      </div>
    </header>
    <div style="text-align: center;" id="main-area">
      <div id="view-studentDiv" style="margin-left: 200px;">
      
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

      let button1 = document.getElementById('2');
      
      button1.addEventListener('click', function viewStudent(e) {
        e.preventDefault();
        console.log("Entered")

        const xhr = new XMLHttpRequest();

        xhr.open('GET', 'view-student/${id}', true);

        xhr.onprogress = function () {
          console.log("on Progresss");
        }

        xhr.onload = function () {
          if (this.status == 200) {

            let obj = JSON.parse(xhr.responseText);
            console.log(obj.length)

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

            for (let i = 0; i < obj.length; i++) {
              console.log(obj[i])
              
              table += `<tr><td>\${obj[i].id}</td>\<td>\${obj[i].name}\</td>\<td>\${obj[i].email}\</td>\<td>\${obj[i].studentNo}\</td></tr>`
            }

            table +=`</tbody> </table>`;
            console.log('table:::::::::::::',table)
            $('#view-studentDiv').append(table)
            console.log(obj)
            
          } else {
            console.log("Error", xhr);
          }
          
        }
        xhr.send();
      },{once:true})

      // $('button1').click(function (e) {
      //   e.preventDefault();
      //   $.ajax({
      //     type: 'GET',
      //     url: 'view-student/${id}',
      //     success: function (result) {
      //       let obj = JSON.parse(xhr.responseText);
      //       console.log(obj)
      //     }
      //   })
      // })
      // $('button1').click(function (e) {
      //   e.preventDefault();
      //   $.ajax({
      //     url: 'view-student/${id}',
      //     method: 'GET',
      
      //     success: function (responseText) {
      //       console.log(this.responseText);
      //     }
      //   })
      // })
    </script>

  </body>

  </html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>View Student</title>
        <link rel="stylesheet" href="/views/login-style.css">
    </head>

    <body>
        <header>
            <jsp:include page="/views/login-details.jsp" />
        </header>
        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Student No</th>
            </tr>
            <tr>
                <td>${id}</td>
                <td>${name}</td>
                <td>${email}</td>
                <td>${studentNo}</td>
            </tr>
        </table>

    </body>

    </html>
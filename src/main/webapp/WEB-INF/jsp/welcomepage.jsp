<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcom Mike</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
          rel="stylesheet">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Test Referral Add Page </title>
    <style>
        table, td, th, tr, caption {
            width: 50%;
            padding: 15px;
            margin-left: 250px;
        }

        tr {
            float: left;
        }

        h1  {
            margin-left: 75px;
            color: brown;
            font-family: Verdana;

        }

        h2 {
            margin-left: 75px;
            color: darkblue;
            font-family: Verdana;

        }

        body {
            font-family: Verdana;
        }
    </style>
</head>
<body>
<form class="text-center border border-light p-5" method="post">
    <h1>Animana Assignment</h1>
    <h2>Search here for albums/books</h2>
    <table class="table table-striped">
        <tbody>
        <tr>
            <!-- search bar -->
            <td><input type="text" name="searchField" id="searchelement" class="form-control mb-4"
                       placeholder="Enter the text to search"
                       style="width:300px;">
            </td>
            <td>
                <input class="btn btn-info" type="submit"/>
            </td>

        </tr>
        <tr>
            <td><h5>Developed by @Michael</h5></td>
        </tr>
        </tbody>
    </table>

    <table class="table table-striped">
        <caption> List of Albums & Books</caption>

        <thead>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Type</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${resources}" var="resources">
        <tr>
            <td>${resources.title}</td>
            <td>${resources.author}</td>
            <td>${resources.type}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <h1>${errorMessage}</h1>
</form>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: amoee
  Date: 7/22/2024
  Time: 1:03 PM
--%>

<!-- grails-app/views/book/addBook.gsp -->
<html>
<head>
    <title>Add New Book</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .container {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        padding: 20px;
        max-width: 500px;
        width: 100%;
        margin: 20px;
    }

    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 20px;
    }

    label {
        display: block;
        margin: 10px 0 5px;
        color: #555;
    }

    input[type="text"],
    input[type="date"],
    input[type="file"],
    button {
        width: 100%;
        padding: 10px;
        margin: 5px 0 20px;
        border: 1px solid #ddd;
        border-radius: 4px;
        box-sizing: border-box;
    }

    button {
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
        font-size: 16px;
    }

    button:hover {
        background-color: #0056b3;
    }

    .back-link {
        display: block;
        text-align: center;
        color: #007bff;
        text-decoration: none;
        font-weight: bold;
    }

    .back-link:hover {
        text-decoration: underline;
    }
    </style>
</head>

<body>
<div class="container">
    <h1>Add New Book</h1>

    <form action="${createLink(controller: 'book', action: 'saveBook')}" method="post" enctype="multipart/form-data">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required/>

        <label for="author">Author:</label>
        <input type="text" id="author" name="author" required/>

        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn" required/>

        <label for="image">Image:</label>
        <input type="file" id="image" name="image"/>

        <button type="submit">Save</button>
    </form>
    <a href="${createLink(controller: 'book', action: 'index')}" class="back-link">Back to Book List</a>
</div>
</body>
</html>

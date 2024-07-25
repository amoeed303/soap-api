<%--
  Created by IntelliJ IDEA.
  User: amoee
  Date: 7/22/2024
  Time: 1:03 PM
--%>
<!-- grails-app/views/book/index.gsp -->
<html>
<head>
    <title>Book List</title>
    <style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

    h1 {
        text-align: center;
        margin-top: 20px;
        color: #333;
    }

    .btn {
        display: inline-block;
        padding: 10px 20px;
        margin: 5px;
        text-decoration: none;
        border-radius: 4px;
        color: #fff;
        font-weight: bold;
        text-align: center;
    }

    .btn-primary {
        background-color: #007bff;
    }

    .btn-warning {
        background-color: #ffc107;
    }

    .btn-danger {
        background-color: #dc3545;
    }

    .container {
        max-width: 1200px;
        margin: 0 auto;
        padding: 0 20px;
        position: relative;
    }

    .add-button {
        position: absolute;
        top: 20px;
        right: 20px;
    }

    .book-list {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        padding: 20px 0;
    }

    .book-card {
        background-color: #fff;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        margin: 10px;
        overflow: hidden;
        width: 250px;
        text-align: center;
    }

    .book-image {
        width: 250px;
        height: 300px;
        object-fit: fill;
    }

    .book-details {
        padding: 15px;
    }

    .book-details h2 {
        font-size: 18px;
        margin: 10px 0;
    }

    .book-details p {
        margin: 5px 0;
        color: #555;
    }
    </style>
</head>
<body>
<div class="container">
    <h1>Book List</h1>
    <a href="${createLink(controller: 'book', action: 'saveBook')}" class="btn btn-primary add-button">Add New Book</a>
    <div class="book-list">
        <g:each in="${books}" var="book">
            <div class="book-card">
               <!-- <img src="${createLink(controller: 'book', action: 'showImage', id: book.id)}" alt="${book.title}" class="book-image" /> -->
                <div class="book-details">
                    <h2>${book.title}</h2>
                    <p>Author: ${book.author}</p>
                    <p>ISBN: ${book.isbn}</p>
                    <a href="${createLink(controller: 'book', action: 'updateBook', id: book.id)}" class="btn btn-warning">Update</a>
                    <a href="${createLink(controller: 'book', action: 'deleteBook', id: book.id)}" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                </div>
            </div>
        </g:each>
    </div>
</div>
</body>
</html>

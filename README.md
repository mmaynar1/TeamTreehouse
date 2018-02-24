# TeamTreehouse
This repository contains Team Treehouse projects

### [course-ideas](course-ideas)
A web app for submitting and voting on course ideas. It is built using Java, the Spark framework, and the Handlebars templating engine. When running locally, use localhost:4567

### [giflib](giflib)
A web app for uploading, favoriting, categorizing, etc gifs. It is built using Java, the Spring framework, and the Thymeleaf templating engine. When running locally, use localhost:8080.

### [hibernate-basics](hibernate-basics)
A few Java files showing how to create a JDBC connection and interact with a database using SQLite. Serves as a primer for what hibernate can offer us.

### [contactmgr-hibernate](contactmgr-hibernate)
A Java application using Hibernate to perform CRUD operations on an H2 database.

### [giflib-hibernate](giflib-hibernate)
An extension of the giflib project that uses Hibernate and a H2 database for data persistence.

What this app does:

- Serves dynamic web content according to URI, including index and detail pages for categories and GIFs
- Includes database connectivity, where GIF data is stored
- Allows a user to perform CRUD (create, read, update, delete) operations on GIF and category data
- Performs server-side form validation for adding/updating GIFs and categories
- Uses a database
- Serves static assets, such as images, fonts, CSS, and JS

What this app does **NOT** do:

- Implement user authentication

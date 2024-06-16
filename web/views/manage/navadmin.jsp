<%-- 
    Document   : navadmin
    Created on : May 26, 2024, 11:55:38 AM
    Author     : bachq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
            />
        <style>
            .nav-item:hover{
                text-decoration: underline;
                /*background-color: orange;*/
            }
        </style>
    </head>
    <body>
        <nav
            id="sidebarMenu"
            class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse"
            >
            <div class="position-sticky pt-3">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">
                            Dashboard
                        </a>
                    </li>
                    
                      <li class="nav-item">
                        <a class="nav-link" href="#">

Admin
                        </a>
                    </li>
                    
                       <li class="nav-item">
                        <a class="nav-link" href="#">
                            Manager 
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">

                            Student
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Lecturers
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">


                            Teacher Assistant
                        </a>
                    </li>
                    
                  


                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Course
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Orders
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">
                            Sale
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="designWebDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Design Web
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="designWebDropdown">
                            <li><a class="dropdown-item" href="#">Edit Header</a></li>
                            <li><a class="dropdown-item" href="#">Edit Banner</a></li>
                            <li><a class="dropdown-item" href="#">Edit Slogan</a></li>
                            <li><a class="dropdown-item" href="#">Edit Footer</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
        ></script>
    </body>
</html>




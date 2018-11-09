<%-- 
    Document   : index
    Created on : 22/09/2018, 05:45:04 PM
    Author     : Jorge Baez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Spring Boot: MVC</title>
    <jsp:include page="layout/head.jsp"/>
</head>
<body>
    <jsp:include page="layout/header.jsp"/>    
    
    <div class="container">
        <div class="row justify-content-center mt-3 pt-2"> <!-- mt-5 pt5 -->
            <div class="col-md-12"> <!-- col-md-7 -->
                
                <%--
                <div>
                    <h1 class="display-4">Crud MVC</h1> <!-- Encabezado Display -->
		</div>                                                                
                --%>
                                               
                <%-- <jsp:include page="layout/jumbotron.jsp"/> --%>
                
                <div class="card border-primary mb-3" style="max-width: 18rem;">
                    <div class="card-header">Header</div>
                    <div class="card-body text-primary">
                        <h5 class="card-title">Primary card title</h5>
                        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                    </div>
                </div>

            </div><!-- .col -->
        </div><!-- .row -->	
    </div><!-- .container -->
    
    <jsp:include page="layout/cdn.jsp"/>
</body>
</html>

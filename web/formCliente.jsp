<%-- 
    Document   : index
    Created on : 22/09/2018, 05:45:04 PM
    Author     : Jorge Baez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>App Angular</title>
    <jsp:include page="layout/head.jsp"/>
</head>
<body>
    <jsp:include page="layout/header.jsp"/>
        
    <div class="container">
        <div class="row justify-content-center mt-3 pt-2"> <!-- mt-5 pt5 -->
            <div class="col-md-12"> <!-- col-md-7 -->

                <div class="card bg-dark text-white">
                    <div class="card-header">Clientes</div>
                    <div class="card-body">                       
                                
                        <c:if test="${not empty flash}">
                        <div>
                            <ul class="alert alert-danger" role="alert">
                                <c:forEach var="flash" items="${flash}">
                                <li class="small pt-0 mt-0">${flash}</li>
                                </c:forEach>
                            </ul>
                        </div>
                        </c:if>
                        
                        <form action="controladorCliente" method="post" id="formulario"> <!-- novalidate -->
                            <div class="row form-group">
                                <label for="nombre" class="col-form-label col-md-2">Nombre:</label> <!-- col-md-4 -->
                                <div class="col-md-5"> <!-- col-md-8 -->
                                    <input type="text" name="nombre" value="${ cliente.getNombre() }" id="nombre" class="form-control"> <!-- required -->
                                </div>		                       
                            </div>
                            <div class="row form-group">
                                <label for="apellido" class="col-form-label col-md-2">Apellido:</label>
                                <div class="col-md-5">
                                    <input type="text" name="apellido" value="${ cliente.getApellido() }" id="apellido" class="form-control"> <!-- required -->
                                </div>		                        
                            </div>
                            <div class="row form-group">
                                <label for="email" class="col-form-label col-md-2">Email:</label>
                                <div class="col-md-5">
                                    <input type="email" name="email" value="${ cliente.getEmail() }" id="email" class="form-control"> <!-- required -->
                                </div>		                        
                            </div>
                            <div class="row form-group">
                                <label for="fecRegistro" class="col-form-label col-md-2">Fec. Registro:</label>
                                <div class="col-md-5">
                                    <input type="date" name="fecRegistro" value="${ cliente.getCreateAt() }" id="fecRegistro" class="form-control"> <!-- required -->
                                </div>		                        
                            </div>                            		                    		                    		                    		                    
                            <h4>
                                <button type="submit" name="action" value="saveCliente" class="btn btn-primary">Crear Cliente</button>
                                <a class="btn btn-primary" href="controladorCliente?action=listarCliente">Atras</a>
                            </h4>

                            <input type="hidden" name="id" value="" id="id" class="form-control">
                        </form>

                    </div>
                </div>

                <jsp:include page="layout/footer.jsp"/>

            </div><!-- .col -->
        </div><!-- .row -->	
    </div><!-- .container -->     
    
    <jsp:include page="layout/cdn.jsp"/>
</body>
</html>

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

                <div class="card border-primary"> <!-- card bg-light -->
                    <div class="card-header">Clientes</div>
                    <div class="card-body text-primary"> <!-- card-body -->
                        
                        <h5 class="card-title">Listado de clientes</h5>
                        <div class="table-responsive">
                            <table id="table_id" class="table table-bordered table-striped"> <!-- <table class="table table-sm table-bordered table-striped table-hover table-responsive"> -->
                                <thead> <!-- class="thead-dark" -->
                                    <tr>
                                        <th>id</th>
                                        <th>nombre</th>
                                        <th>apellido</th>
                                        <th>email</th>
                                        <th>fec. registro</th>                                    
                                        <th>editar</th>	
                                        <th>eliminar</th>
                                        <th>estado</th>
                                    </tr>						
                                </thead>					
                                <tbody>   
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <%--
                                    <c:forEach var="c" items="${ cliente }">
                                    <tr>
                                        <td><a class="btn btn-sm btn-primary" href="javascript:verCliente('${ c.getId() }')">${ c.getId() }</a></td>
                                        <td>${ c.getNombre() }</td>
                                        <td>${ c.getApellido() }</td>
                                        <td>${ c.getEmail() }</td>
                                        <td>${ c.getCreateAt() }</td>
                                        <td><a class="btn btn-sm btn-primary" href="controladorCliente?action=buscarCliente&id=${ c.getId() }">Editar</a></td>
                                        <c:choose>
                                            <c:when test="${ c.getEstado() == 1 }">
                                                <td><a class="btn btn-sm btn-danger" href="javascript:confirm('${ c.getId() }')" onclick="return confirm('Estas seguro que quieres eliminar?');">Desactivar</a></td>
                                            </c:when> 
                                            <c:otherwise>
                                                <td><a class="btn btn-sm btn-danger" href="javascript:confirm('${ c.getId() }')" onclick="return confirm('Estas seguro que quieres eliminar?');">Activar</a></td>
                                            </c:otherwise>                                            
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${ c.getEstado() == 1 }">
                                                <td><h6><span class="badge badge-success">Activado</span></h6></td>
                                            </c:when> 
                                            <c:otherwise>
                                                <td><h6><span class="badge badge-dark">Desactivado</span></h6></td>
                                            </c:otherwise>                                            
                                        </c:choose>
                                    </tr>
                                    </c:forEach>
                                    --%>
                                </tbody>                            
                            </table>
                        </div>
                        <h4>
                            <a class="btn btn-primary mr-2" href="controladorCliente?action=formCliente">Crear Cliente</a>                            
                        </h4>

                    </div>
                </div>
                                
                <!-- Modal -->
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h6 class="modal-title" id="exampleModalLabel">Modal title</h6>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <ul class="list-group">
                                    <li class="list-group-item list-group-item-primary">Nombre: <span id="nombre"></span></li>                                            
                                    <li class="list-group-item">Email: <span id="email"></span></li>
                                    <li class="list-group-item">Fec. Registro: <span id="fecRegistro"></span></li>
                                    <li class="list-group-item">Estado: <span id="estado"></span></li>
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-sm btn-success" data-dismiss="modal">Atras</button>
                            </div>
                        </div>
                    </div>
                </div>

                <jsp:include page="layout/footer.jsp"/>

            </div><!-- .col -->
        </div><!-- .row -->	
    </div><!-- .container -->
    
    <jsp:include page="layout/cdn.jsp"/>
    
    <script src="datatables/datatablesCliente.js"></script>
    
    <script>
        function confirm(id) {
            swal({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                type: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.value) {
                    $(location).attr('href',"controladorCliente?action=cambiarEstadoCliente&id=" + id);
//                    location.href="controladorCliente?action=cambiarEstadoCliente&id=" + id;
                }
            })
        }
        
        function verCliente(id) {            
            $.ajax({
               method: 'get',
               url: 'controladorCliente?action=verCliente&id=' + id,
               dataType: 'json',
               success: function(data) {
                    $('#exampleModalLabel').text('Detalle cliente: ' + data.nombre);
                    $('#nombre').text(data.string);                    
                    $('#email').text(data.email);
                    $('#fecRegistro').text(data.createAt);
                    var estado = ((data.estado) == 1) ? estado = 'Activado' : estado = 'Desactivado';                    
                    $('#estado').text(estado);
                    $('#exampleModal').modal('show');
               }
            });
        }
    </script>
</body>
</html>

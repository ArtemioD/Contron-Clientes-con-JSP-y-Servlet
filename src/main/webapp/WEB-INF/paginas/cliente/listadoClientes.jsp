<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Telefono</th>
                                <th>Email</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos la lista de clientes -->
                            <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${cliente.nombre} ${cliente.apellido}</td>
                                    <td>${cliente.telefono}</td>
                                    <td>${cliente.email}</td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?action=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-warning">
                                            <i class="fas fa-angle-double-right"></i>
                                            Editar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Tarjeta para total clientes -->
            <div class="col-md-3">
                <div class="card text-center text-white mb-3 bg-success">
                    <div class="card-body">
                        <h3>Total Clientes</h3>
                        <h4 class="display-4">
                            <i class="fas fa-users"></i>
                            ${clientesTotal}
                        </h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
                        
<!-- Agregar cliente MODAL -->
<jsp:include page="/WEB-INF/paginas/cliente/agregarCliente.jsp"/>




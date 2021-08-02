<%-- --%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <jsp:include page="/WEB-INF/paginas/comunes/headMeta.jsp"/>
        <title>Nuestros Clientes</title>
    </head>
    <body>
        <!-- Cabecero -->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>
        
        <!-- Botones de navegacion -->
        <jsp:include page="/WEB-INF/paginas/comunes/btnNavegacion.jsp"/>
        
        <!-- Listado de clientes -->
        <jsp:include page="/WEB-INF/paginas/cliente/listadoClientes.jsp"/>

        <!-- Pie de pagina -->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>
        

        <!-- Archivos Bootstrap 4 JS -->
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- Ayuda Bootstrap: https://hackerthemes.com/bootstrap-cheatsheet/ -->
    </body>
</html>

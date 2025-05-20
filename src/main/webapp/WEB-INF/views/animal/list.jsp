<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Animals - Zoo Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="container mt-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2>Animals</h2>
            <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'STAFF'}">
                <a href="${pageContext.request.contextPath}/animal/new" class="btn btn-primary">Add New Animal</a>
            </c:if>
        </div>

        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">
                ${message}
            </div>
        </c:if>

        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Species</th>
                        <th>Habitat</th>
                        <th>Arrival Date</th>
                        <th>Health Status</th>
                        <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'STAFF'}">
                            <th>Actions</th>
                        </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="animal" items="${animals}">
                        <tr>
                            <td>${animal.name}</td>
                            <td>${animal.species}</td>
                            <td>${animal.habitat}</td>
                            <td>${animal.arrivalDate}</td>
                            <td>
                                <span class="badge bg-${animal.healthStatus == 'HEALTHY' ? 'success' : 
                                                      animal.healthStatus == 'OBSERVATION' ? 'warning' : 'danger'}">
                                    ${animal.healthStatus}
                                </span>
                            </td>
                            <c:if test="${sessionScope.user.role == 'ADMIN' || sessionScope.user.role == 'STAFF'}">
                                <td>
                                    <a href="${pageContext.request.contextPath}/animal/edit/${animal.id}" 
                                       class="btn btn-sm btn-primary">Edit</a>
                                    <button onclick="deleteAnimal('${animal.id}')" 
                                            class="btn btn-sm btn-danger">Delete</button>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function deleteAnimal(id) {
            if (confirm('Are you sure you want to delete this animal?')) {
                fetch('${pageContext.request.contextPath}/animal/' + id, {
                    method: 'DELETE'
                }).then(response => {
                    if (response.ok) {
                        window.location.reload();
                    } else {
                        alert('Error deleting animal');
                    }
                });
            }
        }
    </script>
</body>
</html>
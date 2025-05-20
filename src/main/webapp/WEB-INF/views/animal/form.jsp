<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${animal == null ? 'Add New' : 'Edit'} Animal - Zoo Management System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="../common/navbar.jsp" />

    <div class="container mt-4">
        <h2>${animal == null ? 'Add New' : 'Edit'} Animal</h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/animal${animal == null ? '' : '/edit'}" 
              method="POST" class="needs-validation" novalidate>
            <c:if test="${animal != null}">
                <input type="hidden" name="id" value="${animal.id}">
            </c:if>

            <div class="mb-3">
                <label for="name" class="form-label">Name</label>
                <input type="text" class="form-control" id="name" name="name" 
                       value="${animal.name}" required>
                <div class="invalid-feedback">
                    Please provide a name.
                </div>
            </div>

            <div class="mb-3">
                <label for="species" class="form-label">Species</label>
                <input type="text" class="form-control" id="species" name="species" 
                       value="${animal.species}" required>
                <div class="invalid-feedback">
                    Please provide a species.
                </div>
            </div>

            <div class="mb-3">
                <label for="habitat" class="form-label">Habitat</label>
                <input type="text" class="form-control" id="habitat" name="habitat" 
                       value="${animal.habitat}" required>
                <div class="invalid-feedback">
                    Please provide a habitat.
                </div>
            </div>

            <div class="mb-3">
                <label for="arrivalDate" class="form-label">Arrival Date</label>
                <input type="date" class="form-control" id="arrivalDate" name="arrivalDate" 
                       value="${animal.arrivalDate}" required>
                <div class="invalid-feedback">
                    Please provide an arrival date.
                </div>
            </div>

            <div class="mb-3">
                <label for="healthStatus" class="form-label">Health Status</label>
                <select class="form-select" id="healthStatus" name="healthStatus" required>
                    <option value="">Select status...</option>
                    <option value="HEALTHY" ${animal.healthStatus == 'HEALTHY' ? 'selected' : ''}>Healthy</option>
                    <option value="OBSERVATION" ${animal.healthStatus == 'OBSERVATION' ? 'selected' : ''}>Under Observation</option>
                    <option value="HOSPITALIZED" ${animal.healthStatus == 'HOSPITALIZED' ? 'selected' : ''}>Hospitalized</option>
                </select>
                <div class="invalid-feedback">
                    Please select a health status.
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Save</button>
            <a href="${pageContext.request.contextPath}/animal" class="btn btn-secondary">Cancel</a>
        </form>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Form validation
        (function () {
            'use strict'
            var forms = document.querySelectorAll('.needs-validation')
            Array.prototype.slice.call(forms).forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
        })()
    </script>
</body>
</html>
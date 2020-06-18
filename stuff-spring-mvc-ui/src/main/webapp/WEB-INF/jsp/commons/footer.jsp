<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>

<!-- FOOTER | START -->
<footer class="footer bg-dark">

    <!-- COPYRIGHT | START -->
    <div class="text-center text-muted py-3">&copy; <fmt:formatDate value="${now}" pattern="yyyy"/> Copyright:
        <a class="text-muted" href="http://vakar.space"> vakar.space</a>
    </div>
    <!-- COPYRIGHT | END -->

</footer>
<!-- FOOTER | END -->

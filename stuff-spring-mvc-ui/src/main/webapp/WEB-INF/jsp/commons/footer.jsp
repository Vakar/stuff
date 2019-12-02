<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>

<!-- FOOTER START -->
<footer class="footer-copyright bg-light">

    <!-- COPYRIGHT START -->
    <div class="text-center py-3">&copy; <fmt:formatDate value="${now}" pattern="yyyy"/> Copyright:
        <a href="http://vakar.space"> vakar.space</a>
    </div>
    <!-- COPYRIGHT END -->

</footer>
<!-- FOOTER END -->

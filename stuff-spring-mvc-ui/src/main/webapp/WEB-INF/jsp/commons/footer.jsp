<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>

<footer class="footer bg-dark">
    <div class="text-center text-muted">&copy; <fmt:formatDate value="${now}" pattern="yyyy"/> Copyright:
        <a class="text-muted" href="http://vakar.space"> vakar.space</a>
    </div>
</footer>

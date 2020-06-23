<!DOCTYPE html>
<html lang="en">
<head>
    <title>Information Page</title>
    <jsp:include page="/WEB-INF/jsp/commons/style.jsp"/>
</head>
<body>

<jsp:include page="commons/navbar.jsp"/>

<main class="container mb-3 mt-3">
    <section about="http exception details">
        <div class="d-flex p-2">
            <div class="jumbotron">
                <h3>${title}</h3>
                <p>${message}</p>
            </div>
        </div>
    </section>
</main>

<jsp:include page="commons/footer.jsp"/>
<jsp:include page="commons/js.jsp"/>

</body>
</html>

<html>

welcome to lzpt-web-admin     呵呵 ${admin.id!}

<br>你好  ${SPRING_SECURITY_CONTEXT.authentication.principal.name!} ${SPRING_SECURITY_CONTEXT.authentication.principal.id!}
<br> <a href="${request.contextPath}/logout">logout</a>
</html>
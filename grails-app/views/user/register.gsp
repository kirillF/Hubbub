
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register new user</title>
    <meta name="layout" content="main" />
</head>

<body>
    <h1>Register new user</h1>

    <g:hasErrors>
        <div class="errors">
            <g:renderErrors bean="${user}" as="list" />
        </div>
    </g:hasErrors>

    <g:form action="register">
        <dl>
            <dt>User Id</dt>
            <dd><g:textField name="userId" value="${user?.userId}"/></dd>
            <dt>Password</dt>
            <dd><g:textField name="password" value="${user?.password}" /></dd>
            <dt>Full Name</dt>
            <dd><g:textField name="fullName" value="${user?.profile?.fullName}"/></dd>
            <dt>Bio</dt>
            <dd><g:textArea name="bio" value="${user?.profile?.bio}" /></dd>
            <dt>Email</dt>
            <dd>
                <g:textField name="email" value="${user?.profile?.email}"/>
                <g:hasErrors bean="${user}" field="profile.email">
                    <p style="color: red;"><g:message error="${it}"/></p>
                    </g:hasErrors>
                </g:hasErrors>
            </dd>

            <g:submitButton name="register" value="Register" />
        </dl>
    </g:form>
</body>
</html>
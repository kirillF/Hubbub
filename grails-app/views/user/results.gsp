<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search results</title>
    <meta name="layout" content="main" />
</head>

<body>
    <h1>Results</h1>
    <p>Searched ${com.grails.User.count()} records
        for items matched <em>${term}</em>.
        Found <strong>${users.size()}</strong> hits.
    </p>
    <ul>
        <g:each var="user" in="${users}">
            <li>${user.userId}</li>
        </g:each>
    </ul>

    <g:link action="search">Search again</g:link>
</body>
</html>
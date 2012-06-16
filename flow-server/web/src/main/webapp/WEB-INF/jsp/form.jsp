<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"
%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"
%>

<h1>Hello form</h1>

<form:form modelAttribute="uploadItem" method="post" enctype="multipart/form-data">
    <fieldset>
        <p style="color:red">${required}</p>
        <legend>Upload Fields</legend>
        <p>
            <form:label for="latitude" path="latitude">Latitude:</form:label><br/>
            <form:input path="latitude" type="text" size="50" />
        </p>
        <p>
            <form:label for="longitude" path="longitude">Longitude:</form:label><br/>
            <form:input path="longitude" type="text" size="50" />
        </p>
        <p>
            <form:label for="file" path="file">File</form:label><br/>
            <form:input path="file" type="file" size="50" />
        </p>
        <p><input type="submit" value="Upload" /></p>
    </fieldset>
</form:form>


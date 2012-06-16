<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Flow Index</title>
</head>
<body>

    <h1>You just entered the marvelous world of 'Flow' ... </h1>

    <p>My IP on campus party: 201.166.131.163 (${server})</p>
    <ul>
        <li>Apache: <a href="${config.staticRoot}" target="_blank">${config.staticRoot}</a></li>
        <li>Apache tomcat: <a href="${config.root}">${config.root}</a></li>
    </ul>

    <p>Services:</p>
    <p style="font-style:italic;">
        By specifying an extra parameter 'radius' on the list services you can extend the search area for pictures.
        The parameter is interpreted as 'meters', the default is 100m. &radius=1000
    </p>
    <ul>
        <li>Upload image: <a href="/flow/rest/upload/form.html" target="_blank">/flow/rest/upload/form.html</a></li>
        <li>List by gps location
            <ul>
                <li>HTML: <a href="/flow/rest/list/gps/40/-50.html" target="_blank">/flow/rest/list/gps/{latitude}/{longitude}.html</a></li>
                <li>Json: <a href="/flow/rest/list/gps/40/-50.html?out=json" target="_blank">/flow/rest/list/gps/{latitude}/{longitude}.html?out=json</a></li>
            </ul>
        </li>
        <li>List by phone
            <ul>
                <li>HTML: <a href="/flow/rest/list/phone/19175550001.html" target="_blank">/flow/rest/list/phone/{phone}.html</a></li>
                <li>Json: <a href="/flow/rest/list/phone/19175550001.html?out=json" target="_blank">/flow/rest/list/phone/{phone}.html?out=json</a></li>
            </ul>
        </li>
        <li>Test phone numbers:
            <ul>
                <li>19175550000</li>
                <li>19175550001</li>
                <li>19175550002</li>
                <li>19175550003</li>
                <li>19175550004</li>
                <li>19175550005</li>
                <li>19175550006</li>
                <li>19175550007</li>
                <li>19175550008</li>
                <li>19175550009</li>
                <li>19175550010</li>
                <li>19175550011</li>
                <li>19175550012</li>
                <li>19175550012</li>
                <li>19175550014</li>
                <li>19175550015</li>
                <li>19175550016</li>
            </ul>
        </li>

    </ul>

</body>
</html>



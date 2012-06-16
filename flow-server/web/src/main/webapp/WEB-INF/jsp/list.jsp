<%@ page contentType="text/html;charset=UTF-8" language="java" session="false"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"
%><%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"
%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="cache-control" content="no-cache"/>
    <meta http-equiv="pragma" content="no-cache"/>
    <meta http-equiv="expires" content="-1"/>
    <title>Flow</title>
    <style type="text/css">
        body{margin:0;padding:10px 5px 10px 5px;}
        ul{margin:0;padding:0;list-style-type:none;}
        li{float:left;padding:1px;border:1px #fff solid;margin:2px;}
        li a{text-decoration:none;margin:0;padding:0;display:block;}
        li a img{text-decoration:none;border:0;}
        .c1{border-color:#CF8A15;}
        .c2{border-color:#00A6E4;}
        .c3{border-color:#79820D;}
        .c4{border-color:#780001;}
        /* CF8A15, 00A6E4, 79820D, 780001 */
    </style>
</head>
<body><!-- latitude: ${lat}, longitude: ${lon} -->
    <c:if test="${fn:length(images)>0}">
    <ul>
        <c:forEach var="i" items="${images}" varStatus="status">
            <li class="c${(status.index%4)+1}"><a href="/flow/rest/list/photo/${i.id}.html"><img src="${config.staticRoot}${i.thumbPath}" alt="Loading" width="90px" /></a></li>
        </c:forEach>
    </ul>
    </c:if>

    <c:if test="${fn:length(images)==0}">
        <p>No flow available for: <br /> -latitude=${lat}<br /> -longitude=${lon}</p>
    </c:if>

</body>
</html>


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
        img {padding:1px;border:1px #fff solid;margin:2px;float:left;}
        .c1{border-color:#CF8A15;}
        .c2{border-color:#00A6E4;}
        .c3{border-color:#79820D;}
        .c4{border-color:#780001;}
        a {padding: 10px; margin: 2px;background:#eee;border:1px #ccc solid;float:left;}
        /* CF8A15, 00A6E4, 79820D, 780001 */
    </style>
</head>
<body>

    <img src="${config.staticRoot}${image.midPath}" alt="Loading..." class="c${(image.id%4)+1}" />
    <a href="javascript:history.go(-1);">Back</a>

</body>
</html>


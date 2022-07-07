<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>웹페이지 제목</title>
</head>
<body>
    <div style="...">
        <h4><%=CmmUtil.nvl(rDTO.getTitle())%></h4>
        <hr>
        <div>
            <p>
                <%=CmmUtil.nvl(rDTO.getContents())%>
            </p>
        </div>
        <hr>
        <div style="...">
            <button onclick="location.href='getNoticeList'">뒤로</button>
        </div>
    </div>
</body>
</html>
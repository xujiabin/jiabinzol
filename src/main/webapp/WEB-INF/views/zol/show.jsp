<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>显示</title>
</head>
<body>
<div style="text-align:center;">
	<h2>${time } 建议更新时间</h2>
	
	<table style="margin-left:390px;">
		<c:forEach var="o" items="${result }" varStatus="status">
		
					<c:if test="${status.count % 2 !=0 }">
						<tr>
					</c:if>
						<td style="width:160px;"><c:out value="${o.key }"></c:out>点   <c:out value="${o.value }"></c:out></td>
					<c:if test="${status.count % 2 ==0 }">
						</tr>
					</c:if>
		</c:forEach>
	
	</table>
</div>

</body>
</html>
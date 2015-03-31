<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设置</title>
<script src="${ctx }/static/js/jquery-1.9.1.min.js" type=text/javascript language="javascript" ></script>
<script src="${ctx }/static/js/mobileinfo.js" type=text/javascript language="javascript" ></script>
</head>
<body>
<h4>添加手机型号，会根据您选择的手机型号，获取利益最大化的刷新时间<font color="red">(注：不要添加店里没有的，会影响计算时间)</font></h4>
	<table>
		<c:forEach items="${list }" var="s" varStatus="in">
				<c:if test="${in.count % 2 !=0 }">
					<tr>
				</c:if>
					<td><input type="checkbox" name="mobile"  value="${s.id }"/> ${s.pname }</td>
				<c:if test="${in.count % 2 ==0 }">
					</tr>
				</c:if>
			
		</c:forEach>
			<tr><td colspan="2"></td></tr>
			<tr><td colspan="2" style="text-align:center;"><input style="height:35px;" type="button"  id="sub"  value="确定我的选择"/></td></tr>
	</table>
</body>
</html>
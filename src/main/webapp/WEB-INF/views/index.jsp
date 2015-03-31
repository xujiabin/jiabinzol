<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>佑名管理系统</title>
</head>
<frameset rows="65,*" cols="*" frameborder="no" border="0" noresize="false" resizable="no" framespacing="0" name="MM">
	<frame src="${ctx }/header" name="topFrame" noresize="noresize" id="topFrame" title="topFrame" scrolling="no">
	<frameset cols="180,*" framespacing="0" border="0" noresize="false" resizable="no" bordercolor="#0066cc">
		<frame src="${ctx }/left" name="leftFrame" bordercolor="#00ffcc" id="leftFrame" title="leftFrame" scrolling="auto">
		<frame src="${ctx }/index" name="content" id="content" title="content">
	</frameset>
</frameset>
</html>
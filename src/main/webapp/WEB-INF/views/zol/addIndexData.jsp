<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>抓取首页数据</title>
</head>
<body>
<form id="form" action="/zol/mobile/getIndexData" method="post">
				url:<input name="url" /><br/>
				<input type="submit" value="确定"/>
			</form>
</body>
</html>
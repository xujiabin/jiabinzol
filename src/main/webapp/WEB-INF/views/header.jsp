<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="static/css/index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/static/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
function changePassword() {
	window.parent.content.location = "/cmgr/frame/blank";
}
function logout() {
	if(window.confirm("确定退出?")) {
				window.parent.location = "/zol/user/logout";
	}
}
</script>
</head>
<body style="min-width:1003px">
	<table id="_TableHeader" width="100%" border="0" cellpadding="0" cellspacing="0" class="bluebg" style="background:#3388bb url(/cmgr/images/vistaBlue.jpg) repeat-x left top;">
		<tbody><tr>
			<td height="70" valign="bottom">
			<table height="70" border="0" cellpadding="0" cellspacing="0" style="position:relative;">
				<tbody><tr>
					<td style="padding-left:15px"><img src="${ctx }/static/img/logo.png"></td>
				</tr>
			</tbody></table>
			</td>
			<td valign="bottom">
			<div style="text-align:right; margin:0 20px 15px 0;">当前用户：<b>${u.username }</b>
			&nbsp;[&nbsp;<a href="javascript:void(0);" onclick="logout();">退出登录</a> | <a href="${ctx }/user/toChangePass" target="content">修改密码</a> ]</div>
			</td>
		</tr>
	</tbody></table>

</body></html>
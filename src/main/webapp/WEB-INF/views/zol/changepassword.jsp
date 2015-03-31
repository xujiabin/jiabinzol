<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<style type="text/css">
        table
        {
            border-collapse: collapse;
            border: none;
        }
		tr{
			padding-bottom:20px;
		}
        td
        {
			padding-bottom:10px;
			padding-top:10px;
        }
    </style>
<script src="${ctx }/static/js/jquery-1.9.1.min.js" type=text/javascript language="javascript" ></script>
<script type=text/javascript language="javascript" >
$(function(){
	$("#sub").click(function(){
		var old = $("#old").val();
		var newp = $("#new").val();
		var surep = $("#sure").val();
		if(newp != surep){
			alert("新密码输入两次不一致!");
			return;
		}
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{"newpassword":newp,"oldpassword":old},
			url:"/zol/user/changePass",
			success:function(data){
				if(data == '0'){
					alert("修改成功！");
					window.parent.location = "/zol/user/logout";
				}else if(data == '-1'){
					alert("原来密码验证失败！");
				}else{
					alert("修改失败！");
				}
			}
		});
	});
});
</script>
</head>
<body>
<div style="text-align:center;">
	<table>
		<tr><td>原密码：</td><td><input type="password" id="old" /></td></tr>
		<tr><td>新密码：</td><td><input type="password"  id="new" /></td></tr>
		<tr><td>新密码确认：</td><td><input type="password"  id="sure" /></td></tr>
		<tr><td colspan="2"><input id="sub" type="button"  value="确定提交" /></td></tr>
	</table>
	
</div>

</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增手机型号</title>
<script src="${ctx }/static/js/jquery-1.9.1.min.js" type=text/javascript language="javascript" ></script>
<script type=text/javascript language="javascript" >
$(function(){
	$("#add").click(function(){
		var url = $("#url").val();
		if(!url){
			alert("请如图所示，添加连接地址!");
			return;
		}
		$.ajax({
			type:"POST",
			dataType:"text",
			data:{"url":url},
			url:"/zol/mobile/addUrl",
			success:function(data){
				if(data == '1'){
					alert("添加成功！");
					$("#url").val("");
				}else{
					alert("添加失败，请重试！若失败，请联系客服！");
				}
			}
		});
	});
});
</script>
</head>
<body>
<div style="text-align:center;">
	<h2>增加没有的手机型号，如图所示：</h2>
	<div>
		连接地址：<input id="url"/>&nbsp;&nbsp;&nbsp;
		<input style="height:30px;" type="button" id="add"  value="确定添加" /><br /><br />
		<img src="${ctx }/static/img/zolcopy.png" alt="添加标注的连接地址" />
	</div>
	
</div>

</body>
</html>
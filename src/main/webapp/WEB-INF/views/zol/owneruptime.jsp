<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设置刷新时间</title>
<script src="${ctx }/static/js/jquery-1.9.1.min.js" type=text/javascript language="javascript" ></script>
<script src="${ctx }/static/js/json.js" type=text/javascript language="javascript" ></script>
<script type=text/javascript language="javascript" >
$(function(){
	$("#sure").click(function(){
		var json = new Json();
		$(".ut").each(function(){
			json.put($(this).attr("op"),$(this).val());
		});
		$("#jsontime").val(json.toJson());
		
		if(!$("#uname").val()){
			alert("用户名不能为空！");
			return;
		}
		if(!$("#url").val()){
			alert("经销商登录地址不能为空！");
			return;
		}
		if(!$("#upass").val()){
			alert("密码不能为空！");
			return;
		}
		
		$("#form").submit();
	});
	
	
	$("#weituo").click(function(){
		if(!$("#uname").val()){
			alert("下面中关村用户名不能为空！");
			return;
		}
		
		if(!$("#upass").val()){
			alert("下面中关村密码不能为空！");
			return;
		}
		if(!$("#url").val()){
			alert("经销商登录地址不能为空！");
			return;
		}
		
		$("#wname").val($("#uname").val());
		$("#wpass").val($("#upass").val());
		$("#wurl").val($("#url").val());
		
		$("#weituoform").submit();
	});
	
	
	$(".edit").click(function(){
		var vl = $(this).val();
		var html = "";
		var selected = "<option value=''><option>";
		for(var i=0;i<=55;i++){
			selected = "";
			if(i == vl){
				selected = "selected=selected";
			}
			html += "<option "+selected+" value="+i+">"+checknum(i)+"</option>";
		}
		$(this).html(html);
	});
	
	
	
});

function checknum(num){
	return num <10?"0"+num:num;
}
</script>
</head>
<body>
<div style="text-align:center;">
	<h2>设置刷新时间</h2>
	<c:choose>
		<c:when test="${ not empty ow}">
			<form id="delform" action="/zol/mobile/removeUpTime" method="post">
				<input style="height:30px;" type="submit"  value="删除我的刷新时间" />
			</form>
		
			<br /><br /><hr />
			<form id="form" action="/zol/mobile/saveOwnerUpTime" method="post">
				<input name="jsontime" type="hidden" id="jsontime" />
				<input name="uptimeid" type="hidden" value="${ow.id }" />
				<table style="margin-left:30%;">
					 <tr><td>09点：</td><td><select op="jiu" class="ut edit" ><option value=${jsontime.jiu }>${jsontime.jiu }</option></select></td><td>10点：</td><td><select op="shi" class="ut edit" ><option value=${jsontime.shi }>${jsontime.shi }</option></select></td><td>11点：</td><td><select op="shiyi" class="ut edit" ><option value=${jsontime.shiyi }>${jsontime.shiyi }</option></select></td><td>12点：</td><td><select op="shier" class="ut edit" ><option value=${jsontime.shier }>${jsontime.shier }</option></select></td></tr>
					 <tr><td>13点：</td><td><select op="shisan" class="ut edit" ><option value=${jsontime.shisan }>${jsontime.shisan }</option></select></td><td>14点：</td><td><select op="shisi" class="ut edit" ><option value=${jsontime.shisi }>${jsontime.shisi }</option></select></td><td>15点：</td><td><select op="shiwu" class="ut edit" ><option value=${jsontime.shiwu }>${jsontime.shiwu }</option></select></td><td>16点：</td><td><select op="shiliu" class="ut edit" ><option value=${jsontime.shiliu }>${jsontime.shiliu }</option></select></td></tr>
					 <tr><td>17点：</td><td><select op="shiqi" class="ut edit" ><option value=${jsontime.shiqi }>${jsontime.shiqi }</option></select></td><td>18点：</td><td><select op="shiba" class="ut edit" ><option value=${jsontime.shiba }>${jsontime.shiba }</option></select></td><td>19点：</td><td><select op="shijiu" class="ut edit"  ><option value=${jsontime.shijiu }>${jsontime.shijiu }</option></select></td><td>20点：</td><td><select op="ershi" class="ut edit" ><option value=${jsontime.ershi }>${jsontime.ershi }</option></select></td></tr>
					 <tr><td>21点：</td><td><select op="ershiyi" class="ut edit" ><option value=${jsontime.ershiyi }>${jsontime.ershiyi }</option></select></td><td>22点：</td><td><select op="ershier" class="ut edit" ><option value=${jsontime.ershier }>${jsontime.ershier }</option></select></td></tr>
					<tr><td colspan="8"><hr /></td></tr>
					 <tr><td colspan="8">下面填写您中关村后台系统的用户名和密码：</td></tr>
					 <tr><td colspan="2">经销商登录地址：</td><td colspan="6"><input  style="width:350px;" id="url" name="url"  value="${ow.url }"/></td></tr>
					 <tr><td colspan="2">用户名：</td><td colspan="2"><input  style="width:120px;" id="uname" name="uname"  value="${ow.uname }"/></td><td colspan="2">密码：</td><td colspan="2"><input  style="width:120px;" id="upass" name="upass"  value="${ow.upass }"/></td></tr>
					 <tr><td colspan="8"><hr /></td></tr>
					 <tr><td colspan="8"><input style="height:30px;" type="button" id="sure" value="确定选择"/></td></tr>
				</table>
			</form>
		</c:when>
		<c:otherwise>
			<form id="weituoform" action="/zol/mobile/authorizeSystemRefertime"  method="post">
				<input type="hidden" name="uname" id="wname" />
				<input type="hidden" name="upass" id="wpass" />
				<input type="hidden" name="url" id="wurl" />
 				<input style="height:30px;" id="weituo" type="button"  value="委托系统刷新时间" />
			</form>
		
			<br /><br /><hr />
			<form id="form" action="/zol/mobile/saveOwnerUpTime" method="post">
				<input name="jsontime" type="hidden" id="jsontime" />
				<table style="margin-left:30%;">
					 <tr><td colspan="8">下面填写您中关村后台系统的用户名和密码：</td></tr>
					 <tr><td colspan="2">经销商登录地址：</td><td colspan="6"><input  style="width:350px;" id="url" name="url"  /><br/></td></tr>
					  <tr><td colspan="8" style="color:red;"> 如：我是北京经销商，地址则是：http://dealer.zol.com.cn/beijing/</td></tr>
					  <tr><td colspan="8"><hr /></td></tr>
					 <tr><td colspan="2">用户名：</td><td colspan="2"><input style="width:120px;" id="uname" name="uname" /></td><td colspan="2">密码：</td><td colspan="2"><input  style="width:120px;" id="upass" name="upass" /></td></tr>
					 <tr><td colspan="8"><hr /></td></tr>
					 <tr><td>09点：</td><td><select op="jiu" class="ut" >${html }</select></td><td>10点：</td><td><select op="shi" class="ut" >${html }</select></td><td>11点：</td><td><select op="shiyi" class="ut" >${html }</select></td><td>12点：</td><td><select op="shier" class="ut" >${html }</select></td></tr>
					 <tr><td>13点：</td><td><select op="shisan" class="ut" >${html }</select></td><td>14点：</td><td><select op="shisi" class="ut" >${html }</select></td><td>15点：</td><td><select op="shiwu" class="ut" >${html }</select></td><td>16点：</td><td><select op="shiliu" class="ut" >${html }</select></td></tr>
					 <tr><td>17点：</td><td><select op="shiqi" class="ut" >${html }</select></td><td>18点：</td><td><select op="shiba" class="ut" >${html }</select></td><td>19点：</td><td><select op="shijiu" class="ut" >${html }</select></td><td>20点：</td><td><select op="ershi" class="ut" >${html }</select></td></tr>
					 <tr><td>21点：</td><td><select op="ershiyi" class="ut" >${html }</select></td><td>22点：</td><td><select op="ershier" class="ut" >${html }</select></td></tr>
					 <tr><td colspan="8"><hr /></td></tr>
					 <tr><td colspan="8"><input style="height:30px;" type="button" id="sure" value="确定自定义选择"/></td></tr>
				</table>
			</form>
		</c:otherwise>
	</c:choose>
	
</div>

</body>
</html>
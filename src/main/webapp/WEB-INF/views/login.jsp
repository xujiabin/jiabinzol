<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>

<html style="overflow-x:hidden;overflow-y:auto;" class="">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
		
<meta http-equiv="Cache-Control" content="no-store"><meta http-equiv="Pragma" content="no-cache"><meta http-equiv="Expires" content="0">
<meta name="author" content="http://thinkgem.iteye.com"><meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10">
<script src="${ctx }/static/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$(".close").click(function (){
		$("#messageBox").hide();
	});
	if('${msg}'!=''){
		$("#messageBox").show();
	}
});
</script>
<link href="${ctx }/static/css/bootstrap.min.css" type="text/css" rel="stylesheet">
	
	<meta name="decorator" content="default">
    <link rel="stylesheet" href="${ctx }/static/css/typica-login.css">
	<style type="text/css">
		.control-group{border-bottom:0px;}
	</style>

	<style type="text/css">.fancybox-margin{margin-right:0px;}</style></head>
	<body>
		
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
         
        </div>
      </div>
    </div>

    <div class="container">
		<!--[if lte IE 6]><br/><div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a><h4>温馨提示：</h4><p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的 Chrome、Firefox、Safari 等。</p></div><![endif]-->
		
		<div id="messageBox" class="alert alert-error hide"><button data-dismiss="alert" class="close">×</button>
			<label id="loginError" class="error" style="text-align:center;">${msg }</label>
		</div>
        <div id="login-wraper">
            <form id="loginForm" class="form login-form" action="${ctx }/user/login" method="post" novalidate="novalidate">
                <legend><span style="color:#08c;">系统登陆</span></legend>
                <div class="body">
					<div class="control-group">
						<div class="controls">
							<input type="text" id="username" name="username" class="required" value="" placeholder="登录名">
						</div>
					</div>
					
					<div class="control-group">
						<div class="controls">
							<input type="password" id="password" name="password" class="required" placeholder="密码">
						</div>
					</div>
					
                </div>
                <div class="footer">
                    
                    <input class="btn btn-primary" type="submit" value="登 录">
                </div>
				
            </form>
        </div>
    </div>
    <footer class="white navbar-fixed-bottom">
		
    </footer>
  
	

<div class="backstretch" style="left: 0px; top: 0px; overflow: hidden; margin: 0px; padding: 0px; height: 620px; width: 1366px; z-index: -999999; position: fixed;">
<img src="${ctx }/static/img/bg2.jpg" class="deleteable" style="position: absolute; margin: 0px; padding: 0px; border: none; width: 1366px; height: 853.75px; max-width: none; z-index: -999999; left: 0px; top: -116.875px;">
<img src="${ctx }/static/img/bg3.jpg" style="position: absolute; margin: 0px; padding: 0px; border: none; width: 1366px; height: 853.75px; max-width: none; z-index: -999999; left: 0px; top: -116.875px; opacity: 0.14258053772694;">
</div>
</body>
</html>
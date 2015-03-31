<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/taglib.jsp"%>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

<style type="text/css">
a { color: #08d; text-decoration: none; border: 0; background-color: transparent; }
a:hover { color: #f80; text-decoration: underline; }
a:active,
a:focus { color: #f60; text-decoration: none; }
a.selected { background: #2266BB; color: #CCFFFF; text-decoration: none; }

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
            border-bottom: solid #C1C1C1 1px;
			padding-bottom:10px;
			padding-top:10px;
        }
    </style>
</head>

	<body style="border-right: 1px solid #B1B1AD">
			 <table>
				 <tr>
					<td>
						<a href="${ctx }/index" target="content" >定制手机型号</a>
					</td>
					
				</tr>
				<tr>
					<td>
						<a href="${ctx }/mobile/showtime" target="content">推荐刷新时间</a>
					</td>
					
				</tr>
				<tr>
					
					<td>
						<a href="${ctx }/mobile/toUptime" target="content">定义刷新时间</a>
					</td>
					
				</tr>
				<tr>
					
					<td>
						<a href="${ctx }/mobile/toAddUrl" target="content">录入手机型号</a>
					</td>
					
				</tr>
			 </table>
	</body>

</html>
$(function(){
	$("#sub").click(function(){
		var ids = new Array();
		$("input[type='checkbox'][name='mobile']:checked").each(function(){
			ids.push($(this).val());
		});
		if(ids){
				$.ajax({
					type : "post",
					dataType : "text",
					data : "ids="+ids.toString(),
					url : "/zol/mobile/saveOrderUrl",
					success : function(data){
						if(data == '1'){
							alert("添加成功，正在计算刷新时间，因时间较长请稍等，");
							window.location.href = "/zol/mobile/showtime";
						}else{
							alert("添加失败");
						}
					}
				});
		}

		
	});
	
	//加载自己定义的
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/zol/user/ownerOrder",
		success : function(data){
			if(data){
				for(var i=0;i<data.length;i++){
					var json = data[i];
					$("input[type='checkbox'][value='"+json.urlid+"']").attr('checked','checked');
				}
			}
		}
	});
	
});
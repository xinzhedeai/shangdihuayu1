$(function(){
	$("#ok").click(function(){// 首页
		loginFun();
	})
	$("#password").keypress(function(e){ //回车键登录
		if(e.which == 13){ 
			loginFun();
		} 
	});
});
function loginFun() {
	var formvalue = $('.loginForm').serialize();
	$.post('userAction/loginSubmit.action', formvalue, function(result) {
		if (result.success) {
			location.href = 'html/home.html';
		} else {
			alert(result.msg);
		}
	}, "JSON");
}

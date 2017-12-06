/**
 * 
 */
$(function(){
	$("#reg").click(function(){// 首页
		window.location.href="html/register.html";
	});
	$("#ok").click(function(){// 首页
		loginFun();
	})
	$("#password").keypress(function(e){ //回车键登录
		if(e.which==13){ 
			loginFun();
		} 
	});
	$("#weibo").click(function(){
		alert("点击事件触发");
		WB2.login(function(){
			console.info("wb2LOGIN");
			WB2.anyWhere(function(W){
				console.info("wb2anywhere");
			  W.parseCMD('/account/get_uid.json', function(oResult, bStatus){
				  W.parseCMD('/users/show.json', function(oResult, bStatus){
					  var o = oResult;
					  var UID = o.id,
		         		nickName = o.name,
		         		sex = o.gender,
		            	clientId = "WEB",
		        		clientSecret = "52FA629647E2D2B2003A65F300B700E0",
		        		landType = "SINA";
			    		if(sex == "m"){
			    			sex = "1";
			    		}else{
			    			sex = "0";
			    		}
			    		if(o.error_code){
			    			var txt=  "第三方权限获取失败！错误码：" + o.error_code;
							window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
			    		}else{
			    			alert(sex);
			    		//	thirdLogin(UID, clientId, clientSecret, sex, nickName, landType);
			    		}
				  },{
					  uid : oResult.uid 
				  },
				  {
					  method : 'get'
		          });
			  },{ },
			  {
				  method : 'get'
	          });
			 });
		});
	});
})
	function loginFun() {
		var form = $('form');//选中的tab里面的form
		var formvalue = $('.loginForm').serialize();
		$.post('userAction/loginSubmit.action', form.serialize(), function(result) {
			if (result.success) {
				var loginUsId = result.result.userid;
				setCookie('loginUsId',loginUsId);
				console.info(loginUsId);
				//location.href = 'html/index.html?userId=';
				location.href = 'html/index.html';
			} else {
				alert(result.msg);
			}
		}, "JSON");
	}
	function thirdLogin(UID, clientId, clientSecret, sex, nickName, landType) {
//		var form = $('form');//选中的tab里面的form
//		var formvalue = $('.loginForm').serialize();
		$.post('userAction/thirdLogin.action',$.param({'usId':UID,'area':area}), function(result) {
			if (result.success) {
				var loginUsId = result.result.userid;
				setCookie('loginUsId',loginUsId);
				console.info(loginUsId);
				//location.href = 'html/index.html?userId=';
				location.href = 'html/index.html';
			} else {
				alert(result.msg);
			}
		}, "JSON");
	}

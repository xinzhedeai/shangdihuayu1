/**
 * @file 公用方法js文件
 * @author zhaojunbao
 * @date: 2017年12月6日13:45:14
 * @version: V1.0
 */
'use strict';

/**
 * 常用提示信息集中配置
 */
var REMIND_MSG = {
	'NO_DATA' : '没有相关数据'	
};

var userInfo = getCookie('userInfo');
if (!userInfo) {
	window.parent.location.href = '/admin/login.html';
}else{
	userInfo = $.parseJSON(userInfo);
}

/*$.ajaxPrefilter( function( options, originalOptions, jqXHR ) { 
	var accessToken = getCookie('access-token'), lang = getCookie('userLanguage');
	if (accessToken != null) jqXHR.setRequestHeader("access-token", accessToken);
	if (lang != null) jqXHR.setRequestHeader("Accept-Language", lang);
 });*/
var util = util || {};
util.ajaxPostCus = function(url, reqParams, callback) {
	$.ajax({
//        async: _async,
        url: contextPath + url,
        timeout:600000,//超时60秒
        data: reqParams, //{ "Alias": $("#PmuAlias").val(), "Name": $("#PmuName").val() },
        dataType: 'json',
        //ifModified:true, 
        type: "POST",
        cache: false,
        complete:function(res){
           // $load.removeClass('progressBar').addClass('progressBarNone');
           // $(".deviceback_opacity").hide();
        },
        dataFilter: function (data) {
        	var resObj = $.parseJSON(data);
        	if (resObj.errCd === -600) {
        		delCookie('access-token');
        		location.href = contextPath + '/error/time-out.html';//登录超时跳转页面
		    }else{
		    	return data;
		    }
        },
        success: callback,
        error: function (XMLHttpRequest, textStatus, errorThrown){
        	layer.alert(textStatus);
        }
    });
}
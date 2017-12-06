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
	location.href = '/admin/login.html';
}else{
	userInfo = $.parseJSON(userInfo);
}
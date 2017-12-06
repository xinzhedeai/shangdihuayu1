/**
 * @file user
 * @author zhaojunbao
 * @date: 2017年12月6日13:45:14
 * @version: V1.0
 */
'use strict';

window.$user = window.$user || {};
$(function() {
	var admin_userId = getCookie('admin_userId');
	if (!admin_userId) {
		location.href = '/admin/login.html';
	}
	var userColumns = [{
		field : 'ck',
		checkbox : true
	}, {
		field : 'user_id',
		title : '用户名',
		width : 200,
		sortable : true,
	
	}, {
		field : 'gender',
		title : '性别',
		width : 100,
		sortable : true,
		formatter : function(value, row, index) {  
			if (value == '0') {
				return	'男';
			} else {
				return	'女';
			}
		}
	},  {
		field : 'user_name',
		title : '用户名',
		width : 100,
		sortable : true,
	},  {
		field : 'tel',
		title : '状态',
		width : 100,
		sortable : true,
    },  {
		field : 'wechat',
		title : '微信号',
		width : 100,
		sortable : true,
	} ,  {
		field : 'qq',
		title : 'QQ号',
		width : 100,
		sortable : true,
	}, {
		field : 'state',
		title : '状态',
		width : 100,
		sortable : true,
	},  {
		field : 'last_login_time',
		title : '最后登录时间',
		width : 100,
		sortable : true,
	}],
	userOptions = {
		url : contextPath + '/UserAction/getAllUser.action',
		columns: [userColumns],
		pagination : true,
		fitColumns : false,
		rownumbers : true,
		checkOnSelect:false,
//		width : 1307,
//		sortOrder : 'desc',
		idField : 'user_id',
		pageSize : 10,
//		frozenColumns : [ ],
		loadFilter : function(data) {
			if (data != null) {
				if(data.result.rows != null){
					return data.result;
				}else{
					var txt=  "没有相关数据！";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
					$('#az-user-tableList').datagrid('loadData', { total: 0, rows: [] });
				}
			} else {
				window.location.href='/admin/login.html';
			}
		}
	};
	$('#az-user-tableList').datagrid(userOptions);
	
	$("#user-searchReset-btn").click(function() {
		$('#az-user-tableList').datagrid('load',{});
		$('#az-user-sNickName').combobox('clear');
		$('#az-user-sLandId').combobox('clear');
		$('#z-user-searchForm select').val('');
	});
		
	
	$('#az-user-sLandId').combobox({    
	    url :'/mUserAction/getUsIds.action',    
	    valueField :'US_ID', 
	    textField : 'US_ID', 
	    width : 400,
	    height : 35,
	    loadFilter : function(data) {
			if (data != null) {
				return data.result.rows;
			} else {
				window.location.href='/admin/login.html';
			}
		}
	});  
	$('#az-user-sNickName').combobox({    
	    url : '/mUserAction/getAllUsersCom.action',    
	    valueField :'US_NAME',    
	    textField : 'US_NAME',
	    width : 400,
	    height : 35,
	    loadFilter : function(data) {
			if (data != null) {
				return data.result.rows;
			} else {
				window.location.href='/admin/login.html';
			}
		},
	}); 
});

function reloadClear(){
	$('#az-user-tableList').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections').datagrid("clearChecked");
	//$('#az-user-tableList').datagrid("reload",{ });
	$('#az-user-tableList').datagrid('load', $.serializeObject($('#z-user-searchForm')));
}

$user = {
		userDetail : function(usId) {// 用户信息详情
		$.post('/mUserAction/userDetail.action', $.param({'usId':usId}), function(res) {
			if (res.success) {
				var result = res.result;
				if (result) {
					//用户详情模态框
					$("#UserdetailModal").modal();
					$("#az-user-mDetail td:even").css("width",90);
					$("#az-user-mDetail td:odd").css("width",370);
				}else{
					var txt = "未查到查询用户详情！";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
				}
			}
			
		}, "JSON");
	},
		deleteUser : function(userIdList) {
			$.post('/mUserAction/deleteUser.action', $.param({'userIdList':userIdList}), function(result) {
				if (result.success) {
					var txt = "禁用成功！";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
					reloadClear();
				} else {
					var txt = "禁用失败！";
					window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
				}
			}, "JSON");
		},
	modifyUser : function(userId,limit){
		$.post('/mUserAction/modifyUser.action', $.param({'usId':userId,'limit':limit}), function(result) {
			if (result.success) {
				var txt = "修改成功！";
				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
				reloadClear();
			} else {
				var txt = "修改失败！";
				window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);
			}
		}, "JSON");
	}

}


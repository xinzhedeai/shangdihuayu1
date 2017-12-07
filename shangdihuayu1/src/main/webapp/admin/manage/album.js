/**
 * @file album
 * @author zhaojunbao
 * @date: 2017年12月6日 20:02:58
 * @version: V1.0
 */
'use strict';

$(function() {
	$album.selector = {
		albumDatagrid : $('#albumDatagrid'),
		addAlbumModal : $('#addAlbumModal'),
		album_form : $('.album_form'),
	}
	$('#addAlbumBtn').click(function(){
		$album.selector.addAlbumModal.modal();
	});
	$('#addOk').click(function(){
		$album.addAlbum();
	});
	var albumColumns = [{
		field : 'ck',
		checkbox : true
	}, {
		field : 'album_id',
		title : '专辑ID',
		width : 200,
		sortable : true,
	
	}, {
		field : 'album_name',
		title : '专辑名称',
		width : 100,
		sortable : true,
		formatter : function(value, row, index) {  
			return value == '0' ? '男' : '女';
		}
	},  {
		field : 'create_date',
		title : '创建日期',
		width : 100,
		sortable : true,
	},  {
		field : 'remark',
		title : '备注',
		width : 100,
		sortable : true,
    }],
	albumOptions = {
		url : contextPath + '/albumAction/getAlbumListByPage.action',
		columns: [albumColumns],
		pagination : true,
		fitColumns : false,
		rownumbers : true,
		checkOnSelect:false,
//		width : 1307,
//		sortOrder : 'desc',
		idField : 'album_id',
		pageSize : 1,
		pageNumber : 1,
//		frozenColumns : [ ],
		pageList: [1],
		queryParams : {
			"album_name" : "读经灵修"
		},
		loadFilter : function(data) {
			if (data != null) {
				if(data.result.rows != null){
					return data.result;
				}else{
					layer.alert(REMIND_MSG.NO_DATA);
					$album.userInfoDatagrid.datagrid('loadData', { total: 0, rows: [] });
				}
			} /*else {
				window.location.href='../login.html';
			}*/
		}
	};
	$album.selector.albumDatagrid.datagrid(albumOptions);
});

function reloadClear(){
	$('#az-user-tableList').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections').datagrid("clearChecked");
	//$('#az-user-tableList').datagrid("reload",{ });
	$('#az-user-tableList').datagrid('load', $.serializeObject($('#z-user-searchForm')));
}

var $album = {
	addAlbum : function(){
		var reqParams = {};
		reqParams = $('.album_form').serializeArray();
		reqParams.push({'name':'user_id', 'value':'haliluya!!!'});
		util.ajaxPostCus('/albumAction/addAlbum.action', reqParams, function(res){
			var result = res.result;
	   		if(res.errCd == '0'){
	   			if(result && result.success){
	   				$($fault.error_info_datagrid).datagrid('reload');
	   	    	}
	   		}else{
	   			layer.alert(res.errMsg);
	   		}
		})
	},
	userDetail : function(usId) {// 用户信息详情
		$.post('/albumAction/addAlbum.action', $.param({'usId':usId}), function(res) {
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


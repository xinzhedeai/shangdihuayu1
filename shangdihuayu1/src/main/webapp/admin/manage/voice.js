/**
 * @file voice
 * @author zhaojunbao
 * @date: 2017年12月6日 20:02:58
 * @version: V1.0
 */
'use strict';

$(function() {
	$voice.selector = {
		voiceDatagrid : $('#voiceDatagrid'),
		addVoiceModal : $('#addVoiceModal'),
		voice_form : $('.voice_form'),
	}
	util.getUserList().getAlbumList().getAlbumLv2List();
	$('#addVoiceBtn').click(function(){
		$voice.selector.addVoiceModal.modal();
	});
	$('#addOk').click(function(){
		$voice.addVoice();
	});
	
	$('#upload_voice_pic_btn').click(function(){//上传音频的背景图片
		$('#upload_img_modal').modal();
		$('#upload_img_div').fileUpload();
		
		var album_id_lv1 = $('#album_id_lv1', '#addVoiceModal').combobox('getText'),
		album_id_lv2 = $('#album_id_lv2', '#addVoiceModal').combobox('getText'),
		module = album_id_lv2 ? encodeURIComponent(album_id_lv1 + '/' + album_id_lv2) : encodeURIComponent(album_id_lv1);
		
//		var album = $('.album_id', '#addVoiceModal').combobox('getText');
		var url =  contextPath + '/uploadAction/uploadFile.action?type=image&module=' + module;
		$('#upload_img_div').plupload('getUploader').setOption('url', url);
	});
	
	$('#upload_multiMedia_pic_btn').click(function(){
		$('#upload_multiMedia_modal').modal();
		$('#upload_multiMedia_div').fileUpload();
//		var album = $('.album_id', '#addVoiceModal').combobox('getText');
		var album_id_lv1 = $('#album_id_lv1', '#addVoiceModal').combobox('getText'),
			album_id_lv2 = $('#album_id_lv2', '#addVoiceModal').combobox('getText'),
			module = album_id_lv2 ? encodeURIComponent(album_id_lv1 + '/' + album_id_lv2) : encodeURIComponent(album_id_lv1);
		var url =  contextPath + '/uploadAction/uploadFile.action?type=multiMedia&module=' + module;
		$('#upload_multiMedia_div').plupload('getUploader').setOption('url', url);
	});
	var voiceColumns = [{
		field : 'ck',
		checkbox : true
	}, {
		field : 'voice_id',
		title : '音频ID',
		width : 150,
		sortable : true,
	
	}, {
		field : 'voice_name',
		title : '音频名称',
		width : 200,
		sortable : true,
	}, {
		field : 'user_name',
		title : '主播名称',
		width : 200,
		sortable : true,
	},{
		field : 'img_file_path',
		title : '封面写真',
		width : 60,
		sortable : true,
		formatter: function(value, row, index ){
			return '<img width="50" height="50" src="/upload/'+ value +'"/>';
		}
	},{
		field : 'multimeida_file_path',
		title : '音频播放路径',
		width : 260,
		sortable : true,
		formatter: function(value, row, index ){
			return '<audio controls="controls" src="/upload/'+ value +'"></audio>';
		}
	},{
		field : 'album_name',
		title : '专辑',
		width : 100,
		sortable : true,
	}, {
		field : 'create_date',
		title : '上传日期',
		width : 90,
		sortable : true,
	},  {
		field : 'remark',
		title : '备注',
		width : 100,
		sortable : true,
    }],
	voiceOptions = {
		url : contextPath + '/voiceAction/getVoiceListByPage.action',
		columns: [voiceColumns],
		pagination : true,
		fitColumns : true,
		rownumbers : true,
		checkOnSelect:false,
//		width : 1307,
//		sortOrder : 'desc',
		idField : 'voice_id',
		pageSize : 5,
		pageNumber : 1,
//		frozenColumns : [ ],
		pageList: [5],
	/*	queryParams : {
			"Voice_name" : "读经灵修"
		},*/
		loadFilter : function(data) {
			if (data != null) {
				if(data.result.rows != null){
					return data.result;
				}else{
					layer.alert(REMIND_MSG.NO_DATA);
					$voice.userInfoDatagrid.datagrid('loadData', { total: 0, rows: [] });
				}
			} /*else {
				window.location.href='../login.html';
			}*/
		}
	};
	$voice.selector.voiceDatagrid.datagrid(voiceOptions);
});


var $voice = {
	addVoice : function(){
		var reqParams = {};
		reqParams = $.serializeObject($('.voice_form'));
		reqParams = $.extend(reqParams, $('.pic_preview_div').find('img').data(), $('.multiMedia_preview_div').find('audio').data());
		util.ajaxPostCus('/voiceAction/addVoice.action', reqParams, function(res){
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
		$.post('/voiceAction/addVoice.action', $.param({'usId':usId}), function(res) {
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


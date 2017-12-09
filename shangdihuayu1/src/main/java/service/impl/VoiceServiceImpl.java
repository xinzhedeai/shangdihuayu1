package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import customMapper.CustomVoiceMapper;
import exception.SysException;
import normalPo.Scripture;
import pageModel.EasyUIGridObj;
import service.VoiceService;
import util.PageUtil;

public class VoiceServiceImpl implements VoiceService {
	@Autowired
	private CustomVoiceMapper voiceMapper;

	@Override
	public int insertVoice(Map map) {
		return voiceMapper.insertVoice(map);
	}

	@Override
	public EasyUIGridObj getVoiceListByPage(Map reqMap) {
		EasyUIGridObj obj = null;
		try {
			obj = PageUtil.searchByPage(voiceMapper, reqMap, "getVoiceListByPage");
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}



}

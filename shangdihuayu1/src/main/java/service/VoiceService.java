package service;

import java.util.Map;

import pageModel.EasyUIGridObj;

public interface VoiceService {

	int insertVoice(Map paramMap);

	EasyUIGridObj getVoiceListByPage(Map reqMap);

}

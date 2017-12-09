package customMapper;

import java.util.List;
import java.util.Map;

import pageModel.EasyUIGridObj;

public interface CustomVoiceMapper {
	int insertVoice(Map paramMap);

	List<Map> getVoiceListByPage(Map reqMap);
}

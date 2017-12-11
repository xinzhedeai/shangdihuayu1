package customMapper;

import java.util.List;
import java.util.Map;
@SuppressWarnings("rawtypes")
public interface CustomUserInfoMapper {
	int checkUser(Map map);

	Object getUserInfo(Map reqMap);
	
	List<Map> getUserListByPage(Map map);

	List<Map> getUserAllList();
}

package customMapper;

import java.util.Map;
@SuppressWarnings("rawtypes")
public interface CustomUserInfoMapper {
	int checkUser(Map map);

	Object getUserInfo(Map reqMap);
}

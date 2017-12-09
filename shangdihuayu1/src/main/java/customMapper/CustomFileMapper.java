package customMapper;

import java.util.Map;

public interface CustomFileMapper {
	int insertFile(Map map);
	
	int insertMultiMediaImg(Map map);

	int insertMultiMediaFile(Map map);
}

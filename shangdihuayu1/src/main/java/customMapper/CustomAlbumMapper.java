package customMapper;

import java.util.List;
import java.util.Map;

public interface CustomAlbumMapper {
	int insertAlbum(Map map);

	List<Map> getAlbumListByPage(Map map);

	List<Map> getAllAlbumList();

}

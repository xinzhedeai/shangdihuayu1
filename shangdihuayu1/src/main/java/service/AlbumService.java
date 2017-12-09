package service;

import java.util.List;
import java.util.Map;

import pageModel.EasyUIGridObj;

public interface AlbumService {
	int insertAlbum(Map map);

	EasyUIGridObj getAlbumListByPage(Map reqMap);

	List<Map> getAllAlbumList();
}

package service;

import java.util.List;
import java.util.Map;

import pageModel.EasyUIGridObj;

public interface AlbumService {
	int insertAlbum(Map map);

	EasyUIGridObj getAlbumListByPage(Map reqMap);

	List<Map> getAllAlbumList(Map map);

	int insertAlbumLv1(Map paramMap);

	int insertAlbumLv2(Map paramMap);
}

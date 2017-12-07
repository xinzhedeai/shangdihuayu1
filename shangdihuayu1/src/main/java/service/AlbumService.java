package service;

import java.util.Map;

import pageModel.EasyUIGridObj;

public interface AlbumService {
	int insertAlbum(Map map);

	EasyUIGridObj getAlbumListByPage(Map reqMap);
}

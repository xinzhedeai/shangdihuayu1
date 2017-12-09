package service;

import java.util.Map;

import pageModel.EasyUIGridObj;

public interface FileService {
	int insertFile(Map map);
	
	int insertMultiMediaImg(Map map);

	int insertMultiMediaFile(Map paramMap);

	int insertAlbumLv1File(Map paramMap);

	int insertAlbumLv2File(Map paramMap);

}

package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import customMapper.CustomAlbumMapper;
import exception.SysException;
import normalPo.Scripture;
import pageModel.EasyUIGridObj;
import service.AlbumService;
import util.PageUtil;

public class AlbumServiceImpl implements AlbumService {
	@Autowired
	private CustomAlbumMapper albumMapper;

	public int insertAlbum(Map map) {
		return albumMapper.insertAlbum(map);
	}

	@Override
	public EasyUIGridObj getAlbumListByPage(Map reqMap) {
		EasyUIGridObj obj = null;
		try {
			obj = PageUtil.searchByPage(albumMapper, reqMap, "getAlbumListByPage");
		} catch (SysException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<Map> getAllAlbumList() {
		return albumMapper.getAllAlbumList();
	}

	@Override
	public int insertAlbumLv1(Map paramMap) {
		return albumMapper.insertAlbumLv1(paramMap);
	}

	@Override
	public int insertAlbumLv2(Map paramMap) {
		return albumMapper.insertAlbumLv2(paramMap);
	}


}

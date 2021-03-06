package service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import customMapper.CustomAlbumMapper;
import customMapper.CustomFileMapper;
import exception.SysException;
import normalPo.Scripture;
import pageModel.EasyUIGridObj;
import service.AlbumService;
import service.FileService;
import util.PageUtil;

public class FileServiceImpl implements FileService {
	@Autowired
	private CustomFileMapper fileMapper;

	public int insertFile(Map map) {
		return fileMapper.insertFile(map);
	}
	
	public int insertMultiMediaImg(Map map) {
		return fileMapper.insertMultiMediaImg(map);
	}

	@Override
	public int insertMultiMediaFile(Map map) {
		return fileMapper.insertMultiMediaFile(map);
	}

	@Override
	public int insertAlbumLv1File(Map paramMap) {
		return fileMapper.insertAlbumLv1File(paramMap);
	}

	@Override
	public int insertAlbumLv2File(Map paramMap) {
		return fileMapper.insertAlbumLv2File(paramMap);
	}

}

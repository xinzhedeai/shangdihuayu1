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

}

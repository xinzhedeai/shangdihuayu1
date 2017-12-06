package normalMapper;

import java.util.List;
import normalPo.File;
import normalPo.FileExample;
import org.apache.ibatis.annotations.Param;

public interface FileMapper {
    int countByExample(FileExample example);

    int deleteByExample(FileExample example);

    int deleteByPrimaryKey(String fileId);

    int insert(File record);

    int insertSelective(File record);

    List<File> selectByExample(FileExample example);

    File selectByPrimaryKey(String fileId);

    int updateByExampleSelective(@Param("record") File record, @Param("example") FileExample example);

    int updateByExample(@Param("record") File record, @Param("example") FileExample example);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKey(File record);
}
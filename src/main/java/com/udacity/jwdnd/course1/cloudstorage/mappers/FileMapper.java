package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE fileId=#{fileId}")
    File findById(Integer fileId);

    @Select("SELECT * FROM FILES WHERE userid=#{userId}")
    List<File> findByUserId(Integer userId);

    @Select("SELECT * FROM FILES WHERE filename=#{fileName} AND userid=#{userId}")
    File findByFileName(String fileName, Integer userId);
    @Insert("INSERT INTO FILES (filename,contenttype,filesize,userid,filedata) VALUES (#{fileName},#{contentType},#{fileSize},#{userId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    int delete(Integer fileId);
}

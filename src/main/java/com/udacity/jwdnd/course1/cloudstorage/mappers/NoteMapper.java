package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.NoteForm;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE noteid=${noteId}")
    Note findById(Integer noteId);

    @Select("SELECT * FROM NOTES WHERE userid=#{userId}")
    List<Note> findByUserId(Integer userId);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insert(Note note);

    @Update("UPDATE NOTES SET notetitle=#{title}, notedescription=#{description} WHERE noteid=#{id}")
    int update(NoteForm note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{fileId}")
    int delete(Integer noteId);
}

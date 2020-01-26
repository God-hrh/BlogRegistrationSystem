package com.example.demo1.dao;

import com.example.demo1.dataobject.GiteeUser;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
@Mapper
public interface GiteeUserMapper {
    @Delete({
        "delete from gitee_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into gitee_user (id, gmt_created, ",
        "gmt_modified, username, ",
        "password)",
        "values (#{id,jdbcType=INTEGER}, now(), ",
        "now(), #{username,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR})"
    })
    int insert(GiteeUser record);

    @Select({
        "select",
        "id, gmt_created, gmt_modified, username, password",
        "from gitee_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    //做一个数据库字段和对象属性的映射
    @Results(id = "giteeUserMap",value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="username", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR)
    })
    GiteeUser selectByPrimaryKey(Integer id);

    @Select({
        "select",
        "id, gmt_created, gmt_modified, username, password",
        "from gitee_user"
    })
    //相同的映射用ResulMap代替
    @ResultMap("giteeUserMap")
    List<GiteeUser> selectAll();
    @Select({
            "select",
            "username, password",
            "from gitee_user",
            "where username = #{username,jdbcType=VARCHAR}"
    })
    @ResultMap("giteeUserMap")
    GiteeUser selectByName(@Param("username") String name);
    @Update({
        "update gitee_user",
        "set ",
          "gmt_modified = now(),",
          "username = #{username,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GiteeUser record);
}
package cn.appservice.base.mapper;

import cn.appservice.base.po.Socketinfo;
import cn.appservice.base.po.SocketinfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SocketinfoMapper {
    int countByExample(SocketinfoExample example);

    int deleteByExample(SocketinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Socketinfo record);

    int insertSelective(Socketinfo record);

    List<Socketinfo> selectByExample(SocketinfoExample example);

    Socketinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Socketinfo record, @Param("example") SocketinfoExample example);

    int updateByExample(@Param("record") Socketinfo record, @Param("example") SocketinfoExample example);

    int updateByPrimaryKeySelective(Socketinfo record);

    int updateByPrimaryKey(Socketinfo record);
}
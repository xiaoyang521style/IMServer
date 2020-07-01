package cn.appservice.base.mapper;

import cn.appservice.base.po.Friendshippolicy;
import cn.appservice.base.po.FriendshippolicyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendshippolicyMapper {
    int countByExample(FriendshippolicyExample example);

    int deleteByExample(FriendshippolicyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Friendshippolicy record);

    int insertSelective(Friendshippolicy record);

    List<Friendshippolicy> selectByExample(FriendshippolicyExample example);

    Friendshippolicy selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Friendshippolicy record, @Param("example") FriendshippolicyExample example);

    int updateByExample(@Param("record") Friendshippolicy record, @Param("example") FriendshippolicyExample example);

    int updateByPrimaryKeySelective(Friendshippolicy record);

    int updateByPrimaryKey(Friendshippolicy record);
}
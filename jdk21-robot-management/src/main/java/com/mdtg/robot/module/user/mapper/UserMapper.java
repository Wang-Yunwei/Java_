package com.mdtg.robot.module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mdtg.robot.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author WangYunwei
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    @Select("SELECT id, username, gender, identity_card, phone, email, address, role_ids, type, status, org_code FROM `mdtg_user` WHERE ${ew.sqlSegment}")
//    List<QueryUserOutputDTO> selectPageDTOList(IPage<?> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}





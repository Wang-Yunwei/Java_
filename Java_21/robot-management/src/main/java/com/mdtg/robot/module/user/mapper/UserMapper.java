package com.mdtg.robot.module.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.mdtg.robot.module.user.dto.QueryUserOutputDTO;
import com.mdtg.robot.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author WangYunwei
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT id, username, gender, identity_card, phone, email, address, role_ids, type, status, org_code FROM `mdtg_user` WHERE ${ew.sqlSegment}")
    List<QueryUserOutputDTO> selectPageDTOList(IPage<?> page, @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}





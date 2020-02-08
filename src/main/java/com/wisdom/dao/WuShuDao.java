package com.wisdom.dao;

import com.wisdom.dto.WuShuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WuShuDao {

    //    查询所有武术部信息
    List<WuShuDTO> queryAllWuShu();
}

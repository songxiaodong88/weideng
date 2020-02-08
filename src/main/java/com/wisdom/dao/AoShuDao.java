package com.wisdom.dao;

import com.wisdom.dto.AoShuZhangDTO;
import com.wisdom.dto.AoShuZhaoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AoShuDao {

    //    查询奥数张老师信息
    List<AoShuZhangDTO> queryAllAoShuZhang();

    //    查询奥数赵老师信息
    List<AoShuZhaoDTO> queryAllAoShuZhao();
}

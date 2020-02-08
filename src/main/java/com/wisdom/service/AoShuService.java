package com.wisdom.service;

import com.wisdom.dto.AoShuZhangDTO;
import com.wisdom.dto.AoShuZhaoDTO;

import java.util.List;

public interface AoShuService {

    //    查询奥数张老师信息
    List<AoShuZhangDTO> queryAllAoShuZhang();

    //    查询奥数找老师信息
    List<AoShuZhaoDTO> queryAllAoShuZhao();
}

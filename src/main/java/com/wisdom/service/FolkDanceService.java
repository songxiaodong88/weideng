package com.wisdom.service;

import com.wisdom.dto.FolkDanceDTO;

import java.util.List;

public interface FolkDanceService {

    //    查询所有陈老师民族舞信息
    List<FolkDanceDTO> queryAllFolkDance_chen();

    //    查询所有殷老师民族舞信息
    List<FolkDanceDTO> queryAllFolkDance_yin();

    //  查询所有幼小衔接班民族舞信息
    List<FolkDanceDTO> queryAllFolkDance_PRE();
}

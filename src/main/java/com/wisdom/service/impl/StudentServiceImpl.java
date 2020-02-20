package com.wisdom.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wisdom.dao.CostDetailsDao;
import com.wisdom.dao.StudentDao;
import com.wisdom.entity.StudentEntity;
import com.wisdom.service.StudentService;
import com.wisdom.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Service实现类
 *
 * @author 8888
 * @date 2020-01-18 23:38:16
 */
@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {

    @Autowired
    private CostDetailsDao costDetailsDao;

    @Override
    public List<StudentEntity> queryAll(Map<String, Object> params) {
        return baseMapper.queryAll(params);
    }

    @Override
    public List<StudentEntity> queryAllStudent(){
        return baseMapper.queryAllStudent();
    }

    @Override
    public Page queryPage(Map<String, Object> params) {
        //排序
        params.put("sidx", "T.sid");
        params.put("asc", false);
        Page<StudentEntity> page = new Query<StudentEntity>(params).getPage();
        return page.setRecords(baseMapper.selectStudentPage(page, params));
    }

    @Override
    public boolean add(StudentEntity student) {
        return this.save(student);
    }

    @Override
    public boolean update(StudentEntity student) {
        return this.updateById(student);
    }

    @Override
    public boolean delete(Integer sid) {
        return this.removeById(sid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatch(Integer[] sids) {
        return this.removeByIds(Arrays.asList(sids));
    }

    @Override
    public Integer selectStuIDBySname(String sname) {
        return baseMapper.selectStuIDBySname(sname);
    }

    @Override
    public StudentEntity detailStudentBySid(Integer sid) {
        return baseMapper.detailStudentBySid(sid);
    }

    @Override
    public List<StudentEntity> queryAllStudentByTuiXue() {
        return baseMapper.queryAllStudentByTuiXue();
    }

    @Override
//    @Transactional
    public Integer updStudentOfStatus(Integer sid) {
        int num1 = baseMapper.updStudentOfStatus(sid);
//        int num2 = costDetailsDao.updCostDetailsOfStatus(sid);
        if (num1 > 0) {
            return 1;
        }
        return 0;
    }
}

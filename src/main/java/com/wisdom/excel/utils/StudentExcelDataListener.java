package com.wisdom.excel.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.wisdom.entity.StudentEntity;
import com.wisdom.excel.entity.StudentExcelEntity;
import com.wisdom.service.CourseService;
import com.wisdom.entity.CourseEntity;
import com.wisdom.excel.entity.CourseExcelEntity;
import com.wisdom.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 学生信息
 */
public class StudentExcelDataListener extends AnalysisEventListener<StudentExcelEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentExcelDataListener.class);
    /** 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收 */
    private static final int BATCH_COUNT = 50;

    List<StudentExcelEntity> list = new ArrayList<>();

    private int successCount = 0; // 成功量

    private int exceptionCount = 0; // 异常量

    private List<StudentExcelEntity> exceptionList = new ArrayList<>(); // 异常数据

    private StudentService studentService;

    public StudentExcelDataListener(StudentService studentService) {
        this.studentService = studentService;
    }

    /** 手机号正则表达式 */
    public static boolean isPhone(String mobile) {
        String regexPhone = "[1][3,4,5,6,7,8,9][0-9]{9}$";
        Pattern p = Pattern.compile(regexPhone);
        Matcher m = p.matcher(mobile);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

//    身份证号码正则表达式
    public static boolean isIdCard(String IdCard) {
        String regexRealname =
                "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
                        + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        Pattern p = Pattern.compile(regexRealname);
        Matcher m = p.matcher(IdCard);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void invoke(StudentExcelEntity data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }

    /** 加上存储数据库 */
    @Transactional(rollbackFor = Exception.class)
    public void saveData() {
        StudentExcelDataListener.LOGGER.info("{}条数据，开始存储数据库！", list.size());
        // this.dealerPlanService.processImportData(list);
        StudentEntity studentEntity = null;
        if (null != list && list.size() > 0) {

            for (StudentExcelEntity stu : list) {
                if (stu != null) {

                    studentEntity = new StudentEntity();
                    BeanUtils.copyProperties(stu, studentEntity);
                    try {
                        if (stu.getBirthday() != null && !"".equals(stu.getBirthday())) {
                            studentEntity.setBirthday((new SimpleDateFormat("yyyy.MM.dd").parse(stu.getBirthday())));
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (stu.getSex() != null && !"".equals(stu.getSex())) {
                        if (stu.getSex().equals("男")) {
                            studentEntity.setSex(0);
                        } else if ("女".equals(stu.getSex())) {
                            studentEntity.setSex(1);
                        }
                    }
                    studentEntity.setSid(0);
                    studentService.save(studentEntity);
                    successCount++;

                    LOGGER.info("姓名=======" + studentEntity.getSname());
                    LOGGER.info("费用=======" + studentEntity.getPhone());
                }
            }
        }

        StudentExcelDataListener.LOGGER.info("存储数据库成功！");
    }

    /**
     * 插入结果返回
     *
     * @return
     */
    public Map<String, Object> getData() {

        Map<String, Object> map = new HashMap<>();
        map.put("success", successCount);
        map.put("exception", exceptionCount);
        //        map.put("username", userNameNo);
        //        map.put("IDCard", IDCardNo);
        return map;
    }

    /**
     * 失败数据返回
     *
     * @return
     */
    public List<StudentExcelEntity> getExceptionList() {
        return exceptionList;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        LOGGER.error("解析失败，但是继续解析下一行", exception);
        // UploadExcelData uploadExcelData  =
        // (UploadExcelData)context.readRowHolder().getCurrentRowAnalysisResult();
        // exceptionList.add(uploadExcelData);
        //        888
        exceptionCount++;
    }
}

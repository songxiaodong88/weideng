package com.wisdom.excel.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wisdom.entity.CostDetailsEntity;
import com.wisdom.excel.entity.WuShuExcelEntity;
import com.wisdom.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 武术
 */
public class WuShuExcelDataListener extends AnalysisEventListener<WuShuExcelEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WuShuExcelDataListener.class);
    /** 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收 */
    private static final int BATCH_COUNT = 50;

    List<WuShuExcelEntity> list = new ArrayList<>();

    private int successCount = 0; // 成功量

    private int exceptionCount = 0; // 异常量

    private List<WuShuExcelEntity> exceptionList = new ArrayList<>(); // 异常数据

    private StudentService studentService;
    private ClassesService classesService;
    private CostDetailsService costDetailsService;

    public WuShuExcelDataListener(StudentService studentService, ClassesService classesService,CostDetailsService costDetailsService) {
        this.studentService = studentService;
        this.classesService = classesService;
        this.costDetailsService = costDetailsService;
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
    public void invoke(WuShuExcelEntity data, AnalysisContext context) {
//        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
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
        WuShuExcelDataListener.LOGGER.info("{}条数据，开始存储数据库！", list.size());
        // this.dealerPlanService.processImportData(list);
//        StudentEntity studentEntity = null;
//        ClassesEntity classesEntity = null;
        CostDetailsEntity costDetailsEntity = null;
        Integer stuId = 0;
        Integer courseId = 9;
        Integer classesId = 0;
        Integer sum = 0;//计算累计课时
        Integer jiuYue = 0;// 9月
        String shiYue = "";//   10月
        String shiYiYue = "";// 11月
        Integer shiErYue = 0;// 12月

        if (null != list && list.size() > 0) {

            for (WuShuExcelEntity e : list) {
//                    System.out.println("cname-======="+e.getCname());
//                    System.out.println("schooltime==="+e.getSchooltime());
//                    sum = Integer.valueOf(e.getJiuYue()) + Integer.valueOf(e.getShiYue()) + Integer.valueOf(e.getShiYiYue()) + Integer.valueOf(e.getShiErYue());
                    /*if (e.getJiuYue() == null || "".equals(e.getJiuYue())) {
                    jiuYue = 0;
                } else {
                    jiuYue = Integer.valueOf(e.getJiuYue());
                }
                if (e.getShiYue() == null || "".equals(e.getShiYue())) {
                    shiYue = "0";
                } else {
                    shiYue = e.getShiYue();
                }
                if (e.getShiYiYue() == null || "".equals(e.getShiYiYue())) {
                    shiYiYue = "0";
                } else {
                    shiYiYue = e.getShiYiYue();
                }
                if (e.getShiErYue() == null || "".equals(e.getShiErYue())) {
                    shiErYue = 0;
                } else {
                    shiErYue = Integer.valueOf(e.getShiErYue());
                }
                sum = jiuYue + Integer.valueOf(shiYue) + Integer.valueOf(shiYiYue) + shiErYue;*/
                classesId = classesService.selectClassIDByClassName(e.getCname().trim(), "");
                stuId = studentService.selectStuIDBySname(e.getSname().trim());
                if (stuId == null) {
                    System.out.println("stuname===========" + e.getSname());
                } else {

//                    studentEntity = new StudentEntity();
//                    classesEntity = new ClassesEntity();
                    costDetailsEntity = new CostDetailsEntity();

                    //  添加费用详情
                    costDetailsEntity.setCdId(0);
                    costDetailsEntity.setStuId(stuId);
                    costDetailsEntity.setClassId(classesId);
                    costDetailsEntity.setCourseId(courseId);
                    costDetailsEntity.setOctober(shiYue);
                    costDetailsEntity.setSeptember(jiuYue);
                    costDetailsEntity.setNovember(shiYiYue);
                    costDetailsEntity.setDecember(shiErYue);
                    if (e.getCost() != null && !"".equals(e.getCost())) {
                        costDetailsEntity.setPrice(Double.parseDouble(e.getCost()));
                    }else{
                        costDetailsEntity.setPrice(0.00);
                    }
                    if (e.getTotal() != null && !"".equals(e.getTotal())) {
                        costDetailsEntity.setTotal(Double.parseDouble(e.getTotal()));
                    }else {
                        costDetailsEntity.setTotal(0.00);
                    }
                    if (e.getPayTime() != null && !"".equals(e.getPayTime())) {
                        try {
                            costDetailsEntity.setPayTime(new SimpleDateFormat("yyyy.MM.dd").parse(e.getPayTime()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                    costDetailsEntity.setClassHour(Integer.valueOf(e.getClassHour()));
                    costDetailsService.save(costDetailsEntity);

                    successCount++;
                }
            }
        }

        WuShuExcelDataListener.LOGGER.info("存储数据库成功！");
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
    public List<WuShuExcelEntity> getExceptionList() {
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

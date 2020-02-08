package com.wisdom.excel.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.wisdom.entity.NurseryClassEntity;
import com.wisdom.excel.entity.PRESchoolExcelEntity;
import com.wisdom.service.NurseryClassService;
import com.wisdom.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 幼小衔接班
 */
public class PRESchoolExcelDataListener extends AnalysisEventListener<PRESchoolExcelEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PRESchoolExcelDataListener.class);
    /** 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收 */
    private static final int BATCH_COUNT = 50;

    List<PRESchoolExcelEntity> list = new ArrayList<>();

    private int successCount = 0; // 成功量

    private int exceptionCount = 0; // 异常量

//    private List<AoShuExcelEntity> exceptionList = new ArrayList<>(); // 异常数据

    private StudentService studentService;
    private NurseryClassService nurseryClassService;

    public PRESchoolExcelDataListener(StudentService studentService,NurseryClassService nurseryClassService) {
        this.studentService = studentService;
        this.nurseryClassService = nurseryClassService;
    }

    @Override
    public void invoke(PRESchoolExcelEntity data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    /** 加上存储数据库 */
    @Transactional(rollbackFor = Exception.class)
    public void saveData() {
        PRESchoolExcelDataListener.LOGGER.info("{}条数据，开始存储数据库！", list.size());
        // this.dealerPlanService.processImportData(list);
//        StudentEntity studentEntity = null;
//        ClassesEntity classesEntity = null;
//        MonthExpendEntity monthExpendEntity = null;
//        CostDetailsEntity costDetailsEntity = null;
        NurseryClassEntity nurseryClassEntity = null;
        Integer stuId = 0;
        Integer courseId = 1;
        Integer classesId = 33;
        Integer sum = 0;//计算累计课时
        Integer yiYue = 0;// 1月
        Integer erYue = 0;// 2月
        Integer sanYue = 0;// 3月
        Integer siYue = 0;// 4月
        Integer wuYue = 0;// 5月
        Integer liuYue = 0;// 6月
        Integer qiYue = 0;// 7月
        Integer baYue = 0;// 8月
        Integer jiuYue = 0;// 9月
        Integer shiYue = 0;// 10月
        Integer shiYiYue = 0;// 11月
        Integer shiErYue = 0;// 12月

        if (null != list && list.size() > 0) {

            for (PRESchoolExcelEntity e : list) {
                /*if (e.getYiYue() == null || "".equals(e.getYiYue())) {
                    yiYue = 0;
                } else {
                     yiYue = Integer.valueOf(e.getYiYue());
                }
                if (e.getErYue() == null || "".equals(e.getErYue())) {
                    erYue = 0;
                }else{
                    erYue = Integer.valueOf(e.getErYue());
                }
                if (e.getSanYue() == null || "".equals(e.getSanYue())) {
                    sanYue = 0;
                }else{
                    sanYue = Integer.valueOf(e.getSanYue());
                }
                if (e.getSiYue() == null || "".equals(e.getSiYue())) {
                    siYue = 0;
                }else{
                    siYue = Integer.valueOf(e.getSiYue());
                }
                if (e.getWuYue() == null || "".equals(e.getWuYue())) {
                    wuYue = 0;
                }else{
                    wuYue = Integer.valueOf(e.getWuYue());
                }
                if (e.getLiuYue() == null || "".equals(e.getLiuYue())) {
                    liuYue = 0;
                } else {
                    liuYue = Integer.valueOf(e.getLiuYue());
                }
                if (e.getJiuYue() == null || "".equals(e.getJiuYue())) {
                    jiuYue = 0;
                } else {
                    jiuYue = Integer.valueOf(e.getJiuYue());
                }
                if (e.getShiYue() == null || "".equals(e.getShiYue())) {
                    shiYue = 0;
                } else {
                    shiYue = Integer.valueOf(e.getShiYue());
                }
                if (e.getShiYiYue() == null || "".equals(e.getShiYiYue())) {
                    shiYiYue = 0;
                } else {
                    shiYiYue = Integer.valueOf(e.getShiYiYue());
                }
                if (e.getShiErYue() == null || "".equals(e.getShiErYue())) {
                    shiErYue = 0;
                } else {
                    shiErYue = Integer.valueOf(e.getShiErYue());
                }
                sum = (yiYue + erYue + sanYue + siYue + wuYue + liuYue + baYue + jiuYue + shiYue + shiYiYue + shiErYue);*/

//                System.out.println("cname==========="+e.getCname());
//                System.out.println("schoolName======"+e.getSchooltime());
                stuId = studentService.selectStuIDBySname(e.getSname().trim());
                if (stuId == null) {
                    System.out.println("stuname===========" + e.getSname());
                } else {

                    nurseryClassEntity = new NurseryClassEntity();

                    //  添加幼儿班信息
//                    nurseryClassEntity.setNcId(0);
                    nurseryClassEntity.setStuId(stuId);
                    nurseryClassEntity.setClassId(classesId);
                    nurseryClassEntity.setCourseId(courseId);
                    nurseryClassEntity.setTotal(e.getTotal());
                    //  缴费方式
                    if (e.getPaymentMethod()!=null && !"".equals(e.getPaymentMethod())) {
                        nurseryClassEntity.setPaymentMethod(e.getPaymentMethod());
                    }
                    //  截止时间
                    if (e.getPeriodOfValidity()!=null && !"".equals(e.getPeriodOfValidity())) {
                        nurseryClassEntity.setPeriodOfValidity(e.getPeriodOfValidity());
                    }
                    //  缴费日期
                    if (e.getPayTime()!=null && !"".equals(e.getPayTime())) {
                        nurseryClassEntity.setPayTime(e.getPayTime());
                       /* try {
                            nurseryClassEntity.setPayTime(new SimpleDateFormat("yyyy.MM.dd").parse(e.getPayTime()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }*/
                    }
                    //  联系方式
                    if (e.getPhone()!=null && !"".equals(e.getPhone())) {
                        nurseryClassEntity.setPhone(e.getPhone());
                    }
                    //  课外班
                    if (e.getInterestClasses()!=null && !"".equals(e.getInterestClasses())) {
                        nurseryClassEntity.setInterestClasses(e.getInterestClasses());
                    }
                    //  备注
                    if (e.getRemark()!=null && !"".equals(e.getRemark())) {
                        nurseryClassEntity.setRemark(e.getRemark());
                    }
                    //  学平险
                    if (e.getStudentsSafetyInsurance()!=null && !"".equals(e.getStudentsSafetyInsurance())) {
                        nurseryClassEntity.setStudentsSafetyInsurance(e.getStudentsSafetyInsurance());
                    }
                    //  档案
                    if (e.getRecord()!=null && !"".equals(e.getRecord())) {
                        nurseryClassEntity.setRecord(e.getRecord());
                    }
                    //  协议
                    if (e.getProtocol()!=null && !"".equals(e.getProtocol())) {
                        nurseryClassEntity.setProtocol(e.getProtocol());
                    }
                    //  备忘
                    if (e.getMemo()!=null && !"".equals(e.getMemo())) {
                        nurseryClassEntity.setMemo(e.getMemo());
                    }
                    //  年度

                    nurseryClassService.save(nurseryClassEntity);

                    successCount++;
                }
            }
        }

        PRESchoolExcelDataListener.LOGGER.info("存储数据库成功！");
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
//    public List<AoShuExcelEntity> getExceptionList() {
//        return exceptionList;
//    }

    @Override
    public void onException(Exception exception, AnalysisContext context) {
        LOGGER.error("解析失败，但是继续解析下一行", exception);
        // UploadExcelData uploadExcelData  =
        // (UploadExcelData)context.readRowHolder().getCurrentRowAnalysisResult();
        // exceptionList.add(uploadExcelData);
        //        888
        exceptionCount++;
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
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");
    }
}

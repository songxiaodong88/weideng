package com.wisdom.excel.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.wisdom.entity.NurseryClassEntity;
import com.wisdom.entity.OilPaintingEntity;
import com.wisdom.excel.entity.OilPaintingExcelEntity;
import com.wisdom.excel.entity.PRESchoolExcelEntity;
import com.wisdom.service.NurseryClassService;
import com.wisdom.service.OilPaintingService;
import com.wisdom.service.StudentService;
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
 * 幼小衔接班
 */
public class OilPaintingExcelDataListener extends AnalysisEventListener<OilPaintingExcelEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(OilPaintingExcelDataListener.class);
    /** 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收 */
    private static final int BATCH_COUNT = 50;

    List<OilPaintingExcelEntity> list = new ArrayList<>();

    private int successCount = 0; // 成功量

    private int exceptionCount = 0; // 异常量

//    private List<AoShuExcelEntity> exceptionList = new ArrayList<>(); // 异常数据

    private StudentService studentService;
    private OilPaintingService oilPaintingService;

    public OilPaintingExcelDataListener(StudentService studentService, OilPaintingService oilPaintingService) {
        this.studentService = studentService;
        this.oilPaintingService = oilPaintingService;
    }

    @Override
    public void invoke(OilPaintingExcelEntity data, AnalysisContext context) {
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
        OilPaintingExcelDataListener.LOGGER.info("{}条数据，开始存储数据库！", list.size());
        // this.dealerPlanService.processImportData(list);
        OilPaintingEntity oilPaintingEntity = null;
        Integer stuId = 0;
        Integer courseId = 0;
        Integer classesId = 34;
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
        Integer yiBaShiYiYue = 0;
        Integer yiJiuShiYiYue = 0;
        Integer yiBaShiErYue = 0;
        Integer yiJiuShiErYue = 0;

        if (null != list && list.size() > 0) {

            for (OilPaintingExcelEntity e : list) {
                //  课程ID待定

                if (e.getYibaNovember() == null || "".equals(e.getYibaNovember())) {
                    yiBaShiYiYue = 0;
                } else {
                     yiBaShiYiYue = Integer.valueOf(e.getYibaNovember());
                }
                if (e.getYibaDecember() == null || "".equals(e.getYibaDecember())) {
                    yiBaShiErYue = 0;
                }else{
                    yiBaShiErYue = Integer.valueOf(e.getYibaDecember());
                }
                if (e.getYijiuJanuary() == null || "".equals(e.getYijiuJanuary())) {
                    yiYue = 0;
                } else {
                    yiYue = Integer.valueOf(e.getYijiuJanuary());
                }
                if (e.getYijiuMarch() == null || "".equals(e.getYijiuMarch())) {
                    sanYue = 0;
                }else{
                    sanYue = Integer.valueOf(e.getYijiuMarch());
                }
                if (e.getYijuApril() == null || "".equals(e.getYijuApril())) {
                    siYue = 0;
                }else{
                    siYue = Integer.valueOf(e.getYijuApril());
                }
                if (e.getYijiuMay() == null || "".equals(e.getYijiuMay())) {
                    wuYue = 0;
                }else{
                    wuYue = Integer.valueOf(e.getYijiuMay());
                }
                if (e.getYijiuJune() == null || "".equals(e.getYijiuJune())) {
                    liuYue = 0;
                } else {
                    liuYue = Integer.valueOf(e.getYijiuJune());
                }
                if (e.getYijiuJuly() == null || "".equals(e.getYijiuJuly())) {
                    qiYue = 0;
                } else {
                    qiYue = Integer.valueOf(e.getYijiuJuly());
                }
                if (e.getYijiuSeptember() == null || "".equals(e.getYijiuSeptember())) {
                    jiuYue = 0;
                } else {
                    jiuYue = Integer.valueOf(e.getYijiuSeptember());
                }
                if (e.getYijiuOctober() == null || "".equals(e.getYijiuOctober())) {
                    shiYue = 0;
                } else {
                    shiYue = Integer.valueOf(e.getYijiuOctober());
                }
                if (e.getYijiuNovember() == null || "".equals(e.getYijiuNovember())) {
                    yiJiuShiYiYue = 0;
                } else {
                    yiJiuShiYiYue = Integer.valueOf(e.getYijiuNovember());
                }
                if (e.getYijiuDecember() == null || "".equals(e.getYijiuDecember())) {
                    yiJiuShiErYue = 0;
                } else {
                    yiJiuShiErYue = Integer.valueOf(e.getYijiuDecember());
                }
                sum = (yiBaShiYiYue + yiBaShiErYue + yiYue + sanYue + siYue + wuYue + liuYue + qiYue + jiuYue + shiYue + yiJiuShiYiYue + yiJiuShiErYue);

//                System.out.println("cname==========="+e.getCname());
//                System.out.println("schoolName======"+e.getSchooltime());
                stuId = studentService.selectStuIDBySname(e.getSname().trim());
                if (stuId == null) {
                    System.out.println("stuname===========" + e.getSname());
                } else {

                    oilPaintingEntity = new OilPaintingEntity();
                    //  添加油画信息
//                    nurseryClassEntity.setNcId(0);
                    oilPaintingEntity.setOpId(0);
                    oilPaintingEntity.setStuId(stuId);
                    oilPaintingEntity.setCourseId(courseId);
                    oilPaintingEntity.setClassId(classesId);
                    // 课时/月
                    /*if (e.getClassHour()!=null && !"".equals(e.getClassHour())) {
                        nurseryClassEntity.setClassHour(e.getClassHour());
                    }else{
                        nurseryClassEntity.setClassHour("0");
                    }*/
                    //  报课时间
                    if (e.getRegistrationTime()!=null && !"".equals(e.getRegistrationTime())) {
                        try {
                            oilPaintingEntity.setRegistrationTime(new SimpleDateFormat("yyyy.MM.dd").parse(e.getRegistrationTime()));
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                    }
                    //  缴费金额
                    if (e.getTotal()!=null && !"".equals(e.getTotal())) {
                        oilPaintingEntity.setTotal(Double.valueOf(e.getTotal()));
                    }
                    //  报课次数
                    if (e.getClassHour()!=null && !"".equals(e.getClassHour())) {
                        oilPaintingEntity.setClassHour(Integer.valueOf(e.getClassHour()));
                    }
                    //  赠课次数
                    if (e.getGiveLessons()!=null && !"".equals(e.getGiveLessons())) {
                        oilPaintingEntity.setGiveLessons(Integer.valueOf(e.getGiveLessons()));
                    }
                    //  每次课费用
                    if (e.getPrice()!=null && !"".equals(e.getPrice())) {
                        oilPaintingEntity.setPrice(Double.valueOf(e.getPrice()));
                    }
                    //  18年11月
                    oilPaintingEntity.setYibaNovember(yiBaShiYiYue);
                    //  18年12月
                    oilPaintingEntity.setYibaDecember(yiBaShiErYue);
                    //  19年1月
                    oilPaintingEntity.setYijiuJanuary(yiYue);
                    //  19年3月
                    oilPaintingEntity.setYijiuMarch(sanYue);
                    //  19年4月
                    oilPaintingEntity.setYijuApril(siYue);
                    //  19年5月
                    oilPaintingEntity.setYijiuMay(wuYue);
                    //  19年6月
                    oilPaintingEntity.setYijiuJune(liuYue);
                    //  19年7月
                    oilPaintingEntity.setYijiuJuly(qiYue);
                    //  19年9月
                    oilPaintingEntity.setYijiuSeptember(jiuYue);
                    //  19年10月
                    oilPaintingEntity.setYijiuOctober(shiYue);
                    //  19年11月
                    oilPaintingEntity.setYijiuNovember(yiJiuShiYiYue);
                    //  19年12月
                    oilPaintingEntity.setYijiuDecember(yiJiuShiErYue);
                    oilPaintingService.save(oilPaintingEntity);
                    successCount++;
                }
            }
        }

        OilPaintingExcelDataListener.LOGGER.info("存储数据库成功！");
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

package com.wisdom.excel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import org.apache.poi.EmptyFileException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.DocumentFactoryHelper;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.util.IOUtils;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@ClassName EasyExcelUtil
 *@Description easyExcel操作excel工具类
 *@Auther William
 *@Date 2019/6/5 11:35
 *@Version 1.0
 */
public class EasyExcelUtil {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(EasyExcelUtil.class);
    /** 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收 */
    private static final int BATCH_COUNT = 50;

    /**
     * @param in 文件输入流
     * @param customContent
     *           自定义模型可以在
     *           AnalysisContext中获取用于监听者回调使用
     * @param eventListener 用户监听
     * @throws IOException
     * @throws EmptyFileException
     * @throws InvalidFormatException
     */
    public static ExcelReader getExcelReader(InputStream in, Object customContent, AnalysisEventListener<?> eventListener) throws EmptyFileException, IOException, InvalidFormatException {
        // 如果输入流不支持mark/reset，需要对其进行包裹
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }
        // 确保至少有一些数据
        byte[] header8 = IOUtils.peekFirst8Bytes(in);
        ExcelTypeEnum excelTypeEnum = null;
        if (NPOIFSFileSystem.hasPOIFSHeader(header8)) {
            excelTypeEnum = ExcelTypeEnum.XLS;
        }
        if (DocumentFactoryHelper.hasOOXMLHeader(in)) {
            excelTypeEnum = ExcelTypeEnum.XLSX;
        }
        if (excelTypeEnum != null) {
            return new ExcelReader(in, excelTypeEnum, customContent, eventListener);
        }
        throw new InvalidFormatException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }

    /**
     * @param in 文件输入流
     * @param customContent
     *           自定义模型可以在
     *           AnalysisContext中获取用于监听者回调使用
     * @param eventListener 用户监听
     * @param trim  是否对解析的String做trim()默认true,用于防止 excel中空格引起的装换报错。
     * @throws IOException
     * @throws EmptyFileException
     * @throws InvalidFormatException
     */
    public static ExcelReader getExcelReader(InputStream in, Object customContent,
                                             AnalysisEventListener<?> eventListener, boolean trim)
            throws EmptyFileException, IOException, InvalidFormatException {
        // 如果输入流不支持mark/reset，需要对其进行包裹
        if (!in.markSupported()) {
            in = new PushbackInputStream(in, 8);
        }

        // 确保至少有一些数据
        byte[] header8 = IOUtils.peekFirst8Bytes(in);
        ExcelTypeEnum excelTypeEnum = null;
        if (NPOIFSFileSystem.hasPOIFSHeader(header8)) {
            excelTypeEnum = ExcelTypeEnum.XLS;
        }
        if (DocumentFactoryHelper.hasOOXMLHeader(in)) {
            excelTypeEnum = ExcelTypeEnum.XLSX;
        }
        if (excelTypeEnum != null) {
            return new ExcelReader(in, excelTypeEnum, customContent, eventListener, trim);
        }
        throw new InvalidFormatException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
    }


    /*
     *@Description: 读取Excel文件内容
     *@param in excel文件流
     *@param tClass 对应excel实体bean
     *@return: 对应excel实体bean的list
     *@Author:  William
     *@Date:  2019/6/5 13:24
     */
    public static<T> List<T> getExcelContent(InputStream in, Class<T> tClass,Integer sheetNo,Integer headLineNum){
        List<T> excelPropertyIndexModelList = new ArrayList<>();
        try {
            AnalysisEventListener<T> listener = new AnalysisEventListener<T>() {
                @Override
                public void invoke(T excelPropertyIndexModel, AnalysisContext analysisContext) {
                    logger.info("解析到一条数据:{}", JSON.toJSONString(excelPropertyIndexModel));
                    excelPropertyIndexModelList.add(excelPropertyIndexModel);
                    if (excelPropertyIndexModelList.size() >= BATCH_COUNT) {
                        excelPropertyIndexModelList.clear();
                    }
                }
                @Override
                public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                    //读取之后的操作

                    logger.info("所有数据解析完成！");
                }
            };
            ExcelReader excelReader = EasyExcelUtil.getExcelReader(in, null, listener);
            // 第二个参数为表头行数，按照实际设置
            excelReader.read(new Sheet(sheetNo, headLineNum, (Class<? extends BaseRowModel>) tClass));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excelPropertyIndexModelList;
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


}

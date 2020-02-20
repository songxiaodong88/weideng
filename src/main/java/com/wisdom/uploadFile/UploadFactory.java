package com.wisdom.uploadFile;

import com.wisdom.service.SysConfigService;
import com.wisdom.utils.Constant;
import com.wisdom.utils.SpringContextUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;

/**
 * 文件上传Factory
 *
 * @date 2019-01-17 16:21:01
 */
public final class UploadFactory {

   /* private static SysConfigService sysConfigService;
    public static String UPLOAD_ML = "userPic";

    static {
        UploadFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }

    public static AbstractCloudStorageService build() {
        //获取云存储配置信息
        CloudStorageConfig config = sysConfigService.getConfigObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

//        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
//            return new QiniuCloudStorageService(config);
//        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
//            return new AliyunCloudStorageService(config);
//        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
//            return new QcloudCloudStorageService(config);
//        } else if (config.getType() == Constant.CloudService.DISCK.getValue()) {
        try{
            File path   = new File(ResourceUtils.getURL("classpath:").getPath());
            System.out.println("path==========="+path.getPath());
            String filePath = path.getParentFile().getParentFile().getParent() + File.separator + UPLOAD_ML;
            if(filePath.indexOf("file:") != -1){
                filePath = filePath.substring(5,filePath.length());
            }
            System.out.println("filePath ==================================================== "+filePath);
            config.setDiskPath(filePath);
        } catch (Exception e){
            e.printStackTrace();
        }

        return new DiskCloudStorageService(config);
//        }

//        return null;
    }*/





//    private static SysConfigService sysConfigService;
    public static String UPLOAD_ML = "userPic";
    private static String filePath = "";

    /*static {
        UploadFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigService");
    }*/

    public static AbstractCloudStorageService build() {
        //获取云存储配置信息
//        CloudStorageConfig config = sysConfigService.getConfigObject(Constant.CLOUD_STORAGE_CONFIG_KEY, CloudStorageConfig.class);

//        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
//            return new QiniuCloudStorageService(config);
//        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
//            return new AliyunCloudStorageService(config);
//        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
//            return new QcloudCloudStorageService(config);
//        } else if (config.getType() == Constant.CloudService.DISCK.getValue()) {
            try{
                File path   = new File(ResourceUtils.getURL("classpath:").getPath());
//                String filePath = path.getParentFile().getParentFile().getParent() + File.separator + UPLOAD_ML + File.separator;
//                filePath = path.getParentFile().getParentFile()+ File.separator + UPLOAD_ML + File.separator;
//                filePath = path.getParentFile().getParentFile()+ File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "static" + File.separator + UPLOAD_ML;
                filePath = path.getParentFile().getParentFile().getParent() + File.separator + UPLOAD_ML;
                if(filePath.indexOf("file:") != -1){
                    filePath = filePath.substring(5,filePath.length());
                }
                System.out.println("filePath ==================================================== "+filePath);
//                config.setDiskPath(filePath);
            } catch (Exception e){
               e.printStackTrace();
            }

//            return new DiskCloudStorageService(config);
            return new DiskCloudStorageService(filePath);
//        }

//        return null;
    }

}

package com.wisdom.uploadFile;

import com.wisdom.exception.BusinessException;
import com.wisdom.utils.Constant;
import org.apache.poi.util.IOUtils;

import javax.imageio.stream.FileImageOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 服务器存储
 *
 * @date 2019-01-17 16:21:01
 */
public class DiskCloudStorageService extends AbstractCloudStorageService {

   /* public DiskCloudStorageService(CloudStorageConfig config) {
        this.config = config;

        //初始化
        init();
    }*/

    private String filePath = "";
    public DiskCloudStorageService(String filePath1) {
        filePath = filePath1;
//        init();
    }

    private void init() {

    }

    @Override
    public String upload(byte[] data, String path) {
        if (data.length < Constant.THREE || "".equals(path)) {
            throw new BusinessException("上传文件为空");
        }
        //本地存储必需要以"/"开头
        if (!path.startsWith(Constant.SLASH)) {
            path = "/" + path;
        }
        try {
//            String fileName = config.getDiskPath() + path;
            String fileName = filePath + path;
            System.out.println("pathName===================="+path);

            String dateDir = path.split("/")[1];

            //文件夹
//            File dirFile = new File(config.getDiskPath() + "/" + dateDir);
            File dirFile = new File(filePath + "/" + dateDir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            //打开输入流
            FileImageOutputStream imageOutput = new FileImageOutputStream(file);
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("上传文件失败", e);
        }

//        return config.getDiskPath() + path;
        return filePath + path;
    }

    @Override
    public String upload(InputStream inputStream, String path) {
        try {
            byte[] data = IOUtils.toByteArray(inputStream);
            return this.upload(data, path);
        } catch (IOException e) {
            throw new BusinessException("上传文件失败", e);
        }
    }

    @Override
    public String uploadSuffix(byte[] data, String suffix) {
        return upload(data, getPath("", suffix));
    }

    @Override
    public String uploadSuffix(InputStream inputStream, String suffix) {
        return upload(inputStream, getPath("", suffix));
    }
}

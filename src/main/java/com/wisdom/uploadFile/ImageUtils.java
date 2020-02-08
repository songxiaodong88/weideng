package com.wisdom.uploadFile;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.util.ResourceUtils;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {


  /**
   * 按照比例进行缩放
   *
   * @throws IOException
   */
  public static void proportionalCompression(String filePath) throws IOException {
    /** scale(比例) */
    Thumbnails.of(filePath) .scale(0.15f) .toFile(filePath);
  }


    /**
     * 本地图片转换成base64字符串
     * @param imageName	图片名称
     * @reture 图片Base64
     * @author Byr
     * @dateTime 2019-03-07
     */
    public static String imageToBase64ByLocal(String imageName) throws IOException {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            File path   = new File(ResourceUtils.getURL("classpath:").getPath());
//            String filePath = path.getParentFile().getParentFile().getParent() + File.separator + imageName;
            String filePath = path.getParentFile().getParentFile()+ File.separator + imageName;
            if(filePath.indexOf("file:") != -1){
                filePath = filePath.substring(5,filePath.length());
            }
            //获取图片路径
            File file = new File(filePath);
            in = new FileInputStream(file.getPath());

            data = new byte[in.available()];
            in.read(data);
            //in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                in.close();
            }
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }


//    /**
//     * @param args
//     * @throws IOException
//     */
//    public static void main(String[] args) throws IOException {
//        ImageUtils thumbnailatorTest = new ImageUtils();
//        String filePath = "";
//        thumbnailatorTest.proportionalCompression("uploads/20191207/0544c9d8cba6431fbcdea687cfdac214.jpg");
//        //    thumbnailatorTest.test2();
//        //    thumbnailatorTest.test3();
//        //    thumbnailatorTest.test4();
//        //    thumbnailatorTest.test5();
//        //    thumbnailatorTest.test6();
//        //    thumbnailatorTest.test7();
//        //    thumbnailatorTest.test8();
//        //    thumbnailatorTest.test9();
//    }
//  /**
//   * 指定大小进行缩放
//   *
//   * @throws IOException
//   */
//  private void test1() throws IOException {
//    /*
//     * size(width,height) 若图片横比200小，高比300小，不变
//     * 若图片横比200小，高比300大，高缩小到300，图片比例不变 若图片横比200大，高比300小，横缩小到200，图片比例不变
//     * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300
//     */
//    Thumbnails.of("uploads/20191207/1a86aa14ad134942bd895ab4eaf8fc61.jpg")
//        .size(300, 400)
//        .toFile("uploads/20191207/1a86aa14ad134942bd895ab4eaf8fc61.jpg");
//    // Thumbnails.of("images/test.jpg").size(2560,
//    // 2048).toFile("/Users/leiyonghui/IdeaProjects1022/uploads/20191207/1a86aa14ad134942bd895ab4eaf8fc61.jpg");
//  }
//
//  /**
//   * 不按照比例，指定大小进行缩放
//   *
//   * @throws IOException
//   */
//  private void test3() throws IOException {
//    /** keepAspectRatio(false) 默认是按照比例缩放的 */
//    Thumbnails.of("images/test.jpg")
//        .size(120, 120)
//        .keepAspectRatio(false)
//        .toFile("C:/image_120x120.jpg");
//  }
//
//  /**
//   * 旋转
//   *
//   * @throws IOException
//   */
//  private void test4() throws IOException {
//    /** rotate(角度),正数：顺时针 负数：逆时针 */
//    Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(90).toFile("C:/image+90.jpg");
//    Thumbnails.of("images/test.jpg").size(1280, 1024).rotate(-90).toFile("C:/iamge-90.jpg");
//  }
//
//  /**
//   * 水印
//   *
//   * @throws IOException
//   */
//  private void test5() throws IOException {
//    /** watermark(位置，水印图，透明度) */
//    Thumbnails.of("images/test.jpg")
//        .size(1280, 1024)
//        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File("images/watermark.png")), 0.5f)
//        .outputQuality(0.8f)
//        .toFile("C:/image_watermark_bottom_right.jpg");
//    Thumbnails.of("images/test.jpg")
//        .size(1280, 1024)
//        .watermark(Positions.CENTER, ImageIO.read(new File("images/watermark.png")), 0.5f)
//        .outputQuality(0.8f)
//        .toFile("C:/image_watermark_center.jpg");
//  }
//
//  /**
//   * 裁剪
//   *
//   * @throws IOException
//   */
//  private void test6() throws IOException {
//    /** 图片中心400*400的区域 */
//    Thumbnails.of("images/test.jpg")
//        .sourceRegion(Positions.CENTER, 400, 400)
//        .size(200, 200)
//        .keepAspectRatio(false)
//        .toFile("C:/image_region_center.jpg");
//    /** 图片右下400*400的区域 */
//    Thumbnails.of("images/test.jpg")
//        .sourceRegion(Positions.BOTTOM_RIGHT, 400, 400)
//        .size(200, 200)
//        .keepAspectRatio(false)
//        .toFile("C:/image_region_bootom_right.jpg");
//    /** 指定坐标 */
//    Thumbnails.of("images/test.jpg")
//        .sourceRegion(600, 500, 400, 400)
//        .size(200, 200)
//        .keepAspectRatio(false)
//        .toFile("C:/image_region_coord.jpg");
//  }
//
//  /**
//   * 转化图像格式
//   *
//   * @throws IOException
//   */
//  private void test7() throws IOException {
//    /** outputFormat(图像格式) */
//    Thumbnails.of("images/test.jpg")
//        .size(1280, 1024)
//        .outputFormat("png")
//        .toFile("C:/image_1280x1024.png");
//    Thumbnails.of("images/test.jpg")
//        .size(1280, 1024)
//        .outputFormat("gif")
//        .toFile("C:/image_1280x1024.gif");
//  }
//
//  /**
//   * 输出到OutputStream
//   *
//   * @throws IOException
//   */
//  private void test8() throws IOException {
//    /** toOutputStream(流对象) */
//    OutputStream os = new FileOutputStream("C:/image_1280x1024_OutputStream.png");
//    Thumbnails.of("images/test.jpg").size(1280, 1024).toOutputStream(os);
//  }
//
//  /**
//   * 输出到BufferedImage
//   *
//   * @throws IOException
//   */
//  private void test9() throws IOException {
//    /** asBufferedImage() 返回BufferedImage */
//    BufferedImage thumbnail = Thumbnails.of("images/test.jpg").size(1280, 1024).asBufferedImage();
//    ImageIO.write(thumbnail, "jpg", new File("C:/image_1280x1024_BufferedImage.jpg"));
//  }
}

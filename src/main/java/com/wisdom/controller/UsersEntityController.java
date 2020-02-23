package com.wisdom.controller;

import com.wisdom.common.annotation.SysLog;
import com.wisdom.entity.UsersEntity;
import com.wisdom.service.UsersEntityService;
import com.wisdom.uploadFile.UploadFactory;
import com.wisdom.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * 用户
 */
@Slf4j
@Controller
@RequestMapping("/users")
public class UsersEntityController {

    @Autowired
    private UsersEntityService usersEntityService;

    private String picPrefix = "/";

    //  查询所有用户信息
    @RequestMapping(value="queryAllUsers")
    public String queryAllUsers(Model model){
        List<UsersEntity> uList = usersEntityService.queryAllUsers();
        model.addAttribute("users", uList);
        return "infoManagement/userList";
    }

    //  使用ajax根据id查看用户详情
    @RequestMapping(value="detailUserById")
    @ResponseBody
    public UsersEntity detailUserById(Integer uid){
        UsersEntity usersEntity = usersEntityService.detailUserById(uid);

        return usersEntityService.detailUserById(uid);
    }

    //  根据id查看用户详情
    @RequestMapping(value = "detailUserInfo")
    public String detailUserInfo(Integer uid,Model model) {
        model.addAttribute("uInfo", usersEntityService.detailUserById(uid));
        return "user/updateUser";
    }

    //  执行修改操作
    @SysLog("修改用户信息")
    @RequestMapping(value="doUpdateUser")
    public String doUpdateUser(UsersEntity usersEntity,@RequestParam(value = "pic",required = false)MultipartFile pic){
        String photo = "";
        log.info("username====updUser==="+usersEntity.getUsername());
        if (pic == null || pic.isEmpty()) {
            UsersEntity user = usersEntityService.detailUserById(usersEntity.getUid());
            log.info("userPic======"+user.getPhoto());
            usersEntity.setPhoto(user.getPhoto());
        }else{
            String url = processUploadFile(pic);
            log.info("url11111111======"+url);
            photo = picPrefix + url;
            usersEntity.setPhoto(photo);
        }
        log.info("pre=-====="+picPrefix);
        usersEntityService.updateUsers(usersEntity);
        return "redirect:/users/queryAllUsers";
    }

    //  去添加用户页面
    @GetMapping(value="toAddUser")
    public String toAddUser(){
        return "user/addUser";
    }

    //  执行添加操作
    @SysLog("添加用户信息")
    @RequestMapping(value="doAddUser")
    public String doAddUser(UsersEntity usersEntity, @RequestParam(value = "pic",required = false) MultipartFile pic){
        String photo = "";

        if (pic == null || pic.isEmpty()) {
            photo = picPrefix + "img/logo.jpg";
        }else{
            log.info("picName======"+pic.getOriginalFilename());
            String url = processUploadFile(pic);
            log.info("url11111111======"+url);
            photo = picPrefix + url;
        }
        log.info("pre=-====="+picPrefix);
        usersEntity.setPhoto(photo);
        if (usersEntityService.addUsers(usersEntity) > 0) {
            return "redirect:/users/queryAllUsers";
        }else{
            return "redirect:/users/toAddUser";
        }
    }

    //  删除用户
    @SysLog("删除用户信息")
    @RequestMapping(value="doDeleteUser")
    public String doDeleteUser(Integer uid,RedirectAttributes model){
        if (usersEntityService.delUsers(uid) > 0) {
            model.addFlashAttribute("delInfo", "删除成功！");
        }else{
            model.addFlashAttribute("delInfo", "删除失败！");
        }
        return "redirect:/users/queryAllUsers";
    }

    //  根据用户名查询用户是否存在
    @ResponseBody
    @RequestMapping(value="detailUserByUsername")
    public boolean detailUserByUsername(String username){
        log.info("username====="+username);
        UsersEntity usersEntity = usersEntityService.detailUserByUsername(username);
        if (usersEntity != null) {
            return false;
        }else{
            return true;
        }
    }

    //  去修改密码页面
    @GetMapping(value="toUpdPwd")
    public String toUpdPwd(){
        return "user/updatePwd";
    }

    //  执行修改密码
    @SysLog("修改用户密码")
    @RequestMapping(value="doUpdPwd")
    public String doUpdPwd(UsersEntity usersEntity){
        if (usersEntityService.updateUsers(usersEntity) > 0) {
            return "redirect:/login/toIndex";
        }else{
            return "redirect:/users/toUpdPwd";
        }
    }

    //  查询用户原始密码是否正确，修改密码
    @ResponseBody
    @RequestMapping(value="checkOldPwd")
    public boolean updPwd(@RequestParam("pwd") String password){
        log.info("password====="+password);
        String username = ((UsersEntity) HttpContextUtils.getHttpServletRequest().getSession().getAttribute("loginUser")).getUsername();
        if (usersEntityService.login(username,password) == null) {
            return true;
        }
        return false;
    }

    /**
     * 上传图片并做压缩处理
     * @param file
     * @return
     */
    private  String processUploadFile(MultipartFile file){
        String url = "";
        String fileUrl = "";
        try{
            // 文件上传保存实现
            String fileName = file.getOriginalFilename();
            log.info("fileName=========="+fileName);
            String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
            log.info("suffix====" + suffix);
            url = UploadFactory.build().uploadSuffix(file.getBytes(), suffix);
            log.info("url======="+url);
            url = url.substring(url.indexOf(UploadFactory.UPLOAD_ML),url.length());
//            Thread.sleep(1500);
            log.info("url ========"+url);
//            将上传的图片按比例缩小
//            ImageUtils.proportionalCompression(url);
        } catch (Exception e){
            log.error("文件上传异常：",e);
        }
        return url;
    }

    private final ResourceLoader resourceLoader;

    @Autowired
    public UsersEntityController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 显示单张图片 测试自动部署11
     * @return
     */
//    @RequestMapping("show")
//    public ResponseEntity showPhotos(String fileName) throws IOException {
//        log.info("fileName======"+fileName);
//        if (fileName!=null && !"".equals(fileName)) {
//            if (fileName.trim().equals("/img/logo.jpg")) {
//                File path3   = null;
//                String filePath = null;
//                String jarURL = null;
//                JarFile jarFile = null;
////                    path3 = new File(ResourceUtils.getURL("classpath:").getPath());
////                    log.info("path3===="+path3.getPath());
////                    filePath = path3.getParentFile().getParentFile()/*.getParent() */+ File.separator;
////                    if(filePath.indexOf("file:") != -1){
////                        filePath = filePath.substring(5,filePath.length());
////                    }
////                    log.info("filePath====="+filePath+"static");//F:\idea-workSpace\weideng\src\main\resources\static\img\logo.jpg
//                    //查找指定资源的URL，其中res.txt仍然开始的bin目录下
////                    URL fileURL = this.getClass().getResource("/UI/image/background.jpg");
//                    URL fileURL=this.getClass().getResource("/static"+fileName);
//                    log.info("fileURL======="+fileURL.getFile());
//                    if (fileURL.getFile().indexOf("file:") != -1) {
//                        log.info("===============================");
////                        jarURL = fileURL.getFile().substring(5, fileURL.getFile().length());
//
//                        String jarPath = fileURL.toString().substring(0, fileURL.toString().indexOf("!/") + 2);
//
//                        JarURLConnection jarCon = null;
//                        try {
//                            URL jarURL1 = new URL(jarPath);
//                            jarCon = (JarURLConnection) jarURL1.openConnection();
//                            jarFile = jarCon.getJarFile();
//                            jarURL = jarFile.getName();
//                            log.info("jarFile====="+jarFile);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }else{
//                        jarURL = fileURL.getFile();
//                    }
//                    log.info("jarUrl=========="+jarURL);
//
//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(this.getClass().getResourceAsStream("/static"+fileName)));
//
//                StringBuilder buffer = new StringBuilder();
//                String line;
//                while ((line = in.readLine()) != null)
//                {
//                    buffer.append(line);
//                }
//                log.info("buffer====="+buffer.toString());
//                return ResponseEntity.ok(resourceLoader.getResource("file:"+buffer.toString()));
//            }else{
//                /*try {
//                    String path = ResourceUtils.getURL("classpath:").getPath();
//                    log.info("path===="+path);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//                String path2 = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//                log.info("path2===="+path2);
//                String path1 = System.getProperty("java.class.path");
//                log.info("path1====="+path1);*/
//                //  获得jar包所在的目录
//                String jarPath = System.getProperty("user.dir");
//                log.info("jarPath====="+jarPath);
//
//                String picPath = null;
//                if (jarPath.startsWith("F:")) {
//                    picPath = "F:\\idea-workSpace";
//                }else{
//                    picPath = jarPath;
//                }
//
//                try {
//                    // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
//                    return ResponseEntity.ok(resourceLoader.getResource("file:" + picPath + fileName));
//                } catch (Exception e) {
//                    return ResponseEntity.notFound().build();
//                }
//            }
//        }else{
//            return ResponseEntity.notFound().build();
//        }
//    }

    @RequestMapping("show")
    @ResponseBody
    public ResponseEntity showPhotos(String fileName) throws IOException {
        if (fileName!=null && !"".equals(fileName)) {
            log.info("fileName======" + fileName);
            if (fileName.trim().equals("/img/logo.jpg")) {
                URL url = this.getClass().getResource("/static" + fileName);
                log.info("fileUrl=====" + url.getPath());
                ImageIcon imageIcon = new ImageIcon(url);
                return ResponseEntity.ok(resourceLoader.getResource(imageIcon.toString()));
            } else {
//                URL fileURL = this.getClass().getResource(fileName);
//                log.info("fileUrl=====" + fileURL.getPath());
                //  获得jar包所在的目录
                String jarPath = System.getProperty("user.dir");
                log.info("jarPath=====" + jarPath);

                String picPath = null;
                if (jarPath.startsWith("F:")) {
                    picPath = "F:\\idea-workSpace";
                } else {
                    picPath = jarPath;
                }
                File file = new File(picPath + fileName);
                if (file.exists()) {
                    // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
                    return ResponseEntity.ok(resourceLoader.getResource("file:" + picPath + fileName));
                }else{
                    return ResponseEntity.notFound().build();
                }
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

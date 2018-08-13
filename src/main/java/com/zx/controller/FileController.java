package com.zx.controller;

import com.zx.util.FileUtil;
import com.zx.util.ZipUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件上传的Controller
 *
 * @author 单红宇(CSDN CATOOP)
 * @create 2017年3月11日
 */
@Controller
public class FileController {
    private List<String> filenames;
    // 保存进程的输入流信息
    private List<String> stdoutList = new ArrayList<String>();
    // 保存进程的错误流信息
    private List<String> erroroutList = new ArrayList<String>();

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deletefilename(@RequestParam("filename") String filename) {
        filenames.remove(filename);
        return "success";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    @RequestMapping(value = "/getall", method = RequestMethod.GET)
    @ResponseBody
    public List<String> deletefilename() {
        return filenames;
    }

    //跳转到上传文件的页面
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String goUploadImg() {
        return "upload";
    }

    //处理文件上传
    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile file,
                            HttpServletRequest request) {
        // 先清空
        stdoutList.clear();
        erroroutList.clear();
        Process proc = null;
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
//        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
//        String filePath = "E:/upload/";
        String filePath = "/data/project/uploads/12345kpi/";
        String command = "python /data/project/12345-kpi/kpi.py /data/project/uploads/" + fileName;
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            proc = Runtime.getRuntime().exec(command);
            // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
            ThreadUtil stdoutUtil = new ThreadUtil(proc.getInputStream(), stdoutList);
            ThreadUtil erroroutUtil = new ThreadUtil(proc.getErrorStream(), erroroutList);
            //启动线程读取缓冲区数据
            stdoutUtil.start();
            erroroutUtil.start();
            proc.waitFor();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("执行完成");
        //返回json
        return "上传成功";
    }

    /**
     * 上传测试用例
     *
     * @return
     */
    @RequestMapping(value = "/uploadDatav", method = RequestMethod.GET)
    public String uploadTest() {
        return "uploadDatav";
    }

    //处理文件上传
    @RequestMapping(value = "/uploadDatavFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadTestFile(@RequestParam("file") MultipartFile file,
                                 HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String filePath = "/data/docker/nginx/html/pudong/screens_test/";

        String[] commands = new String[]{"/system/bin/sh", "-c",
                "chmod -R 755 /data/docker/nginx/html/pudong/screens_test/*"};
        Process process = null;
        DataOutputStream dataOutputStream = null;

        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            ZipUtil.unzip("/data/docker/nginx/html/pudong/screens_test/" + fileName, "/data/docker/nginx/html/pudong/screens_test/");

            process = Runtime.getRuntime().exec("su");
            dataOutputStream = new DataOutputStream(process.getOutputStream());
            int length = commands.length;
            for (int i = 0; i < length; i++) {
                dataOutputStream.writeBytes(commands[i] + "\n");
            }
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
            process.waitFor();
        } catch (Exception e1) {
            e1.printStackTrace();
            return "上传失败";
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        System.out.println("执行完成");
        //返回json
        return "上传成功";
    }

    private void Delete() {
        // 先清空
        stdoutList.clear();
        erroroutList.clear();
        Process proc = null;
        String command = "python /data/project/muck/illegal_parse/illegal_parse.py >> /data/project/muck/illegal_parse/logs.log";
        try {
            proc = Runtime.getRuntime().exec(command);
            // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
            ThreadUtil stdoutUtil = new ThreadUtil(proc.getInputStream(), stdoutList);
            ThreadUtil erroroutUtil = new ThreadUtil(proc.getErrorStream(), erroroutList);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            List<String> list = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                if (line != null) {
                    list.add(line);
                }
            }

            //启动线程读取缓冲区数据
            stdoutUtil.start();
            erroroutUtil.start();
            proc.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 渣土文件上传页面
     *
     * @return
     */
    @RequestMapping(value = "/uploadForMuck", method = RequestMethod.GET)
    public String uploadformuck() {
        return "uploadformuck";
    }

    //处理文件上传
    @RequestMapping(value = "/uploadfileformuck", method = RequestMethod.POST)
    @ResponseBody
    public String uploadfileformuck(@RequestParam("file") MultipartFile file,
                                    HttpServletRequest request) {
        // 先清空
        stdoutList.clear();
        erroroutList.clear();
        Process proc = null;
        String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();

        /*System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);*/
//        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
//        String filePath = "E:/upload/";
        String filePath = "/data/project/uploads/muck/";
        String command = "python /data/project/muck/illegal_parse/illegal_parse.py >> /data/project/muck/illegal_parse/logs.log";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
            proc = Runtime.getRuntime().exec(command);
            // 创建2个线程，分别读取输入流缓冲区和错误流缓冲区
            ThreadUtil stdoutUtil = new ThreadUtil(proc.getInputStream(), stdoutList);
            ThreadUtil erroroutUtil = new ThreadUtil(proc.getErrorStream(), erroroutList);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            List<String> list = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                if (line != null) {
                    list.add(line);
                }
            }

            //启动线程读取缓冲区数据
            stdoutUtil.start();
            erroroutUtil.start();
            proc.waitFor();

            return list.toString();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println("执行完成");
        //返回json
        return "上传成功";
    }


    public List<String> getStdoutList() {
        return stdoutList;
    }

    public List<String> getErroroutList() {
        return erroroutList;
    }

    class ThreadUtil implements Runnable {
        // 设置读取的字符编码
        private String character = "GB2312";
        private List<String> list;
        private InputStream inputStream;

        public ThreadUtil(InputStream inputStream, List<String> list) {
            this.inputStream = inputStream;
            this.list = list;
        }

        public void start() {
            Thread thread = new Thread(this);
            thread.setDaemon(true);//将其设置为守护线程
            thread.start();
        }

        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(inputStream, character));
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line != null) {
                        list.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    //释放资源
                    inputStream.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

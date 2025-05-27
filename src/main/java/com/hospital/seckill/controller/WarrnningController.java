package com.hospital.seckill.controller;


import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;

@Controller
@RequestMapping("/")
public class WarrnningController {

    @RequestMapping("/notify")
    public String getnotify() {
        return "notify";
    }
    @RequestMapping("/send")
    public String send(Model model) {
        model.addAttribute("echo",false);
        return "send";
    }

    @RequestMapping("/warning")
    public String warning(String room, Model model) {
        String url="smb://zhujiang:123456@192.168.159.132/zhujiang/system.log";
        try {

            SmbFile smbFile=new SmbFile(url);
            //但客户端与服务器之间只能进行流式传输，这里保存使用输出流
            SmbFileOutputStream sos=new SmbFileOutputStream(smbFile,true);
            //接收流后，使用write号:调用文件的writer
            Writer mywriter=new OutputStreamWriter(sos);
            //这里提高写的效率，这里增加缓存
            BufferedWriter bw=new BufferedWriter(mywriter);
            //直接写入数据
            bw.write(room+" ill"+"\r\n");
            //写文件一定要writer后flush
            bw.flush();
            //关团文件
            bw.close();
            mywriter.close();
            sos.close();
            model.addAttribute("echo",true);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "send";
    }
}

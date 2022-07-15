package com.inspur.fosunbond.core.domain.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
@Slf4j
public class UploadUtil {
    private static ChannelSftp sftp=null;
    public static boolean uploadFile(String filename, InputStream input)
    {
        boolean result=false;
        FTPClient ftp=new FTPClient();
        File file=null;
        try
        {
            JSch jSch=new JSch();
            //获取shhSession 账号-IP-端口
            Session sshSession=jSch.getSession("","",5200);
            //设置服务器密码
            sshSession.setPassword("");
            Properties sshConfig=new Properties();
            //严格主机密钥检查
            sshConfig.put("StrictHostKeyChecking","no");
            sshSession.setConfig(sshConfig);
            //开启sshSession链接
            sshSession.connect();
            //获取ftp通道
            Channel chanel=sshSession.openChannel("sftp");
            //开启
            chanel.connect();
            sftp=(ChannelSftp) chanel;
            //服务器路径
            file=new File("");
            //设置为被动模式
            ftp.enterLocalPassiveMode();
            //设置上传文件类型为二进制类型
            //进去到要上传的目录 然后上传文件
            sftp.cd("");
            sftp.put(input,filename);
            input.close();
            result=true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            log.error(e.getMessage());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
          log.error(ex.getMessage());
        }
        finally {

            if (ftp.isConnected())
            {
                try {
                    ftp.disconnect();
                }
                catch (IOException ex)
                {

                }
            }
        }


        return  result;
    }
}

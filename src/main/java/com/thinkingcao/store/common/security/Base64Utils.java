package com.thinkingcao.store.common.security;


import org.apache.commons.codec.binary.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @desc:
 * @author: cao_wencao
 * @date: 2020-04-22 15:34
 */
public class Base64Utils {

    /**
     * 将图片转换为base64字符串
     *
     * @param imgPath 编码图片的路径
     * @return imgStr 图片的base64编码字符串
     */
    public static String imgToBase64(String imgPath) {
        byte[] data = null;
        InputStream in = null;
        try {
            // 将图片读入data中
            in = new FileInputStream(new File(imgPath));
            data = new byte[in.available()];
            in.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 对data进行Base64编码
        String imgStr = new String(Base64.encodeBase64(data));
        return imgStr;
    }
    /**
     * 将图片转换为base64字符串
     *
     * @param in 编码图片的流
     * @return imgStr 图片的base64编码字符串
     */
    public static String imgToBase64(InputStream in) {
        byte[] data = null;
        try {
            data = new byte[in.available()];
            in.read(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 对data进行Base64编码
        String imgStr = new String(Base64.encodeBase64(data));
        return imgStr;
    }
    /**
     * 将Base64字符串转换为图片
     *
     * @param imgStr  图片的base64编码字符串；
     * @param imgPath 生成图片的路径
     * @return 是否生成图片
     */
    public static boolean base64ToImg(String imgStr, String imgPath) {
        if (imgStr == null) {
            return false;
        }
        // 对imgBase64字符串进行解码
        OutputStream out = null;
        try {
            byte[] b = Base64.encodeBase64(imgStr.getBytes());
            for (int i = 0; i < b.length; i++) {
                // 调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            // 生成图片文件
            out = new FileOutputStream(new File(imgPath));
            out.write(b);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    /**
     * 将网络图片编码为base64
     *
     * @param urlStr
     * @return String
     * @throws Exception
     */
    public static String encodeImageToBase64(String urlStr) throws Exception {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        //打开链接
        HttpURLConnection conn = null;
        InputStream inStream = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            byte[] data = outStream.toByteArray();
            //对字节数组Base64编码
            String base64Str = new String(Base64.encodeBase64(data));
            return base64Str;//返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("图片上传失败,请联系客服!");
        }finally {
            try {
                //关闭输入流
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String encodeImgToBase64(byte[] bytes)  {
      return new String(Base64.encodeBase64(bytes));
    }

}

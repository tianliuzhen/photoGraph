package com.aaa.photo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@Controller
//@RequestMapping("/billController")

public class BillController {
	
	@RequestMapping("/billshow")
	public String billshow(Model model,HttpServletRequest request){
		model.addAttribute("list", "aaa123");
		/* List<Map<String,Object>> list=mapservice.getListMap();
		 System.out.println(list);*/
		/*String [] test=request.getParameterValues("test");
		
		for (int i = 0; i < test.length; i++) {
			System.out.println(test[i]);
		}*/
		System.out.println(11);
		//	String 	imgStr =request.getParameter("base");
		String 	imgStr =	GetImageStr();
			System.out.println(imgStr);
		
    	System.out.print("已经收到了把字节码转化为图片的方法");
    	//对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return "";
        
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imagePath="c:";
            //System.currentTimeMillis()
            String imgFilePath = "C:\\java\\school_mart2.jpg";//新生成的图片
            String contexPath= request.getSession().getServletContext().getRealPath("/zw")+"\\"+"ss437.jpg";
            System.out.println(contexPath);
            OutputStream out = new FileOutputStream(contexPath);
            out.write(b);
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
        }
		
		
		return "zw/billshow";
		
	}
	
    public  String GetImageStr()
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "C:\\java\\xxx.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }

}

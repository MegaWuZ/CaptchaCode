package com.wuzheyi.code;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class CaptcahCode {

    public static String drawImage(HttpServletResponse response){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            builder.append(randomChar());
        }
        String code = builder.toString();

        int width = 120;
        int height = 25;
        //bufferedImage对象,指定图片长度及色彩
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        //获取到Graphic2D对象
        Graphics2D g = bi.createGraphics();
        //设置文字的字体和大小
        Font font = new Font("宋体",Font.PLAIN,20);
        //字体颜色
        Color color = new Color(0,0,0);
        g.setFont(font);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        //开始绘制
        g.clearRect(0,0,width,height);
        //绘制形状
        FontRenderContext fontRenderContext = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code,fontRenderContext);
        //计算文字的坐标和间距
        //计算文件的坐标和间距
        double x = (width - bounds.getWidth())/2;
        double y = (height - bounds.getHeight())/2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code,(int)x,(int)baseY);
        g.dispose();

        try {
            ImageIO.write(bi,"jpg",response.getOutputStream());
            //刷新响应流
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /*
    //算术表达式验证码
    //1 随机数 2 干扰线，背景颜色
    */

    public static String drawImageVerificate(HttpServletResponse response){

        int width = 100,height = 30;
        //在内存中创建图片
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Random random = new Random();
        g.setColor(getRandomColor(240,250));
        g.setFont(new Font("微软雅黑",Font.PLAIN,22));
        g.fillRect(0,0,width,height);
        //干扰线的的绘制
        g.setColor(getRandomColor(180,230));
        for (int i = 0; i < 10 ; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);
            int y1 = random.nextInt(60);
            g.drawLine(x,y,x1,y1);
        }

        //开始对算术验证码表达式的拼接
        int num1 = (int) (Math.random()*10 + 1);
        int num2 = (int) (Math.random()*10 + 1);
        int fuhao = random.nextInt(3);//产生一个[0,2]
        String fuhaostr = "";
        int result = 0;
        switch (fuhao){
            case 0:
                fuhaostr = "+";result = num1 + num2;break;
            case 1:
                fuhaostr = "-";result = num1 - num2;break;
            case 2:
                fuhaostr = "*";result = num1 * num2;break;
        }
        //拼接算术表达式，用户图片显示
        String calc = num1 + " " + fuhaostr + " " + num2 +"= ?";
        //设置随机颜色
        g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
        //绘制表达式
        g.drawString(calc,5,25);
        //结束绘制
        g.dispose();
        try {
            //输出图片到页面
            ImageIO.write(image,"JPEG",response.getOutputStream());
            return String.valueOf(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }



    public static Color getRandomColor(int fc,int bc){
        //利用随机数
        Random random = new Random();
        //随机颜色
        if(fc > 255) fc = 255;
        if(bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);

        return new Color(r,g,b);
    }



    /*
    * 随机产生字母和数字
    * */
    public static char randomChar(){
        String string = "QWERTYUIOPASDFGHJKLZXCVBNM0123456789";
        Random random = new Random();

        return string.charAt(random.nextInt(string.length()));
    }

    public static void main(String[] args) {
        drawImage(null);
    }
}

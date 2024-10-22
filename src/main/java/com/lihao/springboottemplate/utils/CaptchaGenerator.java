package com.lihao.springboottemplate.utils;

import jakarta.servlet.http.HttpSession;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class CaptchaGenerator {

    public static String generateCaptcha(HttpSession session) {
        int width = 160;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // 生成随机验证码
        String captcha = String.valueOf(new Random().nextInt(9999));
        session.setAttribute("captcha", captcha);  // 将验证码存储在会话中

        // 绘制验证码图像
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.drawString(captcha, 20, 40);
        g2d.dispose();

        // 将图像转为字节数组
        ByteArrayOutputStream base = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", base);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(base.toByteArray());  // 返回图像的Base64字符串
    }
}

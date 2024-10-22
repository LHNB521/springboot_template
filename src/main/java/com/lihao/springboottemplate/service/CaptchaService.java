package com.lihao.springboottemplate.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CaptchaService {
    private final ConcurrentHashMap<String, String> captchaStore = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public String generateCaptcha() {
        int width = 140;
        int height = 50;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        String captcha = String.valueOf(random.nextInt(9999)); // 生成4位数字验证码
        captchaStore.put("captcha", captcha);

        // 启动一个线程，设置过期时间
        new Thread(() -> {
            try {
                Thread.sleep(60 * 1000); // 1分钟后过期
                captchaStore.remove("captcha");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

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

    public boolean validateCaptcha(String captcha) {
        String storedCaptcha = captchaStore.get("captcha");
        return storedCaptcha != null && storedCaptcha.equals(captcha);
    }
}

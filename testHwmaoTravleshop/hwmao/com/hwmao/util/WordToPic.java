/**
 * 
 */
package com.hwmao.util;

import java.awt.AlphaComposite;  
import java.awt.Color;  
import java.awt.Font;  
import java.awt.FontMetrics;  
import java.awt.Graphics2D;  
import java.awt.RenderingHints;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import javax.imageio.ImageIO;  
import sun.swing.*;  

/**
 * @author Administrator
 *
 */
public class WordToPic {  
	  
    //  
    public static int getLength(String text) {  
        int length = 0;  
        for (int i = 0; i < text.length(); i++) {  
            if (new String(text.charAt(i) + "").getBytes().length > 1) {  
                length += 2;  
            } else {  
                length += 1;  
            }  
        }  
        return length / 2;  
    }  
  
    public static String TextToPic(String text, int width, int height,int fontSize) {  
        try {  
            String filepath = "D://" + getDate() + ".png";  
            File file = new File(filepath);  
            System.out.println("topic=" + text);  
            Font font = new Font("微软雅黑", Font.BOLD, fontSize);  
            BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);  
            Graphics2D g2 = (Graphics2D) bi.getGraphics();  
            g2.setBackground(null);  
            g2.clearRect(0, 0, width, height);  
            g2.setFont(font);  
            g2.setPaint(Color.BLACK);  
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,0.3f));  
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);  
            printString(g2, text, (width - (text.length() * fontSize)) / 2 + 0,(height - fontSize) / 2 + 40, fontSize);  
            g2.dispose();  
            ImageIO.write(bi, "png", file);  
            return "image" + getDate() + ".png";  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
  
    private static void printString(Graphics2D g2d, String str, int x, int y,int fontSize) {  
        FontMetrics metrics = SwingUtilities2.getFontMetrics(null,g2d.getFont());  
        for (char ca : str.toCharArray()) {  
            int px = metrics.stringWidth("" + ca);  
            g2d.drawString("" + ca, x + (fontSize - px) / 2, y);  
            x += fontSize;  
        }  
    }  
  
    public static String getDate() {  
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");  
        return formatter.format(new Date());  
    }  
  
    public static void main(String[] args) throws IOException {  
        TextToPic("中文生成图片", 500, 100, 50);  
    }  
  
}  
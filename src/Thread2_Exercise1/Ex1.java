/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread2_Exercise1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 *
 * @author LukaszKrawczyk
 */
public class Ex1 implements Runnable{
    
    private final String url;
    private long sum;
   
    public Ex1(String url){
            this.url = url;
        }
    
    protected byte[] getBytesFromUrl(String url) {
       
    
    ByteArrayOutputStream bis = new ByteArrayOutputStream();
        try {
        InputStream is = new URL(url).openStream();
        byte[] bytebuff = new byte[4096];
        int read;
        while ((read = is.read(bytebuff)) > 0) {
        bis.write(bytebuff, 0, read);
        }
        } catch (IOException ex) {System.out.println(ex);}
        return bis.toByteArray();
    }

    public long getSum() {
        return sum;
    }

    @Override
    public void run() {
        byte[] bytes = getBytesFromUrl(this.url);
        
        this.sum = 0;
        for (int i = 0; i < bytes.length; i++) {
            this.sum += bytes[i];
        }
        System.out.println(sum);
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Avilable Processors: " + Runtime.getRuntime().availableProcessors());
        
        Ex1 p1 = new Ex1("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/fronter_big-logo.png");
        Ex1 p2 = new Ex1("https://fronter.com/cphbusiness/design_images/images.php/Classic/login/folder-image-transp.png");
        Ex1 p3 = new Ex1("https://fronter.com/volY12-cache/cache/img/design_images/Classic/other_images/button_bg.png");
        
        long start = System.nanoTime();
        p1.run();
        p2.run();
        p3.run();
        long end = System.nanoTime();
        System.out.println("Time Squental: " + (end - start));
        
        Thread a1Thread = new Thread(p1);
        Thread a2Thread = new Thread(p2);
        Thread a3Thread = new Thread(p3);
        
        long start2 = System.nanoTime();
        a1Thread.start();
        a2Thread.start();
        a3Thread.start();
        
        a1Thread.join();
        a2Thread.join();
        a3Thread.join();
        long end2 = System.nanoTime();
        
        
        System.out.println("Time Normal: " + (end2 - start2));
        long sum = (p1.getSum() + p2.getSum() + p3.getSum());
        System.out.println(sum);
        
    }
    
    
    
    
}

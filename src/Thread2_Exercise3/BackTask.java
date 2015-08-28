/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread2_Exercise3;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author LukaszKrawczyk
 */
public class BackTask implements Runnable{
    private final List<String> lines;
    
    
    
    public BackTask(List<String> lines){
        this.lines = lines;
    }

    @Override
    public void run() {
        while (true) {          
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                Logger.getLogger(BackTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            String userDir = new JFileChooser().getFileSystemView().getDefaultDirectory().toString();
            
            try {
                FileWriter writer = new FileWriter("backup.txt", false);
                PrintWriter out = new PrintWriter(writer);
                for (String line : lines) {
                    out.println(line);
                }
                
                out.close(); 
            } catch (Exception e) {
            }
        }
        
    }
    
    
    
}

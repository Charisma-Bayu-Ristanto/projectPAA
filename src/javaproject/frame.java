/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;



import javax.swing.JFrame;

/**
 *
 * @author bayuristanto
 */


public class frame extends JFrame {
    
    
    
    
    public frame(){
        
        setTitle("hideandseek");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().add(new hideandseek());
            setVisible(true);
    }
    
}

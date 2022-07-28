/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytetris;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author HP
 */
public class MyTetris extends JFrame{
    public JLabel score;
    
    public MyTetris(){
        score=new JLabel("score : 0");
        setFont(new Font("Serif",Font.BOLD,25));
        add(score,BorderLayout.NORTH);
        add(new Game(this));
        
        setTitle("Tetrise");
        setSize(500,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(() ->{
            MyTetris tetris=new MyTetris();
            tetris.setVisible(true);
        }
        );
        
    }

    /**
     * @return the score
     */
    public JLabel getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(JLabel score) {
        this.score = score;
    }
    
}

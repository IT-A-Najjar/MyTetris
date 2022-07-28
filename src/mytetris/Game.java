/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytetris;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static javafx.scene.paint.Color.color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import mytetris.shape.Quartet;
/**
 *
 * @author HP
 */
public class Game extends JPanel{
    
    private final int GRID_WIDTH=15;
    private final int GRID_HEIGHT=15;
    private Quartet[] board;
    private shape piece;
    private int x,y;
    private Timer timer;
    private int tempX,tempY;
    private JLabel score;
    private int completLines;
    
    public Game(MyTetris tetris){
        setFocusable(true);
        board=new Quartet[GRID_WIDTH*GRID_HEIGHT];
        score=tetris.getScore();
        initBoard();
        piece=new shape();
        piece.set_RandomShape();
        addKeyListener(new mykays());
        tempX=GRID_WIDTH/2;
        tempY=GRID_HEIGHT - 1 + piece.get_minY() ;
        
        timer=new Timer(300,new gTimer());
        timer.start();
    }
    
    private void removeLines(){
        int num=0;
        boolean flag;
        for(int i=0 ; i< GRID_HEIGHT ; i++){
            flag=true;
            for(int j=0 ; j< GRID_WIDTH ; j++){
                if(cellAt(j,i)==Quartet.Noshape){
                    flag=false;
                    break;
                }
            }
            if(flag){
                num++;
                for(int u=i ; u<GRID_HEIGHT-1 ; u++){
                    for(int j=0 ; j<GRID_WIDTH ; j++){
                        board[(u*GRID_WIDTH)+j]=cellAt(j,u+1);
                    }
                }
            }
            if(num>0){
                completLines+=num*15;
                score.setText("Score : "+ String.valueOf(completLines));
                num=0;
            }
        }
    }
    
    class mykays extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            if(key==KeyEvent.VK_LEFT) checkMove(piece,tempX-1,tempY);
            if(key==KeyEvent.VK_RIGHT)checkMove(piece,tempX+1,tempY);
            if(key==KeyEvent.VK_UP)checkMove(piece.rotate_Left(),tempX,tempY);
            if(key==KeyEvent.VK_DOWN)checkMove(piece.rotate_Right(),tempX,tempY);
            if(key==KeyEvent.VK_SPACE)checkMove(piece,tempX,tempY-2);
        }
    }
    
    class gTimer implements ActionListener{
       
        @Override
        public void actionPerformed(ActionEvent e) {
//            pieceMove();
//            tempY--;
//            repaint();
              if(!checkMove(piece,tempX,tempY-1)){
                  pieceBoarid();
                  removeLines();
                  newPiece();
              }
        }
    }
    
    private void pieceBoarid(){
        int x,y;
//        initBoard();
        for(int i=0 ; i<4 ; i++){
            x= tempX+ piece.get_x(i);
            y= tempY- piece.get_y(i);
            board[(y*GRID_WIDTH) + x] = piece.getShape();
        }
        
    }
    
    private void newPiece(){
        piece.set_RandomShape();
        tempX=GRID_WIDTH/2;
        tempY=GRID_HEIGHT - 1 + piece.get_minY() ;
        
        if(!checkMove(piece,tempX,tempY)){
            score.setText("Game Over  ::  your score :" +String.valueOf(completLines));
            timer.stop();
        }
    }
    
    private void initBoard(){
        for(int i=0 ; i< GRID_WIDTH*GRID_HEIGHT ; i++){
            board[i]=Quartet.Noshape;
        }
    }
    private int cellHeight(){
        return (int) getSize().getHeight()/GRID_HEIGHT;
    }
    private int cellWidght(){
        return (int) getSize().getWidth()/GRID_WIDTH;
    }
    
    private Quartet cellAt(int x, int y){
        return board[(y*GRID_WIDTH) + x];
    }
    
    private boolean checkMove( shape newpiece,int newx,int newy){
        for(int i=0 ;i<4 ; i++){
            x= newx+ newpiece.get_x(i);
            y= newy- newpiece.get_y(i);
            
            if(x<0||x>=GRID_WIDTH||y<0||y>=GRID_HEIGHT)
                return false;
            
            if(cellAt(x,y)!=Quartet.Noshape)
                return false;
        }
        piece=newpiece;
        tempX=newx;
        tempY=newy;
        repaint();
        return true;
    }
    
    private void drawCell(Graphics g,int x, int y, Quartet Shape){
        
        Color sColors[]={
          Color.green , Color.BLUE , Color.RED , Color.ORANGE , Color.MAGENTA , Color.PINK , Color.yellow.darker() , Color.gray
        };
        Color Shape_Color=sColors[Shape.ordinal()];
        
        g.setColor(Shape_Color);
        g.fillRect(x+1, y+1, cellWidght()-2, cellHeight()-2);
        
    }
    
    
    
    public void paint(Graphics g){
        super.paint(g);
        
        for (int i=0 ; i<GRID_HEIGHT ; i++){
            for(int j=0 ;j<GRID_WIDTH ; j++){
                Quartet shape=cellAt(j, GRID_HEIGHT-i-1);
                if(shape !=Quartet.Noshape)
                    drawCell(g,j*cellWidght(),i*cellHeight(),shape);
            }
        }
        
        for(int i=0 ; i<4; i++){
            x= tempX+ piece.get_x(i);
            y= tempY- piece.get_y(i);
            drawCell(g,(x)*cellWidght(),(GRID_HEIGHT -y -1)*cellHeight(),piece.getShape());
        }
//        
    }
    
    
}

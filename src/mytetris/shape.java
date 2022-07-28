/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytetris;

import java.util.Random;

/**
 *
 * @author HP
 */
public class shape {
    protected enum Quartet {Noshape,Zshape,Sshape,Ishape,Tshape,Oshape,Lshape,Jshape};
    private Quartet piece;
    private int coords[][];
    private int coordsTable[][][];
    
    public shape(){
        init();
    }
    
    private void init(){
        coords=new int[4][2];
        coordsTable=new int[][][]{
            {{0,0},{0,0},{0,0},{0,0}},  //N0
            {{0,-1},{0,0},{-1,0},{-1,1}},  //Z
            {{0,-1},{0,0},{1,0},{1,1}},  //S
            {{0,-1},{0,0},{0,1},{0,2}},  //I
            {{-1,0},{0,0},{1,0},{0,-1}},  //T
            {{0,0},{1,0},{1,1},{0,1}},  //O
            {{-1,-1},{0,-1},{0,0},{0,1}},  //L
            {{1,-1},{0,-1},{0,0},{0,1}}   //J
        };
        setShape(Quartet.Noshape);
    }
    
    public void set_RandomShape(){
        Random rand=new Random();
        
        int s=rand.nextInt(7)+1;
        Quartet[] values=Quartet.values();
        setShape(values[s]);
    }
    
    public int get_x(int index){
        return coords[index][0];
    }
    public int get_y(int index){
        return coords[index][1];
    }
    
    public void set_x(int index,int value){
        coords[index][0]=value;
    }
    public void set_y(int index,int value){
        coords[index][1]=value;
    }
    
    public shape rotate_Left(){
        if(this.piece==Quartet.Oshape)
            return this;
        else{
            shape temp_shape=new shape();
            temp_shape.piece=this.piece;
            for(int i=0;i<4;i++){
                temp_shape.set_x(i, this.get_y(i));
                temp_shape.set_y(i, -this.get_x(i));
            }
            return temp_shape;
        }
    }
    
    public shape rotate_Right(){
        if(this.piece==Quartet.Oshape)
            return this;
        else{
            shape temp_shape=new shape();
            temp_shape.piece=this.piece;
            for(int i=0;i<4;i++){
                temp_shape.set_x(i, -this.get_y(i));
                temp_shape.set_y(i, this.get_x(i));
            }
            return temp_shape;
        }
    }
    
    public Quartet getShape(){
        return piece;
    }
    
    public int get_minY(){
        int temp = coords[0][1];
        for(int i=1 ; i<4 ; i++){
            if(coords[i][1]<temp)
                temp=coords[i][1];
        }
        return temp;
    }
    
    protected void setShape(Quartet shape){
        for(int i=0;i<4;i++){
            for(int j=0;j<2;j++){
                coords[i][j]=coordsTable[shape.ordinal()][i][j];
            }
        }
        piece=shape;
    
    }
    
}

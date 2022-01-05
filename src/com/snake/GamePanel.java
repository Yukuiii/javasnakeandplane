package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int lenth;
    int[] snakeX = new int[600];
    int[] snakeY = new int[500];
    String fx = "R";
    boolean isStart = false;
    int foodx;
    int foody;
    Random random = new Random();
    boolean isFail = false;
    int score;
    Timer timer = new Timer(100, this);
    //构造方法
    public GamePanel(){
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }
    //初始化方法
    public void init(){
        lenth = 3;
        snakeX[0] = 100; snakeY[0] = 100;
        snakeX[1] = 75; snakeY[1] = 100;
        snakeX[2] = 50; snakeY[2] = 100;

        foodx = 25 + 25* random.nextInt(34);
        foody = 75 + 25* random.nextInt(24);

        score = 0;
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.fillRect(25,75,850,600);
        g.fillRect(25,10,850,50);
        if (fx.equals("R")){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("L")){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("U")){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if (fx.equals("D")){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }
        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }
        Data.food.paintIcon(this,g,foodx,foody);
        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度 " + lenth,750,35);
        g.drawString("分数 " + score,750,50);

        //游戏提示
        if (isStart==false){
            g.setColor(Color.white);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始游戏!",300,300);
        }
        //失败判断
        if (isFail){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("失败, 按下空格重新开始",200,300);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘监听事件
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode==KeyEvent.VK_SPACE){
            if (isFail){
                isFail = false;
                init();
            }else {
                isStart = !isStart;
            }
            repaint();
        }

        if (keyCode==KeyEvent.VK_LEFT){
            fx = "L";
        }else if (keyCode==KeyEvent.VK_RIGHT){
            fx = "R";
        }else if (keyCode==KeyEvent.VK_UP){
            fx = "U";
        }else if (keyCode==KeyEvent.VK_DOWN){
            fx = "D";
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //定时执行的操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFail==false){
            for (int i = lenth-1; i > 0; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }
            if (fx.equals("R")){
                snakeX[0] = snakeX[0]+25;
                if (snakeX[0]>850) snakeX[0] = 25;
            }else if (fx.equals("L")){
                snakeX[0] = snakeX[0]-25;
                if (snakeX[0]<25) snakeX[0] = 850;
            }else if (fx.equals("U")){
                snakeY[0] = snakeY[0]-25;
                if (snakeY[0]<75) snakeY[0] = 650;
            }else if (fx.equals("D")){
                snakeY[0] = snakeY[0]+25;
                if (snakeY[0]>650) snakeY[0] = 75;
            }

            if (snakeX[0]==foodx && snakeY[0]==foody){
                lenth++;
                score = score + 10;
                foodx = 25 + 25* random.nextInt(34);
                foody = 75 + 25* random.nextInt(24);
            }

            for (int i = 1; i < lenth; i++) {
                if (snakeX[i]==snakeX[0] && snakeY[i]==snakeY[0] ){
                    isFail = true;
                }
            }
            repaint();
        }

    }

}

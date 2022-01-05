package com.plane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements MouseMotionListener, MouseListener {
    String backPath = "/statics/background_1.gif";    //����ͼƬ
    Myplane myplane;
    ArrayList<EnemyPlane> enemyPlanes;//�л�����
    ArrayList<MyBullet> myBullets;//�һ��ӵ�����
    ArrayList<EnemyBullet> enemyBullets;    //�����ӵ�����
    int enemyNum = 30;
    Boos_1 boos_1;
    Boos_2 boss_2;
    RedHp redHp;
    boolean isBoosDie = false;//�ж�Boos�Ƿ�����
    boolean isBoos02Die = false;//�ж�Boos02�Ƿ�����
    boolean isBoos03Die = false;//�ж�Boos03�Ƿ�����
    int myPlaneLefe = 200;//�һ�����
    int score = 0;

    public GamePanel() {
        myplane = new Myplane();
        addMouseMotionListener(this);
        addMouseListener(this);
        enemyPlanes = new ArrayList<EnemyPlane>();
        myBullets = new ArrayList<MyBullet>();
        enemyBullets = new ArrayList<EnemyBullet>();
        boos_1 = new Boos_1();
        boss_2 = new Boos_2();
        redHp = new RedHp();
        for (int i = 0; i < enemyNum; i++) {
            enemyPlanes.add(new EnemyPlane());
        }

    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);//����
        drawback(g);//������
        drawmyplane(g);
        drawenemyplane(g);
        drawMybullets(g);
        drawEnemyBullets(g);
        drawScoreAndLife(g);
        drawScoreAndLife(g);
        boosShowHint(g);
        drawBoosLife(g);
        drawBoos(g);
        drawBoos_2(g);
        drawRedHp(g);
        drawscore(g);
        drawtitle(g);
    }

    private void drawtitle(Graphics g) {
        if(isBoosDie&&PlaneThread.passCount==2&&TimeThread.time2>0){
            g.setColor(Color.WHITE);
            g.setFont(new Font("����", Font.PLAIN, 26));
            g.drawString("�ڶ��ؼ������٣�����", 150, 200);
        }


    }

    public void drawscore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("����", Font.PLAIN, 18));
        g.drawString("���֣�", 10, 20);
        g.drawString(String.valueOf(score),70,20);
    }


    //��Ѫ��
    private void drawRedHp(Graphics g) {
        g.drawImage(new ImageIcon(RedHp.class.getResource(redHp.getPath())).getImage(), redHp.getX(), redHp.getY(), redHp.getWidth(), redHp.getHeight(), this);

    }


    //��Boss_2

    public void drawBoos_2(Graphics g) {
        if (TimeThread.time < 0 && isBoos02Die == false && PlaneThread.passCount == 2 && TimeThread.time2 < 0) {
            g.drawImage(new ImageIcon(Boos_2.class.getResource(boss_2.getPath())).getImage(), boss_2.getX(), boss_2.getY(), boss_2.getWidth(), boss_2.getHeight(), this);
        }
    }

    /*
     * ��Boos_1
     */
    public void drawBoos(Graphics g) {
        //ʱ��Ϊ0ʱ BOOS����
        if (TimeThread.time < 0 && isBoosDie == false && PlaneThread.passCount == 1) {
            g.drawImage(new ImageIcon(Boos_1.class.getResource(boos_1.getPath())).getImage(), boos_1.getX(), boos_1.getY(), boos_1.getWidth(), boos_1.getHeight(), this);
        }
    }

    /*
     * �� Boos����ֵ�ķ���
     */
    public void drawBoosLife(Graphics g) {
        //ʱ��Ϊ0ʱ BOOS����
        if (TimeThread.time < 0 && isBoosDie == false && PlaneThread.passCount == 1) {
            g.setColor(Color.RED);
            g.setFont(new Font("����", Font.PLAIN, 18));
            g.drawString("B", 22, 50);
            g.drawString("O", 22, 70);
            g.drawString("O", 22, 90);
            g.drawString("S", 22, 110);
            g.drawRect(23, 120, 6, boos_1.getLeft());
            g.fillRect(23, 120, 6, boos_1.getLeft());
        }
        //��Boss_2��Ѫ��
        if (TimeThread.time < 0 && isBoos02Die == false && PlaneThread.passCount == 2 && TimeThread.time2 < 0) {
            g.setColor(Color.RED);
            g.setFont(new Font("����", Font.PLAIN, 18));
            g.drawString("B", 22, 50);
            g.drawString("O", 22, 70);
            g.drawString("O", 22, 90);
            g.drawString("S", 22, 110);
            g.drawRect(23, 120, 6, boss_2.getLeft());
            g.fillRect(23, 120, 6, boss_2.getLeft());
        }

    }

    /*
     * Boos������ʾ
     */
    public void boosShowHint(Graphics g) {
        //5  9
        if (TimeThread.time > 5 && TimeThread.time < 9) {
            g.setFont(new Font("����", Font.PLAIN, 32));
            g.setColor(Color.WHITE);
            g.drawString("BOOS������Ϯ!!!", 150, 200);
        }

    }

    /*
     * �� ���ֺ�����ֵ�ķ���
     */
    public void drawScoreAndLife(Graphics g) {
        if (myPlaneLefe > 100)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.RED);
        g.setFont(new Font("����", Font.PLAIN, 18));
        //g.drawString("����:"+score, GameFrame.frameWidth-100, 30);
        g.drawString("��", PlaneFrame.WIDTH - 30, 50);
        g.drawString("��", PlaneFrame.WIDTH - 30, 70);
        g.drawRect(PlaneFrame.WIDTH - 27, 80, 10, 200);
        g.fillRect(PlaneFrame.WIDTH - 27, 80, 10, myPlaneLefe);
    }

    /*
     * �����ӵ�
     */
    public void drawEnemyBullets(Graphics g) {
        for (int i = 0; i < enemyBullets.size(); i++) {
            EnemyBullet enemyBullet = enemyBullets.get(i);
            Image img = new ImageIcon(
                    GamePanel.class.getResource(enemyBullet.getPath())
            ).getImage();
            g.drawImage(img, enemyBullet.getX(), enemyBullet.getY(),
                    enemyBullet.getWidth(), enemyBullet.getHeight(), this);
        }
    }

    public void drawMybullets(Graphics g) {//���һ��ӵ�
        for (int i = 0; i < myBullets.size(); i++) {
            MyBullet myBullet = myBullets.get(i);//ȡ��ǰ�һ��ӵ�
            g.drawImage(new ImageIcon(MyBullet.class.getResource(myBullet.getPath())).getImage(),
                    myBullet.getX(),
                    myBullet.getY(),
                    myBullet.getWidth(),
                    myBullet.getHeight(),
                    this);
        }
    }

    public void drawenemyplane(Graphics g) {//���л�
        for (int i = 0; i < enemyNum; i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);//ȡ��ǰ�л�
            g.drawImage(new ImageIcon(EnemyPlane.class.getResource(enemyPlane.getPath())).getImage(),
                    enemyPlane.getX(),
                    enemyPlane.getY(),
                    enemyPlane.getWidth(),
                    enemyPlane.getHeigth(),
                    this);
        }
    }

    public void drawmyplane(Graphics g) {//���һ�
        g.drawImage(new ImageIcon(Myplane.class.getResource(myplane.getPath())).getImage(),
                myplane.getX(),
                myplane.getY(),
                myplane.getWidth(),
                myplane.getHeigth(),
                this);
    }

    public void drawback(Graphics g) {//������
        g.drawImage(new ImageIcon(GamePanel.class.getResource(backPath)).getImage(), 0, 0, PlaneFrame.WIDTH, PlaneFrame.HEIGHT, this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        myplane.setX(e.getX() - 20);
        myplane.setY(e.getY() - 43);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        myplane.setX(e.getX() - 20);
        myplane.setY(e.getY() - 43);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        myplane.setFire(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        myplane.setFire(false);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}



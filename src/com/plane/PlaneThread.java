package com.plane;

import java.util.ArrayList;
import java.util.Random;

//ϵͳ���߳�
public class PlaneThread extends Thread {

    private GamePanel gamePanel;
    public static Boolean isEffec = true;//Ϊ��������ײ ʧѪ����
    public static int passCount = 1;//��ǰ��һ��
    public static int Boss_1time = 0;
    public static boolean Isfail = true;

    public PlaneThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int count = 0;
        System.out.println("�߳̽�����");
        while (Isfail) {
            enemyPlanemove();//�л��ƶ�
            if (count++ % 20 == 0)
                addMyPlaneBullet();//����һ��ӵ�
            myBulletMove();
            removeBullet();
            addEnemyBullet();
            callEnemyBulletsMove();
            removeEnemyBullets();
            boos1Move();
            boss2Move();
            MyplanehitEnemyplane();
            MyBullethitEnemyplane();
            MyBullethitBoss1();
            MyBullethiBoss2();
            MyplanehitBoss1();
            isBoosDie();
            isBoss_2();
            MyBulletsandenemmybullet();
            removeenemybullet();
            EnemyPlanehitMyplane();
            RedHpmove();
            GetRedHp();
            gamePanel.repaint();
            try {
                sleep(10);
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

    }

    public void GetRedHp() {
        Myplane myplane = gamePanel.myplane;
        RedHp redHp = gamePanel.redHp;
        Random random = new Random();
        if (isHit(redHp.getX(), redHp.getY(), redHp.getWidth(), redHp.getHeight(), myplane.getX(), myplane.getY(), myplane.getWidth(), myplane.getHeigth())) {
            if(gamePanel.myPlaneLefe<=200){
                gamePanel.myPlaneLefe = gamePanel.myPlaneLefe + 20;
                redHp.setX(random.nextInt(PlaneFrame.WIDTH - 30));
                redHp.setY(-50);
            }


        }

        if(redHp.getY()>PlaneFrame.HEIGHT){
            redHp.setX(random.nextInt(PlaneFrame.WIDTH - 30));
            redHp.setY(-50);
        }
    }

    public void RedHpmove() {
        RedHp redHp = gamePanel.redHp;
        redHp.move();


    }

    //�һ���ײ�л��ӵ�
    public void EnemyPlanehitMyplane() {
        ArrayList<EnemyBullet> enemybullet = gamePanel.enemyBullets;
        Myplane myplane = gamePanel.myplane;
        for (int i = 0; i < enemybullet.size(); i++) {
            EnemyBullet enemyBullet = enemybullet.get(i);
            if (isHit(myplane.getX(), myplane.getY(), myplane.getWidth(), myplane.getHeigth(), enemyBullet.getX(), enemyBullet.getY(), enemyBullet.getWidth(), enemyBullet.getY())) {
                enemybullet.remove(i);
                gamePanel.myPlaneLefe = gamePanel.myPlaneLefe - 10;
            }
        }

    }

    //		Boss_2�ƶ�����
    public void boss2Move() {
        if (passCount == 2 && TimeThread.time < 0 && gamePanel.isBoos02Die == false) {
            Boos_2 boos_2 = gamePanel.boss_2;
            boos_2.move();
        }

    }

    /*
     * �ж�Boos1�Ƿ�����
     */
    public void isBoosDie() {
        Boos_1 boos_1 = gamePanel.boos_1;

        //�ж�Boos����
        if (boos_1.getLeft() < 0 && passCount == 1) {
            //ΪBOOS��ӱ�ըͼƬ
            // for(int i=0;i<100;i++)//���10�ű�ըͼƬ
            // addBoosBomb(boos_1);

            //����Boos����
            gamePanel.isBoosDie = true;
            passCount = 2;
            boos_1.setX(-5000);
            boos_1.setY(-5000);
            gamePanel.score=gamePanel.score+100;
        }
    }

    //	�ж�Boss_2�Ƿ�����
    public void isBoss_2() {
        Boos_2 boss_2 = gamePanel.boss_2;
        if (boss_2.getLeft() < 0 && passCount == 2) {
            gamePanel.isBoos02Die = true;
            passCount = 3;
            boss_2.setX(-5000);
            boss_2.setY(-5000);
            gamePanel.score=gamePanel.score+100;
            new Frame(gamePanel);
        }
    }

    //�һ���ײBOSS1
    public void MyplanehitBoss1() {
        Myplane myplane = gamePanel.myplane;//ȡ�һ�
        Boos_1 boos_1 = gamePanel.boos_1;
        if (isHit(myplane.getX(), myplane.getY(), myplane.getWidth(), myplane.getHeigth(),
                boos_1.getX(), boos_1.getY(), boos_1.getWidth(), boos_1.getHeight())) {
            if (isEffec == true) {
                boos_1.setLeft(boos_1.getLeft() - 100);
                gamePanel.myPlaneLefe = gamePanel.myPlaneLefe - 10;
                isEffec = false;
            }
        }
    }

    /**
     * �һ��ӵ���ײBoss_2
     */
    public void MyBullethiBoss2() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        Boos_2 boos_2 = gamePanel.boss_2;
        for (int i = 0; i < myBullets.size(); i++) {
            MyBullet myBullet = myBullets.get(i);
            if (isHit(myBullet.getX(), myBullet.getY(), myBullet.getWidth(), myBullet.getHeight(), boos_2.getX(), boos_2.getY(), boos_2.getWidth(), boos_2.getHeight())) {
                myBullets.remove(i);
                boos_2.setLeft(boos_2.getLeft() - 100);
                System.out.println(boos_2.left);
            }
        }

    }

    //�һ��ӵ���ײBoss1
    public void MyBullethitBoss1() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;//ȡ�һ��ӵ�����
        Boos_1 boos_1 = gamePanel.boos_1;
        for (int i = 0; i < myBullets.size(); i++) {
            MyBullet myBullet = myBullets.get(i);
            if (isHit(myBullet.getX(), myBullet.getY(), myBullet.getWidth(), myBullet.getHeight(),
                    boos_1.getX(), boos_1.getY(), boos_1.getWidth(), boos_1.getHeight())) {
                System.out.println(PlaneThread.isEffec);
                myBullets.remove(i);
                boos_1.setLeft(boos_1.getLeft() - 20);
                System.out.println(boos_1.left);
            }
        }
    }

    //�һ��ӵ��͵л���ײ
    public void MyBullethitEnemyplane() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;//ȡ�һ��ӵ�����
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;//ȡ�л�����
        for (int i = 0; i < myBullets.size(); i++)
            for (int j = 0; j < enemyPlanes.size(); j++) {
                MyBullet myBullet = myBullets.get(i);
                EnemyPlane enemyPlane = enemyPlanes.get(j);//ȡ��ǰ�л�
                if (isHit(myBullet.getX(), myBullet.getY(), myBullet.getWidth(), myBullet.getHeight(),
                        enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getWidth(), enemyPlane.getHeigth())) {
                    myBullet.setEffect(false);//��Ч�ӵ�
                    Random random = new Random();
                    enemyPlane.setX(random.nextInt(PlaneFrame.WIDTH));
                    enemyPlane.setY(enemyPlane.getY() - PlaneFrame.HEIGHT);
                    gamePanel.score=gamePanel.score+10;
                }
            }
    }


    //�һ����л���ײ
    public void MyplanehitEnemyplane() {
        Myplane myplane = gamePanel.myplane;//ȡ�һ�
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;//ȡ�л�����
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);//ȡ��ǰ�л�
            if (isHit(myplane.getX(), myplane.getY(), myplane.getWidth(), myplane.getHeigth(),
                    enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getWidth(), enemyPlane.getHeigth())) {
                Random random = new Random();
                gamePanel.myPlaneLefe = gamePanel.myPlaneLefe - 10;
                enemyPlane.setY(enemyPlane.getY() - PlaneFrame.HEIGHT);
                enemyPlane.setX(random.nextInt(PlaneFrame.WIDTH));

            }
        }
    }

    //�����κͳ�������ײ
    public Boolean isHit(int x1, int y1, int x1_width, int y1_height,
                         int x2, int y2, int x2_width, int y2_height) {
        if (isHit(x1, y1, x1_width, y1_height,
                x2, y2) || isHit(x1, y1, x1_width, y1_height,
                x2 + x2_width, y2) || isHit(x1, y1, x1_width, y1_height,
                x2, y2 + y2_height) || isHit(x1, y1, x1_width, y1_height,
                x2 + x2_width, y2 + y2_height))
            return true;
        else {
            return false;
        }
    }

    //�����������ײ
    public Boolean isHit(int x1, int y1, int x1_width, int y1_height,
                         int x2, int y2) {
        if (x2 >= x1 && x2 <= x1 + x1_width && y2 >= y1 && y2 <= y1 + y1_height)
            //if(snow_x>=my_x&&snow_x<=my_x+my_width&&snow_y>=my_y&&snow_y<=my_y+my_height)
            return true;
        else
            return false;
    }

    /*
     * Boos�ƶ�
     */
    public void boos1Move() {
        //��Boosû����ʱ��С��0ʱ
        //if(TimeThread.time<0)
        if (passCount == 1 && TimeThread.time < 0 && gamePanel.isBoosDie == false) {
            Boos_1 boos_1 = gamePanel.boos_1;
            boos_1.move();
        }
    }

    /*
     * ɾ���л��ӵ�
     */
    public void removeEnemyBullets() {
        //�ȴ������ȡ���һ��ӵ�����
        ArrayList<EnemyBullet> enmeyBullets = gamePanel.enemyBullets;
        //�л��ӵ�����
        for (int i = enmeyBullets.size() - 1; i >= 0; i--) {
            EnemyBullet enemyBullet = enmeyBullets.get(i);
            //�ж�ÿ���ӵ��Ƿ���Ч����Ч�Ϳ���ɾ����
            if (enemyBullet.isEffect() == false) {
                enmeyBullets.remove(i);
            }
        }
    }

    /*
     * �л��ӵ��ƶ�
     */
    public void callEnemyBulletsMove() {
        //ȡ���л��ӵ�����
        ArrayList<EnemyBullet> enemyBullets = gamePanel.enemyBullets;
        for (int i = 0; i < enemyBullets.size(); i++) {
            //ȡ��ÿһ���ӵ�
            EnemyBullet enemyBullet = enemyBullets.get(i);
            //֪ͨÿһ���ӵ��ƶ�
            enemyBullet.move();
        }
    }

    /*
     * ��ӵл��ӵ�
     */
    public void addEnemyBullet() {
        //�л��ӵ�
        ArrayList<EnemyBullet> enemyBullets = gamePanel.enemyBullets;
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            //����л��ǿ���״̬���ͽ��ӵ��ӵ�������
            if (enemyPlane.toFire() == true) {
                enemyBullets.add(
                        new EnemyBullet(enemyPlane.getX() + 22, enemyPlane.getY() + 50));
            }
        }
    }

    /*
     * ɾ���һ��ϳ��ӵ�
     */
    public void removeBullet() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        for (int i = myBullets.size() - 1; i >= 0; i--) {
            MyBullet myBullet = myBullets.get(i);
            //�ж�����ӵ�Ϊ��Ч����ɾ��
            if (myBullet.isEffect() == false)
                myBullets.remove(i);
        }
    }


    //�һ��ӵ��͵л��ӵ���ײ


    public void MyBulletsandenemmybullet() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        ArrayList<EnemyBullet> enemyBullets = gamePanel.enemyBullets;
        for (int i = 0; i < myBullets.size(); i++) {
            for (int j = 0; j < enemyBullets.size(); j++) {
                MyBullet myBullet = myBullets.get(i);
                EnemyBullet enemyBullet = enemyBullets.get(j);
                if (isHit(myBullet.getX(), myBullet.getY(), myBullet.getWidth(), myBullet.getWidth(), enemyBullet.getX(), enemyBullet.getY(), enemyBullet.getWidth(), enemyBullet.getHeight())) {
                    myBullet.setEffect(false);
                    enemyBullet.setEffect(false);

                }

            }
        }
    }


    //ɾ���л��ӵ�


    public void removeenemybullet() {
        ArrayList<EnemyBullet> enemyBullets = gamePanel.enemyBullets;
        for (int i = 0; i < enemyBullets.size(); i++) {
            EnemyBullet enemyBullet = enemyBullets.get(i);
            if (enemyBullet.isEffect == false)
                enemyBullets.remove(i);

        }
    }


    /*
     * �һ��ӵ��ƶ�
     */
    public void myBulletMove() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        for (int i = 0; i < myBullets.size(); i++) {
            //System.out.println("�ӵ��ƶ�");
            MyBullet myBullet = myBullets.get(i);
            myBullet.move();
        }
    }

    /*
     * ����һ��ӵ�
     */
    public void addMyPlaneBullet() {
        //ȡ���һ��ӵ�����
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        //ȡ���һ�
        Myplane myPlane = gamePanel.myplane;
        //������� ���ϼ��ӵ�
        if (myPlane.isFire() == true) {
            myBullets.add(new MyBullet(myPlane.getX(), myPlane.getY()));
        }
    }

    public void enemyPlanemove() {//�л��ƶ�����
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;//ȡ�л�����
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);//��ȡ��ǰ�л�
            enemyPlane.move();
        }
    }
}



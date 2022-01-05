package com.plane;

import java.util.ArrayList;
import java.util.Random;

//系统主线程
public class PlaneThread extends Thread {

    private GamePanel gamePanel;
    public static Boolean isEffec = true;//为避免多次碰撞 失血过多
    public static int passCount = 1;//当前第一关
    public static int Boss_1time = 0;
    public static boolean Isfail = true;

    public PlaneThread(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        int count = 0;
        System.out.println("线程进行中");
        while (Isfail) {
            enemyPlanemove();//敌机移动
            if (count++ % 20 == 0)
                addMyPlaneBullet();//添加我机子弹
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

    //我机碰撞敌机子弹
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

    //		Boss_2移动方法
    public void boss2Move() {
        if (passCount == 2 && TimeThread.time < 0 && gamePanel.isBoos02Die == false) {
            Boos_2 boos_2 = gamePanel.boss_2;
            boos_2.move();
        }

    }

    /*
     * 判断Boos1是否死了
     */
    public void isBoosDie() {
        Boos_1 boos_1 = gamePanel.boos_1;

        //判断Boos已死
        if (boos_1.getLeft() < 0 && passCount == 1) {
            //为BOOS添加爆炸图片
            // for(int i=0;i<100;i++)//添加10张爆炸图片
            // addBoosBomb(boos_1);

            //设置Boos已死
            gamePanel.isBoosDie = true;
            passCount = 2;
            boos_1.setX(-5000);
            boos_1.setY(-5000);
            gamePanel.score=gamePanel.score+100;
        }
    }

    //	判断Boss_2是否死亡
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

    //我机碰撞BOSS1
    public void MyplanehitBoss1() {
        Myplane myplane = gamePanel.myplane;//取我机
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
     * 我机子弹碰撞Boss_2
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

    //我机子弹碰撞Boss1
    public void MyBullethitBoss1() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;//取我机子弹集合
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

    //我机子弹和敌机碰撞
    public void MyBullethitEnemyplane() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;//取我机子弹集合
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;//取敌机数组
        for (int i = 0; i < myBullets.size(); i++)
            for (int j = 0; j < enemyPlanes.size(); j++) {
                MyBullet myBullet = myBullets.get(i);
                EnemyPlane enemyPlane = enemyPlanes.get(j);//取当前敌机
                if (isHit(myBullet.getX(), myBullet.getY(), myBullet.getWidth(), myBullet.getHeight(),
                        enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getWidth(), enemyPlane.getHeigth())) {
                    myBullet.setEffect(false);//无效子弹
                    Random random = new Random();
                    enemyPlane.setX(random.nextInt(PlaneFrame.WIDTH));
                    enemyPlane.setY(enemyPlane.getY() - PlaneFrame.HEIGHT);
                    gamePanel.score=gamePanel.score+10;
                }
            }
    }


    //我机跟敌机碰撞
    public void MyplanehitEnemyplane() {
        Myplane myplane = gamePanel.myplane;//取我机
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;//取敌机数组
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);//取当前敌机
            if (isHit(myplane.getX(), myplane.getY(), myplane.getWidth(), myplane.getHeigth(),
                    enemyPlane.getX(), enemyPlane.getY(), enemyPlane.getWidth(), enemyPlane.getHeigth())) {
                Random random = new Random();
                gamePanel.myPlaneLefe = gamePanel.myPlaneLefe - 10;
                enemyPlane.setY(enemyPlane.getY() - PlaneFrame.HEIGHT);
                enemyPlane.setX(random.nextInt(PlaneFrame.WIDTH));

            }
        }
    }

    //长方形和长方形碰撞
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

    //点跟长方形碰撞
    public Boolean isHit(int x1, int y1, int x1_width, int y1_height,
                         int x2, int y2) {
        if (x2 >= x1 && x2 <= x1 + x1_width && y2 >= y1 && y2 <= y1 + y1_height)
            //if(snow_x>=my_x&&snow_x<=my_x+my_width&&snow_y>=my_y&&snow_y<=my_y+my_height)
            return true;
        else
            return false;
    }

    /*
     * Boos移动
     */
    public void boos1Move() {
        //当Boos没死且时间小于0时
        //if(TimeThread.time<0)
        if (passCount == 1 && TimeThread.time < 0 && gamePanel.isBoosDie == false) {
            Boos_1 boos_1 = gamePanel.boos_1;
            boos_1.move();
        }
    }

    /*
     * 删除敌机子弹
     */
    public void removeEnemyBullets() {
        //先从面板中取出我机子弹集合
        ArrayList<EnemyBullet> enmeyBullets = gamePanel.enemyBullets;
        //敌机子弹集合
        for (int i = enmeyBullets.size() - 1; i >= 0; i--) {
            EnemyBullet enemyBullet = enmeyBullets.get(i);
            //判断每颗子弹是否还有效，无效就可以删掉。
            if (enemyBullet.isEffect() == false) {
                enmeyBullets.remove(i);
            }
        }
    }

    /*
     * 敌机子弹移动
     */
    public void callEnemyBulletsMove() {
        //取出敌机子弹集合
        ArrayList<EnemyBullet> enemyBullets = gamePanel.enemyBullets;
        for (int i = 0; i < enemyBullets.size(); i++) {
            //取出每一颗子弹
            EnemyBullet enemyBullet = enemyBullets.get(i);
            //通知每一个子弹移动
            enemyBullet.move();
        }
    }

    /*
     * 添加敌机子弹
     */
    public void addEnemyBullet() {
        //敌机子弹
        ArrayList<EnemyBullet> enemyBullets = gamePanel.enemyBullets;
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);
            //如果敌机是开火状态，就将子弹加到集合中
            if (enemyPlane.toFire() == true) {
                enemyBullets.add(
                        new EnemyBullet(enemyPlane.getX() + 22, enemyPlane.getY() + 50));
            }
        }
    }

    /*
     * 删除我机废除子弹
     */
    public void removeBullet() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        for (int i = myBullets.size() - 1; i >= 0; i--) {
            MyBullet myBullet = myBullets.get(i);
            //判断如果子弹为无效，则删除
            if (myBullet.isEffect() == false)
                myBullets.remove(i);
        }
    }


    //我机子弹和敌机子弹碰撞


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


    //删除敌机子弹


    public void removeenemybullet() {
        ArrayList<EnemyBullet> enemyBullets = gamePanel.enemyBullets;
        for (int i = 0; i < enemyBullets.size(); i++) {
            EnemyBullet enemyBullet = enemyBullets.get(i);
            if (enemyBullet.isEffect == false)
                enemyBullets.remove(i);

        }
    }


    /*
     * 我机子弹移动
     */
    public void myBulletMove() {
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        for (int i = 0; i < myBullets.size(); i++) {
            //System.out.println("子弹移动");
            MyBullet myBullet = myBullets.get(i);
            myBullet.move();
        }
    }

    /*
     * 添加我机子弹
     */
    public void addMyPlaneBullet() {
        //取出我机子弹集合
        ArrayList<MyBullet> myBullets = gamePanel.myBullets;
        //取出我机
        Myplane myPlane = gamePanel.myplane;
        //如果开火 马上加子弹
        if (myPlane.isFire() == true) {
            myBullets.add(new MyBullet(myPlane.getX(), myPlane.getY()));
        }
    }

    public void enemyPlanemove() {//敌机移动方法
        ArrayList<EnemyPlane> enemyPlanes = gamePanel.enemyPlanes;//取敌机数组
        for (int i = 0; i < enemyPlanes.size(); i++) {
            EnemyPlane enemyPlane = enemyPlanes.get(i);//获取当前敌机
            enemyPlane.move();
        }
    }
}



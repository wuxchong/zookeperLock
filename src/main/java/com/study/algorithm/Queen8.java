package com.study.algorithm;

//八皇后问题，参考：https://www.cnblogs.com/newflydd/p/5091646.html
public class Queen8 {

    private static final int K = 8;
    private static int count = 0;
    private static void putQueenAtRow(int[] chess, int row) {
        if(row == K) {
            count++;
            System.out.println("第 "+ count +" 种解：");
            for(int i = 0; i < K; i++){
                for(int j = 0; j < K; j++) {
                    if(chess[i] == j){
                        System.out.print("0 ");
                    }else
                        System.out.print("+ ");
                }
                System.out.println();
            }
            return;
        }
        int[] chessTemp = chess.clone();
        /**
         * 向这一行的每一个位置尝试排放皇后
         * 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
         */
        for (int i = 0; i < K; i++) {
            //表示棋盘第row行第i列
            chessTemp[row] = i;
            if(isSafety(chessTemp, row, i)) {
                putQueenAtRow(chessTemp, row + 1);
            }
        }

    }

    private static boolean isSafety(int[] chess,int row,int col) {
        int step = 1;
        for(int i = row - 1; i >= 0; i--) {
            if (chess[i] == col) {
                return false;  //在同一列
            }
            if (chess[i] == col - step) {
                return false;  //在同一条反斜线
            }
            if(chess[i] == col + step) {
                return false;  //在同一条斜线
            }
            step++;
        }
        return true;
    }

    public static void main(String args[]){
        Queen8 queen = new Queen8();
        int[] chess = new int[K];
        /**
         * 初始化棋盘，使用一维数组存放棋盘信息
         * chess[n]=X:表示第n行X列有一个皇后
         */
        for(int i = 0; i < K; i++){
            chess[i] = 0;
        }
        putQueenAtRow(chess, 0);
        System.out.println("解决 " + K + "皇后问题，计算结果：" + count);
    }

}

package com.study.algorithm;

//背包问题，参考：https://blog.csdn.net/lanyu_01/article/details/79815801
public class BackPack {
    public static void main(String[] args) {
        BackPack backPack = new BackPack();
        int v = 10, n = 5;
        int[] w = {2, 2, 4, 5, 6};
        int[] vs = {6, 5, 3, 6, 4};
        System.out.println(backPack.ZeroOnePack(v, n, w, vs));
        System.out.println(backPack.ZeroOnePack2(v, n, w, vs));
        System.out.println(backPack.manyPack(v, n, w, vs, new int[]{1, 2, 3, 2, 5}));
        System.out.println(backPack.completePack(v, n, w, vs));
        System.out.println(backPack.completePack2(v, n, w, vs));
    }

    //0-1背包，每类物品最多只能装一次 ，状态转移方程：dp[i][j] = Math.max(dp[i-1][j], (dp[i-1][j-weight[i]]+value[i])'
    // V 背包容量, N 物品种类, weight 物品重量, value 物品价值
    //dp[i - 1][j]表示前i-1件物品装入容量j的背包的价值总和
    //i从1开始取值，因为有dp[i][j] = dp[i - 1][j];
    private String ZeroOnePack(int V, int N, int[] weight, int[] value) {
        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        // 由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
        for(int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //能够装进去
                if(j >= weight[i - 1]){
                    //选择装还是不装
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[N][V];
        System.out.println(maxValue);
        //逆推找出装入背包的所有商品的编号
        int j = V;
        String numStr="";
        for(int i = N; i > 0; i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if(dp[i][j] > dp[i - 1][j]){
                numStr = i + " " + numStr;
                j = j - weight[i - 1];
            }
            if(j==0)
                break;
        }
        return numStr;
    }

    /**
     * 0-1背包的优化解法
     * 思路：
     * 只用一个一维数组记录状态，dp[j]表示容量为j的背包所能装入物品的最大价值
     * 用逆序来实现
     */
    private int ZeroOnePack2(int V,int N,int[] weight,int[] value){
        //动态规划
        int[] dp = new int[V+1];
        for(int i = 1; i < N + 1; i++) {
           //逆序实现
            for (int j = V; j >= weight[i - 1]; j--)
                dp[j] = Math.max(dp[j], dp[j - weight[i - 1]] + value[i - 1]);
        }
        return dp[V];
    }

    //多重背包：每类物品都有个数限制，第i类物品最多可以装num[i]次
    private int manyPack(int V, int N, int[] weight, int[] value, int[] num) {
        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i=1;i<N+1;i++){
            for(int j=1;j<V+1;j++){
                //能够至少装一个进去
                if(j >= weight[i - 1]){
                    //选择装几个还是不装,并且考虑物品的件数限制
                    int maxV = Math.min(num[i - 1],j / weight[i - 1]);//可以放几个
                    for(int k = 0;k < maxV + 1;k++){
                        dp[i][j]=Math.max(dp[i - 1][j],
                                dp[i - 1][j - k * weight[i - 1]] + k * value[i - 1]);
                    }
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][V];
    }

    /** 完全背包：每类物品可以无限次装进包内
     *  思路分析：
     * 01背包问题是在前一个子问题（i-1种物品）的基础上来解决当前问题（i种物品），
     * 向i-1种物品时的背包添加第i种物品；而完全背包问题是在解决当前问题（i种物品）
     * 向i种物品时的背包添加第i种物品。
     * 推公式计算时，f[i][y] = max{f[i-1][y], (f[i][y-weight[i]]+value[i])}，
     * 注意这里当考虑放入一个物品 i 时应当考虑还可能继续放入 i，
     * 因此这里是f[i][y-weight[i]]+value[i], 而不是f[i-1][y-weight[i]]+value[i]。
     */
    private int completePack(int V,int N,int[] weight,int[] value) {
        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        for(int i = 1; i < N + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                //能够装进去
                if(j >= weight[i - 1]){
                    //考虑放入一个物品 i 时应当考虑还可能继续放入 i
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
                }else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[N][V];
    }
    /**
     * 完全背包的第二种解法
     * 思路：
     * 只用一个一维数组记录状态，dp[i]表示容量为i的背包所能装入物品的最大价值
     * 用顺序来实现
     */
    private int completePack2(int V,int N,int[] weight,int[] value) {
        //动态规划
        int[] dp = new int[V + 1];
        for (int i = 1; i < N + 1; i++) {
            //顺序实现
            for (int j = weight[i - 1]; j < V + 1; j++) {
                dp[j] = Math.max(dp[j - weight[i - 1]] + value[i - 1], dp[j]);
            }
        }
        return dp[V];
    }
}

package com.study.algorithm;

import java.util.ArrayList;
import java.util.Collections;

//全排列，剑指offer：https://www.nowcoder.com/questionTerminal/fe6b651b66ae47d7acce78ffdd9a96c7
public class Permutation {
    //通过递归回溯
    public static void main(String[] args) {
        String str = "abc";
        Permutation permutation = new Permutation();
        System.out.println(permutation.Permutation(str));
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> result = new ArrayList<>();
        if(str.length() == 0) {
            return result;
        }
        getPermutation(str.toCharArray(), 0, result);
        Collections.sort(result);
        return result;
    }

    private void getPermutation(char[] ch, int i, ArrayList<String> list) {
        //出口，就是i下标已经移到char数组的末尾的时候，考虑添加这一组字符串进入结果集中
        if(i == ch.length - 1) {
            //判断一下是否重复(即字符串是否已经被添加进list中了)
            if(!list.contains(new String(ch))) {
                list.add(new String(ch));
            }
            return;
        }else {
            for(int j = i; j < ch.length; j++){
                swap(ch, i, j);
                getPermutation(ch, i + 1, list);
                //还原字符串
                swap(ch, i, j);
            }
        }
    }

    private void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }
}

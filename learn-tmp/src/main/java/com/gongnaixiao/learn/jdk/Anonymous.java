package com.gongnaixiao.learn.jdk;

/**
 * Created by xg on 2018/4/17.
 */
public class Anonymous {
    private int n;

    public boolean test() {
        return false;
    }
    public Anonymous(int n) {
        this.n = n;
    }
    public int getN() {
        return n;
    }

    public static void main(String[] args) {
        Anonymous test = new Anonymous(4) {
            @Override
            public boolean test() {
                if(getN() % 2 == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        System.out.println(test.test());
    }
}

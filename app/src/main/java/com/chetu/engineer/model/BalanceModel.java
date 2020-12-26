package com.chetu.engineer.model;

import java.io.Serializable;

/**
 * Created by zyz on 2020/6/21.
 */
public class BalanceModel implements Serializable {
    /**
     * user_balance : 99900.0
     */

    private double user_balance;

    public double getUser_balance() {
        return user_balance;
    }

    public void setUser_balance(double user_balance) {
        this.user_balance = user_balance;
    }

}

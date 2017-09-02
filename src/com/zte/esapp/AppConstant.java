package com.zte.esapp;

/**
 * Created by Administrator on 2017/8/22.
 */

public class AppConstant {

    private static String userId;

    public class PlayMsg{
        public static final int PAUSE_MSG = 260;
        public static final int STOP_MSG = 436;
        public static final int PLAY_MSG = 77;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        AppConstant.userId = userId;
    }
}

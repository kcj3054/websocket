package com.example.socket;

public class Settings {

    private static volatile Settings instance;

    private Settings() {}

//    instance가 있는 경우에는
    private static  Settings getInstance() {
        if(instance == null) {
            synchronized (Settings.class) {
                if( instance == null) {
                    instance = new Settings();
                }
            }

        }
        return instance;
    }
}

package com.spiderrobotman;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Main main = new Main();

    private HashMap<Integer, Integer> clickCounts = new HashMap<>();
    private boolean isDebug = false;

    public static void main(String[] args) {
        try {
            GlobalScreen.registerNativeHook();
        }
        catch (NativeHookException ex) {
            System.err.println("There was a problem registering the native hook.");
            System.err.println(ex.getMessage());

            System.exit(1);
        }

        MouseListen mouseListen = new MouseListen();

        // Add the appropriate listeners.
        GlobalScreen.addNativeMouseListener(mouseListen);

        // Get the logger for "org.jnativehook" and set the level to warning.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.SEVERE);

        logger.setUseParentHandlers(false);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            String next;
            if((next = scanner.next()) != null) {
                if(next.toLowerCase().equals("debug")) {
                    Main.getInstance().setDebug(!Main.getInstance().isDebug());
                    System.out.println("Debug is now: " + Main.getInstance().isDebug());
                }

                if(next.toLowerCase().equals("list")) {
                    HashMap<Integer, Integer> list = Main.getInstance().getClickCounts();
                    if(list.isEmpty()) {
                        System.out.println("No clicks have been tracked.");
                    }
                    for (Integer button : list.keySet()) {
                        int count = list.get(button);
                        System.out.println("Mouse Button [" + button + "] was clicked " + count + " times.");
                    }
                }

                if(next.toLowerCase().equals("clear")) {
                    Main.getInstance().getClickCounts().clear();
                    System.out.println("Click counts have been cleared.");
                }
            }
        }
    }

    public static Main getInstance() {
        return main;
    }

    public HashMap<Integer, Integer> getClickCounts() {
        return this.clickCounts;
    }

    public boolean isDebug() {
        return isDebug;
    }

    public void setDebug(boolean debug) {
        this.isDebug = debug;
    }
}
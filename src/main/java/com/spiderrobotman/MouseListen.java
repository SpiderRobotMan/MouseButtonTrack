package com.spiderrobotman;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseListen implements NativeMouseInputListener {

    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {}

    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {}

    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        int button = nativeMouseEvent.getButton();
        int count = Main.getInstance().getClickCounts().getOrDefault(button, 0) + 1;
        Main.getInstance().getClickCounts().put(button, count);
        if(Main.getInstance().isDebug()) {
            System.out.println("Mouse button [" + button + "] released, count click. (" + count + ")");
        }
    }

    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {}

    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {}
}

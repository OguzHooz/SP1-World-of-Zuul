package org.CrabGame;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.BitSet;

public class Input {
    /**
     * Bitset which registers if any {@link KeyCode} keeps being pressed or if it is released.
     *
     * Note: ordinal may be not appropriate, but we don't have a method in {@link KeyCode} to get the int code, so it'll have to do
     */
    private BitSet keyboardBitSet = new BitSet();

    // -------------------------------------------------
    // default key codes
    // -------------------------------------------------

    private KeyCode upKey = KeyCode.W;
    private KeyCode downKey = KeyCode.S;
    private KeyCode leftKey = KeyCode.A;
    private KeyCode rightKey = KeyCode.D;

    Scene scene;

    public Input( Scene scene) {
        this.scene = scene;
    }

    public void addListeners() {

        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);

    }

    public void removeListeners() {

        scene.removeEventFilter(KeyEvent.KEY_PRESSED, keyPressedEventHandler);
        scene.removeEventFilter(KeyEvent.KEY_RELEASED, keyReleasedEventHandler);

    }

    /**
     * "Key Pressed" handler for all input events: register pressed key in the bitset
     */
    private EventHandler<KeyEvent> keyPressedEventHandler = event -> {
        // register key down
        keyboardBitSet.set(event.getCode().ordinal(), true);
    };

    /**
     * "Key Released" handler for all input events: unregister released key in the bitset
     */
    private EventHandler<KeyEvent> keyReleasedEventHandler = event -> {
        // register key up
        keyboardBitSet.set(event.getCode().ordinal(), false);
    };


    // -------------------------------------------------
    // Evaluate bitset of pressed keys and return the player input.
    // If direction and its opposite direction are pressed simultaneously, then the direction isn't handled.
    // -------------------------------------------------

    public boolean isMoveUp() {
        return keyboardBitSet.get( upKey.ordinal()) && !keyboardBitSet.get( downKey.ordinal());
    }

    public boolean isMoveDown() {
        return keyboardBitSet.get( downKey.ordinal()) && !keyboardBitSet.get( upKey.ordinal());
    }

    public boolean isMoveLeft() {
        return keyboardBitSet.get( leftKey.ordinal()) && !keyboardBitSet.get( rightKey.ordinal());
    }

    public boolean isMoveRight() {
        return keyboardBitSet.get( rightKey.ordinal()) && !keyboardBitSet.get( leftKey.ordinal());
    }


    // -------------------------------------------------
    // getters & setters for key code modification (more players, user key settings change)
    // -------------------------------------------------

}

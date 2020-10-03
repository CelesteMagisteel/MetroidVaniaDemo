package net.celestemagisteel;

import net.celestemagisteel.engine.ControlHandler;
import net.celestemagisteel.engine.GameState;
import net.celestemagisteel.engine.ui.GameDisplayPanel;
import net.celestemagisteel.entity.Player;
import net.celestemagisteel.events.EntityFireProjectileEvent;
import net.celestemagisteel.events.EntityMoveEvent;
import net.celestemagisteel.handlers.EventManager;
import net.celestemagisteel.handlers.Handler;
import net.celestemagisteel.map.TileMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import static java.awt.event.KeyEvent.*;
import static net.celestemagisteel.Game.SPRITE_SIZE;
import static net.celestemagisteel.map.TileMap.MAP_HEIGHT;
import static net.celestemagisteel.map.TileMap.MAP_WIDTH;

public class AwtStart extends TimerTask {

    private static JFrame frame;
    private static GameDisplayPanel displayPanel;
    private static GameState state;
    private static ControlHandler controlHandler;

    private static void registerHandlers() {
        EventManager.registerHandler(new Handler<>(EntityMoveEvent.class));
        EventManager.registerHandler(new Handler<>(EntityFireProjectileEvent.class));
    }

    public static void main(String[] args) throws IOException {
        registerHandlers();
        state = new GameState(TileMap.generateBasicTileMap(), new Player("player", 20, 1, TileMap.MAP_HEIGHT - 2), new ArrayList<>());
        controlHandler = new ControlHandler(state.getPlayer());
        EventManager.registerEvents(state);
        frame = new JFrame("My Awesome Game!");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            Map<Integer, Boolean> keyPressed = new HashMap<>();
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (keyPressed.getOrDefault(e.getKeyCode(), false)) return;
                keyPressed.put(e.getKeyCode(), true);
                switch (e.getKeyCode()) {
                    case VK_D:
                        controlHandler.startWalkForward();
                        break;
                    case VK_A:
                        controlHandler.startWalkBackwards();
                        break;
                    case VK_W:
                        controlHandler.startGoUp();
                        break;
                    case VK_S:
                        controlHandler.startGoDown();
                        break;
                    case VK_SPACE:
                        controlHandler.startFiring();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyPressed.put(e.getKeyCode(), false);
                switch (e.getKeyCode()) {
                    case VK_D:
                        controlHandler.stopWalkForward();
                        break;
                    case VK_A:
                        controlHandler.stopWalkBackwards();
                        break;
                    case VK_W:
                        controlHandler.stopGoUp();
                        break;
                    case VK_S:
                        controlHandler.stopGoDown();
                        break;
                    case VK_SPACE:
                        controlHandler.stopFiring();
                        break;
                }
            }
        });
        displayPanel = new GameDisplayPanel(state);
        frame.add(displayPanel);
        frame.getContentPane().setPreferredSize(new Dimension(SPRITE_SIZE * MAP_WIDTH, SPRITE_SIZE * MAP_HEIGHT));
        frame.pack();
        new Timer().scheduleAtFixedRate(new AwtStart(), 1000, 1000 / 30);
        frame.setVisible(true);
    }


    @Override
    public void run() {
        frame.repaint();
    }
}

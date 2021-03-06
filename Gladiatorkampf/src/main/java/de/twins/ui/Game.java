package de.twins.ui;

import de.twins.arena.domain.Arena;
import de.twins.arena.domain.Obstacle;
import de.twins.enemy.domain.Minion;
import de.twins.equipment.domain.Bow;
import de.twins.gladiator.domain.Gladiator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640;
    public static final int HEIGHT = WIDTH / 16 * 9;
    private Thread thread;
    private boolean running = false;
    private GameObjectHandler handler;
    private HUD hud;

    public Game() {

        Gladiator conan = new Gladiator("CONAN", new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000), null);
        conan.setHeight(30);
        conan.setWidth(30);
        conan.setXSpeed(0);
        conan.setX(100);
        conan.setY(100);
        conan.setWeapon(new Bow(10,20));
        Gladiator conan2 = new Gladiator("CONAN2", new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000), null);
        conan.setHeight(30);
        conan.setWidth(30);
        conan.setXSpeed(20);
        conan.setX(200);
        conan.setY(100);
        Minion snake = new Minion("Snake", new BigDecimal(10000), new BigDecimal(10000), new BigDecimal(10000));
        snake.setHeight(30);
        snake.setWidth(30);
        snake.setYSpeed(2);
        snake.setTarget(conan);
        Arena arena = new Arena(WIDTH, HEIGHT);
        arena.addFighter(conan);
        arena.addFighter(conan2);
        arena.addObstacle(new Obstacle(100, 200, 200, 50));
        handler = new GameObjectHandler();
        handler.setArena(arena);
        KeyInput gameListener = new KeyInput(handler);
        addKeyListener(gameListener);
        addMouseMotionListener(gameListener);
        new Window(WIDTH, HEIGHT, "Working title: Gladiators", this);
    }

    public static void main(String[] args) {
        new Game();
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                if (hud != null) {
                    hud.tick();
                }
                delta--;
            }
            if (running) {
                render();
            }
            frames++;
            // Prints FPS every second
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
        stop();
    }

    private void render() {
        // important creates buffer which paints the picture in whole before
        // showing it in UI.
        // Otherwise the image would flicker
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        File file = new File(ImagePaths.GRASS_UNDERGROUND);
        try {
            BufferedImage read = ImageIO.read(file);
            g.drawImage(read, 0, 0, WIDTH, HEIGHT, (img, infoflags, x, y, width, height) -> false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (hud != null) {
            hud.render(g);
        }
        handler.render(g);
        g.dispose();
        bs.show();
    }

    private void tick() {
        handler.tick();
    }
}

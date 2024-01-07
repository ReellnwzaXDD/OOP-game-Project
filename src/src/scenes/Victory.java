package scenes;

import java.awt.Graphics;

import main.Game;
import java.awt.Color;
import java.awt.Font;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import ui.MyButton;
import static main.GameStates.*;

public class Victory extends GameScene implements SceneMethods {
    private MyButton bReplay, bMenu, bEdit;
    private BufferedImage img;

    public Victory(Game game) {
        super(game);
        initButtons();
    }

    private void initButtons() {

        int w = 150;
        int h = w / 3;
        int x = 690 / 2 - w / 2;
        int y = 400;
        int yOffset = 100;

        bMenu = new MyButton("Menu", x, y + yOffset + 100, w, h);
        bReplay = new MyButton("Play Again", x, y, w, h);
        bEdit = new MyButton("Edit Map", x, y + yOffset, w, h);

    }

    private void replayGame() {
        // reset everything
        resetAll();

        // change state to playing
        SetGameState(PLAYING);

    }

    private void Editmap() {
        resetAll();
        SetGameState(EDIT);
    }

    private void resetAll() {
        game.getPlaying().resetEverything();
    }

    @Override
    public void render(Graphics g) {

        // Image
        InputStream is = getClass().getResourceAsStream("/res/Victorypic.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, null);
        // Text
        g.setFont(new Font("LucidaSans", Font.BOLD, 50));
        g.setColor(Color.GREEN);
        g.drawString("Victory!", 250, 250);
        // buttons
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        bMenu.draw(g);
        bReplay.draw(g);
        bEdit.draw(g);

    }

    @Override
    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            SetGameState(MENU);
            resetAll();
        } else if (bReplay.getBounds().contains(x, y)) {
            replayGame();
        } else if (bEdit.getBounds().contains(x, y)) {
            Editmap();
        }

    }

    @Override
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bReplay.setMouseOver(false);
        bEdit.setMouseOver(false);
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMouseOver(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMouseOver(true);
        else if (bEdit.getBounds().contains(x, y))
            bEdit.setMouseOver(true);

    }

    @Override
    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y))
            bMenu.setMousePressed(true);
        else if (bReplay.getBounds().contains(x, y))
            bReplay.setMousePressed(true);
        else if (bEdit.getBounds().contains(x, y))
            bEdit.setMousePressed(true);
    }

    @Override
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bReplay.resetBooleans();
        bEdit.resetBooleans();
    }

    @Override
    public void mouseDragged(int x, int y) {
        // TODO Auto-generated method stub

    }

}

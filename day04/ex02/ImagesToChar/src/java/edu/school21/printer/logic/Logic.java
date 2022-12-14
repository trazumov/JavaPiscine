package edu.school21.printer.logic;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import javax.imageio.ImageIO;
import javax.management.Attribute;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Logic {
    private String white;
    private String black;
    private BufferedImage image;

    public Logic(Args args, String filepath) throws IOException {
        this.white = args.getWhite();
        this.black = args.getBlack();
        image = ImageIO.read(Logic.class.getResource(filepath));
    }

    public void display() {
        ColoredPrinter coloredPrinter = new ColoredPrinter();
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int pixel = image.getRGB(x, y);

                if (pixel == Color.WHITE.getRGB()) {
                    coloredPrinter.print(" ", Ansi.Attribute.NONE,
                            Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                } else if (pixel == Color.BLACK.getRGB()) {
                    coloredPrinter.print(" ", Ansi.Attribute.NONE,
                            Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                }
            }
            System.out.println();
        }
    }
}

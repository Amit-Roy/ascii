/*Copyright (c) 2011 Aravind Rao


Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation 
 * files (the "Software"), to deal in the Software without restriction, including without limitation the rights to 
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons 
 * to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO 
 * THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE 
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

package img.to.ascii;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ASCII {

    private final int heightFactor;
    private final int widthFactor;
    private JTextArea area;
    private PrintWriter pw;
    private FileWriter fw;

    public ASCII() {
        heightFactor = 4;
        widthFactor = 3;
    }

    public static void main(String[] args) throws IOException {
        ASCII ascii = new ASCII();
        ascii.buildGUI();
        ascii.start();

    }

    private void buildGUI() throws IOException // Method to build the GUI
    {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        area = new JTextArea();
        area.setRows(1000);
        area.setColumns(1000);
        Font font = new Font("Monospaced", Font.BOLD, 5); // Set font to monospaced, bold and 5.
        area.setFont(font);
        frame.getContentPane().add(BorderLayout.NORTH, area);

        pw = new PrintWriter(fw = new FileWriter(("result.txt"), true)); //create new file result.txt to store results
    }

    public void start() throws IOException {
        BufferedImage image = ImageIO.read(new File("C:\\Users\\sunny\\Desktop\\Dota2-3.jpg"));

        for (int y = 0; y < image.getHeight(); y += heightFactor) {
            for (int x = 0; x < image.getWidth(); x += widthFactor) {
                Color pixelColor = new Color(image.getRGB(x, y));
                double gValue = (pixelColor.getRed() * 0.3 + pixelColor.getBlue() * 0.6 + pixelColor.getGreen() * 0.1) / 255;
                area.append(returnStrPos(gValue));
                print(returnStrNeg(gValue));
            }
            area.append("\n");
            pw.println("");
            pw.flush();
            fw.flush();
        }

    }

    public String returnStrPos(double g) {
        String str;

        if (g >= 0.9) {
            str = " ";
        } else if (g >= 0.785) {
            str = ".";
        } else if (g >= 0.7) {
            str = "*";
        } else if (g >= 0.63) {
            str = ":";
        } else if (g >= 0.5) {
            str = "o";
        } else if (g >= 0.4) {
            str = "&";
        } else if (g >= 0.275) {
            str = "8";
        } else if (g >= 0.2) {
            str = "#";
        } else {
            str = "@";
        }

        return str;

    }

    public String returnStrNeg(double g) {
        String str = " ";

        if (g >= 0.9) {
            str = "@";
        } else if (g >= 0.785) {
            str = "#";
        } else if (g >= 0.7) {
            str = "8";
        } else if (g >= 0.63) {
            str = "&";
        } else if (g >= 0.5) {
            str = "o";
        } else if (g >= 0.4) {
            str = ":";
        } else if (g >= 0.275) {
            str = "*";
        } else if (g >= 0.2) {
            str = ".";
        }

        return str;

    }

    public void print(String str) {
        try {
            pw.print(str);
            pw.flush();
            fw.flush();
        } catch (Exception ex) {
        }
    }

}

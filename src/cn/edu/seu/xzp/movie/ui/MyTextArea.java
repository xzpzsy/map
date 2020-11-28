package cn.edu.seu.xzp.movie.ui;

import javax.swing.*;
import java.awt.*;

public class MyTextArea extends JTextArea {
    public MyTextArea(Color bc, Color fc, Font font, Dimension dimension) {
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(bc);
        setForeground(fc);
        setFont(font);
        setLineWrap(true);
        setWrapStyleWord(true);
        setPreferredSize(dimension);
    }
}

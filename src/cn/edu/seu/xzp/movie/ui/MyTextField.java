package cn.edu.seu.xzp.movie.ui;

import javax.swing.*;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(Font font, Dimension dimension, Color fc, Color bc){
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        // this.setUI(new MyButtonUI());
        this.setPreferredSize(dimension);
        this.setFont(font);
        this.setBackground(bc);
        this.setForeground(fc);
    }
}

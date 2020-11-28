package cn.edu.seu.xzp.movie.ui;

import javax.swing.*;
import java.awt.*;

public class MyJRadioButton extends JRadioButton {
    public MyJRadioButton(String content, Font font){
        super(content);
        setPreferredSize(new Dimension(100, 40));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(font);
    }
}

package cn.edu.seu.xzp.movie.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.security.PublicKey;

public class MyButton extends JButton {
    public MyButton(String content, Font font, Dimension dimension, Color fc, Color bc, ActionListener actionListener){
        this.setFont(font);
        this.setOpaque(true);
        this.setFocusPainted(false);
        //this.setBorderPainted(false);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        // this.setContentAreaFilled(false);
        this.setForeground(fc);
        this.setBackground(bc);
        // this.setUI(new MyButtonUI());
        this.setText(content);
        this.setPreferredSize(dimension);
        this.addActionListener(actionListener);
    }

    public MyButton(Dimension dimension, ActionListener actionListener){
        this.setOpaque(true);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setContentAreaFilled(false);
        this.setPreferredSize(dimension);
        this.addActionListener(actionListener);
    }
    public MyButton(Dimension dimension){
        this.setPreferredSize(dimension);
        this.setBorder(null);
        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
    }
    public MyButton(Dimension dimension, ActionListener actionListener, int index){
        this.setPreferredSize(dimension);
//        this.setOpaque(false);
        this.setFocusPainted(false);
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.addActionListener(actionListener);
    }
}

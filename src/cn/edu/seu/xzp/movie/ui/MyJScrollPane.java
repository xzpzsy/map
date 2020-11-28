package cn.edu.seu.xzp.movie.ui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MyJScrollPane extends JScrollPane {
    public MyJScrollPane(Component component,Dimension dimension){
        super(component);
        this.getVerticalScrollBar().setUI(new DemoScrollBarUI());
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.setPreferredSize(dimension);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.setOpaque(false);
        this.getViewport().setOpaque(false);
    }
}

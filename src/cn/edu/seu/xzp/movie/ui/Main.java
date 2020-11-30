package cn.edu.seu.xzp.movie.ui;

import cn.edu.seu.xzp.movie.color.ColorPickerCombobox;
import cn.edu.seu.xzp.movie.jdbc.JDBCHelper;
import cn.edu.seu.xzp.movie.object.City;
import cn.edu.seu.xzp.movie.object.ImageFilter;
import cn.edu.seu.xzp.movie.object.Province;
import cn.edu.seu.xzp.movie.object.Record;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.ResultSetMetaData;
import java.util.*;
import java.util.List;

public class Main extends JFrame {

    static Color white = new Color(237, 255, 255);
    static Color origin = new Color(238, 107, 99);
    static Color gray = new Color(228, 226, 227);
    static Color black = new Color(47, 44, 35);
    static HashMap<String, Color> colors = new HashMap<>();

    static String current_province = "中国";
    static String current_city = "中国";
    static String current_record = "中国";
    // 当前详情
    static String current_detail_of_province = "中华人民共和国";
    static String current_detail_of_city = "中华人民共和国";
    static String current_detail_of_record = "中华人民共和国";
    // 当前页
    static int current_page = 1;
    // 当前图片下标
    static int current_index = 0;

    // 列表列名
    static Object[] columnNames_record = {"城市", "日期", "目的", "美食"};
    static Object[] columnNames_city = {"城市", "省份", "别名", "美食"};
    static Object[][] rowData_records;
    static Object[][] rowData_cities;
    static String orderRecord = "date";
    static String orderCity = "city";
    static ArrayList<Record> Records = new ArrayList<>();
    static ArrayList<City> cities = new ArrayList<>();
    static ArrayList<Province> provinces = new ArrayList<>();
    static String current_url = "https://baike.baidu.com/item/中国/1122445";
    // 默认字体
    static Font npFont = new Font("微软雅黑", Font.BOLD, 18);
    /*
      总panel
          左子panel
              JLabel标题                             JLabel标题
              JLabel图片                             左Jpanel
              JButton链接 <=> 上下图片按钮                 label : textfield
              JTextArea详情 记录                         label : textfield
              省份 城市 记录 编辑按钮

       右子panel
           列表（列表 列表样式 滚轮样式）
           三个按钮
     */

    /**
     * 左子JPanel
     */
    static JPanel leftJpanel = new MyPanel(new Dimension(580, 830), 0, 0);
    static JPanel leftJpanel1 = new MyPanel(new Dimension(580, 740), 0, 0);
    static JPanel leftJpanel2 = new MyPanel(new Dimension(580, 740), 0, 0);
    // 标题
    static JButton titleJButton = new MyButton(current_province, npFont, new Dimension(580, 50), black, gray, e -> {
        urlOpen(current_url);
    });
    // 图片
    // 省份
    static String provinceImageUrl = "images//中国地图//" + "中国" + ".png";
    static ImageIcon provinceImageIcon = new ImageIcon(provinceImageUrl);
    // 城市
    static String cityImageUrl = "images//城市图集//" + "南京" + ".png";
    static ImageIcon cityImageIcon = new ImageIcon(cityImageUrl);
    // memory
    static String memoryImageUrl = "";
    static ImageIcon memoryImageIcon = new ImageIcon(memoryImageUrl);

    // 双击找到文件位置进行播放
    static File dir = new File("images//回忆图集//1"); // 创建文件对象
    static File[] files = dir.listFiles(new ImageFilter());
    static List<File> fileList;

    static {
        if (files != null) {
            fileList = Arrays.asList(files);
        }
    }
    static JPanel contentPanel;
    /*
    edit区域
     */
    // 标题
    static JButton titleEditJButton = new MyButton("记录", npFont, new Dimension(580, 50), black, gray, e -> {
    });

    static Dimension inputJLabelDimension = new Dimension(60, 40);
    static Dimension inputJTextDimension = new Dimension(395, 40);

    static JPanel inputJPanel = new MyPanel(new Dimension(460, 384), 0, 2);
    static JLabel cityJLabel = new MyLabel("城市", npFont, inputJLabelDimension, black);
    static JTextField cityJTextfield = new MyTextField(npFont, inputJTextDimension, black, gray);
    static JLabel provinceJLabel = new MyLabel("省份", npFont, inputJLabelDimension, black);
    static JTextField provinceJTextfield = new MyTextField(npFont, inputJTextDimension, black, gray);
    static JLabel dateJLabel = new MyLabel("日期", npFont, inputJLabelDimension, black);
    static JTextField dateJTextfield = new MyTextField(npFont, inputJTextDimension, black, gray);
    static JLabel purposeJLabel = new MyLabel("目的", npFont, inputJLabelDimension, black);
    static JTextField purposeJTextfield = new MyTextField(npFont, inputJTextDimension, black, gray);
    static JLabel recordJLabel = new MyLabel("记录", npFont, new Dimension(60, 80), black);
    static JTextArea recordJTextarea = new MyTextArea(gray, black, npFont, new Dimension(395, 80));
    static JLabel gourmetJLabel = new MyLabel("美食", npFont, new Dimension(60, 80), black);
    static JTextArea gourmetJTextarea = new MyTextArea(gray, black, npFont, new Dimension(395, 80));

    static JPanel buttonJPanel = new MyPanel(new Dimension(120, 384), 0, 2);
    static JButton addJButton = new MyButton("添加", npFont, new Dimension(120, 165), black, gray, e -> {
    });
    static JButton updateJButton = new MyButton("更新", npFont, new Dimension(120, 163), black, gray, e -> {

    });


    /*
    上传图片
     */
    static JLabel fontColorJLabel = new MyLabel("字体",npFont,new Dimension(193, 60),black);
    static JLabel backColorJLabel = new MyLabel("背景",npFont,new Dimension(193,60),black);
    static JLabel componentColorJLabel = new MyLabel("组件",npFont,new Dimension(193,60),black);
    static JButton themeJButton = new MyButton("确定", npFont, new Dimension(580, 40), black, gray, e -> {
        update_color();
    });

    static JLabel uploadJLabel = new MyLabel("图集", npFont, inputJLabelDimension, black);
    static JRadioButton provinceJRadioButton = new MyJRadioButton("省",npFont);
    static JRadioButton cityJRadioButton = new MyJRadioButton("城市",npFont);
    static JRadioButton recordJRadioButton = new MyJRadioButton("记录",npFont);
    static ButtonGroup bg = new ButtonGroup();
    static JButton uploadJButton = new MyButton("上传", npFont, new Dimension(120, 40), black, gray, e -> {
    });

    // 省份图片
    static JLabel imageJLabel = new MyLabel(new Dimension(580, 435));
    // 详情区
    static JTextArea detailJTextArea = new JTextArea();
    static JScrollPane detailJscrollPane = new MyJScrollPane(detailJTextArea, new Dimension(580, 260));
    // 上一张 下一张图片按钮
    static JButton previousJButton = new MyButton(new Dimension(288, 435), e -> {
        if (current_page == 3) {
            current_index -= 1;
            switch_picture(0);
        }
    });
    static JButton nextJButton = new MyButton(new Dimension(288, 435), e -> {
        if (current_page == 3) {
            current_index += 1;
            switch_picture(1);
        }
    });
    // 按钮区域
    static JButton provinceJButton = new MyButton("省份", npFont, new Dimension(145, 50), black, gray, e -> {
        current_page = 1;
        leftJpanel2.setVisible(false);
        leftJpanel1.setVisible(true);
        rebuild();
    });
    static JButton cityJButton = new MyButton("城市", npFont, new Dimension(145, 50), black, gray, e -> {
        current_page = 2;
        leftJpanel2.setVisible(false);
        leftJpanel1.setVisible(true);
        rebuild();
    });
    static JButton recordJButton = new MyButton("记录", npFont, new Dimension(145, 50), black, gray, e -> {
        current_page = 3;
        leftJpanel2.setVisible(false);
        leftJpanel1.setVisible(true);
        rebuild();
    });
    static JButton editJButton = new MyButton("编辑", npFont, new Dimension(145, 50), black, gray, e -> {
        leftJpanel1.setVisible(false);
        leftJpanel2.setVisible(true);
    });

    /**
     * 分割区域
     */
    static JButton divided_area_1 = new MyButton(new Dimension(10, 830));
    static JButton divided_area_2 = new MyButton(new Dimension(440, 30));
    static JButton divided_area_3 = new MyButton(new Dimension(120, 30));
    /**
     * 右列表
     */
    static JPanel rightJPanel = new MyPanel(new Dimension(419, 830), 0, 0);

    // 列表样式
    static TableModel tableModel = new TableModel();
    // 列表
    static JTable recordsJTable = new JTable(tableModel);
    // record列表滚轮
    static JScrollPane recordsJscrollPane = new MyJScrollPane(recordsJTable, new Dimension(419, 485));

    // 列表样式
    static TableModel tableModel2 = new TableModel();
    // 列表
    static JTable citiesJTable = new JTable(tableModel2);
    // city列表滚轮
    static JScrollPane citiesJscrollPane = new MyJScrollPane(citiesJTable, new Dimension(419, 345));



    static ArrayList<Component> components = new ArrayList<>(){
        {
            add(titleJButton);
            add(provinceJButton);
            add(cityJButton);
            add(recordJButton);
            add(editJButton);
            add(recordsJTable);
            add(citiesJTable);
            add(recordsJTable.getTableHeader());
            add(citiesJTable.getTableHeader());
            add(cityJTextfield);
            add(provinceJTextfield);
            add(dateJTextfield);
            add(purposeJTextfield);
            add(recordJTextarea);
            add(gourmetJTextarea);
            add(addJButton);
            add(updateJButton);
            add(cityJRadioButton);
            add(provinceJRadioButton);
            add(recordJRadioButton);
            add(uploadJButton);
            add(titleEditJButton);
            add(cityJLabel);
            add(provinceJLabel);
            add(dateJLabel);
            add(purposeJLabel);
            add(recordJLabel);
            add(gourmetJLabel);
            add(uploadJLabel);
            add(fontColorJLabel);
            add(backColorJLabel);
            add(componentColorJLabel);
            add(themeJButton);
            add(detailJTextArea);
        }
    };

    /*
    壁纸
     */
    static ColorPickerCombobox fontColorPicker = new ColorPickerCombobox();
    static ColorPickerCombobox backColorPicker = new ColorPickerCombobox();
    static ColorPickerCombobox componentColorPicker = new ColorPickerCombobox();



    public Main() {
        init();
    }

    public static void main(String[] args) {
        new Main();

    }

    // 初始化服务器界面
    public void init() {
//        colors.put("浙江", new Color(23, 131, 180, 255));
//        colors.put("安徽", new Color(35, 32, 25, 255));
//        colors.put("澳门", new Color(44, 97, 191, 255));
//        colors.put("北京", new Color(205, 46, 46, 255));
//        colors.put("福建", new Color(86, 136, 47, 255));
//        colors.put("甘肃", new Color(106, 113, 50, 255));
//        colors.put("广东", new Color(123, 153, 210, 255));
//        colors.put("广西", new Color(113, 200, 110, 255));
//        colors.put("贵州", new Color(86, 126, 37, 255));
//        colors.put("海南", new Color(48, 183, 213, 255));
//        colors.put("河北", new Color(85, 141, 78, 255));
//        colors.put("河南", new Color(219, 160, 68, 255));
//        colors.put("黑龙江", new Color(60, 46, 20, 255));
//        colors.put("湖北", new Color(43, 111, 107, 255));
//        colors.put("湖南", new Color(52, 151, 216, 255));
//        colors.put("吉林", new Color(170, 198, 203, 255));
//        colors.put("江苏", new Color(121, 73, 125, 255));
//        colors.put("江西", new Color(215, 226, 91, 255));
//        colors.put("辽宁", new Color(75, 135, 80, 255));
//        colors.put("内蒙古", new Color(46, 98, 16, 255));
//        colors.put("宁夏", new Color(201, 67, 83, 255));
//        colors.put("青海", new Color(114, 178, 118, 255));
//        colors.put("山东", new Color(223, 148, 166, 255));
//        colors.put("山西", new Color(106, 156, 47, 255));
//        colors.put("陕西", new Color(120, 82, 26, 255));
//        colors.put("上海", new Color(66, 167, 211, 255));
//        colors.put("四川", new Color(143, 52, 33, 255));
//        colors.put("台湾", new Color(74, 129, 36, 255));
//        colors.put("天津", new Color(44, 154, 204, 255));
//        colors.put("西藏", new Color(47, 60, 102, 255));
//        colors.put("香港", new Color(62, 71, 138, 255));
//        colors.put("新疆", new Color(172, 97, 58, 255));
//        colors.put("云南", new Color(141, 178, 79, 255));
//        colors.put("重庆", new Color(166, 23, 32, 255));
        this.setTitle("电影");
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1025, 830);
        // 背景设置
        contentPanel = new JPanel() {
//            /**
//             * 服务器背景
//             */
//            private static final long serialVersionUID = 1L;
//
//            @Override
//            protected void paintComponent(Graphics g) {
//                // 背景设置
//                super.paintComponent(g);
//                ImageIcon img = new ImageIcon("images//1.jpg");
//                img.paintIcon(this, g, 0, 0);
//            }
        };
        setContentPane(contentPanel);
        contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftJpanel1.add(titleJButton);


        imageJLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        imageJLabel.setIcon(provinceImageIcon);
        imageJLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        imageJLabel.add(previousJButton);
        imageJLabel.add(nextJButton);
        leftJpanel1.add(imageJLabel);

        detailJTextArea.setBorder(null);
        detailJTextArea.setEditable(false);
        detailJTextArea.setOpaque(false);
        detailJTextArea.setForeground(origin);
        detailJTextArea.setFont(npFont);
        detailJTextArea.setLineWrap(true);
        detailJTextArea.setWrapStyleWord(true);

        detailJscrollPane.setOpaque(false);
        leftJpanel1.add(detailJscrollPane);


        leftJpanel2.add(titleEditJButton);
        leftJpanel2.add(new MyButton(new Dimension(580,20)));
        inputJPanel.add(cityJLabel);
        inputJPanel.add(cityJTextfield);
        inputJPanel.add(provinceJLabel);
        inputJPanel.add(provinceJTextfield);
        inputJPanel.add(dateJLabel);
        inputJPanel.add(dateJTextfield);
        inputJPanel.add(purposeJLabel);
        inputJPanel.add(purposeJTextfield);
        inputJPanel.add(divided_area_2);
        inputJPanel.add(recordJLabel);
        inputJPanel.add(recordJTextarea);
        inputJPanel.add(gourmetJLabel);
        inputJPanel.add(gourmetJTextarea);

        buttonJPanel.add(addJButton);
        buttonJPanel.add(divided_area_3);
        buttonJPanel.add(updateJButton);

        leftJpanel2.add(inputJPanel);
        leftJpanel2.add(buttonJPanel);

        leftJpanel2.add(fontColorJLabel);
        leftJpanel2.add(backColorJLabel);
        leftJpanel2.add(componentColorJLabel);

        fontColorPicker.setPreferredSize(new Dimension(193,40));
        backColorPicker.setPreferredSize(new Dimension(193,40));
        componentColorPicker.setPreferredSize(new Dimension(193,40));
        leftJpanel2.add(fontColorPicker);
        leftJpanel2.add(backColorPicker);
        leftJpanel2.add(componentColorPicker);
        leftJpanel2.add(themeJButton);
        leftJpanel2.add(new MyButton(new Dimension(580, 40)));

        bg.add(cityJRadioButton);
        bg.add(recordJRadioButton);
        bg.add(provinceJRadioButton);
        leftJpanel2.add(uploadJLabel);
        leftJpanel2.add(new MyButton(new Dimension(50,40)));
        leftJpanel2.add(cityJRadioButton);
        leftJpanel2.add(recordJRadioButton);
        leftJpanel2.add(provinceJRadioButton);
        leftJpanel2.add(new MyButton(new Dimension(49,40)));
        leftJpanel2.add(uploadJButton);
        // 先加大的panel
        leftJpanel.add(leftJpanel1);
        leftJpanel.add(leftJpanel2);

        // 再加4个button
        leftJpanel.add(provinceJButton);
        leftJpanel.add(cityJButton);
        leftJpanel.add(recordJButton);
        leftJpanel.add(editJButton);
        contentPanel.add(leftJpanel);
        leftJpanel2.setVisible(false);
        contentPanel.add(divided_area_1);

        recordsJTable.setBackground(gray);
        recordsJTable.setForeground(black);
        recordsJTable.setFont(new Font("微软雅黑", Font.BOLD, 12));
        recordsJTable.setBorder(BorderFactory.createRaisedBevelBorder());
        recordsJTable.setSelectionBackground(origin);
        recordsJTable.setSelectionForeground(white);
        recordsJTable.setRowHeight(30);


        citiesJTable.setBackground(gray);
        citiesJTable.setForeground(black);
        citiesJTable.setFont(new Font("微软雅黑", Font.BOLD, 12));
        citiesJTable.setBorder(BorderFactory.createRaisedBevelBorder());
        citiesJTable.setSelectionBackground(origin);
        citiesJTable.setSelectionForeground(white);
        citiesJTable.setRowHeight(30);



        rightJPanel.add(recordsJscrollPane);
        rightJPanel.add(citiesJscrollPane);
        contentPanel.add(rightJPanel);

        citiesJTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = citiesJTable.getSelectedRow();
                City city = cities.get(row);
                if (e.getClickCount() == 1) {
                    provinceImageUrl = "images//中国地图//" + city.getProvince() + ".png";
                    cityImageUrl = "images//城市图集//" + city.getCity() + ".png";
                    // 双击找到文件位置进行播放
                    //update_color(colors.get(city.getProvince()));
                    current_province = city.getProvince();
                    current_city = city.getCity();
                    current_detail_of_province = getDetailOfProvince(city.getProvince());
                    current_detail_of_city = getDetailOfCity(city.getCity());
                    rebuild();
                } else {
                    System.out.println("完善当前城市信息");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // 记录列表点击事件
        recordsJTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = recordsJTable.getSelectedRow();
                Record record = Records.get(row);
                if (e.getClickCount() == 1) {
                    provinceImageUrl = "images//中国地图//" + record.getProvince() + ".png";
                    cityImageUrl = "images//城市图集//" + record.getCity() + ".png";
                    // 双击找到文件位置进行播放
                    dir = new File("images//回忆图集//" + record.getId()); // 创建文件对象
                    files = dir.listFiles(new ImageFilter());
                    if (files != null) {
                        fileList = Arrays.asList(files);
                        switch_picture(2);
                        current_province = record.getProvince();
                        current_city = record.getCity();
                        current_record = "record";
                        current_detail_of_city = getDetailOfCity(record.getCity());
                        current_detail_of_province = getDetailOfProvince(record.getProvince());
                        current_detail_of_record = "record";
                        rebuild();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
//
        JTableHeader tableHeader = recordsJTable.getTableHeader();
        tableHeader.setReorderingAllowed(false);
        tableHeader.setResizingAllowed(false);
        tableHeader.setFont(new Font("微软雅黑", Font.BOLD, 19));
        tableHeader.setPreferredSize(new Dimension(tableHeader.getWidth(), 35));
        tableHeader.setBorder(BorderFactory.createRaisedBevelBorder());
        tableHeader.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int choose = tableHeader.columnAtPoint(e.getPoint());
                switch (choose) {
                    case 0:
                        orderRecord = "city";
                        break;
                    case 1:
                        orderRecord = "date";
                        break;
                    case 2:
                        orderRecord = "purpose";
                        break;
                    case 3:
                        orderRecord = "gourmet";
                        break;
                    default:
                        break;
                }
                update_records();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        update_records();
        update_province();
        update_cities();
        // update_color(new Color(77,87,91),new Color(92,130,149),new Color(28,31,39));
        this.setVisible(true);
    }

    public static void update_color() {
        Color cc = componentColorPicker.getSelectedColor();
        Color fc = fontColorPicker.getSelectedColor();
        Color bc = backColorPicker.getSelectedColor();
        if (cc!=null && fc!=null && bc!=null) {
            contentPanel.setBackground(bc);
            fontColorPicker.setBackground(cc);
            fontColorPicker.setForeground(fc);
            backColorPicker.setBackground(cc);
            backColorPicker.setForeground(fc);
            componentColorPicker.setBackground(cc);
            componentColorPicker.setForeground(fc);
            for (Component c: components
            ) {
                c.setBackground(cc);
                c.setForeground(fc);
            }
        }
    }

    public static void update_province() {
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        List<Map<String, Object>> resultList = jdbcHelper.executeQuery("select * from provinces", rs -> {
            List<Map<String, Object>> result = new ArrayList<>();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                result.add(rowData);
            }
            return result;
        });
        for (Map<String, Object> stringObjectMap : resultList) {
            Province province = new Province();
            stringObjectMap.forEach((key, value) -> {
            });
            for (Map.Entry<String, Object> entry : stringObjectMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    switch (key) {
                        case "provincial_capital":
                            province.setProvince_capital((String) value);
                            break;
                        case "province":
                            province.setProvince((String) value);
                            break;
                        case "alias":
                            province.setAlias((String) value);
                            break;
                        case "gourmet":
                            province.setGourmet((String) value);
                            break;
                        case "links":
                            province.setLink((String) value);
                            break;
                        case "climate":
                            province.setClimate((String) value);
                            break;
                        case "area":
                            province.setArea((String) value);
                            break;
                        case "location":
                            province.setLocation((String) value);
                            break;
                        default:
                            break;
                    }
                }
            }
            provinces.add(province);
        }
    }

    public static void switch_picture(int pre_next) {

        if (files != null) {
            fileList.sort((o1, o2) -> {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            });
        }
        if (fileList.size() > current_index && current_index >= 0) {
            memoryImageUrl = fileList.get(current_index).getPath();
            memoryImageIcon = new ImageIcon(memoryImageUrl);
            imageJLabel.setIcon(memoryImageIcon);
        } else {
            if (pre_next == 0) {
                current_index += 1;
            } else if (pre_next == 1) {
                current_index -= 1;
            }

        }

    }

    public static String getDetailOfProvince(String pro) {
        for (Province province : provinces
        ) {
            if (pro.equals(province.getProvince())) {
                return province.toString();
            }
        }
        return "";
    }

    public static String getDetailOfCity(String c) {
        for (City city : cities
        ) {
            if (c.equals(city.getCity())) {
                return city.toString();
            }
        }
        return "";
    }
    
    public static void rebuild() {
        switch (current_page) {
            case 1:
                titleJButton.setText(current_province);
                detailJTextArea.setText(current_detail_of_province);
                provinceImageIcon = new ImageIcon(provinceImageUrl);
                imageJLabel.setIcon(provinceImageIcon);
                break;
            case 2:
                titleJButton.setText(current_city);
                detailJTextArea.setText(current_detail_of_city);
                cityImageIcon = new ImageIcon(cityImageUrl);
                imageJLabel.setIcon(cityImageIcon);
                break;
            case 3:
                titleJButton.setText(current_record);
                detailJTextArea.setText(current_detail_of_record);
                memoryImageIcon = new ImageIcon(memoryImageUrl);
                imageJLabel.setIcon(memoryImageIcon);
                break;
        }
    }

    public static void update_cities() {
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        List<Map<String, Object>> resultList = jdbcHelper.executeQuery("select * from cities" + "  order by " + orderCity, rs -> {
            List<Map<String, Object>> result = new ArrayList<>();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                result.add(rowData);
            }
            return result;
        });

        rowData_cities = new Object[resultList.size()][3];
        for (int i = 0; i < resultList.size(); i++) {
            City city = new City();
            resultList.get(i).forEach((key, value) -> {
            });
            for (Map.Entry<String, Object> entry : resultList.get(i).entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    switch (key) {
                        case "gourmet":
                            city.setGourmet((String) value);
                            break;
                        case "city":
                            city.setCity((String) value);
                            rowData_cities[i][0] = value;
                            break;
                        case "province":
                            city.setProvince((String) value);
                            rowData_cities[i][1] = value;
                            break;
                        case "alias":
                            city.setAlias((String) value);
                            rowData_cities[i][2] = value;
                            break;
                        case "location":
                            city.setLocation((String) value);
                            break;
                        case "dialect":
                            city.setDialect((String) value);
                            break;
                        default:
                            break;
                    }
                }
            }
            cities.add(city);
        }

        tableModel2.setDataVector(rowData_cities, columnNames_city);

        // 获取表头
        JTableHeader jTableHeader = citiesJTable.getTableHeader();
        // 设置表头名称字体样式
        jTableHeader.setResizingAllowed(false);
        jTableHeader.setReorderingAllowed(false);
        jTableHeader.setFont(new Font("微软雅黑", Font.BOLD, 19));
        jTableHeader.setForeground(black);
        jTableHeader.setBackground(gray);
        jTableHeader.setBorder(BorderFactory.createRaisedBevelBorder());
        jTableHeader.setPreferredSize(new Dimension(1, 35));
        //列宽设置

        // 左对齐
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnNames_city.length; i++) {
            citiesJTable.getColumnModel().getColumn(i).setCellRenderer(render);
        }
    }

    // 读取数据库内容以更新电影列表
    public static void update_records() {
        JDBCHelper jdbcHelper = JDBCHelper.getInstance();

        List<Map<String, Object>> resultList = jdbcHelper.executeQuery("select * from records" + "  order by " + orderRecord, rs -> {
            List<Map<String, Object>> result = new ArrayList<>();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                Map<String, Object> rowData = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    rowData.put(md.getColumnName(i), rs.getObject(i));
                }
                result.add(rowData);
            }
            return result;
        });
        Records.clear();
        rowData_records = new Object[resultList.size()][4];
        for (int i = 0; i < resultList.size(); i++) {
            Record record = new Record();
            resultList.get(i).forEach((key, value) -> {
            });
            for (Map.Entry<String, Object> entry : resultList.get(i).entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null) {
                    switch (key) {
                        case "id":
                            record.setId((int) value);
                            break;
                        case "city":
                            record.setCity((String) value);
                            rowData_records[i][0] = value;
                            break;
                        case "province":
                            record.setProvince((String) value);
                            break;
                        case "date":
                            record.setDate((Date) value);
                            rowData_records[i][1] = value;
                            break;
                        case "purpose":
                            record.setPurpose((String) value);
                            rowData_records[i][2] = value;
                            break;
                        case "gourmet":
                            record.setGourmet((String) value);
                            rowData_records[i][3] = value;
                            break;

                        default:
                            break;
                    }
                }
            }
            Records.add(record);
        }
//
//        for (int i=0;i<Records.size();i++){
//            System.out.println("1");
//            System.out.println(Records.get(i).getCity());
//        }

        tableModel.setDataVector(rowData_records, columnNames_record);

        // 获取表头
//        JTableHeader jTableHeader = recordsJTable.getTableHeader();
//        // 设置表头名称字体样式
//        jTableHeader.setFont(new Font("微软雅黑", Font.BOLD, 12));
//        jTableHeader.setForeground(black);
//        jTableHeader.setBackground(gray);
//        jTableHeader.setPreferredSize(new Dimension(1, 25));
        //列宽设置

        // 左对齐
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnNames_record.length; i++) {
            recordsJTable.getColumnModel().getColumn(i).setCellRenderer(render);
        }

        switch_picture(3);
    }

    // 打开url
    public static void urlOpen(String url) {
        String osName = System.getProperty("os.name", "");// 获取操作系统的名字

        if (osName.startsWith("Windows")) {// windows
            try {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (osName.startsWith("Mac OS")) {// Mac
            try {
                Class fileMgr;
                fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL;
                try {
                    openURL = fileMgr.getDeclaredMethod("openURL", String.class);
                    try {
                        openURL.invoke(null, url);
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                        e.printStackTrace();
                    }
                } catch (NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else {// Unix or Linux
            String[] browsers = {"firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"};
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; count++) { // 执行代码，在brower有值后跳出，
                // 这里是如果进程创建成功了，==0是表示正常结束。
                try {
                    if (Runtime.getRuntime().exec(new String[]{"which", browsers[count]}).waitFor() == 0) {
                        browser = browsers[count];
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }

            if (browser == null) {
                throw new RuntimeException("未找到任何可用的浏览器");
            } else {// 这个值在上面已经成功的得到了一个进程。
                try {
                    Runtime.getRuntime().exec(new String[]{browser, url});
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
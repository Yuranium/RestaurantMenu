package RestaurantMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.EventObject;

public class MainPanel
{
    private final Item[] items = new Item[15];
    public InternetOrder internetOrder;
    public OrderManager orderManager;
    private void construct()
    {
        items[0] = new Dish("Спагетти", "Макаронные изделия с круглым сечением", 1100);
        items[1] = new Dish("Харчо", "Грузинский суп с говядиной и рисом", 850);
        items[2] = new Dish("Жареные ребрышки", "Вкусное и сытное блюдо с соусом", 1750);
        items[3] = new Dish("Мясо камчатского краба", "Нежное и мягкое мясо", 3500);
        items[4] = new Dish("Картофель с рыбой", "Вкусное блюдо домашней кухни", 1000);
        items[5] = new Dish("Креветки в кляре", "Сытное мясо в хрустящей панировке", 100);
        items[6] = new Dish("Роллы", "Блюдо из риса, уксуса и морепродуктов", 1050);
        items[7] = new Dish("Пицца", "Итальянское блюдо с начинкой сверху", 1500);
        items[8] = new Dish("Чизбургер", "Гамбургер с сыром", 600);
        items[9] = new Drink("Эспрессо", "Бодрящий кофе с сахаром", 200);
        items[10] = new Drink("Фанта", "Газированный напиток со вкусом апельсина", 175);
        items[11] = new Drink("Coca-Cola", "Газированный безалкогольный напиток", 175);
        items[12] = new Drink("Чай холодный", "Прохладный, вкусный зеленый чай", 175);
        items[13] = new Drink("Бурбон", "Сильнокрепкий алкогольный напиток", 1500);
        items[14] = new Drink("Текила", "Крепкий алкогольный напиток", 850);
    }
    MainPanel()
    {
        construct();
        String[][] positions = new String[items.length][3];
        for (int i = 0; i < items.length; i++)
        {
            positions[i][0] = items[i].getName();
            positions[i][1] = items[i].getDescription();
            positions[i][2] = items[i].getPrice() +" \u20BD";
        }

        JFrame jframe = new JFrame("Меню");
        jframe.setSize(800, 500);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) (screenSize.getWidth() - jframe.getWidth()) / 2;
        int centerY = (int) (screenSize.getHeight() - jframe.getHeight()) / 2;
        jframe.setLocation(centerX, centerY);
        jframe.setVisible(true);
        JPanel tempPanel = new JPanel(new CardLayout());

        JPanel mainPanel = new JPanel(new FlowLayout());

        JPanel panel1 = new JPanel();
        JLabel label1 = new JLabel("Главное меню");
        label1.setFont(new Font("Calibri", Font.BOLD, 30));
        panel1.add(label1);

        JPanel panel2 = new JPanel();
        JButton button1 = new JButton("Оформить заказ");
        JButton button2 = new JButton("Посмотреть меню");
        JButton button3 = new JButton("Корзина");
        JButton button4 = new JButton("Выход");

        button1.setPreferredSize(new Dimension(180, 100));
        button2.setPreferredSize(new Dimension(180, 100));
        button3.setPreferredSize(new Dimension(180, 100));
        button4.setPreferredSize(new Dimension(180, 100));
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);

        JMenuBar menuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Менеджер / клиент");
        menuBar.add(jMenu);
        JMenuItem jMenuItem_1 = new JMenuItem("Менеджер");
        JMenuItem jMenuItem_2 = new JMenuItem("Клиент");
        jMenu.add(jMenuItem_1);
        jMenu.addSeparator();
        jMenu.add(jMenuItem_2);
        jframe.setJMenuBar(menuBar);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        JLabel label2 = new JLabel("Оформить новый заказ?");
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        label2.setFont(new Font("Calibri", Font.BOLD, 30));
        JPanel Btpanel = new JPanel();
        JButton button5 = new JButton("Да");
        JButton button6 = new JButton("Нет");
        button5.setPreferredSize(new Dimension(100, 75));
        button6.setPreferredSize(new Dimension(100, 75));
        Btpanel.add(button5);
        Btpanel.add(button6);
        panel3.add(label2);
        panel3.add(Btpanel);
        panel3.setVisible(false);

        JPanel panel4 = new JPanel(new BorderLayout());
        JLabel label3 = new JLabel("<html>В нашем ресторане вы сможете попробовать изысканные блюда из разных<br>стран всего мира. Наши шеф-повара имеют многолетний стаж работы.</html>");
        label3.setHorizontalAlignment(SwingConstants.CENTER);
        label3.setFont(new Font("Calibri", Font.BOLD, 20));
        JPanel pn1 = new JPanel();
        pn1.add(label3);
        JButton button7 = new JButton("Назад");
        button7.setPreferredSize(new Dimension(100, 75));
        String[] columnNames = {"Название", "Описание", "Цена"};
        JTable table = new JTable(positions, columnNames);
        table.setRowHeight(40);
        for (int i = 0; i < table.getColumnCount(); i++)
        {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            Class<?> colClass = table.getColumnClass(i);
            DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean isCellEditable(EventObject e) {
                    return false;
                }
            };
            table.setDefaultEditor(colClass, editor);
        }
        JScrollPane scrollPane = new JScrollPane(table);
        panel4.add(pn1, BorderLayout.NORTH);
        panel4.add(button7, BorderLayout.SOUTH);
        panel4.add(scrollPane);
        panel4.setVisible(false);

        JPanel panel5 = new JPanel(new BorderLayout());
        JLabel label4 = new JLabel("Меню состоит из 15 позиций, и содержит различные блюда всего мира");
        label4.setFont(new Font("Calibri", Font.BOLD, 20));
        label4.setOpaque(true);
        label4.setBackground(Color.WHITE);
        JPanel addpanel = new JPanel();
        JButton addbutton = new JButton("Добавить позицию");
        addpanel.add(addbutton);
        JTable table1 = new JTable(positions, columnNames);
        table1.setRowHeight(40);
        for (int i = 0; i < table1.getColumnCount(); i++)
        {
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
            table1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            Class<?> colClass = table1.getColumnClass(i);
            DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                @Override
                public boolean isCellEditable(EventObject e) {
                    return false;
                }
            };
            table1.setDefaultEditor(colClass, editor);
        }
        JScrollPane scrollPane1 = new JScrollPane(table1);
        panel5.add(label4, BorderLayout.NORTH);
        panel5.add(scrollPane1);
        panel5.add(addpanel, BorderLayout.SOUTH);
        panel5.setVisible(false);

        JPanel panel6 = new JPanel(new BorderLayout());
        JPanel emptyPanel = new JPanel(new FlowLayout());
        JLabel label5 = new JLabel("Корзина пока пуста");
        emptyPanel.add(label5);
        emptyPanel.setVisible(false);
        label5.setFont(new Font("Calibri", Font.BOLD, 30));
        JPanel backpanel1 = new JPanel(new FlowLayout());
        JButton backbutton1 = new JButton("Назад");
        JButton button8 = new JButton("Оплатить заказ");
        JButton button9 = new JButton("Удалить заказ");
        backpanel1.add(backbutton1);
        backpanel1.add(button8);
        backpanel1.add(button9);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setVisible(false);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(columnNames[0]);
        model.addColumn(columnNames[1]);
        model.addColumn(columnNames[2]);
        JTable table2 = new JTable(model);
        table2.setRowHeight(40);
        JScrollPane scrollPane2 = new JScrollPane(table2);
        panel6.add(backpanel1, BorderLayout.SOUTH);
        tablePanel.add(scrollPane2);
        panel6.add(emptyPanel, BorderLayout.NORTH);
        panel6.add(tablePanel);
        panel6.setVisible(false);

        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table1.getSelectedRow();
                if (selectedRow >= 0) {
                    Object[] rowData = new Object[3];
                    rowData[0] = table1.getValueAt(selectedRow, 0);
                    rowData[1] = table1.getValueAt(selectedRow, 1);
                    rowData[2] = table1.getValueAt(selectedRow, 2);
                    internetOrder.add(items[selectedRow]);
                    // Добавляем выбранную позицию в таблицу заказа
                    model.addRow(rowData);
                    //System.out.println(items[selectedRow].getName());
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3.setVisible(true);
                mainPanel.setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel4.setVisible(true);
                mainPanel.setVisible(false);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel6.setVisible(true);
                mainPanel.setVisible(false);
                if (internetOrder == null ||  internetOrder.Quantity() == 0)
                {
                    emptyPanel.setVisible(true);
                    tablePanel.setVisible(false);
                    button8.setEnabled(false); // Запретить нажатие кнопки
                    button9.setEnabled(false);

                }
                else
                {
                    emptyPanel.setVisible(false);
                    tablePanel.setVisible(true);
                    for (int i = 0; i < table2.getColumnCount(); i++)
                    {
                        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                        table2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                        Class<?> colClass = table2.getColumnClass(i);
                        DefaultCellEditor editor = new DefaultCellEditor(new JTextField()) {
                            @Override
                            public boolean isCellEditable(EventObject e) {
                                return false;
                            }
                        };
                        table2.setDefaultEditor(colClass, editor);
                    }
                }
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Выход");
                frame.setSize(300, 150); // Установка размеров окна
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame.getHeight()) / 2;
                frame.setLocation(centerX, centerY);

                frame.setVisible(true); // Делает видимым само окно
                JLabel label = new JLabel("Действительно выйти?");
                label.setFont(new Font("Calibri", Font.ITALIC, 25));
                frame.add(label, BorderLayout.NORTH);
                JPanel panel = new JPanel(new FlowLayout());
                JButton button1 = new JButton("Да");
                JButton button2 = new JButton("Нет");
                panel.add(button1);
                panel.add(button2);
                frame.add(panel);
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int adr = 65;
                button8.setEnabled(true); // Запретить нажатие кнопки
                button9.setEnabled(true);
                panel5.setVisible(true);
                panel3.setVisible(false);
                if (internetOrder == null)
                {
                    internetOrder = new InternetOrder();
                    orderManager = new OrderManager();
                    try
                    {
                        orderManager.addOrder(++adr + "AAA", internetOrder);
                    } catch (Exception ex)
                    {
                        throw new RuntimeException(ex);
                    }
                }
                else
                    System.out.println("Ошибка");
            }
        });
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel3.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel4.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Оформление заказа");
                frame1.setSize(700, 350);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame1.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame1.getHeight()) / 2;
                frame1.setLocation(centerX, centerY);
                frame1.setVisible(true);

                JPanel mainPN = new JPanel();
                JPanel mainPanel1 = new JPanel(new BorderLayout());
                JLabel label1 = new JLabel("Итоговая стоимость заказа: " + internetOrder.priceTotal() + " \u20BD");
                label1.setFont(new Font("Calibri", Font.ITALIC, 30));
                JPanel panel1 = new JPanel(new FlowLayout());
                JButton button1 = new JButton("Оплатить");
                JButton button2 = new JButton("Закрыть");
                button1.setPreferredSize(new Dimension(150, 75));
                button2.setPreferredSize(new Dimension(150, 75));

                JPanel panel2 = new JPanel();
                JLabel label3 = new JLabel();
                panel2.add(label3);
                panel2.setVisible(false);

                if (internetOrder == null || internetOrder.Quantity() == 0)
                {
                    label3.setText("Заказ не оформлен");
                    mainPanel1.setVisible(false);
                    panel2.setVisible(true);
                }
                label3.setFont(new Font("Calibri", Font.BOLD, 30));
                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        label3.setText("Оплачено");
                        DefaultTableModel model1 = (DefaultTableModel) table2.getModel();
                        model1.setRowCount(0);
                        tablePanel.setVisible(false);
                        emptyPanel.setVisible(true);
                        button8.setEnabled(false); // Запретить нажатие кнопки
                        button9.setEnabled(false);
                        mainPanel1.setVisible(false);
                        panel2.setVisible(true);
                        internetOrder = null;
                    }
                });
                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame1.dispose();
                    }
                });
                panel1.add(button1);
                panel1.add(button2);
                mainPanel1.add(label1, BorderLayout.NORTH);
                mainPanel1.add(panel1, BorderLayout.CENTER);
                mainPN.add(mainPanel1);
                mainPN.add(panel2);
                frame1.add(mainPN);
            }
        });
        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame2 = new JFrame("Удаление");
                frame2.setSize(500, 200);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame2.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame2.getHeight()) / 2;
                frame2.setLocation(centerX, centerY);
                frame2.setVisible(true);

                JPanel mainpanel2 = new JPanel(new FlowLayout());
                JLabel label1 = new JLabel("Заказ удалён");
                label1.setText((internetOrder == null) ? "Заказ не оформлен" : "Успешно удалено");
                label1.setFont(new Font("Calibri", Font.BOLD, 20));
                DefaultTableModel model1 = (DefaultTableModel) table2.getModel();
                model1.setRowCount(0);
                internetOrder = null;
                tablePanel.setVisible(false);
                emptyPanel.setVisible(true);
                button8.setEnabled(false); // Запретить нажатие кнопки
                button9.setEnabled(false);
                mainpanel2.add(label1);
                frame2.add(mainpanel2);
            }
        });
        backbutton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel6.setVisible(false);
                mainPanel.setVisible(true);
            }
        });
        jMenuItem_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JFrame frame1 = new JFrame("Менеджер ресторана");
                frame1.setSize(700, 350);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int centerX = (int) (screenSize.getWidth() - frame1.getWidth()) / 2;
                int centerY = (int) (screenSize.getHeight() - frame1.getHeight()) / 2;
                frame1.setLocation(centerX, centerY);
                frame1.setVisible(true);
                JPanel tempPanel1 = new JPanel(new CardLayout());

                JPanel mainPanel1 = new JPanel(new FlowLayout());

                JPanel panel1 = new JPanel();
                JLabel label1 = new JLabel("Введите пароль: ");
                label1.setFont(new Font("Arial Black", Font.BOLD, 40));
                panel1.add(label1);

                JPanel panel2 = new JPanel();
                JPasswordField passwordField = new JPasswordField(25);
                passwordField.setPreferredSize(new Dimension(1000, 40));
                JButton button1 = new JButton("Подтвердить");
                button1.setPreferredSize(new Dimension(150, 40));
                panel2.add(passwordField);
                panel2.add(button1);

                JPanel panel3 = new JPanel();
                panel3.add(new JLabel("       Введён неправильный пароль!       "));
                panel3.setVisible(false);
                JPanel panel4 = new JPanel();
                panel4.add(new JLabel("Пароль правильный"));
                panel4.setVisible(false);

                JPanel panel5 = new JPanel(new FlowLayout());
                JPanel panel6 = new JPanel();
                JLabel label2 = new JLabel("Категории менеджера");
                label2.setFont(new Font("Bahnschrift", Font.BOLD, 30));
                JButton button2 = new JButton("<html>Посмотреть<br> все заказы</html>");
                JButton button3 = new JButton("<html>Сумма всех<br>интернет-заказов</html>");
                JButton button4 = new JButton("<html>Количество<br>интернет-заказов</html>");
                JButton button5 = new JButton("<html>Удалить все<br>интернет-заказы</html>");
                button2.setPreferredSize(new Dimension(150, 70));
                button3.setPreferredSize(new Dimension(150, 70));
                button4.setPreferredSize(new Dimension(150, 70));
                button5.setPreferredSize(new Dimension(150, 70));
                panel5.add(label2);
                panel6.add(button2);
                panel6.add(button3);
                panel6.add(button4);
                panel6.add(button5);
                panel5.add(panel6);

                button1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        button5.setEnabled(true);
                        char[] password = passwordField.getPassword();
                        String enteredPassword = new String(password);
                        if (enteredPassword.equals("123Qwerty"))
                        {
                            panel4.setVisible(true);
                            panel3.setVisible(false);
                            CardLayout cardLayout = (CardLayout) tempPanel1.getLayout();
                            cardLayout.show(tempPanel1, "Panel5");
                        }
                        else
                        {
                            panel3.setVisible(true);
                            panel4.setVisible(false);
                        }
                        passwordField.setText("");
                    }
                });
                JPanel panel_1 = new JPanel(new FlowLayout());
                JLabel label_1 = new JLabel();
                label_1.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_1 = new JButton("Назад");
                panel_1.add(label_1);
                panel_1.add(button_1);
                panel_1.setVisible(false);

                JPanel panel_2 = new JPanel(new FlowLayout());
                JLabel label_2 = new JLabel();
                label_2.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_2 = new JButton("Назад");
                panel_2.add(label_2);
                panel_2.add(button_2);
                panel_2.setVisible(false);

                JPanel panel_3 = new JPanel(new FlowLayout());
                JLabel label_3 = new JLabel();
                label_3.setFont(new Font("Calibri", Font.BOLD, 30));
                JButton button_3 = new JButton("Назад");
                panel_3.add(label_3);
                panel_3.add(button_3);
                panel_3.setVisible(false);

                button2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_1.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        if (orderManager == null)
                            label_1.setText("Заказа нет");
                        else label_1.setText("Всего интернет-заказов: " + orderManager.getInternetOrders().length);
                    }
                });
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_2.setVisible(true);
                        panel4.setVisible(false);
                        panel5.setVisible(false);
                        panel2.setVisible(false);
                        panel1.setVisible(false);
                        if (orderManager == null)
                            label_2.setText("Заказа нет");
                        else label_2.setText("Сумма всех интернет-заказов: " + orderManager.Price_total_InternetOrders());
                    }
                });
                button5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame fr = new JFrame("Удаление заказов");
                        fr.setSize(600, 270);
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        int centerX = (int) (screenSize.getWidth() - fr.getWidth()) / 2;
                        int centerY = (int) (screenSize.getHeight() - fr.getHeight()) / 2;
                        fr.setLocation(centerX, centerY);
                        fr.setVisible(true);

                        JPanel pn1 = new JPanel(new FlowLayout());
                        JLabel lb1 = new JLabel("Точно удалить интернет-заказы?");
                        lb1.setFont(new Font("Calibri", Font.BOLD, 30));
                        JLabel lb2 = new JLabel();
                        lb2.setFont(new Font("Calibri", Font.BOLD, 30));
                        JPanel pn2 = new JPanel();
                        JButton bt1 = new JButton("Да");
                        bt1.setPreferredSize(new Dimension(125, 60));
                        JButton bt2 = new JButton("Нет");
                        bt2.setPreferredSize(new Dimension(125, 60));
                        pn1.add(lb1);
                        pn1.add(lb2);
                        pn2.add(bt1);
                        pn2.add(bt2);
                        pn1.add(pn2);
                        bt1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                lb2.setText("Удалено");
                                lb1.setText("");
                                button5.setEnabled(false);
                                internetOrder = null;
                                orderManager = null;
                                pn2.setVisible(false);
                            }
                        });
                        bt2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                fr.dispose();
                            }
                        });
                        fr.add(pn1);
                    }
                });
                button_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_1.setVisible(false);
                        panel5.setVisible(true);
                        //panel4.setVisible(true);
                        //panel2.setVisible(true);
                        //panel1.setVisible(true);
                    }
                });
                button_2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_2.setVisible(false);
                        panel5.setVisible(true);
                    }
                });
                button_3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel_3.setVisible(false);
                        panel5.setVisible(true);
                    }
                });
                mainPanel1.add(panel1);
                mainPanel1.add(panel2);
                mainPanel1.add(panel3);
                mainPanel1.add(panel4);
                mainPanel1.add(panel_1);
                mainPanel1.add(panel_2);

                tempPanel1.add(mainPanel1);
                tempPanel1.add(panel5, "Panel5"); // Используйте "Panel5", чтобы связать с вашей панелью
                frame1.add(tempPanel1);
            }
        });
        jMenuItem_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) tempPanel.getLayout();
                cardLayout.show(tempPanel, "MainPanel");
            }
        });

        //mainPanel.add(tempPanel, "MainPanel");
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        tempPanel.add(mainPanel, "MainPanel");
        tempPanel.add(panel3);
        tempPanel.add(panel4);
        tempPanel.add(panel5);
        tempPanel.add(panel6);
        jframe.add(tempPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainPanel();
            }
        });
    }
}
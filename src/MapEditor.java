package project;
import javafx.scene.control.RadioButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by free on 05/21/2015.
 */
public class MapEditor extends JFrame
{

    public static void mapMaker() throws FileNotFoundException
    {
        final int columns = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Number of columns"));
        final int rows = Integer.parseInt(JOptionPane.showInputDialog("Please Enter Number of rows"));
        System.out.println(rows);
        System.out.println(columns);
        final int[][] adjacency = new int[2 * rows - 1][2 * columns - 1];
        for(int i  = 0; i < 2 * rows - 1 ; i++)
        {
            for (int j = 0; j < 2 * columns - 1; j++)
            {
                if(i % 2 == 1 && j % 2 == 1)
                    adjacency[i][j] = 0;
                adjacency[i][j] = 1;
            }
        }

        JFrame frame = new JFrame();
        final JPanel mapPanel = new JPanel();
        final JPanel namesPanel = new JPanel();

        ArrayList <String> name = new ArrayList<String>();
        final ArrayList <JButton> jbuttons = new ArrayList<JButton>();
        final ArrayList <JButton> jButtonsMap = new ArrayList<JButton>();
        final int[] condition = new int[rows*columns];

        name.add("Hitman");
        name.add("Spinic");
        name.add("Static");
        name.add("Dynamic");
        name.add("Sightic");
        name.add("Smellic");
        name.add("Gun");
        name.add("Stone");
        name.add("UnderGround");
        name.add("Vase");
        name.add("Remove Vertex");
        name.add("Remove Edge");
        name.add("Save");
        name.add("GameEnd");

        mapPanel.setLayout(new GridLayout(rows, columns, 10, 10));

        for(int i = 0 ; i < rows*columns ; i++)
        {

            condition[i] = 1;
            jButtonsMap.add(new JButton());
            ((JButton)jButtonsMap.get(i)).setText(Integer.toString(i));
            mapPanel.add((JButton)jButtonsMap.get(i));
        }

        namesPanel.setLayout(new GridLayout(7 , 2, 5, 5));
        for(int i = 0 ; i < 14 ; i++)
        {
            jbuttons.add(new JButton());
            String str = name.get(i).toString();
            JButton button = (JButton) jbuttons.get(i);
            button.setText(str);
            namesPanel.add(button);
            button.addActionListener(new AbstractAction()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String direction = new String();
                    int j = jbuttons.indexOf((JButton) e.getSource());
                    String type = jbuttons.get(j).getText();
                    if(j < 10)
                    {
                        if (j > 0 && j < 6)
                        {
                            direction = JOptionPane.showInputDialog("Choose Your Direction:");
                        }

                        JButton btn = (JButton) jbuttons.get(j);
                        String location = JOptionPane.showInputDialog(btn.getText() + "'s Place:");
                        try
                        {
                            jButtonsMap.get(Integer.parseInt(location)).setText(type + direction);
                            condition[Integer.parseInt(location)]++;
                        }
                        catch (Exception ex)
                        {
                            System.out.println("catch executed");
                            for (int k = 0; k < rows * columns; k++) {
                                if (((JButton) jButtonsMap.get(k)).getText().charAt(0) == location.charAt(0) && ((JButton) jButtonsMap.get(k)).getText().charAt(1) == location.charAt(1)) {
                                    System.out.println(k);
                                    ((JButton) jButtonsMap.get(k)).setText(type + direction);
                                    break;
                                }
                            }
                        }
                    }
                    else if (j == 11)
                    {
                        int head = Integer.parseInt(JOptionPane.showInputDialog("The head of Connection"));
                        int tail = Integer.parseInt(JOptionPane.showInputDialog("The tail of Connection"));

                        int xhead = (head/columns)*2;
                        int yhead = (head%columns)*2;
                        int xtail = (tail/columns)*2;
                        int ytail = (tail%columns)*2;

                        adjacency[(xhead+xtail)/2][(yhead+ytail)/2] = 0;

                    }

                    else if(j == 10)  // 0 age bashe yani khune visible nist , 1 age bashe yani khune hast vali khalie , age 2 bashe yani pore
                    {
                        int vertex = Integer.parseInt(JOptionPane.showInputDialog("The Vertex"));
                        if(condition[vertex] == 1)
                        {
                            ((JButton)jButtonsMap.get(vertex)).setVisible(false);
                            adjacency[(vertex/columns)*2][(vertex%columns)*2] = 0;

                            try
                            {
                                adjacency[(vertex/columns)*2][(vertex%columns)*2 - 1] = 0;
                            }
                            catch (Exception e1)
                            {

                            }
                            try
                            {
                                adjacency[(vertex/columns)*2][(vertex%columns)*2 + 1] = 0;
                            }
                            catch (Exception e1)
                            {

                            }
                            try
                            {
                                adjacency[(vertex/columns)*2 - 1][(vertex%columns)*2] = 0;
                            }
                            catch (Exception e1)
                            {

                            }
                            try
                            {
                                adjacency[(vertex/columns)*2 + 1][(vertex%columns)*2] = 0;
                            }
                            catch (Exception e1)
                            {

                            }
                        }
                        else if(condition[vertex] == 2)
                        {
                            ((JButton)jButtonsMap.get(vertex)).setText(Integer.toString(vertex));
                            condition[vertex]--;
                        }

                    }
                    else if(j == 12)
                    {
                        try
                        {
                            W2F.write(rows,columns,adjacency,jButtonsMap);
                        }
                        catch (FileNotFoundException e1)
                        {

                        }
                    }
                    else if(j == 13)
                    {
                        JButton btn = (JButton) jbuttons.get(j);
                        String location = JOptionPane.showInputDialog(btn.getText() + "'s Place:");
                        try
                        {
                            jButtonsMap.get(Integer.parseInt(location)).setText(type + direction);
                            condition[Integer.parseInt(location)]++;
                        }
                        catch (Exception ex)
                        {
                            System.out.println("catch executed");
                            for (int k = 0; k < rows * columns; k++) {
                                if (((JButton) jButtonsMap.get(k)).getText().charAt(0) == location.charAt(0) && ((JButton) jButtonsMap.get(k)).getText().charAt(1) == location.charAt(1)) {
                                    System.out.println(k);
                                    ((JButton) jButtonsMap.get(k)).setText(type + direction);
                                    break;
                                }
                            }
                        }
                    }
                }
            });
        }

        frame.add(namesPanel ,BorderLayout.EAST);
        frame.add(mapPanel , BorderLayout.WEST);
        frame.setSize(900,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


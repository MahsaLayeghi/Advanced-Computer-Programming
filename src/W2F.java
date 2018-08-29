package project;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by free on 05/23/2015.
 */
public class W2F
{
    static void write(int rows, int columns, int[][] adjacency, ArrayList<JButton> jButtonsMap) throws FileNotFoundException
    {
        final PrintWriter pw = new PrintWriter("SaveMap.txt");
        pw.println(rows);
        pw.println(columns);
        for(int m = 0; m < adjacency.length;m++)
        {
            for(int n = 0 ;n < adjacency[m].length; n++)
                pw.println(adjacency[m][n]);
        }
        for (int k = 0; k < columns * rows; k++)
        {
            if (((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'H')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'S' && ((JButton) jButtonsMap.get(k)).getText().charAt(1) == 'p')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'S' && ((JButton) jButtonsMap.get(k)).getText().charAt(1) == 't' && ((JButton) jButtonsMap.get(k)).getText().charAt(2) == 'a')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'D')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'S' && ((JButton) jButtonsMap.get(k)).getText().charAt(1) == 'i')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'S' && ((JButton) jButtonsMap.get(k)).getText().charAt(1) == 'm')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'G')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'S' && ((JButton) jButtonsMap.get(k)).getText().charAt(1) == 't' && ((JButton) jButtonsMap.get(k)).getText().charAt(2) == 'o')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'U')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
            else if(((JButton) jButtonsMap.get(k)).getText().charAt(0) == 'V')
            {
                pw.println(k);
                pw.println(((JButton)jButtonsMap.get(k)).getText());
            }
        }
        pw.close();
    }
}

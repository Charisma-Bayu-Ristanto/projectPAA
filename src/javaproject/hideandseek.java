
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

/**
 *
 * @author bayuristanto
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class hideandseek extends JPanel implements ActionListener {
    private final int CELL_SIZE = 30;
    private final int CELL = 15;
    private Droid reddroid;
    private Droid greendroid;
    private final ArrayList<Droid> redDroids = new ArrayList<>();
    private final int[][] matrix = new int[CELL][CELL];
    private JButton resetButton;
    private JButton acakdroidmerah;
    private JButton acakdroidhijau;
    private JButton droidbergerak;
    
    private JButton tambahkandroidmerah;
    private JButton sudutpandangdroidmerah;
    private JButton sudutpandangdroidhijau;
    private boolean hideGreenDroid = false;
    


    public hideandseek() {
        setPreferredSize(new Dimension(500, 500));
        setLayout(null);
        init();
        mulai();
    }

    private void mulai() {
        generateMaze();
        acakDroid(reddroid);
        acakDroid(greendroid);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.LIGHT_GRAY);

        g.setColor(Color.gray);
        for (int i = 0; i < CELL; i++) {
            for (int j = 0; j < CELL; j++) {
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }

        g.setColor(Color.BLACK);
        for (int i = 0; i < CELL; i++) {
            for (int j = 0; j < CELL; j++) {
                if (matrix[i][j] == 1) {
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }
        
        reddroid.draw(g);

    if (!hideGreenDroid) {
        greendroid.draw(g);
    }
    
    for (Droid redDroid : redDroids) {
        redDroid.draw(g);
    }
    }

    private void init() {
        resetButton = new JButton("Acak Map");
        resetButton.setBounds(600, 40, 250, 35);
        resetButton.addActionListener(this);

        acakdroidmerah = new JButton("Acak Droid Merah");
        acakdroidmerah.setBounds(600, 80, 250, 35);
        acakdroidmerah.addActionListener(this);

        acakdroidhijau = new JButton("Acak Droid Hijau");
        acakdroidhijau.setBounds(600, 120, 250, 35);
        acakdroidhijau.addActionListener(this);

        droidbergerak = new JButton("Mulai Game");
        droidbergerak.setBounds(600, 160, 250, 35);
        droidbergerak.addActionListener(this);
        
        
        tambahkandroidmerah = new JButton("Tambah Droid");
        tambahkandroidmerah.setBounds(600, 240, 250, 35);
        tambahkandroidmerah.addActionListener(this);
        
        sudutpandangdroidmerah = new JButton("Sudut pandang Droid Merah");
        sudutpandangdroidmerah.setBounds(600, 280, 250, 35);
        sudutpandangdroidmerah.addActionListener(this);
        
        sudutpandangdroidhijau = new JButton("Sudut pandang Droid Hijau");
        sudutpandangdroidhijau.setBounds(600, 320, 250, 35);
        sudutpandangdroidhijau.addActionListener(this);
        
        reddroid = new Droid(Color.RED);
        greendroid = new Droid(Color.GREEN);
        add(resetButton);
        add(acakdroidmerah);
        add(acakdroidhijau);
        add(droidbergerak);
        add(tambahkandroidmerah);
        add(sudutpandangdroidmerah);
        add(sudutpandangdroidhijau);
    }

    // Droid class
    public class Droid {
        private int x;
        private int y;
        private final Color color;

        public Droid(Color color) {
            this.color = color;
        }

        public void setLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void draw(Graphics g) {
            int xPos = x * CELL_SIZE;
            int yPos = y * CELL_SIZE;

            g.setColor(color);
            g.fillOval(xPos, yPos, CELL_SIZE, CELL_SIZE);
        }
    }

    // map atau labirin
    private void generateMaze() {
        for (int i = 0; i < CELL; i++) {
            for (int j = 0; j < CELL; j++) {
                matrix[i][j] = 1;
            }
        }

        recursiveBacktracking(0, 0);
    }

    private void recursiveBacktracking(int row, int col) {
        matrix[row][col] = 0;

        int[] directions = { 0, 1, 2, 3 };
        shuffleArray(directions);

        for (int direction : directions) {
            int newRow = row;
            int newCol = col;

            if (direction == 0 && newRow > 1) {
                newRow -= 2;
            } else if (direction == 1 && newCol < CELL - 2) {
                newCol += 2;
            } else if (direction == 2 && newRow < CELL - 2) {
                newRow += 2;
            } else if (direction == 3 && newCol > 1) {
                newCol -= 2;
            }

            if (matrix[newRow][newCol] == 1) {
                matrix[(newRow + row) / 2][(newCol + col) / 2] = 0;
                recursiveBacktracking(newRow, newCol);
            }
        }
    }

    private void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    // acak droid
    private void acakDroid(Droid droid) {
        Random rand = new Random();
        int newX, newY;

        do {
            newX = rand.nextInt(CELL);
            newY = rand.nextInt(CELL);
        } while (matrix[newY][newX] != 0);

        droid.setLocation(newX, newY);
    }
    
    // mengecek lintasan
    private boolean isCellValid(int x, int y) {
        return x >= 0 && x < CELL && y >= 0 && y < CELL && matrix[y][x] == 0;
    }
    
    //membuat matrix jarak
    private int[][] createDistanceMatrix() {
        int[][] distance = new int[CELL][CELL];
        for (int i = 0; i < CELL; i++) {
            for (int j = 0; j < CELL; j++) {
                distance[i][j] = -1;
            }
        }
        return distance;
    }
    
    //algoritma gerak droid merah menggunakan bfs
    private void caridroidhijau() {
    int[][] distance = createDistanceMatrix();
    boolean[][] visited = new boolean[CELL][CELL];
    Queue<Point> queue = new LinkedList<>();

    int startX = reddroid.getX();
    int startY = reddroid.getY();

    distance[startY][startX] = 0;
    visited[startY][startX] = true;
    queue.add(new Point(startX, startY));

    while (!queue.isEmpty()) {
        Point current = queue.poll();
        int x = current.x;
        int y = current.y;

        if (matrix[y][x] == 2) { // Jika ditemukan droid hijau
            break;
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isCellValid(newX, newY) && !visited[newY][newX]) {
                distance[newY][newX] = distance[y][x] + 1;
                visited[newY][newX] = true;
                queue.add(new Point(newX, newY));
            }
        }
    }

    // Backtracking mencari jalur terpendek
    ArrayList<Point> path = new ArrayList<>();
    int x = greendroid.getX();
    int y = greendroid.getY();
    while (x != startX || y != startY) {
        path.add(new Point(x, y));

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];

            if (isCellValid(newX, newY) && distance[newY][newX] == distance[y][x] - 1) {
                x = newX;
                y = newY;
                break;
            }
        }
    }
    Collections.reverse(path);

    // bergerak ke jalur terpendek
    Timer timer = new Timer(250, new ActionListener() {
        int index = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (index < path.size()) {
                Point point = path.get(index);
                int newX = point.x;
                int newY = point.y;
                reddroid.setLocation(newX, newY);
                repaint();
                index++;
            } else {
                ((Timer) e.getSource()).stop();
            }
        }
    });
    timer.start();
    }
    
    //tambah droid merah
    private void tambahDroidMerah() {
    Droid newRedDroid = new Droid(Color.RED);
    acakDroid(newRedDroid);
    redDroids.add(newRedDroid);
    repaint();
}
    
    

    
    
@Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == resetButton) {
        generateMaze();
        repaint();
    } else if (e.getSource() == acakdroidmerah) {
        acakDroid(reddroid);
        repaint();
    } else if (e.getSource() == acakdroidhijau) {
        acakDroid(greendroid);
        repaint();
    } else if (e.getSource() == droidbergerak) {
        caridroidhijau();
    }else if (e.getSource() == tambahkandroidmerah) {
        tambahDroidMerah();
    } else if (e.getSource() == sudutpandangdroidmerah) {
        hideGreenDroid = !hideGreenDroid;
        repaint();
    } else if (e.getSource() == sudutpandangdroidhijau) {
       
    }
  }

}

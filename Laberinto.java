import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Laberinto extends JFrame implements KeyListener {
    JButton[][] Tablero;
    JPanel Panel_juego;
    String persona = "\u26F9";
    int Persona_x;
    int Persona_y;

    public static void main(String[] args) {
        Laberinto laberinto = new Laberinto();
    }

    public Laberinto() {
        super("Laberinto JAVA");
        setBounds(0, 0, 1920, 1080);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font = new Font(null, Font.TRUETYPE_FONT, 60);

        Panel_juego = new JPanel(new GridLayout(8, 8));
        Panel_juego.setBounds(460, 0, 1000, 1000);
        Tablero = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tablero[i][j] = new JButton();
                Panel_juego.add(Tablero[i][j]);
                Tablero[i][j].setFont(font);
            }
        }
        Tablero[0][0].setBackground(new Color(0xDED7AB)); Tablero[0][1].setBackground(new Color(0xDED7AB));
        Tablero[1][0].setBackground(new Color(0x467710)); Tablero[1][1].setBackground(new Color(0xDED7AB));
        Tablero[2][0].setBackground(new Color(0x467710)); Tablero[2][1].setBackground(new Color(0xDED7AB));
        Tablero[3][0].setBackground(new Color(0x467710)); Tablero[3][1].setBackground(new Color(0xDED7AB));
        Tablero[4][0].setBackground(new Color(0x467710)); Tablero[4][1].setBackground(new Color(0x467710));
        Tablero[5][0].setBackground(new Color(0x467710)); Tablero[5][1].setBackground(new Color(0x467710));
        Tablero[6][0].setBackground(new Color(0x467710)); Tablero[6][1].setBackground(new Color(0x467710));
        Tablero[7][0].setBackground(new Color(0x467710)); Tablero[7][1].setBackground(new Color(0x467710));

        Tablero[0][2].setBackground(new Color(0x467710)); Tablero[0][3].setBackground(new Color(0x467710));
        Tablero[1][2].setBackground(new Color(0x467710)); Tablero[1][3].setBackground(new Color(0x467710));
        Tablero[2][2].setBackground(new Color(0x467710)); Tablero[2][3].setBackground(new Color(0xDED7AB));
        Tablero[3][2].setBackground(new Color(0xDED7AB)); Tablero[3][3].setBackground(new Color(0xDED7AB));
        Tablero[4][2].setBackground(new Color(0x467710)); Tablero[4][3].setBackground(new Color(0x467710));
        Tablero[5][2].setBackground(new Color(0xDED7AB)); Tablero[5][3].setBackground(new Color(0xDED7AB));
        Tablero[6][2].setBackground(new Color(0xDED7AB)); Tablero[6][3].setBackground(new Color(0xDED7AB));
        Tablero[7][2].setBackground(new Color(0x467710)); Tablero[7][3].setBackground(new Color(0xDED7AB));

        Tablero[0][4].setBackground(new Color(0x467710)); Tablero[0][5].setBackground(new Color(0x467710));
        Tablero[1][4].setBackground(new Color(0xDED7AB)); Tablero[1][5].setBackground(new Color(0xDED7AB));
        Tablero[2][4].setBackground(new Color(0xDED7AB)); Tablero[2][5].setBackground(new Color(0x467710));
        Tablero[3][4].setBackground(new Color(0x467710)); Tablero[3][5].setBackground(new Color(0x467710));
        Tablero[4][4].setBackground(new Color(0x467710)); Tablero[4][5].setBackground(new Color(0x467710));
        Tablero[5][4].setBackground(new Color(0xDED7AB)); Tablero[5][5].setBackground(new Color(0xDED7AB));
        Tablero[6][4].setBackground(new Color(0x467710)); Tablero[6][5].setBackground(new Color(0x467710));
        Tablero[7][4].setBackground(new Color(0xDED7AB)); Tablero[7][5].setBackground(new Color(0xDED7AB));

        Tablero[0][6].setBackground(new Color(0x467710)); Tablero[0][7].setBackground(new Color(0x467710));
        Tablero[1][6].setBackground(new Color(0xDED7AB)); Tablero[1][7].setBackground(new Color(0x467710));
        Tablero[2][6].setBackground(new Color(0xDED7AB)); Tablero[2][7].setBackground(new Color(0x467710));
        Tablero[3][6].setBackground(new Color(0xDED7AB)); Tablero[3][7].setBackground(new Color(0x467710));
        Tablero[4][6].setBackground(new Color(0xDED7AB)); Tablero[4][7].setBackground(new Color(0x467710));
        Tablero[5][6].setBackground(new Color(0xDED7AB)); Tablero[5][7].setBackground(new Color(0x467710));
        Tablero[6][6].setBackground(new Color(0x467710)); Tablero[6][7].setBackground(new Color(0x467710));
        Tablero[7][6].setBackground(new Color(0xDED7AB));  Tablero[7][7].setBackground(new Color(0xF6DC36));

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Tablero[i][j].getBackground().equals(new Color(0x467710))) {
                    Tablero[i][j].setEnabled(false);
                }
            }
        }

        Persona_x = 0;
        Persona_y = 0;
        Tablero[Persona_x][Persona_y].setText(persona);

        add(Panel_juego);
        addKeyListener(this);


    }

    private void moverPersonaje(int nuevoX, int nuevoY) {
        if (nuevoX >= 0 && nuevoX < 8 && nuevoY >= 0 && nuevoY < 8) {
            Tablero[Persona_x][Persona_y].setText("");
            Persona_x = nuevoX;
            Persona_y = nuevoY;
            Tablero[Persona_x][Persona_y].setText(persona);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if(Tablero[Persona_x-1][Persona_y].isEnabled()) {
                    moverPersonaje(Persona_x - 1, Persona_y);
                    if(Tablero[7][7].getText().equals(persona)){
                        JOptionPane.showMessageDialog(null,"Has ganado");
                    }
                }
                break;
            case KeyEvent.VK_DOWN:
                if(Tablero[Persona_x+1][Persona_y].isEnabled()) {
                    moverPersonaje(Persona_x + 1, Persona_y);
                    if(Tablero[7][7].getText().equals(persona)){
                        JOptionPane.showMessageDialog(null,"Has ganado");
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                if(Tablero[Persona_x][Persona_y-1].isEnabled()) {
                    moverPersonaje(Persona_x, Persona_y - 1);
                    if(Tablero[7][7].getText().equals(persona)){
                        JOptionPane.showMessageDialog(null,"Has ganado");
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(Tablero[Persona_x][Persona_y+1].isEnabled()) {
                    moverPersonaje(Persona_x, Persona_y + 1);
                    if(Tablero[7][7].getText().equals(persona)){
                        JOptionPane.showMessageDialog(null,"Has ganado");
                    }
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

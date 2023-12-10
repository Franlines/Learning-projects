import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Pacman extends JFrame implements KeyListener {
    int [][] tablero =

            {   {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2},
                    {0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,0},
                    {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
                    {0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0},
                    {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
                    {1,1,1,0,0,0,1,1,2,1,1,0,0,0,1,1,1},
                    {0,0,0,0,1,0,1,2,2,2,1,0,1,0,0,0,0},
                    {1,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,1},
                    {0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0},
                    {0,1,1,0,1,0,1,1,1,1,1,0,1,0,1,1,0},
                    {0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0},
                    {0,1,1,1,0,1,1,0,1,0,1,1,0,1,1,1,0},
                    {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2}
            };

    int PersonaX;
    int PersonaY;
    int Contador;
    int Contador_max;
    double Probabilidades;
    Fantasma fantasma1;
    Fantasma fantasma2;
    Fantasma fantasma3;
    Fantasma fantasma4;
    Fantasma fantasma5;
    Fantasma fantasma6;
    Fantasma fantasma7;
    Fantasma fantasma8;
    JButton [][] cuadros;

    public static void main(String[] args) {
        Pacman pacmanPrueba = new Pacman();
    }


    public Pacman(){
        super("programas_propios.Pacman Game");
        setVisible(true);
        setBounds(0,0,50*17,50*13);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel_juego = new JPanel(new GridLayout(13,17));
        panel_juego.setVisible(true);

        PersonaX =10;
        PersonaY = 8;

        fantasma1 = new Fantasma(5,8);
        fantasma2 = new Fantasma(6,7);
        fantasma3 = new Fantasma(6,8);
        fantasma4 = new Fantasma(6,9);
        fantasma5 = new Fantasma(0,0);
        fantasma6 = new Fantasma(12,0);
        fantasma7 = new Fantasma(0,16);
        fantasma8 = new Fantasma(12,16);

        Contador = 0;

        cuadros = new JButton[13][17];
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 17; j++) {
                cuadros[i][j] = new JButton();
                panel_juego.add(cuadros[i][j]);
                Contador_max=13400;
                if(tablero[i][j]==0){
                    cuadros[i][j].setBackground(new Color(0x329632));
                    cuadros[i][j].setText("*");
                    cuadros[i][j].setFont(new Font(null,Font.TRUETYPE_FONT,20));
                    cuadros[i][j].setForeground(Color.YELLOW);
                } else if (tablero[i][j] == 1){
                    cuadros[i][j].setBackground(new Color (0x6043E7));
                } else if (tablero[i][j]==2) {
                    cuadros[i][j].setBackground(new Color(0xC42C2C));
                }else cuadros[PersonaX][PersonaY].setBackground(new Color(0xE5C42B));
            }
        }

        add(panel_juego);
        addKeyListener(this);
        revalidate();
        repaint();

    }
    private void Traspasar_Personaje(int Opcion){
        if (Opcion == 0){
            cuadros[PersonaX][PersonaY].setBackground(new Color(0x329632));
            PersonaX = 6;
            PersonaY = 16;
            cuadros[PersonaX][PersonaY].setText("");
            cuadros[PersonaX][PersonaY].setBackground(new Color(0xE5C42B));
        }

        else if (Opcion == 1) {
            cuadros[PersonaX][PersonaY].setBackground(new Color(0x329632));
            PersonaX = 6;
            PersonaY = 0;
            cuadros[PersonaX][PersonaY].setText("");
            cuadros[PersonaX][PersonaY].setBackground(new Color(0xE5C42B));
        }
    }

    private void moverPersonaje(int nuevoX, int nuevoY) {
        cuadros[PersonaX][PersonaY].setBackground(new Color(0x329632));
        PersonaX = nuevoX;
        PersonaY = nuevoY;
        if(cuadros[PersonaX][PersonaY].getText().equals("*")){
            Contador= Contador +100;
        }
        cuadros[PersonaX][PersonaY].setText("");
        cuadros[PersonaX][PersonaY].setBackground(new Color(0xE5C42B));
    }
    private void Morir (){
        JOptionPane.showMessageDialog(null, "Te han matado \n Puntuación: " + Contador);
    }

    private void Ganar(){
        JOptionPane.showMessageDialog(null, "¡ENHORABUENA! HAS GANADO \n Puntuación: " + Contador);
    }

    public class Fantasma{
        Fantasma(int PosicionX, int PosicionY){
            this.PosicionX = PosicionX;
            this.PosicionY = PosicionY;
        }
        private double cuenta= 0;
        private int PosicionX;
        private int PosicionY;
        private ArrayList<Integer> AccesibleX = new ArrayList<Integer>();
        private ArrayList<Integer> AccesibleY = new ArrayList<Integer>();

        private void calcularn(){
            if(PosicionY+1 < 17 && !cuadros[PosicionX][PosicionY+1].getBackground().equals(new Color (0x6043E7))
                    && !(cuadros[PosicionX][PosicionY+1].getBackground().equals((new Color (0xC42C2C))))){
                AccesibleX.add(PosicionX);
                AccesibleY.add(PosicionY + 1);
                cuenta++;
            }
            if(PosicionY-1 >= 0 && !cuadros[PosicionX][PosicionY-1].getBackground().equals(new Color (0x6043E7))
                    && !(cuadros[PosicionX][PosicionY-1].getBackground().equals((new Color (0xC42C2C)))) ){
                AccesibleX.add(PosicionX);
                AccesibleY.add(PosicionY-1);
                cuenta++;
            }
            if(PosicionX+1 < 13 && !cuadros[PosicionX +1][PosicionY].getBackground().equals(new Color (0x6043E7))
                    && !(cuadros[PosicionX +1][PosicionY].getBackground().equals((new Color (0xC42C2C))))){
                AccesibleX.add(PosicionX+1);
                AccesibleY.add(PosicionY);
                cuenta++;
            }
            if(PosicionX-1 >= 0 && !cuadros[PosicionX -1][PosicionY].getBackground().equals(new Color (0x6043E7))
                    && !(cuadros[PosicionX -1][PosicionY].getBackground().equals((new Color (0xC42C2C))))){
                AccesibleX.add(PosicionX-1);
                AccesibleY.add(PosicionY);
                cuenta++;
            }


        }

        private void moverFantasma() {
            calcularn();
             double Aleatorio =Math.random();

            if(Aleatorio< cuenta/cuenta && Aleatorio > (cuenta -1)/cuenta){
                cuadros[PosicionX][PosicionY].setBackground(new Color(0x329632));
                PosicionX = AccesibleX.get(0);
                PosicionY = AccesibleY.get(0);
                cuadros[PosicionX][PosicionY].setBackground(new Color(0xC42C2C));
                if(PosicionX == PersonaX && PosicionY == PersonaY){
                    Morir();
                }
                AccesibleX.clear();
                AccesibleY.clear();
                cuenta = 0;
            }
            if (Aleatorio< (cuenta -1)/ cuenta && Aleatorio > (cuenta -2)/cuenta){
                cuadros[PosicionX][PosicionY].setBackground(new Color(0x329632));
                PosicionX = AccesibleX.get(1);
                PosicionY = AccesibleY.get(1);
                cuadros[PosicionX][PosicionY].setBackground(new Color(0xC42C2C));
                if(PosicionX == PersonaX && PosicionY == PersonaY){
                    Morir();
                }
                AccesibleX.clear();
                AccesibleY.clear();
                cuenta = 0;
            }
            if (Aleatorio < (cuenta-2)/cuenta && Aleatorio > (cuenta-3)/cuenta){
                cuadros[PosicionX][PosicionY].setBackground(new Color(0x329632));
                PosicionX = AccesibleX.get(2);
                PosicionY = AccesibleY.get(2);
                cuadros[PosicionX][PosicionY].setBackground(new Color(0xC42C2C));
                if(PosicionX == PersonaX && PosicionY == PersonaY){
                    Morir();
                }
                AccesibleX.clear();
                AccesibleY.clear();
                cuenta = 0;
            }
            if (Aleatorio < (cuenta-3)/cuenta){
                cuadros[PosicionX][PosicionY].setBackground(new Color(0x329632));
                PosicionX = AccesibleX.get(3);
                PosicionY = AccesibleY.get(3);
                cuadros[PosicionX][PosicionY].setBackground(new Color(0xC42C2C));
                if(PosicionX == PersonaX && PosicionY == PersonaY){
                    Morir();
                }
                AccesibleX.clear();
                AccesibleY.clear();
                cuenta = 0;
            }
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
                Probabilidades = Math.random()*100;
                if(cuadros[PersonaX-1][PersonaY].getBackground().equals(new Color(0x329632))) {
                    moverPersonaje(PersonaX - 1, PersonaY);
                    fantasma1.moverFantasma();
                    fantasma2.moverFantasma();
                    fantasma3.moverFantasma();
                    fantasma4.moverFantasma();
                    fantasma5.moverFantasma();
                    fantasma6.moverFantasma();
                    fantasma7.moverFantasma();
                    fantasma8.moverFantasma();
                }
                if(Contador == Contador_max){
                    Ganar();
                }
                break;

            case KeyEvent.VK_DOWN:
                if(cuadros[PersonaX+1][PersonaY].getBackground().equals(new Color(0x329632))) {
                    moverPersonaje(PersonaX + 1, PersonaY);
                    fantasma1.moverFantasma();
                    fantasma2.moverFantasma();
                    fantasma3.moverFantasma();
                    fantasma4.moverFantasma();
                    fantasma5.moverFantasma();
                    fantasma6.moverFantasma();
                    fantasma7.moverFantasma();
                    fantasma8.moverFantasma();
                }
                if(Contador == Contador_max){
                    Ganar();
                }
                break;

            case KeyEvent.VK_LEFT:
                if (PersonaX == 6 && PersonaY == 0 ){
                    Traspasar_Personaje(0);
                }

                else if(cuadros[PersonaX][PersonaY-1].getBackground().equals(new Color(0x329632))) {
                    moverPersonaje(PersonaX, PersonaY - 1);
                    fantasma1.moverFantasma();
                    fantasma2.moverFantasma();
                    fantasma3.moverFantasma();
                    fantasma4.moverFantasma();
                    fantasma5.moverFantasma();
                    fantasma6.moverFantasma();
                    fantasma7.moverFantasma();
                    fantasma8.moverFantasma();
                }
                if(Contador == Contador_max){
                    Ganar();
                }
                break;

            case KeyEvent.VK_RIGHT:
                if (PersonaX == 6 && PersonaY == 16 ){
                    Traspasar_Personaje(1);
                }
                else if(cuadros[PersonaX][PersonaY+1].getBackground().equals(new Color(0x329632))) {
                    moverPersonaje(PersonaX, PersonaY + 1);
                    fantasma1.moverFantasma();
                    fantasma2.moverFantasma();
                    fantasma3.moverFantasma();
                    fantasma4.moverFantasma();
                    fantasma5.moverFantasma();
                    fantasma6.moverFantasma();
                    fantasma7.moverFantasma();
                    fantasma8.moverFantasma();

                }
                if(Contador == Contador_max){
                    Ganar();
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
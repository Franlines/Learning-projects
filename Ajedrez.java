import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ajedrez extends JFrame implements ActionListener {
    private final JPanel panelJuego;
    private final JPanel Ganador;
    private final JButton Celdas[][];
    private final String reina_blanca = "♕";
    private final String rey_blanca = "♔";
    private final String peones_blancos = "♙";
    private final String caballo_blanco = "♘";
    private final String torre_blanca = "♖";
    private final String alfil_blanca = "♗";
    private final String rey_negra = "♚";
    private final String reina_negra = "♛";
    private final String torre_negra = "♜";
    private final String alfil_negra = "♝";
    private final String caballo_negra = "♞";
    private final String peones_negra = "♟";
    private String piezaseleccionada = "";
    private boolean seleccion = false;
    boolean turnoblanco = true;
    boolean turnonegro = false;

    public static void main(String[] args) {
        Ajedrez ajedrez = new Ajedrez();
    }

    public Ajedrez() {
        super("Juego Ajedrez");
        setBounds((1920 - 1080) / 2, 0, 1080, 1000);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font(null, Font.TRUETYPE_FONT, 60);

        Ganador = new JPanel();
        Ganador.setBounds(0, 0, 1080, 30);
        Ganador.setBackground(new Color(0xFF7B7B7E, true));
        add(Ganador);

        panelJuego = new JPanel(new GridLayout(8, 8));
        panelJuego.setBounds(0, 30, 1080, 930);
        Celdas = new JButton[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Celdas[i][j] = new JButton();
                Celdas[i][j].setBorder(BorderFactory.createLineBorder(new Color(0x755539)));
                if ((i + j) % 2 == 1) {
                    Celdas[i][j].setBackground(new Color(0xE8A66D));
                }
                panelJuego.add(Celdas[i][j]);
                Celdas[i][j].repaint();
                Celdas[i][j].revalidate();
                Celdas[i][j].addActionListener(this);
                Celdas[i][j].setFont(font);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Celdas[1][j].setText(peones_blancos);
                Celdas[6][j].setText(peones_negra);
            }
        }
        Celdas[0][0].setText(torre_blanca);
        Celdas[0][1].setText(caballo_blanco);
        Celdas[0][2].setText(alfil_blanca);
        Celdas[0][3].setText(reina_blanca);
        Celdas[0][4].setText(rey_blanca);
        Celdas[0][5].setText(alfil_blanca);
        Celdas[0][6].setText(caballo_blanco);
        Celdas[0][7].setText(torre_blanca);

        Celdas[7][0].setText(torre_negra);
        Celdas[7][1].setText(caballo_negra);
        Celdas[7][2].setText(alfil_negra);
        Celdas[7][4].setText(reina_negra);
        Celdas[7][3].setText(rey_negra);
        Celdas[7][5].setText(alfil_negra);
        Celdas[7][6].setText(caballo_negra);
        Celdas[7][7].setText(torre_negra);


        add(panelJuego);
        repaint();
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();

        //PIEZAS BLANCAS
        //PEONES BLANCOS
        if (turnoblanco && boton.getText().equals(peones_blancos) && !seleccion) {
            piezaseleccionada = boton.getText();
            boton.setText("");
            seleccion = true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        Celdas[i][j].setEnabled(true);
                        //CELDAS DE SALIDA
                        if(boton.equals(Celdas[1][j])){
                            if (Celdas[2][j].getText().isEmpty()) {
                                Celdas[2][j].setEnabled(true);
                            }
                            if(Celdas[3][j].getText().isEmpty()){
                                Celdas[3][j].setEnabled(true);
                            }
                        }
                        //CELDAS NORMALES
                        if ((i + 1) < 7) {
                            if (Celdas[i + 1][j].getText().isEmpty()) {
                                Celdas[i + 1][j].setEnabled(true);
                            }
                        }

                        //CELDAS DIAGONALES
                        if ((i + 1 < 8) && (j + 1 < 8)) {
                            if (Celdas[i + 1][j + 1].getText().equals(peones_negra)||Celdas[i + 1][j + 1].getText().equals(torre_negra)||Celdas[i + 1][j + 1].getText().equals(caballo_negra)
                                    ||Celdas[i + 1][j + 1].getText().equals(alfil_negra)||Celdas[i + 1][j + 1].getText().equals(reina_negra)||Celdas[i + 1][j + 1].getText().equals(rey_negra)) {
                                Celdas[i + 1][j + 1].setEnabled(true);
                            }
                        }

                        if ((i + 1 < 8) && (j - 1) >= 0) {
                            if (Celdas[i + 1][j - 1].getText().equals(peones_negra)||Celdas[i + 1][j - 1].getText().equals(torre_negra)||Celdas[i + 1][j - 1].getText().equals(caballo_negra)
                                    ||Celdas[i + 1][j - 1].getText().equals(reina_negra)||Celdas[i + 1][j - 1].getText().equals(rey_negra)||Celdas[i + 1][j - 1].getText().equals(alfil_negra)) {
                                Celdas[i + 1][j - 1].setEnabled(true);
                            }
                        }
                    }
                }
            }
        }

        //TORRES BLANCAS
        else if (turnoblanco && boton.getText().equals(torre_blanca) && !seleccion) {
            piezaseleccionada = boton.getText();
            boton.setText("");
            seleccion = true;


            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }


            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {

                        for (int d1 = 0; d1 < 8; d1++) {
                            if (i+d1 < 8  && (Celdas[i + d1][j].getText().equals(peones_blancos) ||
                                    Celdas[i + d1][j].getText().equals(torre_blanca) ||
                                    Celdas[i + d1][j].getText().equals(caballo_blanco) ||
                                    Celdas[i + d1][j].getText().equals(alfil_blanca) ||
                                    Celdas[i + d1][j].getText().equals(reina_blanca) ||
                                    Celdas[i + d1][j].getText().equals(rey_blanca))) {
                                break;
                            }
                            else if (i+d1 < 8 && (Celdas[i + d1][j].getText().equals(peones_negra) ||
                                    Celdas[i + d1][j].getText().equals(torre_negra) ||
                                    Celdas[i + d1][j].getText().equals(caballo_negra) ||
                                    Celdas[i + d1][j].getText().equals(alfil_negra) ||
                                    Celdas[i + d1][j].getText().equals(reina_negra) ||
                                    Celdas[i + d1][j].getText().equals(rey_negra))) {
                                Celdas[i+d1][j].setEnabled(true);
                                break;
                            }
                            else if (i+d1 < 8 && Celdas[i + d1][j].getText().isEmpty()){
                                Celdas[i+d1][j].setEnabled(true);
                            }

                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_blancos) ||
                                    Celdas[i - d1][j].getText().equals(torre_blanca) ||
                                    Celdas[i - d1][j].getText().equals(caballo_blanco) ||
                                    Celdas[i - d1][j].getText().equals(alfil_blanca) ||
                                    Celdas[i - d1][j].getText().equals(reina_blanca) ||
                                    Celdas[i - d1][j].getText().equals(rey_blanca))) {
                                break;
                            }

                            else if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_negra) ||
                                    Celdas[i - d1][j].getText().equals(torre_negra) ||
                                    Celdas[i - d1][j].getText().equals(caballo_negra) ||
                                    Celdas[i - d1][j].getText().equals(alfil_negra) ||
                                    Celdas[i - d1][j].getText().equals(reina_negra) ||
                                    Celdas[i - d1][j].getText().equals(rey_negra))) {
                                Celdas[i-d1][j].setEnabled(true);
                                break;
                            }
                            else if (i - d1 >= 0 && Celdas[i -d1][j].getText().isEmpty()){
                                Celdas[i-d1][j].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_blancos) ||
                                    Celdas[i][j + d1].getText().equals(torre_blanca) ||
                                    Celdas[i][j + d1].getText().equals(caballo_blanco) ||
                                    Celdas[i][j + d1].getText().equals(alfil_blanca) ||
                                    Celdas[i][j + d1].getText().equals(reina_blanca) ||
                                    Celdas[i][j + d1].getText().equals(rey_blanca))) {
                                break;
                            }

                            else if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_negra) ||
                                    Celdas[i][j + d1].getText().equals(torre_negra) ||
                                    Celdas[i][j + d1].getText().equals(caballo_negra) ||
                                    Celdas[i][j + d1].getText().equals(alfil_negra) ||
                                    Celdas[i][j + d1].getText().equals(reina_negra) ||
                                    Celdas[i][j + d1].getText().equals(rey_negra))) {
                                Celdas[i][j+d1].setEnabled(true);
                                break;
                            }

                            else if (j+d1 < 8 && Celdas[i][j + d1].getText().isEmpty()) {
                                Celdas[i][j+d1].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_blancos) ||
                                    Celdas[i][j - d1].getText().equals(torre_blanca) ||
                                    Celdas[i][j - d1].getText().equals(caballo_blanco) ||
                                    Celdas[i][j - d1].getText().equals(alfil_blanca) ||
                                    Celdas[i][j - d1].getText().equals(reina_blanca) ||
                                    Celdas[i][j - d1].getText().equals(rey_blanca))) {
                                break;
                            }

                            else if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_negra) ||
                                    Celdas[i][j - d1].getText().equals(torre_negra) ||
                                    Celdas[i][j - d1].getText().equals(caballo_negra) ||
                                    Celdas[i][j - d1].getText().equals(alfil_negra) ||
                                    Celdas[i][j - d1].getText().equals(reina_negra) ||
                                    Celdas[i][j - d1].getText().equals(rey_negra))) {
                                Celdas[i][j-d1].setEnabled(true);
                                break;
                            }
                            else if (j-d1 >= 0 && Celdas[i][j - d1].getText().isEmpty()){
                                Celdas[i][j-d1].setEnabled(true);
                            }
                        }
                    }
                }
            }
        }


        //CABALLOS BLANCOS
        else if (turnoblanco && boton.getText().equals(caballo_blanco) &&!seleccion) { //|| boton.getText().equals(caballo_negra)
            if (!seleccion) {
                piezaseleccionada = boton.getText();
                boton.setText("");
                seleccion = true;
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        Celdas[i][j].setEnabled(true);
                        if ((i + 2) < 8 && (j + 1) < 8 && !(Celdas[i+2][j+1].getText().equals(peones_blancos)||
                                Celdas[i+2][j+1].getText().equals(caballo_blanco)||
                                Celdas[i+2][j+1].getText().equals(torre_blanca)||
                                Celdas[i+2][j+1].getText().equals(alfil_blanca)||
                                Celdas[i+2][j+1].getText().equals(reina_blanca)||
                                Celdas[i+2][j+1].getText().equals(rey_blanca))) {
                            Celdas[i + 2][j + 1].setEnabled(true);
                        }
                        if ((i + 2) < 8 && (j - 1) >= 0 && !(Celdas[i+2][j-1].getText().equals(peones_blancos)||
                                Celdas[i+2][j-1].getText().equals(caballo_blanco)||
                                Celdas[i+2][j-1].getText().equals(torre_blanca)||
                                Celdas[i+2][j-1].getText().equals(alfil_blanca)||
                                Celdas[i+2][j-1].getText().equals(reina_blanca)||
                                Celdas[i+2][j-1].getText().equals(rey_blanca))) {
                            Celdas[i + 2][j - 1].setEnabled(true);
                        }
                        if ((i + 1) < 8 && (j - 2) >= 0 && !(Celdas[i+1][j-2].getText().equals(peones_blancos)||
                                Celdas[i+1][j-2].getText().equals(caballo_blanco)||
                                Celdas[i+1][j-2].getText().equals(torre_blanca)||
                                Celdas[i+1][j-2].getText().equals(alfil_blanca)||
                                Celdas[i+1][j-2].getText().equals(reina_blanca)||
                                Celdas[i+1][j-2].getText().equals(rey_blanca))) {
                            Celdas[i + 1][j - 2].setEnabled(true);
                        }
                        if ((i + 1) < 8 && (j + 2) < 8 && !(Celdas[i+1][j+2].getText().equals(peones_blancos)||
                                Celdas[i+1][j+2].getText().equals(caballo_blanco)||
                                Celdas[i+1][j+2].getText().equals(torre_blanca)||
                                Celdas[i+1][j+2].getText().equals(alfil_blanca)||
                                Celdas[i+1][j+2].getText().equals(reina_blanca)||
                                Celdas[i+1][j+2].getText().equals(rey_blanca))) {
                            Celdas[i + 1][j + 2].setEnabled(true);
                        }
                        if ((i - 2) >= 0 && (j + 1) < 8 && !(Celdas[i-2][j+1].getText().equals(peones_blancos)||
                                Celdas[i-2][j+1].getText().equals(caballo_blanco)||
                                Celdas[i-2][j+1].getText().equals(torre_blanca)||
                                Celdas[i-2][j+1].getText().equals(alfil_blanca)||
                                Celdas[i-2][j+1].getText().equals(reina_blanca)||
                                Celdas[i-2][j+1].getText().equals(rey_blanca))) {
                            Celdas[i - 2][j + 1].setEnabled(true);
                        }
                        if ((i - 2) >= 0 && (j - 1) >= 0&& !(Celdas[i-2][j-1].getText().equals(peones_blancos)||
                                Celdas[i-2][j-1].getText().equals(caballo_blanco)||
                                Celdas[i-2][j-1].getText().equals(torre_blanca)||
                                Celdas[i-2][j-1].getText().equals(alfil_blanca)||
                                Celdas[i-2][j-1].getText().equals(reina_blanca)||
                                Celdas[i-2][j-1].getText().equals(rey_blanca))) {
                            Celdas[i - 2][j - 1].setEnabled(true);
                        }
                        if ((i - 1) >= 0 && (j + 2) < 8 && !(Celdas[i-1][j+2].getText().equals(peones_blancos)||
                                Celdas[i-1][j+2].getText().equals(caballo_blanco)||
                                Celdas[i-1][j+2].getText().equals(torre_blanca)||
                                Celdas[i-1][j+2].getText().equals(alfil_blanca)||
                                Celdas[i-1][j+2].getText().equals(reina_blanca)||
                                Celdas[i-1][j+2].getText().equals(rey_blanca))) {
                            Celdas[i - 1][j + 2].setEnabled(true);
                        }
                        if ((i - 1) >= 0 && (j - 2) >= 0 && !(Celdas[i-1][j-2].getText().equals(peones_blancos)||
                                Celdas[i-1][j-2].getText().equals(caballo_blanco)||
                                Celdas[i-1][j-2].getText().equals(torre_blanca)||
                                Celdas[i-1][j-2].getText().equals(alfil_blanca)||
                                Celdas[i-1][j-2].getText().equals(reina_blanca)||
                                Celdas[i-1][j-2].getText().equals(rey_blanca))) {
                            Celdas[i - 1][j - 2].setEnabled(true);
                        }
                    }
                }
            }
        }


        //ALFILES BLANCOS
        else if (turnoblanco && boton.getText().equals(alfil_blanca) && !seleccion) { //boton.getText().equals(alfil_negra) POR AÑADIR
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            piezaseleccionada = boton.getText();
            boton.setText("");
            seleccion = true;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        for (int d1 = 0; d1 < 8; d1++) {
                            if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_blancos) ||
                                    Celdas[i + d1][j + d1].getText().equals(torre_blanca) ||
                                    Celdas[i + d1][j + d1].getText().equals(caballo_blanco) ||
                                    Celdas[i + d1][j + d1].getText().equals(alfil_blanca) ||
                                    Celdas[i + d1][j + d1].getText().equals(reina_blanca) ||
                                    Celdas[i + d1][j + d1].getText().equals(rey_blanca))) {
                                break;
                            }
                            else if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(torre_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(caballo_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(alfil_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(reina_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(rey_negra))) {
                                Celdas[i+d1][j+d1].setEnabled(true);
                                break;
                            }
                            else if (i+d1 < 8 && j+d1 < 8 && Celdas[i + d1][j + d1].getText().isEmpty()){
                                Celdas[i+d1][j+d1].setEnabled(true);
                            }

                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_blancos) ||
                                    Celdas[i + d1][j - d1].getText().equals(torre_blanca) ||
                                    Celdas[i + d1][j - d1].getText().equals(caballo_blanco) ||
                                    Celdas[i + d1][j - d1].getText().equals(alfil_blanca) ||
                                    Celdas[i + d1][j - d1].getText().equals(reina_blanca) ||
                                    Celdas[i + d1][j - d1].getText().equals(rey_blanca))) {
                                break;
                            }

                            else if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(torre_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(caballo_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(alfil_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(reina_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(rey_negra))) {
                                Celdas[i+d1][j-d1].setEnabled(true);
                                break;
                            }
                            else if (i + d1 < 8 && j - d1 >= 0 && Celdas[i + d1][j - d1].getText().isEmpty()){
                                Celdas[i+d1][j-d1].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_blancos) ||
                                    Celdas[i - d1][j + d1].getText().equals(torre_blanca) ||
                                    Celdas[i - d1][j + d1].getText().equals(caballo_blanco) ||
                                    Celdas[i - d1][j + d1].getText().equals(alfil_blanca) ||
                                    Celdas[i - d1][j + d1].getText().equals(reina_blanca) ||
                                    Celdas[i - d1][j + d1].getText().equals(rey_blanca))) {
                                break;
                            }

                            else if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(torre_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(caballo_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(alfil_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(reina_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(rey_negra))) {
                                Celdas[i-d1][j+d1].setEnabled(true);
                                break;
                            }

                            else if (i-d1 >= 0 && j+d1 < 8 && Celdas[i - d1][j + d1].getText().isEmpty()) {
                                Celdas[i-d1][j+d1].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_blancos) ||
                                    Celdas[i - d1][j - d1].getText().equals(torre_blanca) ||
                                    Celdas[i - d1][j - d1].getText().equals(caballo_blanco) ||
                                    Celdas[i - d1][j - d1].getText().equals(alfil_blanca) ||
                                    Celdas[i - d1][j - d1].getText().equals(reina_blanca) ||
                                    Celdas[i - d1][j - d1].getText().equals(rey_blanca))) {
                                break;
                            }

                            else if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(torre_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(caballo_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(alfil_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(reina_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(rey_negra))) {
                                Celdas[i-d1][j-d1].setEnabled(true);
                                break;
                            }
                            else if (i-d1 >= 0 && j-d1 >= 0 && Celdas[i - d1][j - d1].getText().isEmpty()){
                                Celdas[i-d1][j-d1].setEnabled(true);
                            }
                        }
                    }
                }
            }
        }


        //REINA BLANCA
        else if (turnoblanco && boton.getText().equals(reina_blanca) && !seleccion) {
            if (!seleccion) {
                piezaseleccionada = boton.getText();
                boton.setText("");
                seleccion = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                     Celdas[i][j].setEnabled(false);
                    }
                }

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (boton.equals(Celdas[i][j])) {
                            for (int d1 = 0; d1 < 8; d1++) {
                                if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_blancos) ||
                                        Celdas[i + d1][j + d1].getText().equals(torre_blanca) ||
                                        Celdas[i + d1][j + d1].getText().equals(caballo_blanco) ||
                                        Celdas[i + d1][j + d1].getText().equals(alfil_blanca) ||
                                        Celdas[i + d1][j + d1].getText().equals(reina_blanca) ||
                                        Celdas[i + d1][j + d1].getText().equals(rey_blanca))) {
                                    break;
                                }
                                else if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(torre_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(caballo_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(alfil_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(reina_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(rey_negra))) {
                                    Celdas[i+d1][j+d1].setEnabled(true);
                                    break;
                                }
                                else if (i+d1 < 8 && j+d1 < 8 && Celdas[i + d1][j + d1].getText().isEmpty()){
                                    Celdas[i+d1][j+d1].setEnabled(true);
                                }

                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_blancos) ||
                                        Celdas[i + d1][j - d1].getText().equals(torre_blanca) ||
                                        Celdas[i + d1][j - d1].getText().equals(caballo_blanco) ||
                                        Celdas[i + d1][j - d1].getText().equals(alfil_blanca) ||
                                        Celdas[i + d1][j - d1].getText().equals(reina_blanca) ||
                                        Celdas[i + d1][j - d1].getText().equals(rey_blanca))) {
                                    break;
                                }

                                else if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(torre_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(caballo_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(alfil_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(reina_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(rey_negra))) {
                                    Celdas[i+d1][j-d1].setEnabled(true);
                                    break;
                                }
                                else if (i + d1 < 8 && j - d1 >= 0 && Celdas[i + d1][j - d1].getText().isEmpty()){
                                    Celdas[i+d1][j-d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_blancos) ||
                                        Celdas[i - d1][j + d1].getText().equals(torre_blanca) ||
                                        Celdas[i - d1][j + d1].getText().equals(caballo_blanco) ||
                                        Celdas[i - d1][j + d1].getText().equals(alfil_blanca) ||
                                        Celdas[i - d1][j + d1].getText().equals(reina_blanca) ||
                                        Celdas[i - d1][j + d1].getText().equals(rey_blanca))) {
                                    break;
                                }

                                else if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(torre_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(caballo_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(alfil_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(reina_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(rey_negra))) {
                                    Celdas[i-d1][j+d1].setEnabled(true);
                                    break;
                                }

                                else if (i-d1 >= 0 && j+d1 < 8 && Celdas[i - d1][j + d1].getText().isEmpty()) {
                                    Celdas[i-d1][j+d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_blancos) ||
                                        Celdas[i - d1][j - d1].getText().equals(torre_blanca) ||
                                        Celdas[i - d1][j - d1].getText().equals(caballo_blanco) ||
                                        Celdas[i - d1][j - d1].getText().equals(alfil_blanca) ||
                                        Celdas[i - d1][j - d1].getText().equals(reina_blanca) ||
                                        Celdas[i - d1][j - d1].getText().equals(rey_blanca))) {
                                    break;
                                }

                                else if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(torre_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(caballo_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(alfil_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(reina_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(rey_negra))) {
                                    Celdas[i-d1][j-d1].setEnabled(true);
                                    break;
                                }
                                else if (i-d1 >= 0 && j-d1 >= 0 && Celdas[i - d1][j - d1].getText().isEmpty()){
                                    Celdas[i-d1][j-d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if (i+d1 < 8  && (Celdas[i + d1][j].getText().equals(peones_blancos) ||
                                        Celdas[i + d1][j].getText().equals(torre_blanca) ||
                                        Celdas[i + d1][j].getText().equals(caballo_blanco) ||
                                        Celdas[i + d1][j].getText().equals(alfil_blanca) ||
                                        Celdas[i + d1][j].getText().equals(reina_blanca) ||
                                        Celdas[i + d1][j].getText().equals(rey_blanca))) {
                                    break;
                                }
                                else if (i+d1 < 8 && (Celdas[i + d1][j].getText().equals(peones_negra) ||
                                        Celdas[i + d1][j].getText().equals(torre_negra) ||
                                        Celdas[i + d1][j].getText().equals(caballo_negra) ||
                                        Celdas[i + d1][j].getText().equals(alfil_negra) ||
                                        Celdas[i + d1][j].getText().equals(reina_negra) ||
                                        Celdas[i + d1][j].getText().equals(rey_negra))) {
                                    Celdas[i+d1][j].setEnabled(true);
                                    break;
                                }
                                else if (i+d1 < 8 && Celdas[i + d1][j].getText().isEmpty()){
                                    Celdas[i+d1][j].setEnabled(true);
                                }

                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_blancos) ||
                                        Celdas[i - d1][j].getText().equals(torre_blanca) ||
                                        Celdas[i - d1][j].getText().equals(caballo_blanco) ||
                                        Celdas[i - d1][j].getText().equals(alfil_blanca) ||
                                        Celdas[i - d1][j].getText().equals(reina_blanca) ||
                                        Celdas[i - d1][j].getText().equals(rey_blanca))) {
                                    break;
                                }

                                else if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_negra) ||
                                        Celdas[i - d1][j].getText().equals(torre_negra) ||
                                        Celdas[i - d1][j].getText().equals(caballo_negra) ||
                                        Celdas[i - d1][j].getText().equals(alfil_negra) ||
                                        Celdas[i - d1][j].getText().equals(reina_negra) ||
                                        Celdas[i - d1][j].getText().equals(rey_negra))) {
                                    Celdas[i-d1][j].setEnabled(true);
                                    break;
                                }
                                else if (i - d1 >= 0 && Celdas[i -d1][j].getText().isEmpty()){
                                    Celdas[i-d1][j].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_blancos) ||
                                        Celdas[i][j + d1].getText().equals(torre_blanca) ||
                                        Celdas[i][j + d1].getText().equals(caballo_blanco) ||
                                        Celdas[i][j + d1].getText().equals(alfil_blanca) ||
                                        Celdas[i][j + d1].getText().equals(reina_blanca) ||
                                        Celdas[i][j + d1].getText().equals(rey_blanca))) {
                                    break;
                                }

                                else if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_negra) ||
                                        Celdas[i][j + d1].getText().equals(torre_negra) ||
                                        Celdas[i][j + d1].getText().equals(caballo_negra) ||
                                        Celdas[i][j + d1].getText().equals(alfil_negra) ||
                                        Celdas[i][j + d1].getText().equals(reina_negra) ||
                                        Celdas[i][j + d1].getText().equals(rey_negra))) {
                                    Celdas[i][j+d1].setEnabled(true);
                                    break;
                                }

                                else if (j+d1 < 8 && Celdas[i][j + d1].getText().isEmpty()) {
                                    Celdas[i][j+d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_blancos) ||
                                        Celdas[i][j - d1].getText().equals(torre_blanca) ||
                                        Celdas[i][j - d1].getText().equals(caballo_blanco) ||
                                        Celdas[i][j - d1].getText().equals(alfil_blanca) ||
                                        Celdas[i][j - d1].getText().equals(reina_blanca) ||
                                        Celdas[i][j - d1].getText().equals(rey_blanca))) {
                                    break;
                                }

                                else if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_negra) ||
                                        Celdas[i][j - d1].getText().equals(torre_negra) ||
                                        Celdas[i][j - d1].getText().equals(caballo_negra) ||
                                        Celdas[i][j - d1].getText().equals(alfil_negra) ||
                                        Celdas[i][j - d1].getText().equals(reina_negra) ||
                                        Celdas[i][j - d1].getText().equals(rey_negra))) {
                                    Celdas[i][j-d1].setEnabled(true);
                                    break;
                                }
                                else if (j-d1 >= 0 && Celdas[i][j - d1].getText().isEmpty()){
                                    Celdas[i][j-d1].setEnabled(true);
                                }
                            }



                        }
                    }
                }

            }
        }

        //REY BLANCO
        else if (turnoblanco && boton.getText().equals(rey_blanca) && !seleccion) {
                piezaseleccionada = boton.getText();
                boton.setText("");
                seleccion = true;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        Celdas[i][j].setEnabled(true);
                        if (j + 1 < 8 && i + 1 < 8 && (Celdas[i+1][j+1].getText().isEmpty() ||
                                Celdas[i+1][j+1].getText().equals(peones_negra) ||
                                Celdas[i+1][j+1].getText().equals(torre_negra) ||
                                Celdas[i+1][j+1].getText().equals(alfil_negra) ||
                                Celdas[i+1][j+1].getText().equals(caballo_negra) ||
                                Celdas[i+1][j+1].getText().equals(reina_negra)||
                                Celdas[i+1][j+1].getText().equals(rey_negra))) {
                            Celdas[i + 1][j + 1].setEnabled(true);
                        }
                        if (i+1 < 8 && j-1 >= 0  && (Celdas[i+1][j-1].getText().isEmpty() ||
                                Celdas[i+1][j-1].getText().equals(peones_negra) ||
                                Celdas[i+1][j-1].getText().equals(torre_negra) ||
                                Celdas[i+1][j-1].getText().equals(alfil_negra) ||
                                Celdas[i+1][j-1].getText().equals(caballo_negra) ||
                                Celdas[i+1][j-1].getText().equals(reina_negra)||
                                Celdas[i+1][j-1].getText().equals(rey_negra))) {
                            Celdas[i + 1][j - 1].setEnabled(true);
                        }
                        if (j + 1 < 8 && i - 1 >= 0  && (Celdas[i-1][j+1].getText().isEmpty() ||
                                Celdas[i-1][j+1].getText().equals(peones_negra) ||
                                Celdas[i-1][j+1].getText().equals(torre_negra) ||
                                Celdas[i-1][j+1].getText().equals(alfil_negra) ||
                                Celdas[i-1][j+1].getText().equals(caballo_negra) ||
                                Celdas[i-1][j+1].getText().equals(reina_negra)||
                                Celdas[i-1][j+1].getText().equals(rey_negra))) {
                            Celdas[i - 1][j + 1].setEnabled(true);
                        }
                        if (j - 1 >= 0 && i - 1 >= 0  && (Celdas[i-1][j-1].getText().isEmpty() ||
                                Celdas[i-1][j-1].getText().equals(peones_negra) ||
                                Celdas[i-1][j-1].getText().equals(torre_negra) ||
                                Celdas[i-1][j-1].getText().equals(alfil_negra) ||
                                Celdas[i-1][j-1].getText().equals(caballo_negra) ||
                                Celdas[i-1][j-1].getText().equals(reina_negra)||
                                Celdas[i-1][j-1].getText().equals(rey_negra))) {
                            Celdas[i - 1][j - 1].setEnabled(true);
                        }
                        if (i + 1 < 8  && (Celdas[i+1][j].getText().isEmpty() ||
                                Celdas[i+1][j].getText().equals(peones_negra) ||
                                Celdas[i+1][j].getText().equals(torre_negra) ||
                                Celdas[i+1][j].getText().equals(alfil_negra) ||
                                Celdas[i+1][j].getText().equals(caballo_negra) ||
                                Celdas[i+1][j].getText().equals(reina_negra)||
                                Celdas[i+1][j].getText().equals(rey_negra))) {
                            Celdas[i + 1][j].setEnabled(true);
                        }
                        if (i - 1 >= 0  && (Celdas[i-1][j].getText().isEmpty() ||
                                Celdas[i-1][j].getText().equals(peones_negra) ||
                                Celdas[i-1][j].getText().equals(torre_negra) ||
                                Celdas[i-1][j].getText().equals(alfil_negra) ||
                                Celdas[i-1][j].getText().equals(caballo_negra) ||
                                Celdas[i-1][j].getText().equals(reina_negra)||
                                Celdas[i-1][j].getText().equals(rey_negra))) {
                            Celdas[i - 1][j].setEnabled(true);
                        }
                        if (j + 1 < 8  && (Celdas[i][j+1].getText().isEmpty() ||
                                Celdas[i][j+1].getText().equals(peones_negra) ||
                                Celdas[i][j+1].getText().equals(torre_negra) ||
                                Celdas[i][j+1].getText().equals(alfil_negra) ||
                                Celdas[i][j+1].getText().equals(caballo_negra) ||
                                Celdas[i][j+1].getText().equals(reina_negra)||
                                Celdas[i][j+1].getText().equals(rey_negra))) {
                            Celdas[i][j+1].setEnabled(true);
                        }
                        if (j - 1 >= 0  && (Celdas[i][j-1].getText().isEmpty() ||
                                Celdas[i][j-1].getText().equals(peones_negra) ||
                                Celdas[i][j-1].getText().equals(torre_negra) ||
                                Celdas[i][j-1].getText().equals(alfil_negra) ||
                                Celdas[i][j-1].getText().equals(caballo_negra) ||
                                Celdas[i][j-1].getText().equals(reina_negra)||
                                Celdas[i][j-1].getText().equals(rey_negra))) {
                            Celdas[i][j-1].setEnabled(true);
                        }
                    }
                }
            }
        }

        //PIEZAS NEGRAS
        //PEONES NEGROS
        else if (turnonegro && boton.getText().equals(peones_negra) && !seleccion) {
            piezaseleccionada = boton.getText();
            boton.setText("");
            seleccion = true;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        Celdas[i][j].setEnabled(true);
                        //CELDAS DE SALIDA
                        if(boton.equals(Celdas[6][j])){
                            if (Celdas[5][j].getText().isEmpty()) {
                                Celdas[5][j].setEnabled(true);
                            }
                            if(Celdas[4][j].getText().isEmpty()){
                                Celdas[4][j].setEnabled(true);
                            }
                        }

                        //CELDAS NORMALES
                        if ((i - 1) >= 0) {
                            if (Celdas[i - 1][j].getText().isEmpty()) {
                                Celdas[i - 1][j].setEnabled(true);
                            }
                        }

                        //CELDAS DIAGONALES
                        if ((i - 1 >= 0) && (j + 1 < 8)) {
                            if (Celdas[i - 1][j + 1].getText().equals(peones_blancos)||Celdas[i - 1][j + 1].getText().equals(torre_blanca) ||Celdas[i - 1][j + 1].getText().equals(caballo_blanco)
                                    ||Celdas[i - 1][j + 1].getText().equals(alfil_blanca) ||Celdas[i - 1][j + 1].getText().equals(reina_blanca)||Celdas[i - 1][j + 1].getText().equals(rey_blanca)) {
                                Celdas[i - 1][j + 1].setEnabled(true);
                            }
                        }

                        if ((i - 1 >= 0) && (j - 1) >= 0) {
                            if (Celdas[i - 1][j - 1].getText().equals(peones_blancos)||Celdas[i - 1][j - 1].getText().equals(torre_blanca) ||Celdas[i - 1][j - 1].getText().equals(caballo_blanco)
                                    ||Celdas[i - 1][j - 1].getText().equals(alfil_blanca) ||Celdas[i - 1][j - 1].getText().equals(reina_blanca)||Celdas[i - 1][j - 1].getText().equals(rey_blanca)) {
                                Celdas[i - 1][j - 1].setEnabled(true);
                            }
                        }
                    }
                }
            }
        }

        //TORRES NEGRAS
        else if (turnonegro && boton.getText().equals(torre_negra) && !seleccion) {
            piezaseleccionada = boton.getText();
            boton.setText("");
            seleccion = true;


            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }


            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {

                        for (int d1 = 0; d1 < 8; d1++) {
                            if (i+d1 < 8  && (Celdas[i + d1][j].getText().equals(peones_negra) ||
                                    Celdas[i + d1][j].getText().equals(torre_negra) ||
                                    Celdas[i + d1][j].getText().equals(caballo_negra) ||
                                    Celdas[i + d1][j].getText().equals(alfil_negra) ||
                                    Celdas[i + d1][j].getText().equals(reina_negra) ||
                                    Celdas[i + d1][j].getText().equals(rey_negra))) {
                                break;
                            }
                            else if (i+d1 < 8 && (Celdas[i + d1][j].getText().equals(peones_blancos) ||
                                    Celdas[i + d1][j].getText().equals(torre_blanca) ||
                                    Celdas[i + d1][j].getText().equals(caballo_blanco) ||
                                    Celdas[i + d1][j].getText().equals(alfil_blanca) ||
                                    Celdas[i + d1][j].getText().equals(reina_blanca) ||
                                    Celdas[i + d1][j].getText().equals(rey_blanca))) {
                                Celdas[i+d1][j].setEnabled(true);
                                break;
                            }
                            else if (i+d1 < 8 && Celdas[i + d1][j].getText().isEmpty()){
                                Celdas[i+d1][j].setEnabled(true);
                            }

                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_negra) ||
                                    Celdas[i - d1][j].getText().equals(torre_negra) ||
                                    Celdas[i - d1][j].getText().equals(caballo_negra) ||
                                    Celdas[i - d1][j].getText().equals(alfil_negra) ||
                                    Celdas[i - d1][j].getText().equals(reina_negra) ||
                                    Celdas[i - d1][j].getText().equals(rey_negra))) {
                                break;
                            }

                            else if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_blancos) ||
                                    Celdas[i - d1][j].getText().equals(torre_blanca) ||
                                    Celdas[i - d1][j].getText().equals(caballo_blanco) ||
                                    Celdas[i - d1][j].getText().equals(alfil_blanca) ||
                                    Celdas[i - d1][j].getText().equals(reina_blanca) ||
                                    Celdas[i - d1][j].getText().equals(rey_blanca))) {
                                Celdas[i-d1][j].setEnabled(true);
                                break;
                            }
                            else if (i - d1 >= 0 && Celdas[i -d1][j].getText().isEmpty()){
                                Celdas[i-d1][j].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_negra) ||
                                    Celdas[i][j + d1].getText().equals(torre_negra) ||
                                    Celdas[i][j + d1].getText().equals(caballo_negra) ||
                                    Celdas[i][j + d1].getText().equals(alfil_negra) ||
                                    Celdas[i][j + d1].getText().equals(reina_negra) ||
                                    Celdas[i][j + d1].getText().equals(rey_negra))) {
                                break;
                            }

                            else if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_blancos) ||
                                    Celdas[i][j + d1].getText().equals(torre_blanca) ||
                                    Celdas[i][j + d1].getText().equals(caballo_blanco) ||
                                    Celdas[i][j + d1].getText().equals(alfil_blanca) ||
                                    Celdas[i][j + d1].getText().equals(reina_blanca) ||
                                    Celdas[i][j + d1].getText().equals(rey_blanca))) {
                                Celdas[i][j+d1].setEnabled(true);
                                break;
                            }

                            else if (j+d1 < 8 && Celdas[i][j + d1].getText().isEmpty()) {
                                Celdas[i][j+d1].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_negra) ||
                                    Celdas[i][j - d1].getText().equals(torre_negra) ||
                                    Celdas[i][j - d1].getText().equals(caballo_negra) ||
                                    Celdas[i][j - d1].getText().equals(alfil_negra) ||
                                    Celdas[i][j - d1].getText().equals(reina_negra) ||
                                    Celdas[i][j - d1].getText().equals(rey_negra))) {
                                break;
                            }

                            else if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_blancos) ||
                                    Celdas[i][j - d1].getText().equals(torre_blanca) ||
                                    Celdas[i][j - d1].getText().equals(caballo_blanco) ||
                                    Celdas[i][j - d1].getText().equals(alfil_blanca) ||
                                    Celdas[i][j - d1].getText().equals(reina_blanca) ||
                                    Celdas[i][j - d1].getText().equals(rey_blanca))) {
                                Celdas[i][j-d1].setEnabled(true);
                                break;
                            }
                            else if (j-d1 >= 0 && Celdas[i][j - d1].getText().isEmpty()){
                                Celdas[i][j-d1].setEnabled(true);
                            }
                        }
                    }
                }
            }
        }

        //CABALLOS NEGROS
        else if (turnonegro && boton.getText().equals(caballo_negra) &&!seleccion) { //|| boton.getText().equals(caballo_negra)
            if (!seleccion) {
                piezaseleccionada = boton.getText();
                boton.setText("");
                seleccion = true;
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        Celdas[i][j].setEnabled(true);
                        if ((i + 2) < 8 && (j + 1) < 8 && !(Celdas[i+2][j+1].getText().equals(peones_negra)||
                                Celdas[i+2][j+1].getText().equals(caballo_negra)||
                                Celdas[i+2][j+1].getText().equals(torre_negra)||
                                Celdas[i+2][j+1].getText().equals(alfil_negra)||
                                Celdas[i+2][j+1].getText().equals(reina_negra)||
                                Celdas[i+2][j+1].getText().equals(rey_negra))) {
                            Celdas[i + 2][j + 1].setEnabled(true);
                        }
                        if ((i + 2) < 8 && (j - 1) >= 0 && !(Celdas[i+2][j-1].getText().equals(peones_negra)||
                                Celdas[i+2][j-1].getText().equals(caballo_negra)||
                                Celdas[i+2][j-1].getText().equals(torre_negra)||
                                Celdas[i+2][j-1].getText().equals(alfil_negra)||
                                Celdas[i+2][j-1].getText().equals(reina_negra)||
                                Celdas[i+2][j-1].getText().equals(rey_negra))) {
                            Celdas[i + 2][j - 1].setEnabled(true);
                        }
                        if ((i + 1) < 8 && (j - 2) >= 0 && !(Celdas[i+1][j-2].getText().equals(peones_negra)||
                                Celdas[i+1][j-2].getText().equals(caballo_negra)||
                                Celdas[i+1][j-2].getText().equals(torre_negra)||
                                Celdas[i+1][j-2].getText().equals(alfil_negra)||
                                Celdas[i+1][j-2].getText().equals(reina_negra)||
                                Celdas[i+1][j-2].getText().equals(rey_negra))) {
                            Celdas[i + 1][j - 2].setEnabled(true);
                        }
                        if ((i + 1) < 8 && (j + 2) < 8 && !(Celdas[i+1][j+2].getText().equals(peones_negra)||
                                Celdas[i+1][j+2].getText().equals(caballo_negra)||
                                Celdas[i+1][j+2].getText().equals(torre_negra)||
                                Celdas[i+1][j+2].getText().equals(alfil_negra)||
                                Celdas[i+1][j+2].getText().equals(reina_negra)||
                                Celdas[i+1][j+2].getText().equals(rey_negra))) {
                            Celdas[i + 1][j + 2].setEnabled(true);
                        }
                        if ((i - 2) >= 0 && (j + 1) < 8 && !(Celdas[i-2][j+1].getText().equals(peones_negra)||
                                Celdas[i-2][j+1].getText().equals(caballo_negra)||
                                Celdas[i-2][j+1].getText().equals(torre_negra)||
                                Celdas[i-2][j+1].getText().equals(alfil_negra)||
                                Celdas[i-2][j+1].getText().equals(reina_negra)||
                                Celdas[i-2][j+1].getText().equals(rey_negra))) {
                            Celdas[i - 2][j + 1].setEnabled(true);
                        }
                        if ((i - 2) >= 0 && (j - 1) >= 0&& !(Celdas[i-2][j-1].getText().equals(peones_negra)||
                                Celdas[i-2][j-1].getText().equals(caballo_negra)||
                                Celdas[i-2][j-1].getText().equals(torre_negra)||
                                Celdas[i-2][j-1].getText().equals(alfil_negra)||
                                Celdas[i-2][j-1].getText().equals(reina_negra)||
                                Celdas[i-2][j-1].getText().equals(rey_negra))) {
                            Celdas[i - 2][j - 1].setEnabled(true);
                        }
                        if ((i - 1) >= 0 && (j + 2) < 8 && !(Celdas[i-1][j+2].getText().equals(peones_negra)||
                                Celdas[i-1][j+2].getText().equals(caballo_negra)||
                                Celdas[i-1][j+2].getText().equals(torre_negra)||
                                Celdas[i-1][j+2].getText().equals(alfil_negra)||
                                Celdas[i-1][j+2].getText().equals(reina_negra)||
                                Celdas[i-1][j+2].getText().equals(rey_negra))) {
                            Celdas[i - 1][j + 2].setEnabled(true);
                        }
                        if ((i - 1) >= 0 && (j - 2) >= 0 && !(Celdas[i-1][j-2].getText().equals(peones_negra)||
                                Celdas[i-1][j-2].getText().equals(caballo_negra)||
                                Celdas[i-1][j-2].getText().equals(torre_negra)||
                                Celdas[i-1][j-2].getText().equals(alfil_negra)||
                                Celdas[i-1][j-2].getText().equals(reina_negra)||
                                Celdas[i-1][j-2].getText().equals(rey_negra))) {
                            Celdas[i - 1][j - 2].setEnabled(true);
                        }
                    }
                }
            }
        }

        //ALFILES NEGROS
        else if (turnonegro && boton.getText().equals(alfil_negra) && !seleccion) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            piezaseleccionada = boton.getText();
            boton.setText("");
            seleccion = true;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        for (int d1 = 0; d1 < 8; d1++) {
                            if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(torre_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(caballo_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(alfil_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(reina_negra) ||
                                    Celdas[i + d1][j + d1].getText().equals(rey_negra))) {
                                break;
                            }
                            else if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_blancos) ||
                                    Celdas[i + d1][j + d1].getText().equals(torre_blanca) ||
                                    Celdas[i + d1][j + d1].getText().equals(caballo_blanco) ||
                                    Celdas[i + d1][j + d1].getText().equals(alfil_blanca) ||
                                    Celdas[i + d1][j + d1].getText().equals(reina_blanca) ||
                                    Celdas[i + d1][j + d1].getText().equals(rey_blanca))) {
                                Celdas[i+d1][j+d1].setEnabled(true);
                                break;
                            }
                            else if (i+d1 < 8 && j+d1 < 8 && Celdas[i + d1][j + d1].getText().isEmpty()){
                                Celdas[i+d1][j+d1].setEnabled(true);
                            }

                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(torre_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(caballo_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(alfil_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(reina_negra) ||
                                    Celdas[i + d1][j - d1].getText().equals(rey_negra))) {
                                break;
                            }

                            else if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_blancos) ||
                                    Celdas[i + d1][j - d1].getText().equals(torre_blanca) ||
                                    Celdas[i + d1][j - d1].getText().equals(caballo_blanco) ||
                                    Celdas[i + d1][j - d1].getText().equals(alfil_blanca) ||
                                    Celdas[i + d1][j - d1].getText().equals(reina_blanca) ||
                                    Celdas[i + d1][j - d1].getText().equals(rey_blanca))) {
                                Celdas[i+d1][j-d1].setEnabled(true);
                                break;
                            }
                            else if (i + d1 < 8 && j - d1 >= 0 && Celdas[i + d1][j - d1].getText().isEmpty()){
                                Celdas[i+d1][j-d1].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(torre_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(caballo_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(alfil_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(reina_negra) ||
                                    Celdas[i - d1][j + d1].getText().equals(rey_negra))) {
                                break;
                            }

                            else if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_blancos) ||
                                    Celdas[i - d1][j + d1].getText().equals(torre_blanca) ||
                                    Celdas[i - d1][j + d1].getText().equals(caballo_blanco) ||
                                    Celdas[i - d1][j + d1].getText().equals(alfil_blanca) ||
                                    Celdas[i - d1][j + d1].getText().equals(reina_blanca) ||
                                    Celdas[i - d1][j + d1].getText().equals(rey_blanca))) {
                                Celdas[i-d1][j+d1].setEnabled(true);
                                break;
                            }

                            else if (i-d1 >= 0 && j+d1 < 8 && Celdas[i - d1][j + d1].getText().isEmpty()) {
                                Celdas[i-d1][j+d1].setEnabled(true);
                            }
                        }

                        for (int d1 = 0; d1 < 8; d1++) {
                            if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(torre_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(caballo_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(alfil_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(reina_negra) ||
                                    Celdas[i - d1][j - d1].getText().equals(rey_negra))) {
                                break;
                            }

                            else if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_blancos) ||
                                    Celdas[i - d1][j - d1].getText().equals(torre_blanca) ||
                                    Celdas[i - d1][j - d1].getText().equals(caballo_blanco) ||
                                    Celdas[i - d1][j - d1].getText().equals(alfil_blanca) ||
                                    Celdas[i - d1][j - d1].getText().equals(reina_blanca) ||
                                    Celdas[i - d1][j - d1].getText().equals(rey_blanca))) {
                                Celdas[i-d1][j-d1].setEnabled(true);
                                break;
                            }
                            else if (i-d1 >= 0 && j-d1 >= 0 && Celdas[i - d1][j - d1].getText().isEmpty()){
                                Celdas[i-d1][j-d1].setEnabled(true);
                            }
                        }
                    }
                }
            }
        }

        //REINA NEGRA
        else if (turnonegro && boton.getText().equals(reina_negra) && !seleccion) {
            if (!seleccion) {
                piezaseleccionada = boton.getText();
                boton.setText("");
                seleccion = true;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        Celdas[i][j].setEnabled(false);
                    }
                }

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (boton.equals(Celdas[i][j])) {
                            for (int d1 = 0; d1 < 8; d1++) {
                                if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(torre_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(caballo_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(alfil_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(reina_negra) ||
                                        Celdas[i + d1][j + d1].getText().equals(rey_negra))) {
                                    break;
                                }
                                else if (i+d1 < 8 && j+d1 < 8 && (Celdas[i + d1][j + d1].getText().equals(peones_blancos) ||
                                        Celdas[i + d1][j + d1].getText().equals(torre_blanca) ||
                                        Celdas[i + d1][j + d1].getText().equals(caballo_blanco) ||
                                        Celdas[i + d1][j + d1].getText().equals(alfil_blanca) ||
                                        Celdas[i + d1][j + d1].getText().equals(reina_blanca) ||
                                        Celdas[i + d1][j + d1].getText().equals(rey_blanca))) {
                                    Celdas[i+d1][j+d1].setEnabled(true);
                                    break;
                                }
                                else if (i+d1 < 8 && j+d1 < 8 && Celdas[i + d1][j + d1].getText().isEmpty()){
                                    Celdas[i+d1][j+d1].setEnabled(true);
                                }

                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(torre_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(caballo_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(alfil_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(reina_negra) ||
                                        Celdas[i + d1][j - d1].getText().equals(rey_negra))) {
                                    break;
                                }

                                else if(i+d1<8 && j-d1 >=0 && (Celdas[i + d1][j - d1].getText().equals(peones_blancos) ||
                                        Celdas[i + d1][j - d1].getText().equals(torre_blanca) ||
                                        Celdas[i + d1][j - d1].getText().equals(caballo_blanco) ||
                                        Celdas[i + d1][j - d1].getText().equals(alfil_blanca) ||
                                        Celdas[i + d1][j - d1].getText().equals(reina_blanca) ||
                                        Celdas[i + d1][j - d1].getText().equals(rey_blanca))) {
                                    Celdas[i+d1][j-d1].setEnabled(true);
                                    break;
                                }
                                else if (i + d1 < 8 && j - d1 >= 0 && Celdas[i + d1][j - d1].getText().isEmpty()){
                                    Celdas[i+d1][j-d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(torre_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(caballo_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(alfil_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(reina_negra) ||
                                        Celdas[i - d1][j + d1].getText().equals(rey_negra))) {
                                    break;
                                }

                                else if(i-d1 >= 0 && j+d1 < 8 && (Celdas[i - d1][j + d1].getText().equals(peones_blancos) ||
                                        Celdas[i - d1][j + d1].getText().equals(torre_blanca) ||
                                        Celdas[i - d1][j + d1].getText().equals(caballo_blanco) ||
                                        Celdas[i - d1][j + d1].getText().equals(alfil_blanca) ||
                                        Celdas[i - d1][j + d1].getText().equals(reina_blanca) ||
                                        Celdas[i - d1][j + d1].getText().equals(rey_blanca))) {
                                    Celdas[i-d1][j+d1].setEnabled(true);
                                    break;
                                }

                                else if (i-d1 >= 0 && j+d1 < 8 && Celdas[i - d1][j + d1].getText().isEmpty()) {
                                    Celdas[i-d1][j+d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(torre_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(caballo_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(alfil_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(reina_negra) ||
                                        Celdas[i - d1][j - d1].getText().equals(rey_negra))) {
                                    break;
                                }

                                else if (i-d1 >= 0 && j-d1 >= 0 && (Celdas[i - d1][j - d1].getText().equals(peones_blancos) ||
                                        Celdas[i - d1][j - d1].getText().equals(torre_blanca) ||
                                        Celdas[i - d1][j - d1].getText().equals(caballo_blanco) ||
                                        Celdas[i - d1][j - d1].getText().equals(alfil_blanca) ||
                                        Celdas[i - d1][j - d1].getText().equals(reina_blanca) ||
                                        Celdas[i - d1][j - d1].getText().equals(rey_blanca))) {
                                    Celdas[i-d1][j-d1].setEnabled(true);
                                    break;
                                }
                                else if (i-d1 >= 0 && j-d1 >= 0 && Celdas[i - d1][j - d1].getText().isEmpty()){
                                    Celdas[i-d1][j-d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if (i+d1 < 8  && (Celdas[i + d1][j].getText().equals(peones_negra) ||
                                        Celdas[i + d1][j].getText().equals(torre_negra) ||
                                        Celdas[i + d1][j].getText().equals(caballo_negra) ||
                                        Celdas[i + d1][j].getText().equals(alfil_negra) ||
                                        Celdas[i + d1][j].getText().equals(reina_negra) ||
                                        Celdas[i + d1][j].getText().equals(rey_negra))) {
                                    break;
                                }
                                else if (i+d1 < 8 && (Celdas[i + d1][j].getText().equals(peones_blancos) ||
                                        Celdas[i + d1][j].getText().equals(torre_blanca) ||
                                        Celdas[i + d1][j].getText().equals(caballo_blanco) ||
                                        Celdas[i + d1][j].getText().equals(alfil_blanca) ||
                                        Celdas[i + d1][j].getText().equals(reina_blanca) ||
                                        Celdas[i + d1][j].getText().equals(rey_blanca))) {
                                    Celdas[i+d1][j].setEnabled(true);
                                    break;
                                }
                                else if (i+d1 < 8 && Celdas[i + d1][j].getText().isEmpty()){
                                    Celdas[i+d1][j].setEnabled(true);
                                }

                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_negra) ||
                                        Celdas[i - d1][j].getText().equals(torre_negra) ||
                                        Celdas[i - d1][j].getText().equals(caballo_negra) ||
                                        Celdas[i - d1][j].getText().equals(alfil_negra) ||
                                        Celdas[i - d1][j].getText().equals(reina_negra) ||
                                        Celdas[i - d1][j].getText().equals(rey_negra))) {
                                    break;
                                }

                                else if(i-d1>=0 && (Celdas[i - d1][j].getText().equals(peones_blancos) ||
                                        Celdas[i - d1][j].getText().equals(torre_blanca) ||
                                        Celdas[i - d1][j].getText().equals(caballo_blanco) ||
                                        Celdas[i - d1][j].getText().equals(alfil_blanca) ||
                                        Celdas[i - d1][j].getText().equals(reina_blanca) ||
                                        Celdas[i - d1][j].getText().equals(rey_blanca))) {
                                    Celdas[i-d1][j].setEnabled(true);
                                    break;
                                }
                                else if (i - d1 >= 0 && Celdas[i -d1][j].getText().isEmpty()){
                                    Celdas[i-d1][j].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_negra) ||
                                        Celdas[i][j + d1].getText().equals(torre_negra) ||
                                        Celdas[i][j + d1].getText().equals(caballo_negra) ||
                                        Celdas[i][j + d1].getText().equals(alfil_negra) ||
                                        Celdas[i][j + d1].getText().equals(reina_negra) ||
                                        Celdas[i][j + d1].getText().equals(rey_negra))) {
                                    break;
                                }

                                else if(j+d1 < 8 && (Celdas[i][j + d1].getText().equals(peones_blancos) ||
                                        Celdas[i][j + d1].getText().equals(torre_blanca) ||
                                        Celdas[i][j + d1].getText().equals(caballo_blanco) ||
                                        Celdas[i][j + d1].getText().equals(alfil_blanca) ||
                                        Celdas[i][j + d1].getText().equals(reina_blanca) ||
                                        Celdas[i][j + d1].getText().equals(rey_blanca))) {
                                    Celdas[i][j+d1].setEnabled(true);
                                    break;
                                }

                                else if (j+d1 < 8 && Celdas[i][j + d1].getText().isEmpty()) {
                                    Celdas[i][j+d1].setEnabled(true);
                                }
                            }

                            for (int d1 = 0; d1 < 8; d1++) {
                                if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_negra) ||
                                        Celdas[i][j - d1].getText().equals(torre_negra) ||
                                        Celdas[i][j - d1].getText().equals(caballo_negra) ||
                                        Celdas[i][j - d1].getText().equals(alfil_negra) ||
                                        Celdas[i][j - d1].getText().equals(reina_negra) ||
                                        Celdas[i][j - d1].getText().equals(rey_negra))) {
                                    break;
                                }

                                else if (j-d1 >= 0 && (Celdas[i][j - d1].getText().equals(peones_blancos) ||
                                        Celdas[i][j - d1].getText().equals(torre_blanca) ||
                                        Celdas[i][j - d1].getText().equals(caballo_blanco) ||
                                        Celdas[i][j - d1].getText().equals(alfil_blanca) ||
                                        Celdas[i][j - d1].getText().equals(reina_blanca) ||
                                        Celdas[i][j - d1].getText().equals(rey_blanca))) {
                                    Celdas[i][j-d1].setEnabled(true);
                                    break;
                                }
                                else if (j-d1 >= 0 && Celdas[i][j - d1].getText().isEmpty()){
                                    Celdas[i][j-d1].setEnabled(true);
                                }
                            }



                        }
                    }
                }

            }
        }

        //REY NEGRA
        else if (turnonegro && boton.getText().equals(rey_negra) && !seleccion) {
            piezaseleccionada = boton.getText();
            boton.setText("");
            seleccion = true;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (boton.equals(Celdas[i][j])) {
                        Celdas[i][j].setEnabled(true);
                        if (j + 1 < 8 && i + 1 < 8 && (Celdas[i+1][j+1].getText().isEmpty() ||
                                Celdas[i+1][j+1].getText().equals(peones_blancos) ||
                                Celdas[i+1][j+1].getText().equals(torre_blanca) ||
                                Celdas[i+1][j+1].getText().equals(alfil_blanca) ||
                                Celdas[i+1][j+1].getText().equals(caballo_blanco) ||
                                Celdas[i+1][j+1].getText().equals(reina_blanca)||
                                Celdas[i+1][j+1].getText().equals(rey_blanca))) {
                            Celdas[i + 1][j + 1].setEnabled(true);
                        }
                        if (i+1 < 8 && j-1 >= 0  && (Celdas[i+1][j-1].getText().isEmpty() ||
                                Celdas[i+1][j-1].getText().equals(peones_blancos) ||
                                Celdas[i+1][j-1].getText().equals(torre_blanca) ||
                                Celdas[i+1][j-1].getText().equals(alfil_blanca) ||
                                Celdas[i+1][j-1].getText().equals(caballo_blanco) ||
                                Celdas[i+1][j-1].getText().equals(reina_blanca)||
                                Celdas[i+1][j-1].getText().equals(rey_blanca))) {
                            Celdas[i + 1][j - 1].setEnabled(true);
                        }
                        if (j + 1 < 8 && i - 1 >= 0  && (Celdas[i-1][j+1].getText().isEmpty() ||
                                Celdas[i-1][j+1].getText().equals(peones_blancos) ||
                                Celdas[i-1][j+1].getText().equals(torre_blanca) ||
                                Celdas[i-1][j+1].getText().equals(alfil_blanca) ||
                                Celdas[i-1][j+1].getText().equals(caballo_blanco) ||
                                Celdas[i-1][j+1].getText().equals(reina_blanca)||
                                Celdas[i-1][j+1].getText().equals(rey_blanca))) {
                            Celdas[i - 1][j + 1].setEnabled(true);
                        }
                        if (j - 1 >= 0 && i - 1 >= 0  && (Celdas[i-1][j-1].getText().isEmpty() ||
                                Celdas[i-1][j-1].getText().equals(peones_blancos) ||
                                Celdas[i-1][j-1].getText().equals(torre_blanca) ||
                                Celdas[i-1][j-1].getText().equals(alfil_blanca) ||
                                Celdas[i-1][j-1].getText().equals(caballo_blanco) ||
                                Celdas[i-1][j-1].getText().equals(reina_blanca)||
                                Celdas[i-1][j-1].getText().equals(rey_blanca))) {
                            Celdas[i - 1][j - 1].setEnabled(true);
                        }
                        if (i + 1 < 8  && (Celdas[i+1][j].getText().isEmpty() ||
                                Celdas[i+1][j].getText().equals(peones_blancos) ||
                                Celdas[i+1][j].getText().equals(torre_blanca) ||
                                Celdas[i+1][j].getText().equals(alfil_blanca) ||
                                Celdas[i+1][j].getText().equals(caballo_blanco) ||
                                Celdas[i+1][j].getText().equals(reina_blanca)||
                                Celdas[i+1][j].getText().equals(rey_blanca))) {
                            Celdas[i + 1][j].setEnabled(true);
                        }
                        if (i - 1 >= 0  && (Celdas[i-1][j].getText().isEmpty() ||
                                Celdas[i-1][j].getText().equals(peones_blancos) ||
                                Celdas[i-1][j].getText().equals(torre_blanca) ||
                                Celdas[i-1][j].getText().equals(alfil_blanca) ||
                                Celdas[i-1][j].getText().equals(caballo_blanco) ||
                                Celdas[i-1][j].getText().equals(reina_blanca)||
                                Celdas[i-1][j].getText().equals(rey_blanca))) {
                            Celdas[i - 1][j].setEnabled(true);
                        }
                        if (j + 1 < 8  && (Celdas[i][j+1].getText().isEmpty() ||
                                Celdas[i][j+1].getText().equals(peones_blancos) ||
                                Celdas[i][j+1].getText().equals(torre_blanca) ||
                                Celdas[i][j+1].getText().equals(alfil_blanca) ||
                                Celdas[i][j+1].getText().equals(caballo_blanco) ||
                                Celdas[i][j+1].getText().equals(reina_blanca)||
                                Celdas[i][j+1].getText().equals(rey_blanca))) {
                            Celdas[i][j+1].setEnabled(true);
                        }
                        if (j - 1 >= 0  && (Celdas[i][j-1].getText().isEmpty() ||
                                Celdas[i][j-1].getText().equals(peones_blancos) ||
                                Celdas[i][j-1].getText().equals(torre_blanca) ||
                                Celdas[i][j-1].getText().equals(alfil_blanca) ||
                                Celdas[i][j-1].getText().equals(caballo_blanco) ||
                                Celdas[i][j-1].getText().equals(reina_blanca)||
                                Celdas[i][j-1].getText().equals(rey_blanca))) {
                            Celdas[i][j-1].setEnabled(true);
                        }
                    }
                }
            }
        }



        //MOVER BLANCAS
        else if (turnoblanco && seleccion) {
            boton.setText(piezaseleccionada);
            piezaseleccionada = "";
            seleccion = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Celdas[i][j].getText().equals(peones_negra) ||
                            Celdas[i][j].getText().equals(torre_negra) ||
                            Celdas[i][j].getText().equals(caballo_negra) ||
                            Celdas[i][j].getText().equals(alfil_negra) ||
                            Celdas[i][j].getText().equals(rey_negra) ||
                            Celdas[i][j].getText().equals(reina_negra)) {
                        Celdas[i][j].setEnabled(true);
                    }
                }
            }
            turnoblanco = false;
            turnonegro = true;
        }

        //MOVER NEGRAS
        else if (turnonegro && seleccion) {
            boton.setText(piezaseleccionada);
            piezaseleccionada = "";
            seleccion = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    Celdas[i][j].setEnabled(false);
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Celdas[i][j].getText().equals(peones_blancos) ||
                            Celdas[i][j].getText().equals(torre_blanca) ||
                            Celdas[i][j].getText().equals(caballo_blanco) ||
                            Celdas[i][j].getText().equals(alfil_blanca) ||
                            Celdas[i][j].getText().equals(rey_blanca) ||
                            Celdas[i][j].getText().equals(reina_blanca)) {
                        Celdas[i][j].setEnabled(true);
                    }
                }
            }
            turnoblanco = true;
            turnonegro = false;
        }

        //PONER LAS CELDAS USABLES EN VERDE
        if (seleccion){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if(Celdas[i][j].isEnabled()){
                        Celdas[i][j].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                    }
                }
            }
        }

        //REPONER LAS CELDAS
        if (!seleccion){
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                        Celdas[i][j].setBorder(BorderFactory.createLineBorder(new Color(0x755539)));
                }
            }
        }
    }
}


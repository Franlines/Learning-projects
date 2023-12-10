import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tres_en_Raya extends JFrame implements ActionListener {
    public static void main(String[] args) {
        Tres_en_Raya Juego = new Tres_en_Raya();
    }

    private JButton[][] buttons;
    private JLabel turnLabel;
    private JButton resetButton;
    private char turno;

    //CREAMOS UN CONSTRUCTOR DE LA CLASE
    public Tres_en_Raya() {
        super("Tres en Rayas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(300, 400));
        setLocation((1920-300)/2,(1080-400)/2);



        //CREAMOS DE ARRIBA A ABAJO.
        //PRIMERO EL JLABEL QUE NOS MOSTRARA EL TURNO DE QUIEN JUEGA

        turnLabel = new JLabel("Turno del jugador X"); //Colocamos un turno inicial que podremos cambiar con el Action Litener
        turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(turnLabel, BorderLayout.NORTH); //CON ESTO ELEGIMOS QUE ESTE EN LA PARTE DE ARRIBA DEL LAYOUT

        //CREAMOS UN JPANEL PARA CONTENER LOS BOTONES DEL JUEGO
        JPanel panel = new JPanel(new GridLayout(3, 3));//GridLayout sirve para dividir el panel en filas y columnas
        buttons = new JButton[3][3];
        turno = 'X';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton(); //CREAMOS CADA UNO DE LOS BOTONES
                Font font = new Font(null, Font.TRUETYPE_FONT, 60);
                buttons[i][j].setFont(font);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));

                buttons[i][j].addActionListener(this); //LES ASIGNAMOS UN ACTION LISTENER
                panel.add(buttons[i][j]);
            }
        }
        add(panel, BorderLayout.CENTER); //AÑADIMOS EL JPANEL AL CONSTRUCTOR Y LO COLOCAMOS EN EL CENTRO

        resetButton = new JButton("Resetear"); //CREAMOS EL BOTON PARA RESETEAR
        resetButton.addActionListener(this); //LE ASIGNAMOS UN ACTION LISTENER
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }
    /*UNA VEZ CREADA LA ESTRUCTURA DE LA VENTANA AHORA LE DAREMOS FUNCIONALIDAD
    Para empezar vamos a definir las acciones que van a suceder:
        -Reiniciar la Partida
        -Bloquear los botones (Para que una vez acabada la partida no puedan seguir pulsandose
        -Verificar la Victoria.
     */

    //RESETEAR LA PARTIDA
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== resetButton){
            resetGame();
        }else {
            JButton boton = (JButton) e.getSource();
            boton.setText(Character.toString(turno)); //CAMBIAMOS EL ESTADO DEL BOTON
            boton.setEnabled(false);

            //COMPROBAMOS EL ESTADO DE LA PARTIDA
            if (Victory()){
                turnLabel.setText("Ha ganado el jugador " + turno);
                BlockButtons();
            }
            else if(turno == 'X'){ //SI ESTABA EN EL TURNO DEL JUGADOR X PASAMOS EL TURNO AL JUGADOR O
                turno= 'O';
                turnLabel.setText("Turno del Jugador O");
            }
            else { //SI ESTABA EN EL TURNO DEL JUGADOR O PASAMOS EL TURNO AL JUGADOR X
                turno = 'X';
                turnLabel.setText("Turno del Jugador X");
            }

        }
    }



    private void resetGame() {
            turno = 'X';
            turnLabel.setText("Turno del Jugador X");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        }
    }

    //BLOQUEAR LOS BOTONES
    private void BlockButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    //COMPROVAMOS LOS CASOS DE VICTORIA
    private Boolean Victory() {
        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText()) &&
                    !buttons[i][0].getText().isEmpty()) {
                buttons[i][0].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                buttons[i][1].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                buttons[i][2].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                return true;
            }
        }

        // Verificar columnas
        for (int i = 0; i < 3; i++) {
            if (buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText()) &&
                    !buttons[0][i].getText().isEmpty()) {
                buttons[0][i].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                buttons[1][i].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                buttons[2][i].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
                return true;
            }
        }

        // Verificar diagonales
        if (buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText()) &&
                !buttons[0][0].getText().isEmpty()) {
            buttons[0][0].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
            buttons[1][1].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
            buttons[2][2].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
            return true;
        }

        if (buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText()) &&
                !buttons[0][2].getText().isEmpty()) {
            buttons[0][2].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
            buttons[1][1].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
            buttons[2][0].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
            return true;
        }

        return false;
    }


}

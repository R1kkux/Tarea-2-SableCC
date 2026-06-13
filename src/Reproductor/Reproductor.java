// Matías Hernando Cáceres Ceballos

package Reproductor;

import SableCC.lexer.Lexer;
import SableCC.node.Start;
import SableCC.parser.Parser;
import javazoom.jlgui.basicplayer.BasicPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;

public class Reproductor {
    String songName = "Zombie"; // Aqui debe colocar el nombre de la cancion. No debe colocar extension
    long offset = 1100; // Hay que cambiar este valor si es que la cancion esta des-sincronizada

    private BasicPlayer player;
    private boolean debug = false;

    public Reproductor(){
        player = new BasicPlayer();
    }

    void main(String[] args) throws Exception {
        Cancion cancion = new Cancion();

        Parser p = new Parser(new Lexer(new PushbackReader(new InputStreamReader(new FileInputStream("src/Lyrics/" + songName + ".lrc")), 1024)));
        Start st = p.parse();

        st.apply(cancion);

        AbrirFichero("src/Canciones/" + songName + ".mp3");

            for (Letras l : cancion.getLyrics()) {
                if (debug) {
                    System.out.println("[" + l.getTimestamp() + "] " + l.getTexto().trim());
                }
                new Reminder(l.getTimestamp() + offset, l.getTexto().trim());
            }

            Play();

        if (debug) {
            System.out.println("== FINALIZO LA CARGA DE LYRICS ==");
        }

        System.out.println("\n-----==== Reproduciendo ====-----\n");
        cancion.imprimirDatos(debug);
        if (offset != 0) {System.out.println("\t Offset [Personal] : " + offset);}
        System.out.println("\n-----=======================-----\n\n\n");
    }

        public void Play() throws Exception {

        player.play();
    }

    public void AbrirFichero(String ruta) throws Exception {
        player.open(new File(ruta));
    }

    public void Pausa() throws Exception {
        player.pause();
    }

    public void Continuar() throws Exception {
        player.resume();
    }

    public void Stop() throws Exception {
        player.stop();
    }
}


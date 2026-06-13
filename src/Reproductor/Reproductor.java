package Reproductor;

import Lyrics.lexer.Lexer;
import Lyrics.node.Start;
import Lyrics.parser.Parser;
import javazoom.jlgui.basicplayer.BasicPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;

public class Reproductor {

    private BasicPlayer player;
    private boolean debug = false;

    public Reproductor(){
        player = new BasicPlayer();
    }

    void main(String[] args) throws Exception {
        String songPath = "C:\\Users\\Matías Cáceres\\IdeaProjects\\Tarea-2-SableCC\\src\\Cancion\\";
        String songName = "metallica"; // Aqui debe colocar el nombre de la cancion. No debe colocar extension

        Cancion cancion = new Cancion();

        Parser p = new Parser(new Lexer(new PushbackReader(new InputStreamReader(new FileInputStream(songPath + songName + ".lrc")), 1024)));
        Start st = p.parse();

        st.apply(cancion);

        try {
            AbrirFichero(songPath + songName + ".mp3");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Play();

        for (Letras l : cancion.getLyrics()) {

            if (debug) {
                System.out.println("[" +l.getTimestamp() +"] " +l.getTexto());
            }
            new Reminder(l.getTimestamp(), l.getTexto());
        }

        if (debug) {
            System.out.println("== FINALIZO LA CARGA DE LYRICS ==");
        }
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

//
//    public static void main(String args[]){
//        try {
//            Reproductor mi_reproductor = new Reproductor();
//            mi_reproductor.AbrirFichero("C:\\Users\\Matías Cáceres\\IdeaProjects\\Tarea-2-SableCC\\src\\Reproductor.Cancion\\Billie_Jean.mp3");
//            mi_reproductor.Play();
//            String st = "Se cumplieron 5 segundos";
//            mi_reproductor.new Reminder(5000, st);
//        } catch (Exception ex) {
//            System.out.println("Error: " + ex.getMessage());
//        }
//    }


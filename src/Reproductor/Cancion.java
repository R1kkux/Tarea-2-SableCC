package Reproductor;

import Lyrics.analysis.DepthFirstAdapter;
import Lyrics.node.AMetadatoContenido;
import Lyrics.node.ATimestampContenido;
import Lyrics.node.PTexto;

import java.util.ArrayList;
import java.util.LinkedList;

public class Cancion extends DepthFirstAdapter {    // Esta clase actua como el visitador
    private ArrayList<Letras> lyrics = new ArrayList<>();

    private String artista;
    private String album;
    private String titulo;

    @Override
    public void caseAMetadatoContenido(AMetadatoContenido node) {
        String metadato = String.valueOf(node.getMetadato()).trim();
        String texto = String.valueOf(node.getTexto());

        switch (metadato) {
            case "ti":
                titulo = texto.trim();
                return;
            case "ar":
                artista = texto.trim();
                return;
            case "al":
                album = texto.trim();
                return;
            default:
                System.out.println("-- El metadato [" + metadato.trim() + ":" + texto.trim() + "] no es un tipo de metadato reconocido.");
        }
    }

    @Override
    public void caseATimestampContenido(ATimestampContenido node) {
        long mil = Long.parseLong(String.valueOf(node.getMil()).trim());
        long min = Long.parseLong(String.valueOf(node.getMin()).trim());
        long seg = Long.parseLong(String.valueOf(node.getSeg()).trim());

        StringBuilder text = new  StringBuilder();

        for (PTexto lista : node.getLyric()) {
            text.append(lista.toString());
        }

        Long timestamp = (mil * 10) + (seg * 1000) + (min * 60000);

        lyrics.add(new Letras(timestamp, text.toString()));
    }

    public ArrayList<Letras> getLyrics() {
        return lyrics;
    }

    public String getArtista() {
        return artista;
    }

    public String getAlbum() {
        return album;
    }

    public String getTitulo() {
        return titulo;
    }

    public void imprimirDatos() {
        System.out.println(" ==== Datos de la cancion ====\n");

        System.out.println("\t Artista: " + artista);
        System.out.println("\t Album: " + album);
        System.out.println("\t Titulo: " + titulo);

        System.out.println(" >> Reproductor.Letras {[ms] lyric}");

        for (Letras l : lyrics) {
            System.out.println("[" +l.getTimestamp() +"] " + l.getTexto());
        }

        System.out.println(" ==== Fin datos de la cancion ===");
    }
}

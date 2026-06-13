package Reproductor;

import Lyrics.analysis.DepthFirstAdapter;
import Lyrics.node.ALinea;
import Lyrics.node.AMetadatoContenido;
import Lyrics.node.ATimestampContenido;

import java.util.ArrayList;

public class Cancion extends DepthFirstAdapter {    // Esta clase actua como el visitador
    private ArrayList<Letras> lyrics = new ArrayList<>();

    private String artista;
    private String album;
    private String titulo;

    @Override
    public void caseAMetadatoContenido(AMetadatoContenido node) {
        String metadato = String.valueOf(node.getTextolimitado()).trim();
        String texto = String.valueOf(node.getTexto()).trim();

        switch (metadato) {
            case "ti":
                titulo = texto;
                return;
            case "ar":
                artista = texto;
                return;
            case "al":
                album = texto;
                return;
            default:
                System.out.println("-- El metadato [" + metadato + ":" + texto + "] no es un tipo de metadato reconocido.");
        }
    }

    @Override
    public void caseATimestampContenido(ATimestampContenido node) {
        long mil = Long.parseLong(String.valueOf(node.getMil()).trim());
        long min = Long.parseLong(String.valueOf(node.getMin()).trim());
        long seg = Long.parseLong(String.valueOf(node.getSeg()).trim());

        String text = "";

        if (node.getLyric() != null) {
            text = String.valueOf(node.getLyric()).trim();
        }

        Long timestamp = (mil * 10) + (seg * 1000) + (min * 60000);

        lyrics.add(new Letras(timestamp, text));
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

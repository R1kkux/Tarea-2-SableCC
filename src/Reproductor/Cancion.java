package Reproductor;

import SableCC.analysis.DepthFirstAdapter;
import SableCC.node.AMetadatoContenido;
import SableCC.node.ATimestampContenido;
import SableCC.node.PTexto;

import java.util.ArrayList;

public class Cancion extends DepthFirstAdapter {    // Esta clase actua como el visitador
    private ArrayList<Letras> lyrics = new ArrayList<>();

    private String artista;
    private String album;
    private String titulo;
    private String lyricsCreadasPor;
    private String autores;
    private long offset = 0;

    @Override
    public void caseAMetadatoContenido(AMetadatoContenido node) {
        String metadato = String.valueOf(node.getMetadato()).trim();

        StringBuilder string = new StringBuilder();

        for (PTexto pTexto : node.getTexto()) {
            string.append(String.valueOf(pTexto));
        }

        String texto = string.toString().trim();

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

            case "offset":
                String offsetLimpio = texto.replaceAll("\\s+", "");
                offset = Long.parseLong(offsetLimpio);
                return;

            case "by":
                lyricsCreadasPor = texto.trim();
                return;

            case "au":
                autores = texto.trim();
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

        StringBuilder text = new StringBuilder();
        String pasoActual;

        if (node.getLyric().isEmpty()) {
            text.append(" ");
        } else {
            for (PTexto elementoActual : node.getLyric()) {
                pasoActual = elementoActual.toString().trim();

                text.append(" ").append(pasoActual);
            }
        }

        Long timestamp = (mil * 10) + (seg * 1000) + (min * 60000);

        lyrics.add(new Letras(timestamp, text.toString()));
    }

    public ArrayList<Letras> getLyrics() {
        return lyrics;
    }

    public void imprimirDatos(boolean debug) {
        if (artista != null) {System.out.println("\t Artista: " + artista);}
        if (album != null) {System.out.println("\t Album: " + album);}
        if (titulo != null) {System.out.println("\t Titulo: " + titulo);}
        if (autores != null) {System.out.println("\t Autores: " + autores);}
        if (lyricsCreadasPor != null) {System.out.println("\t Archivo .lrc: " + lyricsCreadasPor);}
        if (offset != 0) { System.out.println("\n\t Offset [Metadata] : " + offset);}

        if (debug) {

            System.out.println(" >> Reproductor.Letras {[ms] lyric}");

            for (Letras l : lyrics) {
                System.out.println("[" + l.getTimestamp() + "] " + l.getTexto());
            }
        }
    }
}

package Reproductor;

public class Letras {
    Long timestamp;
    String texto;

    public Letras(Long timestamp, String texto) {
        this.timestamp = timestamp;
        this.texto = texto;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}

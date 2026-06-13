package Reproductor;

import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
        Timer timer;
        String texto;

        public Reminder(long seconds, String texto) {
            this.texto = texto;
            timer = new Timer();
            timer.schedule(new RemindTask(), seconds); //5000 milisegundos = 5 segundos
        }

        class RemindTask extends TimerTask {
            public void run() { //se ejecuta solo cuando se cumple el tiempo
                System.out.println(texto);
            }
        }
    }

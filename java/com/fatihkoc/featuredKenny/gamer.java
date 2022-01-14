package com.fatihkoc.featuredKenny;

import java.io.Serializable;

public class gamer implements Serializable {
    int id,time,skor;
    String isim;
    String difficulty;

    public gamer(int id, int time, int skor, String isim, String difficulty) {
        this.id = id;
        this.time = time;
        this.skor = skor;
        this.isim = isim;
        this.difficulty = difficulty;
    }
}

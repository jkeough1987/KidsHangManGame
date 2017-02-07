package com.theironyard;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by joshuakeough on 10/7/16.
 */
@Entity
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String text;

    private ArrayList<Character> gameChars = new ArrayList<>();

    public Word() {
    }

    public Word(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<Character> getGameChars() {
        return gameChars;
    }

    public void setGameChars(ArrayList<Character> gameChars) {
        this.gameChars = gameChars;
    }
}

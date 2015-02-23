package knockknockserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ioan
 */

import java.util.Random;

public class Joke {
    private int cursor = 0;
    private int choice = 0;
    
    private final String[][] mjokes = { 
        {
            "Knock Knock", 
            "Nunya", 
            "Nunya of your business.",        
        },
        {
            "Knock Knock", 
            "Butter!", 
            "It's butter if you don't know it.",        
        },
        {
            "Knock Knock", 
            "Train", 
            "Someone needs to train you to open the door",        
        }
     };
    private String mjoke;
    
    public void chooseJoke() {
        Random ran = new Random();
        choice = ran.nextInt(3);
    }
    
    public void advanceJoke() {
        if (cursor <= mjokes.length) {
            cursor++;
        }
        else {
            cursor = 0;
        }
    }
    
    public String getJoke() {
        mjoke = mjokes[choice][cursor];
        return mjoke;
    }
    
    public void resetJoke() {
        chooseJoke();
        cursor = 0;
    }
    
    public String getKeyword() {
        return mjokes[choice][1];
    }
    
}

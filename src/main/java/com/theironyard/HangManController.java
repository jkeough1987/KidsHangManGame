package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by joshuakeough on 10/7/16.
 */
@Controller
public class HangManController {
    @Autowired
    PlayerRepository players;
    @Autowired
    WordRepository words;

    private static ArrayList<Character> usedChars = new ArrayList<>();
    private static ArrayList<Character> secretChars = new ArrayList<>();
    private static ArrayList<Character> gameChars = new ArrayList<>();

    private static int guesses;
    private static int counter;
    private static String reset = "true";
    private static String secretWord;



    @PostConstruct
    public void init() throws FileNotFoundException {
        if (players.count() == 0) {
            Player player = new Player();
            player.setFirstName("Joshua");
            player.setLastName("Keough");
            player.setPassword("123456");
            player.setWin(0);
            player.setLoss(0);
            player.setName("JoshuaKeough");
            players.save(player);
        }

        if (words.count() == 0) {
            File wordfile = new File("HangmanWords");
            Scanner fileScanner = new Scanner(wordfile);
            fileScanner.nextLine();
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Word word = new Word(line);
                words.save(word);

            }
        }
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model m) {
        String playerName = (String) session.getAttribute("playerName");
        Player player = players.findByName(playerName);
        HangmanGuy guy = new HangmanGuy();
        Word word = new Word();
        String lose = "You lose, the word was -" + secretWord + "-" ;
        String winner = "You Win!!";
        int guessesLeft = 10 - guesses;

        if (guesses > 0) {
            m.addAttribute("v1", guy.getV1());
        }
        if (guesses > 1) {
            m.addAttribute("v2", guy.getV2());
        }
        if (guesses > 2) {
            m.addAttribute("v3", guy.getV3());
        }
        if (guesses > 3) {
            m.addAttribute("v4", guy.getV4());
        }
        if (guesses > 4) {
            m.addAttribute("v5", guy.getV5());
        }
        if (guesses > 5) {
            m.addAttribute("v6", guy.getV6());
        }
        if (guesses > 6) {
            m.addAttribute("v7", guy.getV7());
        }
        if (guesses > 7) {
            m.addAttribute("v8", guy.getV8());
        }
        if (guesses > 8) {
            m.addAttribute("v9", guy.getV9());
        }
        if (guesses > 9) {
            m.addAttribute("v10", guy.getV10());
            m.addAttribute("lose", lose);
            m.addAttribute("reset", reset);
            m.addAttribute("word", secretWord);
            player.setLoss(player.getLoss()+1);
        }
        if(player != null) {
            if (!word.getGameChars().contains('_')) {
                m.addAttribute("winner", winner);
                m.addAttribute("reset", reset);
                player.setWin(player.getWin()+1);
            }
            m.addAttribute("word", word.getGameChars());
            m.addAttribute("player", player);
            m.addAttribute("guesses", guessesLeft);
        }


        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String firstName, String lastName, String password) throws Exception {
        Player player = players.findByName(firstName + lastName);
        if (player == null) {
            player = new Player(firstName, lastName, password, 0, 0, firstName + lastName);
            players.save(player);
        } else if (!password.equals(player.getPassword())) {
            return "redirect:/";
        }
        session.setAttribute("playerName", firstName + lastName);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(path = "/instructions", method = RequestMethod.GET)
    public String instructions(Model model) {
        return "instructions";
    }

    @RequestMapping(value = "/guess", method = RequestMethod.POST)
    public String guess(String letter) {

        if (letter == null) {
            letter = "";
        }
        char guessletter = letter.charAt(0);
        boolean a = true;

        while (a) {
            counter = 0;
            for (int i = 0; i < secretChars.size(); i++) {
                if (guessletter == (secretChars.get(i))) {
                    gameChars.set(i, guessletter);
                    a = false;
                } else {
                    counter++;
                }
                a = false;
            }
            if (counter >= secretChars.size()) {
                guesses++;
            }
            usedChars.add(guessletter);

        }
        return "redirect:/";
    }

}

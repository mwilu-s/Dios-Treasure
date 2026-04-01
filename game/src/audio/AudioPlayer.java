/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author User
 */
public class AudioPlayer {
    //constant values for where the song will be found

    /**
     * The song that will be played for the menu
     */
    public static int menu1 = 0;

    /**
     *The song that will be played for the level
     */
    public static int lvl = 1;
    
    /**
     *The sound effects for when the player dies
     */
    public static int die = 0;

    /**
     * The sound effect for when the player jumps
     */
    public static int jump = 1;

    /**
     * The sound effect for the game over
     */
    public static int gameOver = 2;

    /**
     * The sound effect for when the player attacks
     */
    public static int attack1 = 3;

    /**
     *The sound effect for when the player attacks
     */
    public static int attack2 = 4;

    /**
     *The sound effect for when the player attacks
     */
    public static int attack3 = 5;
    
    private Clip[] songs, effects; //clip is like music player. you can play audio whenever and manipulate it (play, pause, make it louder/softer)
    private int currentSong; //the song that is currently playing
    private float volume = 0.5f; //the volume of the sound
    private boolean songMute, effectsMute; //determines whether the effects or song sounds are mute
    private Random rand = new Random();
    
    /**
     *
     */
    public AudioPlayer(){
        //A non-parameterised constructor that instantiates an audioPlayer object. It loads the effects and songs used in the game as well as playing the menu song.
        loadSongs();
        loadEffects();
        playSong(menu1);
    }
    
    private void loadSongs(){
        //Loads the songs from the file that are to be played and are stored in an array.
        String [] names = {"menu2Song", "lvlSong"};
        songs = new Clip[names.length];
        
        for(int loop = 0; loop < songs.length; loop++){
            songs[loop] = getClip(names[loop]);
        }
    }
    
    private void loadEffects(){
        //Loads the sound effects from the file that are to be played and are stored in an array.
        String[] effectNames = {"die", "jump", "gameover", "attack1", "attack2", "attack3"};
        effects = new Clip[effectNames.length];
        for(int loop = 0; loop < effects.length; loop++){
            effects[loop] = getClip(effectNames[loop]);
        }
        updateEffectsVolume();
    }
    
    private Clip getClip(String file){
        //It returns the audio from the files.
        URL url = getClass().getResource("/audio/" + file + ".wav");
        AudioInputStream audio;
        
        try{
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
            
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     *
     */
    public void playAttackSound(){
        //This plays the sound effect for the attack sound
        int start = 3;
        start += rand.nextInt(2);
        playEffect(start);
    }
    
    /**
     *
     * @param effect : the effect that will be played
     */
    public void playEffect(int effect){
        //This plays the required effect for an action performed
        if(effects[effect].getMicrosecondPosition() > 0){
            effects[effect].setMicrosecondPosition(0);
        }
        effects[effect].start();
    }
    
    /**
     *
     * @param song : the song that will be played
     */
    public void playSong(int song){
        //This plays the music
        stopSong();
        
        currentSong = song;
        updateSongVolume();
        songs[currentSong].setMicrosecondPosition(0);
        songs[currentSong].loop(Clip.LOOP_CONTINUOUSLY);
        
    }
    
    /**
     *
     */
    public void toggleSongMute(){
        //This mutes the music
        this.songMute = !songMute;
        for(Clip c : songs){
            BooleanControl  bc = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            bc.setValue(songMute);
        }
    }
    
    /**
     *
     */
    public void toggleEffectMute(){
        //This mutes the effects
        this.effectsMute = !effectsMute;
        for(Clip c : effects){
            BooleanControl  bc = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            bc.setValue(effectsMute);
        }
        if(!effectsMute){
            playEffect(jump);
        }
        
    }
    
    
    private void updateSongVolume(){
        //This changes the song volume
        FloatControl gainControl = (FloatControl) songs[currentSong].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range*volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }
    
    private void updateEffectsVolume(){
        //This changes the effects volume
        for(Clip c : effects){
           FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
           float range = gainControl.getMaximum() - gainControl.getMinimum();
           float gain = (range*volume) + gainControl.getMinimum();
           gainControl.setValue(gain);
        }
    }
    
    /**
     *
     * @param volume : the specific volume that the user sets it to
     */
    public void setVolume(float volume){
        //This sets the volume to the user’s preference
        this.volume = volume;
        updateSongVolume();
        updateEffectsVolume();
    }
    
    /**
     *
     */
    public void stopSong(){
        //This stops playing the song
        if(songs[currentSong].isActive()){
            songs[currentSong].stop();
        }
    }
    
    /**
     *This plays the song for all the levels
     * @param lvlIndex : this is the number of the level being played
     */
    public void setLevelSong(int lvlIndex){//This sets a song to a level
       playSong(lvl);
    }
    
        
}

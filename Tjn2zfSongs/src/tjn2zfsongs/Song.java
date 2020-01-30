/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfsongs;

/**
 *
 * @author thomasnewman
 */
public class Song {
    private String name;
    private String artist;
    private String album;
    private int releaseYear;
    private Genre genre;
    private int version;
    public static int numberOfSongs = 0;
    
    
    public Song(){
        this.name=null;
        this.artist=null;
        numberOfSongs++;
    }
    
    public Song(String name, String artist){
        this();
        this.name=name;
        this.artist=artist;
        this.version=0;
    }
    
    public Song(String name, String artist, String album,
    int releaseYear, Genre genre){
        this(name, artist);
        this.album=album;
        this.releaseYear=releaseYear;
        this.genre=genre;
        this.version=1;
    }
    
    public void setName(String name){
        this.name=name;
        version++;
    }
    
    public void setArtist(String artist){
        this.artist=artist;
    }
    
    public void setAlbum(String album){
        this.album=album;
        version++;
    }
    
    public void setReleaseYear(int releaseYear){
        this.releaseYear=releaseYear;
        version++;
    }
    
    public void setGenre(Genre genre){
        this.genre=genre;
        version++;
    }
    
    public String getName(){
    return this.name;
}
    
    public String getArtist(){
        return this.artist;
    }
    
    public String getAlbum(){
        return this.album;
    }
    
    public int getReleaseYear(){
        return this.releaseYear;
    }
    
    public Genre getGenre(){
        return this.genre;
    }
    
    public int getVersion(){
        return this.version;
    }
}

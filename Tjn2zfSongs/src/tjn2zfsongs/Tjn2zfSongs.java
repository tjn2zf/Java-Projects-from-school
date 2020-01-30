/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfsongs;
import static tjn2zfsongs.Genre.*;
/**
 *
 * @author thomasnewman
 */
public class Tjn2zfSongs {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Song song1;
        song1 = new Song("My Heart Will Go On", "Celine Dion");
        song1.setAlbum("Let's Talk About Love");
        song1.setReleaseYear(1997);
        song1.setGenre(POP);
        
        Song song2 = new Song("Hotel California", "Eagles", "Hotel California", 1976, ROCK );
        
        System.out.println("Song 1: ");
        System.out.println("Name: " + song1.getName());
        System.out.println("Artist: " + song1.getArtist());
        System.out.println("Album: " + song1.getAlbum());
        System.out.println("Release Year: " + song1.getReleaseYear());
        System.out.println("Genre: " + song1.getGenre());
        System.out.println("Version: " + song1.getVersion() + "\n");
        
        System.out.println("Song 2: ");
        System.out.println("Name: " + song2.getName());
        System.out.println("Artist: " + song2.getArtist());
        System.out.println("Album: " + song2.getAlbum());
        System.out.println("Release Year: " + song2.getReleaseYear());
        System.out.println("Genre: " + song2.getGenre());
        System.out.println("Version: " + song2.getVersion() + "\n");
        
        System.out.println("Number of Songs: " + song2.numberOfSongs);
    }
    
}

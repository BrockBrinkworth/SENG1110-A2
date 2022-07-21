/**
 * Album is the class that runs, stores and accesses the song class to store and retrieve songs
 *
 * @author Brock Brinkworth (c3331952) & Keelan Ashford (c3354911)
 * @version (12/06/2020)
 */
import java.util.*;

public class Album
{
    private int MAX_SONG = 5;
    private String name = "";
    Song[] song = new Song[MAX_SONG];
    private int songCnt = 0;
    private int totalTime = 0;
    static final int MAX_TIME = 720; // 12 minutes
    static Scanner console = new Scanner(System.in);

    public void setName(String inputName) 
    {
        name = inputName;
    }

    public String getName() 
    {
        return name;
    }

    public void listSong() 
    {
        for(int i = 0; i < songCnt; i++)
        {
            System.out.println("Song Name: " + song[i].getName() + "\n" + "Genre: " + song[i].getGenre()+ "\n" + "Artist: " + song[i].getArtist() + "\n" + "Duration: " + song[i].getDuration() + "\n");
        }
    }

    public void listSongDuration(int minutes)
    {
        for(int i = 0; i < songCnt; i++)
        {
            if (song[i].getTime()/60 < minutes) 
            {
                System.out.println("Song Name: " + song[i].getName() + "\n" + "Genre: " + song[i].getGenre()+ "\n" + "Artist: " + song[i].getArtist() + "\n" + "Duration: " + song[i].getDuration() + "\n");
            }
        }
        
    }

    public void deleteSong() 
    {
        if (songCnt == 0) 
        {
            System.out.println("Error: No songs in album");
            return;
        }
        // auto delete from album if one song in album
        if (songCnt == 1) 
        {
            songCnt--;
            System.out.println("Deleted " + song[0].getName() + " from " + getName());
            return;
        }
        System.out.println("Select Song to delete: ");
        for (int i = 0; i < songCnt; i++)
        {
            System.out.println("Press " + (i+1) + " for " + song[i].getName());
        }
        int response = console.nextInt();
        if (0 < response && response <= songCnt) 
        {
            System.out.println("Deleted " + song[(response-1)].getName() + " from " + getName());
            for (int i = (response-1); i < (songCnt-1); i++)
            {
                song[i] = song[(i+1)];
            }
            songCnt--;
            return;
        }
        else
        {
                System.out.println("Error: choose a valid number next time");
                return;

        } 

    }

    public void addSong(Song inputSong) 
    {
        
        if (songCnt == MAX_SONG)
        {
            System.out.println("Album is full");
            return;
        }
        song[songCnt] = inputSong;
        songCnt++;
        totalTime += inputSong.getTime();
    }

    public void listSongGenre(String inputGenre)
    {
        for (int i = 0; i < songCnt; i++)
        {
            if (song[i].getGenre().equalsIgnoreCase(inputGenre)) 
            {
                System.out.println("Song Name: " + song[i].getName() + "\n" + "Genre: " + song[i].getGenre()+ "\n" + "Artist: " + song[i].getArtist() + "\n" + "Duration: " + song[i].getDuration() + "\n");
            }
        }
    }

    public String getTotalTime() 
    {
        String min = Integer.toString(totalTime/60) + " min ";
        String sec = Integer.toString(totalTime % 60) + " sec ";

        return(min + sec);
    }

    public int getDuration()
    {
        return totalTime;
    }

    public boolean checkSongName(String newSong)
    {
        for (int i = 0; i < songCnt; i++)
        {
            if (song[i].getName().equalsIgnoreCase(newSong))
            {
                return false;
            }
        }
        return true;
    }
}
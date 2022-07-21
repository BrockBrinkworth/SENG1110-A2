/**
 * Song.class stores and runs methods relating the song objects.
 *
 * @author Brock Brinkworth (c3331952) & Keelan Ashford (c3354911)
 * @version (12/06/2020)
 */
public class Song
{
    private String name = "";
    private String artist = "";
    private int duration = 0;
    private String genre = "Rock";
    
    public Song()
    {
        
    }
    
    
    public void setName(String inputName)
    {
         name = inputName;
    }
    public String getName()
    {
        return name;
    }
    public void setArtist(String inputArtist)
    {
         artist = inputArtist;
    }
    public String getArtist()
    {
        return artist;
    }
    public void setDuration(int inputDuration)
    {
       duration = inputDuration;
    }
    public String getDuration()
    {
        String min = Integer.toString(duration/60) + " min ";
        String sec = Integer.toString(duration % 60) + " sec ";

        return(min + sec);
    }
    public int getTime()
    {
        return duration;
    }
    public void setGenre(String inputGenre)
    {
        genre = inputGenre;
    }
    public String getGenre()
    {
        return genre;
    }
    
   
}
            
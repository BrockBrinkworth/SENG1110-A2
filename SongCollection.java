    /*
    * Song collection is used to access and run all methods located throughout all the classes. 
    * This class acts as the interface for the entire project.
    * @author Brock Brinkworth (c3331952) & Keelan Ashford(c3354911)
    * @version (12/06/2020)
    */
    import java.util.*;
    import java.io.BufferedReader;
    import java.io.FileReader;
    import java.io.IOException; 

    public class SongCollection
    {
        private int MAX_ALBUM = 4;
        private Album[] album = new Album[MAX_ALBUM];
        private int albumCnt = 0;
        static Scanner console = new Scanner(System.in);

        public void run() 
        {
            System.out.println("Would you like to import an existing album and songs?");
            System.out.println("y/n");
            String answer = console.next();
            
            if(("y".equalsIgnoreCase(answer)))
            {
                importSongCollection();
            }
            System.out.println("Enter 'help' for list of commands.");
            System.out.println("Warning: Use underscores for spaces.");
            while (true) 
            {
                String response = console.next();

                if ("help".equalsIgnoreCase(response)) 
                {
                    help();

                } 
                else if ("add_album".equalsIgnoreCase(response)) 
                {
                    addAlbum();
                } 
                else if ("quit".equalsIgnoreCase(response)) 
                {
                    System.exit(0);

                } 
                else if ("add_song".equalsIgnoreCase(response)) 
                {
                    addSong();
                } 
                else if ("list_songs".equalsIgnoreCase(response)) 
                {
                    listSong();
                }
                else if ("list_allsongs".equalsIgnoreCase(response)) 
                {
                    listAllSong();
                } 
                else if ("list_albums".equalsIgnoreCase(response)) 
                {
                    listAlbum();
                } 
                else if ("song_time".equalsIgnoreCase(response)) 
                {
                    listSongDuration();
                } 
                else if ("genre".equalsIgnoreCase(response)) 
                {
                    listSongGenre();
                } 
                else if ("delete_album".equalsIgnoreCase(response)) 
                {
                    deleteAlbum();
                } 
                else if ("delete_song".equalsIgnoreCase(response)) 
                {
                    deleteSong();
                } 
                else if ("import_songcollection".equalsIgnoreCase(response))
                {
                    importSongCollection();
                }
                else 
                {
                    System.out.println("invalid command, Enter 'help' for list of commands.");
                }

            }

        }

        public static void main(String[] args) 
        {
            System.out.println("running");
            SongCollection sg = new SongCollection();
            sg.run();
        }

        private void help()
        {
            System.out.println("===========================================");
            System.out.println("'add_album'--> create an album");
            System.out.println("'add_song'--> enter a song");
            System.out.println("'import_songcollection'--> Import a songcollection from a .txt file");
            System.out.println("'list_songs'--> lists all songs from an album");
            System.out.println("'list_allsongs'--> lists all songs");
            System.out.println("'list_albums'--> lists all albums");
            System.out.println("'song_time'--> lists the duration of a song");
            System.out.println("'genre'--> lists all the songs of the entered genre");
            System.out.println("'delete_album'--> deletes an album");
            System.out.println("'delete_song'--> deletes a song");
            System.out.println("'quit'--> quits the program");
            System.out.println("===========================================");
        }

        private void addAlbum() 
        {
            if (albumCnt == MAX_ALBUM) 
            {
                System.out.println("Error: SongCollection is full");
                return;
            }
            System.out.println("Underscores are required for spaces.");
            System.out.println("Enter name: ");
            String albumName = console.next();
            System.out.println("Name: " + albumName);
            Album newAlbum = new Album();
            newAlbum.setName(albumName);
            album[albumCnt] = newAlbum;
            
            albumCnt++;
            return;
        }
        
        private void addSong()
        {
            if (albumCnt < 1) 
            {
                System.out.println("Error: SongCollection is empty");
                return;
            }
            
            String songName = " ";
            for (int i = 0; i < 3; i++)
            {
                System.out.println("Enter song name: ");
                songName = console.next();
                if (checkSongName(songName) == true) 
                {
                    break;
                }
                if (i == 2)
                {
                    System.out.println("Error: Too many attempts.");
                    return;
                }
                System.out.println("Warning: Song name '" + songName + "' is use. Please try again. " + (2-i) + " attempts remaining.");
            }
            Song song1 = new Song();
            song1.setName(songName);
            System.out.println("Name: " + song1.getName());

            System.out.println("Enter Artist: ");
            String artistname = console.next();
            song1.setArtist(artistname);
            System.out.println("Artist: " + song1.getArtist());

            System.out.println("Enter Duration in seconds: ");
            int duration = console.nextInt();
            song1.setDuration(duration);
            System.out.println("Duration: " + song1.getDuration());

            System.out.println("Select Genre: ");
            System.out.println("Press 1 for Rock");
            System.out.println("Press 2 for Pop");
            System.out.println("Press 3 for Hip-Hop");
            System.out.println("Press 4 for Bossa Nova");
            int genreNo = console.nextInt();
            String genre = "";
            switch(genreNo) 
            {
                case 1: genre = "Rock";
                break;
                case 2: genre = "Pop";
                break;
                case 3: genre = "Hip Hop";
                break;
                case 4: genre = "Bossa Nova";
                break;
                default: System.out.println("Error: Invalid choice");
                break;
            }
            song1.setGenre(genre);
            System.out.println("Genre: " + song1.getGenre());
            // if there is 1 album the song will be automatically added to the first album
            if (albumCnt == 1) 
            {
                if (album[0].getDuration() > Album.MAX_TIME) 
                {
                    System.out.println("Max time for the album has been exceeded");
                    return;
                }
                album[0].addSong(song1);
                System.out.println("Song added to " + album[0].getName());
            } 
            else 
            {
                System.out.println("Select which album the song will be added to.");
                for (int i = 0; i < albumCnt; i++) 
                {
                    System.out.println("Press " + (i+1) + " for " + album[i].getName());
                }
                int response = console.nextInt();
                // check if the number is an album
                if (response <= albumCnt && 1 <= response) 
                {
                    if (album[(response-1)].getDuration() > Album.MAX_TIME) 
                    {
                        System.out.println("Max time for the album has been exceeded");
                        return;
                    }
                    album[(response-1)].addSong(song1);
                    System.out.println("Song added to " + album[(response-1)].getName());
                } 
                else 
                {
                    System.out.println("Error: Invalid input");
                    return;
                }
                
            }

        }
        
        private void listAlbum()
        {
            for (int i = 0; i < albumCnt; i++)
            {
                System.out.println("===========================================");
                System.out.println("Album Name: " + album[i].getName());
                System.out.println("Total Duration: " + album[i].getTotalTime());
                album[i].listSong();
                System.out.println("===========================================");
            }
            
        }
        
        private void listSong()
        {
            // if there is 1 album the song will be automatically added to the first album
            if (albumCnt == 1) {
                System.out.println("===========================================");
                System.out.println("Album Name: " + album[0].getName());
                album[0].listSong();
                System.out.println("===========================================");
                return;
            }
            System.out.println("Select Album: ");
            for (int i = 0; i < albumCnt; i++) {
                System.out.println("Press " + (i+1) + " for " + album[i].getName());
            }
            int response = console.nextInt();
            if (response <= albumCnt && 1 <= response) {
                System.out.println("===========================================");
                System.out.println("Album Name: " + album[(response-1)].getName());
                album[(response-1)].listSong();
                System.out.println("===========================================");
            } else {
                System.out.println("Error: Invalid input");
            }
            
        }
        
        private void listAllSong()
        {
            for (int i = 0; i < albumCnt; i++)
            {
                album[i].listSong();
            }
        }
        
        private void deleteSong()
        {
            if (albumCnt == 1) 
            {
                album[0].deleteSong();
            } else {
                System.out.println("Select which album the song will be added to.");
                for (int i = 0; i < albumCnt; i++) {
                    System.out.println("Press " + (i+1) + " for " + album[i].getName());
                }
                int response = console.nextInt();
                // check if the number is an album
                if (response <= albumCnt && 1 <= response) {
                    album[(response-1)].deleteSong();
                } else {
                    System.out.println("Error: Invalid input");
                    return;
                }
            }
        }

        private void listSongDuration()
        {
            System.out.println("Please enter the max duration of songs to view.");
            int maxMin = console.nextInt();
            for (int i = 0; i < albumCnt; i++)
            {
                System.out.println("===========================================");
                album[i].listSongDuration(maxMin);
                System.out.println("===========================================");
            }
            
        }   
         
        private void listSongGenre()
        {
            System.out.println("Select Genre: ");
            System.out.println("Press 1 for Rock");
            System.out.println("Press 2 for Pop");
            System.out.println("Press 3 for Hip-Hop");
            System.out.println("Press 4 for Bossa Nova");
            int genreNo = console.nextInt();
            String genre;
            switch(genreNo) 
            {
                case 1: genre = "Rock";
                break;
                case 2: genre = "Pop";
                break;
                case 3: genre = "Hip Hop";
                break;
                case 4: genre = "Bossa Nova";
                break;
                default: System.out.println("Error: invalid choice");
                return;
            }

            for (int i = 0; i < albumCnt; i++)
            {
                System.out.println("===========================================");
                album[i].listSongGenre(genre);
                System.out.println("===========================================");
            }

        }
        
        private void deleteAlbum()
        {
            if (albumCnt == 0) 
            {
                System.out.println("Error: No albums");
            }
            System.out.println("Select ALBUM to delete: ");
            for (int i = 0; i < albumCnt; i++) {
                System.out.println("Press " + (i+1) + " for " + album[i].getName());
            }
            int response = console.nextInt();

            if (0 < response && response <= albumCnt)
            {
                System.out.println("Deleting " + album[(response-1)].getName() + " from song collection.");
                for (int i = response-1; i < albumCnt - 1; i++)
                {
                    album[i] = album[(i+1)];
                }
                albumCnt--;
            } else 
            {
                System.out.println("Error: Invalid input");
            }
            
        }
        private void importSongCollection()
        {
            System.out.println("Type file name here (include .txt): ");
            String filename = console.next();
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader(filename));
                String line;
                Album curAlbum = null;
                String songName = "unknown";
                String artist = "unknown";
                String genre = "unknown";
                int duration = 0;
                while ((line = br.readLine()) != null)
                {
                    String[] arrSplit = line.split(" ");
                    if (arrSplit[0].equalsIgnoreCase("Album"))
                    {
                        String albumName = concat(arrSplit);
                        importAlbum(albumName);
                        curAlbum = album[(albumCnt-1)];
                    } else if (arrSplit[0].equalsIgnoreCase("Name"))
                    {
                        songName = concat(arrSplit);
                    } else if (arrSplit[0].equalsIgnoreCase("Artist"))
                    {
                        artist = concat(arrSplit);
                    } else if (arrSplit[0].equalsIgnoreCase("Duration"))
                    {
                        if (arrSplit.length > 1)
                        {
                            duration = Integer.parseInt(arrSplit[1]);
                        }
                    } else if (arrSplit[0].equalsIgnoreCase("Genre"))
                    {
                        genre = concat(arrSplit);
                        if (curAlbum != null)
                        {
                            importSong(songName, artist, duration, genre, curAlbum);
                            songName = "unknown";
                            artist = "unknown";
                            duration = 0;
                            genre = "unknown";
                        }
                    }
                }
            } 
            catch (IOException e)
            {
                e.printStackTrace();
                return;
            } 
            finally 
            {
                try 
                {
                    br.close();
                } 
                catch (IOException e) 
                {
                    return;
                }
            }

            return;
        
        }
        
        private void importAlbum(String albumName)
        {
            if (albumCnt == MAX_ALBUM) 
            {
                System.out.println("Error: SongCollection is full");
                return;
            }
            Album newAlbum = new Album();
            newAlbum.setName(albumName);
            album[albumCnt] = newAlbum;
            albumCnt++;
            return;
        }
        
        private void importSong(String songName, String artist, int duration, String genre, Album inputAlbum)
        {
            Song newSong = new Song();
            if (checkSongName(songName) == false) 
            {
                System.out.println("That song name already exists.");
                return;
            }
            newSong.setName(songName);
            if (inputAlbum.getDuration() > Album.MAX_TIME)
            {
                System.out.println("Maximum time has been exceeded.");
                return;
            }
            newSong.setDuration(duration);
            newSong.setArtist(artist);
            newSong.setGenre(genre);
            inputAlbum.addSong(newSong);
            System.out.println(songName + " Imported to " + inputAlbum.getName());
        }

        private boolean checkSongName(String songName)
        {
            for (int i = 0; i < albumCnt; i++)
            {
                if (album[i].checkSongName(songName) == false) {
                    return false;
                }
            }
            return true;
        }

        private String concat(String[] inputArray) {
            String outputName = "";
            for (int i = 1; i < inputArray.length; i++)
            {
                outputName += inputArray[i];
                outputName += " ";
            }

            return outputName;
        }

    }

        

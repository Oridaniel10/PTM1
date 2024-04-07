package test;
import java.util.Random;

public class Tile { 
//------------------------------------------------------------
    public final char letter;  // const
    public final int score;   // const
//------------------------------------------------------------

    private Tile(char letter, int score) {  // private constructor , not everyone will create new Tiles..
        this.letter = letter;
        this.score = score;
    }


    @Override
    public int hashCode() {  //Generator
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + score;
        return result;
    }


    @Override
    public boolean equals(Object obj) { //Generator
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Tile other = (Tile) obj;
        if (letter != other.letter)
            return false;
        if (score != other.score)
            return false;
        return true;
    }


 

        public static class Bag {
            //------------------------------------//
        private int[] letters;
        private int[] initialLetters;
        private int amountOfTiles;
        private Tile[] tiles;
        private static Bag instance; // getBag method
        
            //------------------------------------//
           
            //construtor 
            public Bag() {
                this.amountOfTiles = 98;
                this.letters = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};//num of Tile of any letter
                this.initialLetters = this.letters.clone();//copy array of letters to know how we started...
                this.tiles = new Tile[26];
                int[] scores = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
                //initilize Tiles
                for (int i = 0; i < 26; i++) {
                    this.tiles[i] = new Tile((char)('A' + i), scores[i]); // initial 26 Tile by the constuctor ( Dad)
                }
            }

            public Tile getRand()
            {
                if (this.amountOfTiles == 0)
                {
                    return null;
                }
                Random rand = new Random();
                int index = rand.nextInt(26); // Generates a random integer between 0 and 25
                while (letters[index] == 0) 
                {
                    index = rand.nextInt(26); // Until we get a tile that has 1 or more letters in it
                }
                amountOfTiles--;
                letters[index]--;
                return tiles[index];
            }


            public Tile getTile(char letter){
            // Convert to uppercase
            //char upperCaseLetter = Character.toUpperCase(letter); 
                int indexOfChar = (int) letter - (int) 'A'; // which char did we get? (husky to in number)
                if(indexOfChar > 26 || indexOfChar < 0 || letters[indexOfChar] == 0) {
                    return null;
                }
    
                letters[indexOfChar]--;
                return tiles[indexOfChar];
            }

            //function that add "tile" to the bag ( letter++) 
            public void put(Tile tile){
                int indexOfChar = (int) tile.letter - (int) 'A'; // which char did we get? (husky to in number)
                if (this.letters[indexOfChar] < initialLetters[indexOfChar] ) {
                    this.letters[indexOfChar]++;
                    amountOfTiles++;
                    
                }
            }

            public int size() {
                return amountOfTiles;
            }

            public int[] getQuantities() {
                return this.letters.clone();
            }


            public static Bag getBag(){
                if (instance == null){
                    instance = new Bag();
                }
                return instance;
            }
        }
        

}
    

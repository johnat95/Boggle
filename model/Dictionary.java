package model;



import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Dictionary {
    ArrayList<String> dictArray = new ArrayList<>();

    
    Path path = Paths.get("./src/resources/BoggleWordList.txt");

    public Dictionary() {
        try {
            loadDict();


        }catch (IOException e){
            e.printStackTrace();
        }

    }
    // reads dictionary text file line by line, adding each line to
    //dictionary array
    private void loadDict() throws IOException {

        String input;

        try (BufferedReader br = Files.newBufferedReader(path)) {
            while ((input = br.readLine()) != null) {
                dictArray.add(input);
            }
            Collections.sort(dictArray);


        }

    }

    //conducts binary search on dictArray
    public boolean checkDictionary(String s){

        int test = Collections.binarySearch(dictArray,s);
        //binary search returns either the index of the object,
        //or the negative index-1
        return test >= 0;
    }




}

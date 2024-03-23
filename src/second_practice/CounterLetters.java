package second_practice;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CounterLetters {
    private final HashMap<Character, Integer> dictionary;
    private final File input_file;
    private final File output_file;

    CounterLetters(File input, File output) throws IOException {
        this.dictionary = new HashMap<>();
        this.input_file = input;
        this.output_file = output;
        this.CountLetters();
        this.WriteData();
    }

    private void CountLine(String line){
        for (int i=0;i<line.length(); i++) {
            char ch = line.charAt(i);
            if (('A' <= ch && ch <= 'Z')||('a' <= ch && ch <= 'z')) {
                this.dictionary.putIfAbsent(ch, 0);
                this.dictionary.put(ch, this.dictionary.get(ch) + 1);
            }
        }
    }
    private void WriteData() throws IOException{
        try (FileWriter writer=new FileWriter(this.output_file)){
            for (Map.Entry<Character, Integer>entry:this.dictionary.entrySet()){
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
        } catch (IOException err){
            throw new IOException("Error in writing results");
        }
    }
    private void CountLetters() throws IOException{
        try (BufferedReader reader = new BufferedReader(new FileReader(this.input_file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                this.CountLine(line);
            }
        } catch (FileNotFoundException err){
            throw new FileNotFoundException("Input file not Found");
        } catch (IOException err){
            throw new IOException("Error in reading input file");
        }
    }
}

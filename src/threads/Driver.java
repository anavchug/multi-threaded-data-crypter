package threads;
import java.io.*;
import java.util.ArrayList;

public class Driver {

    public static void main(String[] args) throws IOException, InterruptedException {
    	BufferedReader br = new BufferedReader(new FileReader("xyz.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] sections = line.split("\\$");
            ArrayList<String> section1 = new ArrayList<>();
            ArrayList<String> section2 = new ArrayList<>();
            ArrayList<String> section3 = new ArrayList<>();
            for (String section : sections) {
                int numAsterisks = section.length() - section.replace("*", "").length();
                section = section.replace("*", "").trim();
                String[] values = section.split(",");
                section = String.join(" ", values);
                if (numAsterisks == 1) {
                    section1.add(section);
                } else if (numAsterisks == 2) {
                    section2.add(section);
                } else if (numAsterisks == 3) {
                    section3.add(section);
                }
            }
             Thread reveal = new Thread(new Reveal(section1));
             Thread expose = new Thread(new Expose(section2));
             Thread discover = new Thread(new Discover(section3));
             
             reveal.start();
             reveal.join();
             
             expose.start();
             expose.join();
             
             discover.start();
             discover.join();
             
             System.out.println();
             if(br.readLine()== null) {
            	 continue;
             }
         }
         br.close();
    }
}

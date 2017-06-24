import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.stream;

/**
 * Created by Nisansa on 6/23/2017.
 */
public abstract class Crawler {


  ArrayList<String> words=new ArrayList<String>();
  //HashMap<String,HashSet<String>> wordIPAs=new HashMap<String, HashSet<String>>();
  HashMap<String,String> wordIPAs=new HashMap<String, String>();
  ArrayList<String> rejects=new ArrayList<String>();

  abstract  public void fetchIPAof(String word);

  String source="";
  String rejected="";
  String ipa="";


  public Crawler(String source, String rejected, String ipa) {
    this.source = source;
    this.rejected = rejected;
    this.ipa = ipa;
  }

  public void run(){
    readWordFile(source);
    crawlAndExtract();
  }

  public void readWordFile(String fileName) {
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      try {
        String line = br.readLine();

        while (line != null) {
          line=line.toLowerCase();
          words.add(line);
          line = br.readLine();
        }

      } finally {
        br.close();
      }
    } catch (Exception e) {

    }
  }


  public void crawlAndExtract(){
    for (int i = 0; i <words.size() ; i++) {
      System.out.println((i+1)+" of "+words.size());
      fetchIPAof(words.get(i));

      if(i%10==0){
        writeIPA();
        writeRejects();
        wordIPAs=new HashMap<String, String>();
        rejects=new ArrayList<String>();
      }

    }

    writeIPA();
    writeRejects();
    System.gc();
  }

  private void writeIPA(){



    try{
      FileWriter fw = new FileWriter(ipa, true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter writer = new PrintWriter(bw);
      for (int i = 0; i <words.size() ; i++) {
        String ipa=wordIPAs.get(words.get(i));
        if(ipa!=null){
          writer.println(words.get(i)+";"+ipa);
        }
      }
      writer.close();
    } catch (IOException e) {
      // do something
    }
  }

  private void writeRejects(){
    try{
      FileWriter fw = new FileWriter(rejected, true);
      BufferedWriter bw = new BufferedWriter(fw);
      PrintWriter writer = new PrintWriter(bw);
      for (int i = 0; i <rejects.size() ; i++) {
        writer.println(rejects.get(i));
      }
      writer.close();
    } catch (IOException e) {
      // do something
    }
  }



}

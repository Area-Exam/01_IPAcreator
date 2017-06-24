import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Cambridge extends Crawler {






  public Cambridge(String source, String rejected, String ipa) {
    super(source, rejected, ipa);
  }




  URL url=null;
  URLConnection spoof=null;
  BufferedReader in=null;
  String strLine = "";
  boolean added=false;

  public void fetchIPAof(String word){
    added=false;
    try {
      url = new URL("http://dictionary.cambridge.org/us/dictionary/english/"+word); //apple
      spoof = url.openConnection();

      //Spoof the connection so we look like a web browser
      spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
      in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
      strLine = "";
      //Loop through every line in the source
      // HashSet<String> ipaSet=new HashSet<String>();


      while ((strLine = in.readLine()) != null) {
        if(strLine.contains("ipa") && !strLine.contains("\"sp\"")) {
          strLine=strLine.substring(strLine.indexOf(">/<")+2,strLine.lastIndexOf(">/<"));
          if(strLine.contains(",")){
            strLine=strLine.substring(0,strLine.lastIndexOf(",")-1);
          }
          //System.out.println(strLine);
          //System.out.println(strLine.lastIndexOf(">")+1);
          //System.out.println(strLine.lastIndexOf("<"));
          strLine=strLine.substring(strLine.lastIndexOf(">")+1,strLine.lastIndexOf("<"));
          // ipaSet.add(strLine);
          // System.out.println(strLine);

          wordIPAs.put(word,strLine);
          added=true;
          break;
        }

      }
      //wordIPAs.put(word,ipaSet);
      // added=true;
    }
    catch(Exception e){
      System.err.println(e.getMessage());
    }

    if(!added){
      rejects.add(word);
    }


  }


}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Nisansa on 6/23/2017.
 */
public class Glosbe extends Crawler {



    //Args ->   "Glosbe" "../Output/01_IPA/rejectWordsCambridge.txt" "../Output/01_IPA/rejectWordsGlosbe.txt" "../Output/01_IPA/IPAglosbe.txt"
    //1599618.hn1

    public Glosbe(String source, String rejected, String ipa) {
        super(source, rejected, ipa);
    }



    @Override
    public void fetchIPAof(String word) {
        added=false;
        try {
            url = new URL("https://en.glosbe.com/en/fr/"+word); //apple
            spoof = url.openConnection();

            //Spoof the connection so we look like a web browser
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            strLine = "";
            //Loop through every line in the source
            // HashSet<String> ipaSet=new HashSet<String>();
            //  System.out.println(word);

            while ((strLine = in.readLine()) != null) {
                if(strLine.contains("IPA:")) {
                    strLine=strLine.substring(strLine.indexOf("IPA:"),strLine.lastIndexOf("defmeta"));
                    strLine=strLine.substring(strLine.indexOf("<span>"),strLine.lastIndexOf("</span>"));
                    strLine=strLine.substring(strLine.indexOf("/")+1,strLine.length());
                    strLine=strLine.substring(0,strLine.indexOf("/"));
                    //       if(strLine.contains(",")){
                    //          strLine=strLine.substring(0,strLine.lastIndexOf(",")-1);
                    //       }
                    //System.out.println(strLine);
                    //System.out.println(strLine.lastIndexOf(">")+1);
                    //System.out.println(strLine.lastIndexOf("<"));
                    //        strLine=strLine.substring(strLine.lastIndexOf(">")+1,strLine.lastIndexOf("<"));
                    // ipaSet.add(strLine);
                    // System.out.println(strLine);

                    wordIPAs.put(word,strLine);
                    added=true;
                    // System.out.println(strLine);
                    break;


                }




            }
            // System.in.read();
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

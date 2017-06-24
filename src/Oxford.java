import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Nisansa on 6/23/2017.
 */
public class Oxford extends Crawler {
    //Args ->   "Oxford" "../Output/01_IPA/rejectWordsGlosbe_run1.txt" "../Output/01_IPA/rejectWordsOxford.txt" "../Output/01_IPA/IPAoxford.txt"



    public Oxford(String source, String rejected, String ipa) {
        super(source, rejected, ipa);
    }

    @Override
    public void fetchIPAof(String word) {
        added=false;
        try {
            url = new URL("http://www.oxfordlearnersdictionaries.com/us/definition/english/"+word); //apple
            spoof = url.openConnection();

            //Spoof the connection so we look like a web browser
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            strLine = "";

           // System.out.println(word);
            while ((strLine = in.readLine()) != null) {
                if(strLine.contains("BrE") || strLine.contains("NAmE")) {

                    if(strLine.contains("BrE")) {
                       strLine=strLine.substring(strLine.indexOf("<span class=\"wrap\">/</span>"),strLine.lastIndexOf("<span class=\"wrap\">/</span>"));
                        strLine=strLine.substring(strLine.indexOf("/")+1,strLine.length());
                        strLine=strLine.substring(0,strLine.indexOf(">/"));
                        strLine=strLine.substring(strLine.indexOf(">")+1,strLine.length());
                        strLine=strLine.substring(0,strLine.indexOf("<"));
                    }
                    else{ //Take american
                        strLine=strLine.substring(strLine.indexOf("NAmE"),strLine.length());
                        strLine=strLine.substring(strLine.indexOf("<span class=\"wrap\">/</span>"),strLine.lastIndexOf("<span class=\"wrap\">/</span>"));
                    strLine=strLine.substring(strLine.lastIndexOf(">")+1,strLine.length());

                    }

                    wordIPAs.put(word,strLine);
                   added=true;
                    break;
                }

            }
         //   System.exit(0);

        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }

        if(!added){
            rejects.add(word);
        }
    }
}

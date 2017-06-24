import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Nisansa on 6/24/2017.
 */
public class DictionaryCom extends Crawler {

  //  "DictionaryCom" "../Output/01_IPA/rejectWordsOxford.txt" "../Output/01_IPA/rejectWordsDictionaryCom.txt" "../Output/01_IPA/IPAdictionaryCom.txt"

    public DictionaryCom(String source, String rejected, String ipa) {
        super(source, rejected, ipa);
    }

    @Override
    public void fetchIPAof(String word) {
     //   word="abe";
        added=false;
        try {
            url = new URL("http://www.dictionary.com/browse/"+word); //apple
            spoof = url.openConnection();

            //Spoof the connection so we look like a web browser
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            strLine = "";

            //System.out.println(word);
            while ((strLine = in.readLine()) != null) {
                if (strLine.contains("British Dictionary")) {



                    
                    while ((strLine = in.readLine()) != null && !strLine.contains("head-entry")) { //Now go down till you find "head-entry"

                        //Do nothing to these useless lines
                    }

                    strLine=strLine.substring(strLine.indexOf("<span class=\"me\">"),strLine.indexOf("</span>"));
                    strLine=strLine.substring(strLine.indexOf(">")+1,strLine.length());

                    //System.out.println(strLine);


                    if (strLine.equalsIgnoreCase(word)) {  //This is because "dictionary.com" tries to be helpful and redirects to the lemma of the word. That would corrupt our data in this case!
                      //  System.out.println(word+" "+strLine);

                        while ((strLine = in.readLine()) != null && !strLine.contains("ipapron")) { //Now go down till you find "ipapron"

                            //Do nothing to these useless lines
                        }

                        strLine = strLine.substring(strLine.indexOf(">/"), strLine.lastIndexOf(">/"));
                        strLine = strLine.replace("</span>", "");
                        strLine = strLine.replace("<span class=\"dbox-sup\">", "");
                        strLine = strLine.substring(strLine.indexOf("/"), strLine.lastIndexOf("<"));
                        strLine = strLine.substring(strLine.indexOf(">") + 1, strLine.length());

                        if (strLine.contains(";")) {
                            strLine = strLine.substring(0, strLine.indexOf(";"));
                        }

                        //      System.out.println(strLine);

                        wordIPAs.put(word, strLine);
                        added = true;
                        break;
                    }
                }

            }
              // System.exit(0);

        }
        catch(Exception e){
           // System.err.println(e.getMessage());
        }

        if(!added){
            rejects.add(word);
        }
    }
}

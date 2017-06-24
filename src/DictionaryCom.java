/**
 * Created by Nisansa on 6/24/2017.
 */
public class DictionaryCom extends Crawler {

    //Args ->   "DictionaryCom" "../Output/rejectWordsOxford.txt" "../Output/rejectWordsDictionaryCom.txt" "../Output/IPAdictionaryCom.txt"

    public DictionaryCom(String source, String rejected, String ipa) {
        super(source, rejected, ipa);
    }

    @Override
    public void fetchIPAof(String word) {

    }
}

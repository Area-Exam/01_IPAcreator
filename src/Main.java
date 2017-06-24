/**
 * Created by Nisansa on 6/23/2017.
 */
public class Main {




  public static void main(String[] args) {

    String source="../Output/words.txt";
    String rejected="../Output/rejectWords.txt";
    String ipa="../Output/IPA.txt";
    String machine="Cambridge";

    if(args.length>0) {
      machine = args[0];
      source = args[1];
      rejected = args[2];
      ipa = args[3];
    }

    Crawler c=null;
    if(machine.equalsIgnoreCase("Cambridge")) {
      c = new Cambridge(source, rejected,ipa);
    }
    else if (machine.equalsIgnoreCase("Glosbe")) {
      c = new Glosbe(source, rejected,ipa);
    }

    if(c!=null) {
      c.run();
    }
    else{
      System.out.println("Crawler not initialized");
    }

  }
}

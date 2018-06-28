package strudel;

import java.util.Date;
import java.util.ArrayList;

public class strudelLogic {
    
    private ArrayList<Strudel> strudel = new ArrayList<>();
    
    public ArrayList<Strudel> getTest() {
        Date now = new Date();
        Strudel s1 = new Strudel();
        s1.setColor("#245455");
        s1.setTime(now.getTime());
        s1.setMessage("dis R test, k");
        s1.setVotes(5);
        s1.setHeight(100);
        
        Strudel s2 = new Strudel();
        s2.setColor("#F23232");
        s2.setTime(now.getTime());
        s2.setMessage("halloderja");
        s2.setVotes(0);
        s2.setHeight(100);
        
        Strudel s3 = new Strudel();
        s3.setColor("#F35232");
        s3.setTime(now.getTime());
        s3.setMessage("LOREM IPSUM OG SÅNT");
        s3.setVotes(1);
        s3.setHeight(150);
        
        Strudel s4 = new Strudel();
        s4.setColor("#852342");
        s4.setTime(now.getTime());
        s4.setMessage("once upon a time..");
        s4.setVotes(0);
        s4.setHeight(150);
        
        Strudel s5 = new Strudel();
        s5.setColor("#F01010");
        s5.setTime(now.getTime());
        s5.setMessage("SADFASDFASDF");
        s5.setVotes(2);
        s5.setHeight(100);
        
        Strudel s6 = new Strudel();
        s6.setColor("#F35232");
        s6.setTime(now.getTime());
        s6.setMessage("LOREM IPSUM OG SÅNT");
        s6.setVotes(1);
        s6.setHeight(150);        
        
        strudel.add(s1);
        strudel.add(s2);
        strudel.add(s3);
        strudel.add(s4);
        strudel.add(s5);
        strudel.add(s6);
        return strudel;
    }
    
    public String getTime(Strudel s) {
        Date now = new Date();
        long dif = (now.getTime() - s.getTime()) / 1000;
        if (dif < 60) {
            return Long.toString(dif) + "s";
        }
        else if (dif >= 60 && dif < 3600) {
            return Long.toString(dif/60) + "min";
        }
        else if (dif >= 3600 && dif < 86400) {
            return Long.toString(dif/3600) + "h";
        }
        else {
            return Long.toString(dif/86400) + "d";
        }
    }
    
}

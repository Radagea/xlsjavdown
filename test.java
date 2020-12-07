/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KordiKereso;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



/**
 *
 * @author Radagea
 */
public class test {
    public static Date subtractDays(Date date, int days) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.DATE, -days);
				
		return cal.getTime();
    }
    
    
    
    public static void main(String[] args) throws FileNotFoundException, IOException, Exception {
        //Település nevek integrálása
        String[][] telepul = new String[12][2];
        telepul[0][0] = "Kaposvár";
        telepul[0][1] = "Siófok";
        telepul[1][0] = "Nagykanizsa";
        telepul[1][1] = "Keszthely";
        telepul[2][0] = "Szekszárd";
        telepul[2][1] = "Dunaújváros";
        telepul[3][0] = "Győr";
        telepul[3][1] = "Kapuvár";
        telepul[4][0] = "Szombathely";
        telepul[4][1] = "Zalaegerszeg";
        telepul[5][0] = "Veszprém";
        telepul[5][1] = "Pápa";
        telepul[6][0] = "Debrecen";
        telepul[6][1] = "Berettyóújfalu";
        telepul[7][0] = "Nyíregyháza";
        telepul[7][1] = "Mátészalka";
        telepul[8][0] = "Szolnok";
        telepul[8][1] = "Karcag";
        telepul[9][0] = "Pécs";
        telepul[10][0] = "Székesfehérvár";
        telepul[11][0] = "Tatabánya";

        //Koordintárok példány 
        java.util.List<Koordinatorok> li = new ArrayList<>();
        Koordinatorok asd = null;
        
        
        //dátum
        Date date = new Date();
        SimpleDateFormat oradate = new SimpleDateFormat("HH");
        if (Integer.parseInt(oradate.format(date))<7) {
            date = subtractDays(date,1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM.dd");
        String datum = formatter.format(date);
        
        //mappa létrehozása
        Path path = Paths.get("exc/");
        Files.createDirectories(path);
        
        //EXCEL letöltés
        String[][] excel = null;
        URL[] webs = new URL[12];
        
        URL webpage = new URL("https://up.labstack.com/api/v1/links/TuVCQnEK/receive");
        for (int i = 0; i < 12; i++) {
            //Letöltés
            ReadableByteChannel rbc = Channels.newChannel(webpage.openStream());
            FileOutputStream fos = new FileOutputStream("exc/tmp_cords"+i+".xls");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            
            //EXCEL bedolgozás
            Excelobj test = new Excelobj();
            test.setInputFile("exc/tmp_cords"+i+".xls");
            excel = test.read();
            System.out.println(excel[4][0]);
            
            for (int j = 9; j < excel.length; j++) {
                String [] tmp = excel[j][0].split(" ");
                if (tmp[0].equals(datum)) {
                    for (int k = 1; k < excel[0].length; k++) {
                        tmp = excel[j][k].split(" ");
                        if (i<9) {
                            if (tmp[0].equals("@IRANYITOA1")) {
                                li.add(new Koordinatorok(excel[4][k],telepul[i][0],excel[6][k]));
                            } else if (tmp[0].equals("@IRANYITOB1")) {
                                li.add(new Koordinatorok(excel[4][k],telepul[i][1],excel[6][k]));
                            }
                            
                        } else {
                            if (tmp[0].equals("@IRANYITOA1")) {
                                li.add(new Koordinatorok(excel[4][k],telepul[i][0],excel[6][k]));
                            }
                        }
                    }
                }
            }
            
            
        }
        
       
        
        //Koordinatorok
       
        for (Koordinatorok i:li) {
            System.out.println(i);
        }
        main k = new main("Ügyeletes kereső",li);
    }
}

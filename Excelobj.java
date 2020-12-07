

package KordiKereso;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 *
 * @author Radagea
 */
public class Excelobj {
    private String inputFile;
    String[][] data = null;
    public void setInputFile(String inputFile) 
    {
        this.inputFile = inputFile;
    }

    public String[][] read() throws IOException  
    {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try 
        {
            w = Workbook.getWorkbook(inputWorkbook);
            Sheet sheet = w.getSheet(0);
            data = new String[sheet.getColumns()][sheet.getRows()];
            for (int j = 0; j <sheet.getColumns(); j++) 
            {
                for (int i = 0; i < sheet.getRows(); i++) 
                {
                    Cell cell = sheet.getCell(j, i);
                    data[j][i] = cell.getContents();
                }
            }

        } 
        catch (BiffException e) 
        {
            e.printStackTrace();
        }
    return data;
    }
}

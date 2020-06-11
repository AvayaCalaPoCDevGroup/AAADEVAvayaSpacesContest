package service.SpacesContest.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

import service.SpacesContest.Beans.Control;

/**
 *
 * @author umansilla
 */
public class ReadExcelData {

    private final String excelFile;

    public ReadExcelData(String excelFile) throws FileNotFoundException {
        this.excelFile = excelFile;
    }

    public String ReadCellData(int vRow, int vColumn) throws FileNotFoundException, IOException {
        String value = null;          //variable for storing the cell value  
        Workbook wb = null;           //initialize Workbook null  
        try {
            //constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
            FileInputStream fis = new FileInputStream(excelFile);
            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.toString());
        } catch (IOException e1) {
            System.out.println("Error: " + e1.toString());
        }
        Sheet sheet = wb.getSheetAt(0);   //getting the XSSFSheet object at given index  
        Row row = sheet.getRow(vRow); //returns the logical row  
        Cell cell = row.getCell(vColumn); //getting the cell representing the given column  
        value = cell.getStringCellValue();    //getting cell value  
        return value;               //returns the cell value  
    }

    public String getCheckedBox(int vRow, int vColumn) throws FileNotFoundException, Exception {
        XSSFWorkbook wb = (XSSFWorkbook) WorkbookFactory.create(new FileInputStream(excelFile));
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(vRow);
        Cell cell = row.getCell(vColumn);
        Control contol = getControlAt((XSSFCell) cell);
        return contol.getChecked();
    }

    public String getB64Image(int numeroPregunta) throws FileNotFoundException {
        // TODO code application logic here
        Workbook wb = null;           //initialize Workbook null  
        try {
//reading data from a file in the form of bytes  
            FileInputStream fis = new FileInputStream(excelFile);
//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);   //getting the XSSFSheet object at given index  
        int countImages = 0;

        List lst = wb.getAllPictures();

        PictureData pictQuiz = (PictureData) lst.get(numeroPregunta);
        String extQuiz = pictQuiz.suggestFileExtension();
        byte[] dataQuiz = pictQuiz.getData();
        String encoded = Base64.getEncoder().encodeToString(dataQuiz);
        return encoded;
//        FileOutputStream outImageQuiz = new FileOutputStream("C:\\Users\\umansilla\\web\\Excel\\Quiz.jpeg");
//        outImageQuiz.write(dataQuiz);
//        outImageQuiz.close();
    }

    private Control getControlAt(XSSFCell cell) throws Exception {
        XSSFSheet sheet = cell.getSheet();
        Row row = cell.getRow();
        int r = row.getRowNum();
        int c = cell.getColumnIndex();

        int drheight = (int) Math.round(sheet.getDefaultRowHeightInPoints() * Units.EMU_PER_PIXEL / Units.EMU_PER_PIXEL);
        int rheight = (int) Math.round(row.getHeightInPoints() * Units.EMU_PER_PIXEL / Units.EMU_PER_PIXEL);
        row = null;
        if (r > 0) {
            row = sheet.getRow(r - 1);
        }
        int rheightbefore = (row != null) ? (int) Math.round(row.getHeightInPoints() * Units.EMU_PER_PIXEL / Units.EMU_PER_PIXEL) : drheight;
        row = sheet.getRow(r + 1);
        int rheightafter = (row != null) ? (int) Math.round(row.getHeightInPoints() * Units.EMU_PER_PIXEL / Units.EMU_PER_PIXEL) : drheight;

        String name = null;
        String objectType = null;
        String checked = null;

        XmlCursor xmlcursor = null;
        if (sheet.getCTWorksheet().getLegacyDrawing() != null) {
            String legacyDrawingId = sheet.getCTWorksheet().getLegacyDrawing().getId();
            POIXMLDocumentPart part = sheet.getRelationById(legacyDrawingId);
            XmlObject xmlDrawing = XmlObject.Factory.parse(part.getPackagePart().getInputStream());
            xmlcursor = xmlDrawing.newCursor();
            QName qnameClientData = new QName("urn:schemas-microsoft-com:office:excel", "ClientData", "x");
            QName qnameAnchor = new QName("urn:schemas-microsoft-com:office:excel", "Anchor", "x");
            boolean controlFound = false;
            while (xmlcursor.hasNextToken()) {
                XmlCursor.TokenType tokentype = xmlcursor.toNextToken();
                if (tokentype.isStart()) {
                    if (qnameClientData.equals(xmlcursor.getName())) {
                        controlFound = true;
                        XmlObject clientdata = xmlcursor.getObject();
                        XmlObject[] xmlchecked = clientdata.selectPath("declare namespace x='urn:schemas-microsoft-com:office:excel' x:Checked");
                        if (xmlchecked.length > 0) {
                            checked = "true";
                        } else {
                            checked = "false";
                        }
                        while (xmlcursor.hasNextToken()) {
                            tokentype = xmlcursor.toNextToken();
                            if (tokentype.isAttr()) {
                                if (new QName("ObjectType").equals(xmlcursor.getName())) {
                                    objectType = xmlcursor.getTextValue();
                                    name = objectType + " in row " + (r + 1);
                                }
                            } else {
                                break;
                            }
                        }
                    } else if (qnameAnchor.equals(xmlcursor.getName()) && controlFound) {
                        controlFound = false;
                        String anchorContent = xmlcursor.getTextValue().trim();
                        String[] anchorparts = anchorContent.split(",");
                        int fromCol = Integer.parseInt(anchorparts[0].trim());
                        int fromColDx = Integer.parseInt(anchorparts[1].trim());
                        int fromRow = Integer.parseInt(anchorparts[2].trim());
                        int fromRowDy = Integer.parseInt(anchorparts[3].trim());
                        int toCol = Integer.parseInt(anchorparts[4].trim());
                        int toColDx = Integer.parseInt(anchorparts[5].trim());
                        int toRow = Integer.parseInt(anchorparts[6].trim());
                        int toRowDy = Integer.parseInt(anchorparts[7].trim());

                        if (fromCol == c /*needs only starting into the column*/
                                && (fromRow == r || (fromRow == r - 1 && fromRowDy > rheightbefore / 2f))
                                && (toRow == r || (toRow == r + 1 && toRowDy < rheightafter / 2f))) {
//System.out.print(fromCol + ":" +fromColDx + ":" + fromRow + ":" + fromRowDy + ":" + toCol + ":" + toColDx + ":" + toRow + ":" + toRowDy);
                            break;
                        }
                    }
                }
            }
        }

        if (xmlcursor != null && xmlcursor.hasNextToken()) {
            return new Control(name, objectType, checked, r, c);
        }

        return new Control("Not found", "unknown", "undefined", r, c);
    }
}

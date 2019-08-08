package edu.odu.cs.cs350;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel (XLSX) file generator from a Website object to create a single sheet
 * summary consisting of headers and the corresponding counts
 * resulting from the analysis.
 */

public class XLSXReport implements Cloneable {


    String [] columns = {"Page", "# Images", "# CSS", "# Scripts"
            , "# Links (Intra-Page)", "# Links (Internal)",
    "# Links (External)"};

    private Workbook workbook = new XSSFWorkbook();


    private String fileName;
    private String analysisTime;
    private String xlsx;
    private FileOutputStream xlsxFile;
    private Website website;
    private Webpage page;
    private Set<Webpage> pages = new HashSet<Webpage> ();
    private static final int PRIME_NUM_HASH = 31;

    /**
     * Empty XLSX report
     */
    public XLSXReport() {

        fileName = "";
        analysisTime ="";
        xlsx = "";

    }


    /**
     * Creates an XLSX file using the website object and
     * time analysis began.
     * @param website
     * @param analysisTime
     */
    public XLSXReport(Website website, String analysisTime) {

        this.website = website;
        this.setPage(page);
        this.setAnalysisTime(analysisTime);
        xlsx = "";
        fileName = "";

    }

    /**
     * Creates formatted XLSX string from analyzed HTML content
     * @param website The object that will be used in the XLSX file
     * @return xlsx The XLSX file as a formatted string
     * @throws IOException
     */
    public void writeXLSX(Website website) throws IOException {

        Iterator <Webpage> itr = website.getPages().iterator();


        while(itr.hasNext()) {
            pages.add(itr.next());
        }
        

        Sheet sheet = workbook.createSheet("summary");
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i = 0; i < columns.length; i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;

        for (Webpage p : pages)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(p.getPath());
            row.createCell(1).setCellValue(p.getSumImages());
            row.createCell(2).setCellValue(p.getSumCSS());
            row.createCell(3).setCellValue(p.getSumJS());
            row.createCell(4).setCellValue(p.getSumIntrapage());
            row.createCell(5).setCellValue(p.getSumLocal());
            row.createCell(6).setCellValue(p.getSumExternal());

        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        setFileName(analysisTime);
        String file = getFileName();

        FileOutputStream fileOut = new FileOutputStream(file);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();

        System.out.println(file);

    }

    /**
     * Creates a XLSX file containing the formatted
     * XLSX string from website.
     *
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void createXLSXFile() throws IOException, FileNotFoundException {

        writeXLSX(website);

    }

    /**
     * Sets the file name and adds .xlsx file extension.
     * @param analysisTime
     *
     */
    public void setFileName(String analysisTime) {

        fileName = analysisTime + ".xlsx";
    }

    /**
     * Returns fileName as analysis time with .xlsx
     * @return fileName 
     */
    public String getFileName() {

        return fileName;
    }

    /**
     * Returns the formatted xlsx string
     * @return xlsx
     */
    @Override
    public String toString() {

        return xlsx;
    }

    /**
     * Returns the website object
     * @return website
     */
    public Website getWebsite() {

        return website;
    }

    /**
     * Sets the website object
     * @param website
     */
    public void setWebsite(Website website) {

        this.website = website;
    }

    /**
     * Returns the webpage object
     * @return page
     */
    public Webpage getPage() {
        return page;
    }

    /**
     * Sets the webpage object
     * @param page
     */
    public void setPage(Webpage page) {
        this.page = page;
    }

    /**
     * Returns the analysisTime string
     * @return analysisTime
     */
    public String getAnalysisTime() {

        return analysisTime;
    }

    /**
     * Sets the analysisTime string
     * @param analysisTime
     */
    public void setAnalysisTime(String analysisTime) {

        this.analysisTime = analysisTime;
    }

    /*
     * Creates a copy of the XLSX object.
     */
    @Override
    public XLSXReport clone() throws CloneNotSupportedException{

        XLSXReport aCopy = (XLSXReport)super.clone();


        aCopy.analysisTime = analysisTime;
        aCopy.fileName = fileName;
        aCopy.xlsx = xlsx;
        aCopy.xlsxFile = xlsxFile;
        aCopy.website = website;

        return aCopy;
    }

    /*
     * Checks if XLSXReport objects are equal.
     * 
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof XLSXReport))
        {
            return false;
        }

        XLSXReport rhs = (XLSXReport) obj;

        boolean isEqual = analysisTime.equals(rhs.analysisTime)
                && fileName.equals(rhs.fileName)
                && xlsx.equals(rhs.xlsx);

        return isEqual;
    }

    /*
     * Creates hashcode based on xlsx and fileName strings
     */
    @Override
    public int hashCode() {
        int xlsxHash = xlsx.hashCode();
        int fileNameHash = fileName.hashCode();

        return PRIME_NUM_HASH * (xlsxHash + fileNameHash);
    }


}

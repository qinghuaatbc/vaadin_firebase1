package com.goldknight;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.function.Consumer;

public class CreatePdf {

    private ArrayList<Zone> pdfZoneList;
    private String id;

    public CreatePdf(ArrayList<Zone> pdfZoneList, String id) {
        this.pdfZoneList = pdfZoneList;
        this.id = id;
    }


    public void make() throws DocumentException {
        String id1 =id.replaceAll("\\s","");
        String current = System.getProperty("user.dir");
        String url = current +"/src/main/resources/META-INF/resources/zoneList"+id1+".pdf";
        Document document = new Document();
        try {
            PdfWriter.getInstance(document,new FileOutputStream(url));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        document.open();
       pdfZoneList.forEach(new Consumer<Zone>() {
           @Override
           public void accept(Zone zone) {

               try {
                   document.add(new Paragraph(zone.zid+":"+zone.zname));
               } catch (DocumentException e) {
                   e.printStackTrace();
               }
           }
       });



        document.close();

    }


}

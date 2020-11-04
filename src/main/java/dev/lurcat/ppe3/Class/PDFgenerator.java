/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.lurcat.ppe3.Class;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.Table;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a.masvidal
 */
public class PDFgenerator {
    public void generePDF(ArrayList<String> panier, String PrixTotale, Integer idCommande ,Client leClient){
        ArrayList columnTitle = new ArrayList<String>();
        columnTitle.add("Id_Produit");
        columnTitle.add("Libelle");
        columnTitle.add("Quantité");
        columnTitle.add("Prix Unitaire HT");
        columnTitle.add("Prix Totale");
        Document unDocument = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(unDocument, new FileOutputStream("G:\\Facture_"+leClient.getNom()+"_"+idCommande+".pdf"));
            unDocument.addAuthor("2TAK_HARDWARE");
            unDocument.addTitle("Facturation_"+leClient.getNom()+"_"+leClient.getPrenom());
            unDocument.open();
            
            Paragraph p = new Paragraph("Facturation commande "+idCommande, FontFactory.getFont(FontFactory.defaultEncoding,24, Font.BOLD));
            p.setAlignment(Element.ALIGN_CENTER);
            
            Paragraph emetteur = new Paragraph("\n \n 2TAK Hardware \n 1 rue de JeSaisPasOù,\n 66 000 Perpignan \n\n",
                    FontFactory.getFont(FontFactory.defaultEncoding,11));
            
            Paragraph destinataire = new Paragraph(leClient.getNom()+" "+leClient.getPrenom()+"\n"+leClient.getEmail()+"\n"+leClient.getTelephone()+" \n\n");
            destinataire.setAlignment(Element.ALIGN_RIGHT);
            
            
            PdfPTable table = new PdfPTable(5);
            PdfPCell c1;
            
            for (int i = 0; i < 5; i++) {
                c1 = new PdfPCell(new Phrase((String) columnTitle.get(i)));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }
            table.setHeaderRows(1);

            for (String string : panier) {
                table.addCell(string);
            }
            
            table.setTotalWidth(PageSize.A4.getWidth()-100);
            table.setLockedWidth(true);
            
            Paragraph Totale = new Paragraph("PrixTotale = "+PrixTotale);
            Totale.setAlignment(Element.ALIGN_RIGHT);
            
            unDocument.add(p);
            unDocument.add(emetteur);
            unDocument.add(destinataire);
            unDocument.add(table);
            unDocument.add(Totale);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFgenerator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFgenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        unDocument.close();
    }
}

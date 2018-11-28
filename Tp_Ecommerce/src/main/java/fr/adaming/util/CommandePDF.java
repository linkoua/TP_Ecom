package fr.adaming.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.modele.Commande;
import fr.adaming.modele.LigneCommande;

public class CommandePDF {
	private Document document ;
	private PdfWriter pdfWriter;	
	private File temp;
	
	public CommandePDF() {
		super();

	}
	
	public File createPDF(Commande c) {
		try {
			temp= File.createTempFile("Commande",".pdf");

			pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(temp));
		
		document.open();
		 //Titre du document
        document.addTitle("Descriptif de la Commande");

        Paragraph p = new Paragraph("Contenu de la commande",new Font(FontFamily.HELVETICA, 20, Font.NORMAL, BaseColor.RED));
        p.setAlignment(Element.ALIGN_CENTER);;
        document.add(p);
        
        Paragraph clientp = new Paragraph("Client : " +c.getpClient().getNom(),new Font(FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLUE));
        document.add(clientp);
        
        Date time = c.getDate();
        DateFormat dfl = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
        Paragraph datep = new Paragraph(dfl.format(time), new Font(FontFamily.HELVETICA, 10));
        document.add(datep);
        
        //Saut de ligne
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );


        //Creation table
        PdfPTable   table = new PdfPTable(new float[] { 2f, 4f, 2f, 2f});

        //Entete
        Font    headingStyle = new Font(FontFamily.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
        PdfPCell cell= new PdfPCell ();
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        
        cell.setPhrase(new Phrase("ID", headingStyle));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Nom Produit", headingStyle));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantite", headingStyle));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Prix", headingStyle));
        table.addCell(cell);
        Double total=0.0;
        for(LigneCommande lc: c.getListelcommande()) {
        	 PdfPCell lid = new PdfPCell(new Phrase(Integer.toString(lc.getId())));
             table.addCell(lid);
        	
        	
        	table.addCell(lc.getProduit().getDesignation());
        	PdfPCell lq = new PdfPCell(new Phrase(Integer.toString(lc.getQuantite())));
        	  table.addCell(lq);
        	PdfPCell lp = new PdfPCell(new Phrase(Double.toString(lc.getPrix())));
        	table.addCell(lp);
        	total= total+lc.getPrix();
            
        }
        document.add(table);
 
      
        
        document.add( Chunk.NEWLINE );
        document.add( Chunk.NEWLINE );
        p.add (new Paragraph("Le total de votre commande s'élève à "+ total+ " €",new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLUE)));
        document.add(p);
        p.add (new Paragraph("TotoShop vous remercie de votre commande, et reste à votre disposition pour toutes informations complémentaire",new Font(FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLUE)));
        document.add(p);
        document.close();
		
		return temp;
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	

}

package cl.safe.view.pdf;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Header;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Image;
import com.lowagie.text.Cell;

import cl.safe.config.ImageResizer;
import cl.safe.dto.CapacitacionParaTrabajadorResponseDto;
import cl.safe.entity.CapacitacionParaTrabajadorEntity;
import cl.safe.service.AsistenciaService;

@Component("certificado/ver")
public class CertificadoPdfView extends AbstractPdfView {
	
	@Autowired
	AsistenciaService asistenciaService;
	
	@Autowired
	ImageResizer imageResizer;
	
	public final String LOGO = "logoMinSafe.PNG";
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			CapacitacionParaTrabajadorEntity c =  (CapacitacionParaTrabajadorEntity) model.get("certificado");
			
			String firmaSrc = asistenciaService.getFirmaByAsistenciaId(c.getAsistenciaId());
			
			String trabajador = c.getNombre() + " "
					+ c.getApellidoPaterno() + " "
					+ c.getApellidoMaterno();
			
			Paragraph titulo = new Paragraph("CERTIFICADO DE CAPACITACIÓN SAFE S.A.");
			titulo.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph nombreCapacitacion = new Paragraph(c.getCapacitacionNombre());
			nombreCapacitacion.setAlignment(Element.ALIGN_CENTER);			

			Table t = new Table(2);
			
			t.setPadding(10);
			
			t.addCell("Trabajador");
			t.addCell("Email");
			
			t.addCell(trabajador);
			t.addCell(c.getEmail());
			
			
			
			Paragraph detalle = new Paragraph("Mediante el presente "
					+ "documento se deja constancia que '" + trabajador + "' "
					+ "rindio con exito esta capacitación");
			
			Table parrafos = new Table(1);
			parrafos.setPadding(10);
			parrafos.addCell(detalle);
			
			/* LOGO */
			Image image = Image.getInstance(imageResizer.getFilePath(LOGO));
			image.setAlignment(Element.ALIGN_CENTER);

			document.add(image);
			
			document.add(titulo);
			document.add(nombreCapacitacion);
			
			document.add(t);
			
			document.add(parrafos);
			
			Image imageFirma = Image.getInstance(imageResizer.getFilePath(firmaSrc));
			imageFirma.setAlignment(Element.ALIGN_CENTER);
			
			Image imageFirmaSafe = Image.getInstance(imageResizer.getFilePath("firmaSafe.png"));
			imageFirmaSafe.setAlignment(Element.ALIGN_CENTER);
			
				Table firmas = new Table(2);
					firmas.setPadding(5);
		
					firmas.setBorder(Rectangle.NO_BORDER);
					firmas.setBackgroundColor(new Color(255,255,255));
					firmas.setBorderColor(Color.white);
		
						Cell firmaTitulo1 = new Cell("Firma Trabajador");
							firmaTitulo1.setBorder(Rectangle.NO_BORDER);
							firmaTitulo1.setBorderWidthBottom(1);
							firmaTitulo1.setHorizontalAlignment(Element.ALIGN_CENTER);
							firmas.addCell(firmaTitulo1);
					
						Cell firmaTitulo2 = new Cell("Firma Safe");
							firmaTitulo2.setBorder(Rectangle.NO_BORDER);
							firmaTitulo2.setBorderWidthBottom(1);
							firmaTitulo2.setHorizontalAlignment(Element.ALIGN_CENTER);
							firmas.addCell(firmaTitulo2);
					
						Cell cellFirma1 = new Cell(imageFirma);
							cellFirma1.setBorder(Rectangle.NO_BORDER);
		
					firmas.addCell(cellFirma1);
					
						Cell cellFirma2 = new Cell(imageFirmaSafe);
							cellFirma2.setBorder(Rectangle.NO_BORDER);
					
					firmas.addCell(cellFirma2);
			
			document.add(firmas);

	}

}

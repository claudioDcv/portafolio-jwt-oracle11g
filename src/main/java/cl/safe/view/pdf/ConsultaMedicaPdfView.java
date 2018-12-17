package cl.safe.view.pdf;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Header;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import cl.safe.config.ImageResizer;
import cl.safe.dto.CapacitacionParaTrabajadorResponseDto;
import cl.safe.entity.CapacitacionParaTrabajadorEntity;
import cl.safe.entity.ConsultaMedicaFullTrabajadorEntity;
import cl.safe.entity.ExamenEntity;

@Component("consulta/ver")
public class ConsultaMedicaPdfView extends AbstractPdfView {

	@Autowired
	ImageResizer imageResizer;
	
	public final String LOGO = "logoMinSafe.PNG";
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			ConsultaMedicaFullTrabajadorEntity c =  (ConsultaMedicaFullTrabajadorEntity) model.get("consulta");
			
			List<ExamenEntity> examenes = (List<ExamenEntity>) model.get("examenes");
			
			String trabajador = c.getNombre() + " "
					+ c.getApellidoPaterno() + " "
					+ c.getApellidoMaterno();

			SimpleDateFormat formateador = new SimpleDateFormat("dd 'del' MM 'de' yyyy", new Locale("es_ES"));
			
			String fecha = formateador.format(c.getFechaRealizacion());
			
			Paragraph titulo = new Paragraph("Consulta medica SAFE S.A. realizada en: " + fecha);
			titulo.setAlignment(Element.ALIGN_CENTER);

			Paragraph nombreCapacitacion = new Paragraph("");
			nombreCapacitacion.setAlignment(Element.ALIGN_CENTER);			

			Table t = new Table(2);
			t.setPadding(5);
			
			t.addCell("Trabajador");
			t.addCell("Email");
			
			t.addCell(trabajador);
			t.addCell(c.getEmail());
			
			Table tablaMedico = new Table(2);
			tablaMedico.setPadding(5);
			
			tablaMedico.addCell("Medico");
			tablaMedico.addCell("Email");
			
			tablaMedico.addCell(c.getMedicoNombre());
			tablaMedico.addCell(c.getMedicoEmail());
			
			final Table tablaExamenes = new Table(2);
			tablaExamenes.setPadding(5);
			
			tablaExamenes.addCell("Examen Código");
			tablaExamenes.addCell("Nombre");

			
			examenes.forEach(new Consumer<ExamenEntity>() {

				@Override
				public void accept(ExamenEntity t) {
					tablaExamenes.addCell(t.getCodigo());
					tablaExamenes.addCell(t.getNombre());
				}
				
			});
			
			Paragraph detalle = new Paragraph("Mediante el presente "
					+ "documento se deja constancia que '" + trabajador + "' "
					+ "fue examinado por '" + c.getMedicoNombre() + "'");
			
			Table parrafosDetalle = new Table(1);
			parrafosDetalle.setPadding(10);
			parrafosDetalle.addCell(detalle);
			
			/* LOGO */
			Image image = Image.getInstance(imageResizer.getFilePath(LOGO));
			image.setAlignment(Element.ALIGN_CENTER);

			document.add(image);

			document.add(titulo);
			document.add(nombreCapacitacion);
			
			document.add(t);
			
			document.add(tablaMedico);
			
			document.add(tablaExamenes);
			
			document.add(parrafosDetalle);
			
			Table firmas = new Table(2);
				firmas.setPadding(5);
	
				firmas.setBorder(Rectangle.NO_BORDER);
				firmas.setBackgroundColor(new Color(255,255,255));
				firmas.setBorderColor(Color.white);
				
						Image imageFirmaSafe = Image.getInstance(imageResizer.getFilePath("firmaSafe.png"));
						imageFirmaSafe.setAlignment(Element.ALIGN_CENTER);
						
						Cell firmaTitulo1 = new Cell("");
						firmaTitulo1.setBorder(Rectangle.NO_BORDER);
						firmaTitulo1.setBorderWidthBottom(1);
						firmaTitulo1.setHorizontalAlignment(Element.ALIGN_CENTER);
						firmas.addCell(firmaTitulo1);
				
					Cell firmaTitulo2 = new Cell("Firma Safe");
						firmaTitulo2.setBorder(Rectangle.NO_BORDER);
						firmaTitulo2.setBorderWidthBottom(1);
						firmaTitulo2.setHorizontalAlignment(Element.ALIGN_CENTER);
						firmas.addCell(firmaTitulo2);
				
					Cell cellFirma1 = new Cell("");
						cellFirma1.setBorder(Rectangle.NO_BORDER);
		
				firmas.addCell(cellFirma1);
				
					Cell cellFirma2 = new Cell(imageFirmaSafe);
						cellFirma2.setBorder(Rectangle.NO_BORDER);
				
				firmas.addCell(cellFirma2);
		
		document.add(firmas);
			
	}

}

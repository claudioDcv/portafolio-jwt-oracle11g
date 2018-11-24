package cl.safe.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Header;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import cl.safe.dto.CapacitacionParaTrabajadorResponseDto;
import cl.safe.entity.CapacitacionParaTrabajadorEntity;

@Component("certificado/ver")
public class CertificadoPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
			CapacitacionParaTrabajadorEntity c =  (CapacitacionParaTrabajadorEntity) model.get("certificado");
			String trabajador = c.getNombre() + " " + c.getApellidoPaterno() + " " + c.getApellidoMaterno();
			
			
			Paragraph titulo = new Paragraph("CERTIFICADO DE CAPACITACIÓN SAFE S.A.");
			titulo.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph nombreCapacitacion = new Paragraph(c.getCapacitacionNombre());
			nombreCapacitacion.setAlignment(Element.ALIGN_CENTER);			

			Table t = new Table(2);
			t.addCell("Trabajador");
			t.addCell("Email");
			
			t.addCell(trabajador);
			t.addCell(c.getEmail());
			
			
			Paragraph detalle = new Paragraph("Mediante el presente "
					+ "documento se deja constancia que '" + trabajador + "' "
					+ "rindio con exito esta capacitación");
			
			document.add(titulo);
			document.add(nombreCapacitacion);
			
			document.add(t);
			
			document.add(detalle);
	}

}

package cl.safe.view.pdf;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Consumer;

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
import cl.safe.entity.ConsultaMedicaFullTrabajadorEntity;
import cl.safe.entity.ExamenEntity;

@Component("consulta/ver")
public class ConsultaMedicaPdfView extends AbstractPdfView {

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
			t.addCell("Trabajador");
			t.addCell("Email");
			
			t.addCell(trabajador);
			t.addCell(c.getEmail());
			
			Table tablaMedico = new Table(2);
			tablaMedico.addCell("Medico");
			tablaMedico.addCell("Email");
			
			tablaMedico.addCell(c.getMedicoNombre());
			tablaMedico.addCell(c.getMedicoEmail());
			
			final Table tablaExamenes = new Table(2);
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
			
			document.add(titulo);
			document.add(nombreCapacitacion);
			
			document.add(t);
			
			document.add(tablaMedico);
			
			document.add(tablaExamenes);
			
			document.add(detalle);
	}

}

package cl.safe.service;

public interface AsistenciaService {
	Long firmarAsistencia(Long asistenciaId, String firma);
	Long inscribirEnCapacitacion(Long trabajadorId, Long capacitacionId);
	
	String getFirmaByAsistenciaId(Long asistenciaId);
}

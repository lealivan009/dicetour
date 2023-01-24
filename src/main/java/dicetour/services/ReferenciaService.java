package dicetour.services;

import dicetour.entities.Referencia;

import java.util.List;

public interface ReferenciaService extends BaseService <Referencia, Long>{
    public List<Referencia> findReferenciasVigentes() throws Exception;
}

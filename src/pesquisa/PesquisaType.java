package pesquisa;

import java.util.ArrayList;
import java.util.List;

public class PesquisaType {

    List<String> tiposPesquisas = new ArrayList();
    
    public PesquisaType() {
        tiposPesquisas.add("Nome");
        tiposPesquisas.add("cod");

    }

    public List<String> getTiposPesquisas() {
        return tiposPesquisas;
    }

    public void setTiposPesquisas(List<String> tiposPesquisas) {
        this.tiposPesquisas = tiposPesquisas;
    }
    
    
    
}

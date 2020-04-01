package bean.backing;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import bean.model.Candidato;

@Named
@RequestScoped

public class VacanteForm{
    
    @Inject
    private Candidato candidato;
    
    public void setCandidato(Candidato candidato){
        this.candidato = candidato;
    }
    
    public String enviar(){
        if(this.candidato.getNombre().equals("Sandy")){
            return "exito";
        }
        else
            return "fallo";
    }
}

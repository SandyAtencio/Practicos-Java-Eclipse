package bean.backing;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.*;

import bean.model.Candidato;

@Named
@RequestScoped

public class VacanteForm{
    
    @Inject
    private Candidato candidato;
    
   Logger log = LogManager.getRootLogger();
    
    public VacanteForm() {
    	log.info("creando el objeto VacanteForm");
	}

	public void setCandidato(Candidato candidato){
        this.candidato = candidato;
    }
    
    public String enviar(){
        if(this.candidato.getNombre().equals("Sandy")){
        	if(this.candidato.getApellido().equals("Atencio")) {
        		String msj = "Gracias pero, Sandy Atencio ya trabaja con nosotros";
        		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msj, msj);
        		FacesContext facesContext = FacesContext.getCurrentInstance();
        		String componentId = null; // este es un mensaje global
        		facesContext.addMessage(componentId, facesMessage);
        		log.info("Entrando en el caso de index");
        		
        	}
        return "index";
        }
        else
        	log.info("Entrando en el caso de fallo");
            return "fallo";
    }
}

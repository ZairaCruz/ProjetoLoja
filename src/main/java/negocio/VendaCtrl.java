package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import beans.Venda;
import persistencia.VendaDAO;

@ManagedBean
@SessionScoped
public class VendaCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Venda venda = new Venda();
	
	public List<Venda> getListagem(){
		return VendaDAO.listagem("");
	}
	
	public String actionGravar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(venda.getId() == 0){
			VendaDAO.inserir(venda);
			context.addMessage(null, new FacesMessage("Sucesso", "Venda inserido com sucesso!"));
			return actionInserir();
		}else{
			VendaDAO.alterar(venda);
			context.addMessage(null, new FacesMessage("Sucesso", "Venda alterado com sucesso!"));
		}
		return "venda";
	}
	
	public String voltar(){
		return "venda";
	}
	
	public String actionInserir(){
		venda = new Venda();
		return "venda";
	}
	
	public String actionExcluir(){
		VendaDAO.excluir(venda);
		return "venda";
	}
	
	public void onRowSelect(SelectEvent event){
		FacesMessage msg = new FacesMessage("Venda selecionado",
				String.valueOf(((Venda) event.getObject()).getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void handleClose(CloseEvent event) {   
        FacesContext facesContext = FacesContext.getCurrentInstance();   
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,    
            event.getComponent().getId() + " closed", "So you don't like nature?");   
        facesContext.addMessage(null, message);   
    }   
	
	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}



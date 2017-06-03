package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import beans.Item;
import beans.Produto;
import persistencia.ItemDAO;

@ManagedBean
@SessionScoped
public class ItemCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Item item = new Item();
	
	public List<Item> getListagem(){
		return ItemDAO.listagem("");
	}
	
	public String actionGravar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(item.getId() == 0){
			ItemDAO.inserir(item);
			context.addMessage(null, new FacesMessage("Sucesso", "Item inserido com sucesso!"));
			return actionInserir();
		}else{
			ItemDAO.alterar(item);
			context.addMessage(null, new FacesMessage("Sucesso", "Item alterado com sucesso!"));
		}
		return "produto";
	}
	
	public String voltar(){
		return "produto";
	}
	
	public String actionInserir(){
		item = new Item();
		return "produto";
	}
	
	public String actionExcluir(){
		ItemDAO.excluir(item);
		return "produto";
	}
	
	public void onRowSelect(SelectEvent event){
		FacesMessage msg = new FacesMessage("Item selecionado",
				String.valueOf(((Produto) event.getObject()).getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void handleClose(CloseEvent event) {   
        FacesContext facesContext = FacesContext.getCurrentInstance();   
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,    
            event.getComponent().getId() + " closed", "So you don't like nature?");   
        facesContext.addMessage(null, message);   
    }

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}   
}



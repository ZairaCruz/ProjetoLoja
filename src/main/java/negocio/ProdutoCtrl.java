package negocio;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import persistencia.ProdutoDAO;
import beans.Produto;

@ManagedBean
@SessionScoped
public class ProdutoCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	
	public List<Produto> getListagem(){
		return ProdutoDAO.listagem("");
	}
	
	public String actionGravar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(produto.getId() == 0){
			ProdutoDAO.inserir(produto);
			context.addMessage(null, new FacesMessage("Sucesso", "Produto inserido com sucesso!"));
			return actionInserir();
		}else{
			ProdutoDAO.alterar(produto);
			context.addMessage(null, new FacesMessage("Sucesso", "Produto alterado com sucesso!"));
		}
		return "produto";
	}
	
	public String voltar(){
		return "produto";
	}
	
	public String actionInserir(){
		produto = new Produto();
		return "produto";
	}
	
	public String actionExcluir(){
		ProdutoDAO.excluir(produto);
		return "produto";
	}
	
	public void onRowSelect(SelectEvent event){
		FacesMessage msg = new FacesMessage("Produto selecionado",
				String.valueOf(((Produto) event.getObject()).getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void handleClose(CloseEvent event) {   
        FacesContext facesContext = FacesContext.getCurrentInstance();   
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,    
            event.getComponent().getId() + " closed", "So you don't like nature?");   
        facesContext.addMessage(null, message);   
    }   
	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}


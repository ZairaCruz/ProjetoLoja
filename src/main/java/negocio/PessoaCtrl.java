package negocio;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import beans.Cidade;
import beans.Estado;
import beans.Fone;
import beans.Pessoa;
import persistencia.PessoaDAO;

@ManagedBean
@SessionScoped
public class PessoaCtrl implements Serializable {

	private static final long serialVersionUID = 1L;
	private Pessoa pessoa = new Pessoa();
	
	public List<Pessoa> getListagem(){
		return PessoaDAO.listagem("");
	}
	public List<Fone> getListagemFones() {
		return PessoaDAO.listagemFones();
	}

	public List<Estado> getListagemEstados() {
		return PessoaDAO.listagemEstados();
	}

	public List<Cidade> getListagemCidades() {
		return PessoaDAO.listagemCidades();
	}
	
	public List<Cidade> getListagemCidadesPorEstado(String estado){
		return PessoaDAO.listagemCidadesFiltradasPorEstado(estado);
	}

	
	public String actionGravar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(pessoa.getId() == 0){
			PessoaDAO.inserir(pessoa);
			context.addMessage(null, new FacesMessage("Sucesso", "Pessoa inserido com sucesso!"));
			return actionInserir();
		}else{
			PessoaDAO.alterar(pessoa);
			context.addMessage(null, new FacesMessage("Sucesso", "Pessoa alterado com sucesso!"));
		}
		return "cliente";
	}
	
	public String voltar(){
		return "cliente";
	}
	
	public String actionInserir(){
		pessoa = new Pessoa();
		return "cliente";
	}
	
	public String actionExcluir(){
		PessoaDAO.excluir(pessoa);
		return "cliente";
	}
	
	public void onRowSelect(SelectEvent event){
		FacesMessage msg = new FacesMessage("Pessoa selecionada",
				String.valueOf(((Pessoa) event.getObject()).getId()));
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void handleClose(CloseEvent event) {   
        FacesContext facesContext = FacesContext.getCurrentInstance();   
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,    
            event.getComponent().getId() + " closed", "So you don't like nature?");   
        facesContext.addMessage(null, message);   
    }   
	
	public String actionInserirFone() { 
		Fone fone = new Fone();
		fone.setPessoa(pessoa);
		pessoa.getFones().add(fone);
		return "publico/form_pessoa";

	}

	public String actionExcluirFone(Fone f) {
		pessoa.getFones().remove(f);
		return "publico/form_pessoa";
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}


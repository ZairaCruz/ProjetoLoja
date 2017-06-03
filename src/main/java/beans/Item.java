package beans;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "item")
@NamedQueries({
	@NamedQuery(name = "Item.listar", query = "Select item from Item item"),
	@NamedQuery(name = "Item.buscarPorCodigo", query = "Select item from Item item where id = :id")
})
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "quantidade", nullable = false)
	private Integer quantidade;
	
	@Column(name = "valorUnitario", precision = 4, scale = 2, nullable = false)
	private BigDecimal valorUnitario;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
	private Produto produto;
	
//	// Relacionamento de vários para 1
//	@ManyToOne
//	@JoinColumn(name = "id_venda", referencedColumnName = "id")
//	private Venda venda;
//
//	public Venda getVenda() {
//		return venda;
//	}
//
//	public void setVenda(Venda venda) {
//		this.venda = venda;
//	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + "]";
	}
		
}

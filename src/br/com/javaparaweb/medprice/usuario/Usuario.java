package br.com.javaparaweb.medprice.usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import br.com.javaparaweb.medprice.comentario.Comentario;
import br.com.javaparaweb.medprice.medicamento.Medicamento;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario")
	private int idUsuario;
	private String email;
	private String nome;
	private String senha;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="usuarioBean")
	private List<Comentario> comentarios;

	//bi-directional many-to-many association to Medicamento
	@ManyToMany(mappedBy="usuarios")
	private List<Medicamento> medicamentos;
	
	@ElementCollection(targetClass = String.class) 
	@JoinTable(
			name="usuario_permissao", 
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario","permissao"})}, 
			joinColumns = @JoinColumn(name = "usuario")) 
	@Column(name = "permissao", length=50) 
	private Set<String>	permissao	= new HashSet<String>();

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setUsuarioBean(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setUsuarioBean(null);

		return comentario;
	}

	public List<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public Set<String> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<String> permissao) {
		this.permissao = permissao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idUsuario;
		result = prime * result + ((medicamentos == null) ? 0 : medicamentos.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((permissao == null) ? 0 : permissao.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		Usuario other = (Usuario) obj;
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		if (medicamentos == null) {
			if (other.medicamentos != null)
				return false;
		} else if (!medicamentos.equals(other.medicamentos))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (permissao == null) {
			if (other.permissao != null)
				return false;
		} else if (!permissao.equals(other.permissao))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}

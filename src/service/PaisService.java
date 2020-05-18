package service;

import java.util.ArrayList;

import dao.PaisDAO;
import model.Pais;

public class PaisService {

	PaisDAO dao = new PaisDAO();

	public int criar (Pais pais) {
		return dao.incluir(pais);
	}
	
	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}
	
	public Pais consultar(int id) {
		return dao.carregar(id);
		 
	}
		public void excluir(int id) {
			dao.excluir(id);
		}
		public ArrayList<Pais> listarTodos(){
		return dao.listarTodos();
		}
	}


package service;

import java.util.ArrayList;

import dao.PaisDAO;
import model.Pais;

public class PaisesService {
	PaisDAO dao;
	
	public PaisesService(){
		dao = new PaisDAO();
	}
	public ArrayList<Pais> listarPaises(){
		return dao.listarTodos();
	}
	public ArrayList<Pais> listarPaises(String chave){
		return dao.listarPaises(chave);
	}

}

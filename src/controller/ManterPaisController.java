package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		String acao = request.getParameter("acao");

//instanciar o javabean
		Pais pais = new Pais();
		pais.setNome(pNome);
		pais.setPopulacao(Long.parseLong(pPopulacao));
		pais.setArea(Double.parseDouble(pArea));

		PaisService cs = new PaisService();
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		if (acao.equals("Criar")) {
			cs.criar(pais);
			ArrayList<Pais> lista = new ArrayList<>();
			lista.add(pais);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");
		} else if (acao.equals("Excluir")) {
			cs.excluir(pais.getId());
			ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
			lista.remove(busca(pais, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPaises.jsp");			
		} else if (acao.equals("Alterar")) {
			cs.atualizar(pais);
			ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
			int pos = busca(pais, lista);
			lista.remove(pos);
			lista.add(pos, pais);
			session.setAttribute("lista", lista);
			request.setAttribute("cliente", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");			
		} else if (acao.equals("Visualizar")) {
			pais = cs.consultar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");		
		} else if (acao.equals("Editar")) {
			pais = cs.consultar(pais.getId());
			request.setAttribute("cliente", pais);
			view = request.getRequestDispatcher("AlterarPais.jsp");		
		}
		
		view.forward(request, response);

	}

	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for(int i = 0; i < lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == pais.getId()){
				return i;
			}
		}
		return -1;
	}

}
		
		
	
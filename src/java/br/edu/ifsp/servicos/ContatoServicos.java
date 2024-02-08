package br.edu.ifsp.servicos;

import br.edu.ifsp.dao.DAO;
import br.edu.ifsp.model.Contato;
import br.edu.ifsp.utility.AgendaException;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;

public class ContatoServicos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private DAO<Contato> dao;
    
    public void salvar(Contato c) throws AgendaException{
        System.out.println("passando pelo salvar");
        dao.salvar(c);
    }
    
    public void remover(Contato c) throws AgendaException{
        dao.remover(Contato.class ,c.getId());
    }
    
    public List<Contato> listarTodos(){
        return dao.buscarTodos("SELECT c FROM Contato c order by c.nome");
    }
}

package br.edu.ifsp.dominio.controller;

import br.edu.ifsp.model.Contato;
import br.edu.ifsp.servicos.ContatoServicos;
import br.edu.ifsp.utility.Menssagens;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import javax.inject.Named;

@Named("contatoBean") 
@SessionScoped
public class ContatoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Contato contato;

    @Inject
    private ContatoServicos servicos;

    private List<Contato> listaContatos;
    
    private boolean editar = false;

   // @PostConstruct
    public void carregar() {
        listaContatos = servicos.listarTodos();
    }

    public void adicionar() {
        
        try {
            servicos.salvar(contato);
            contato = new Contato();
            carregar();
            Menssagens.info("Contato adicionado");
        } catch (Exception e) {
            Menssagens.erro(e.getMessage());
       }
        
      }
    
    public void excluir(Contato contatoexcluir){
        try{
            servicos.remover(contatoexcluir);
            carregar();
            Menssagens.info(contatoexcluir.getNome()+" foi removido!");
           
        }catch(Exception e){
            Menssagens.erro(e.getMessage());
        }
    }
    
    public void salvar() {
        try{
            servicos.salvar(contato);
            carregar();
            Menssagens.info(contato.getNome()+" foi editado");
        }catch(Exception e){
            Menssagens.erro(e.getMessage());
        }
    }
    
    public String isEditar(Contato contatoSelecionado){
         
        contato = contatoSelecionado;
           
        editar = true;
        
        Menssagens.info(contato.getNome()+" selecionado para edição");
       
        return "cadastro.xhtml";
        
    }
    
    public String isAdicionar(){
         
        contato = new Contato();
           
        editar = false;
        
        
   
        return "cadastro.xhtml";
        
    }
    
    
    
    public String cadastrarOuAlterar() {
        
        if (editar) {
            salvar(); 
        } else {
            adicionar(); 
        }
        
        return "agenda.xhtml";
    }


    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public List<Contato> getListaContatos() {
        carregar();
        return listaContatos;
    }

    public boolean getEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

        
    

}
 
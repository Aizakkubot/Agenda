package br.com.agenda_facil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.agenda_facil.model.Contato;
import br.com.agenda_facil.repository.ContatoRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;




@Controller 
public class ContatoController {


    private final ContatoRepository contatoRepository;

    public ContatoController(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    // READ: listar os contatos


    @GetMapping ("/")
    public String listar(Model model ) {

        model.addAttribute("contatos", contatoRepository.findAll());
        
        model.addAttribute("total", contatoRepository.count());

        return "contatos";
    }

    // CREATE: abrir o formulario vazio

    @GetMapping ("/contatos/novo")
   public String novo (Model model) {

    model.addAttribute("contato", new Contato());

    model.addAttribute("titulo", "Novo contato");

    return "formulario";
   }

   // CREATE / UPDATE: Salvar os dados do formulario

   @PostMapping("/contatos/salvar")
   public String salvar(
    @Valid  @ModelAttribute ("contato") Contato contato,
    BindingResult resultado,
    Model model) {

if (resultado.hasErrors()) {

    model.addAttribute("titulo",
        contato.getId() == null ? "Novo contato" : "Editar contato");

        return "formulario";
}

    

contatoRepository.save(contato);

return "redirect:/";
}

//UPDATE: abrir o formulario preenchido

@GetMapping("/contatos/editar/{id}")
public String editar(@PathVariable Long id, Model model) {
    
    Contato contato = contatoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(
                "Contato não encontrado: " + id));
    model.addAttribute("contato", contato);
    model.addAttribute("titulo", "Editar Contato");            
            
    return "formulario";
}

// DELETE: excluir um contato

    @PostMapping("/contatos/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        
        contatoRepository.deleteById(id);

        return "redirect:/";
     
    }
    
}
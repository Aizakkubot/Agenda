package br.com.agenda_facil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.agenda_facil.model.Contato;

//@Repository identifica esta interface como responsavel
//pelo acesso aos dados (a camada que conversa com o banco).
@Repository
//extends JpaRepository<Contato, Long:
//Contato -> a entidade que será gravada e lida;
//long -> O tipo do identificadpr (o atributo "id").
//Não escrevemos nenhuma implementação: O Spring cria a classe em tempo de execução. 
public interface ContatoRepository extends JpaRepository<Contato, Long> {

    // O JpaRespository já fornece os métodos necessarios para o CRUD:
    //
    // save()       -> cadastrar ou atualizar (SQL: INSERT ou UPDATE)
    // findAll()    -> listar todos           (SQL: SELECT)
    // findById()   -> buscar pelo ID         (SQL: SELECT ... WHERE id = ?)  
    // deleteById() -> excluir pelo ID        (SQL: DELETE)
    // count()      -> contar registros       (SQL: SELECT COUNT(*))  
}

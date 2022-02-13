package modelo;


import javax.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente() {
    }

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome,cpf); // injeção de dependencia com inversão de controle DI/IOC
    }

    public String getNome(){  // Método Delegate, delegando a classe DadosPessoais o retorno do nome.
        return this.dadosPessoais.getNome();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DadosPessoais getDadosPessoais() {
        return dadosPessoais;
    }
}

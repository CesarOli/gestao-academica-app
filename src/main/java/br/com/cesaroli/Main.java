package br.com.cesaroli;

import br.com.cesaroli.model.Aluno;
import br.com.cesaroli.model.Curso;
import br.com.cesaroli.service.AcademiaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AcademiaService service = new AcademiaService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("#### SEJA BEM-VINDO AO SISTEMA DE GESTÃO ACADÊMICA ####");

        System.out.println("\n---- Cadastro de Novo Aluno ----");

        System.out.println("Digite o nome do Aluno: ");
        String nome = scanner.nextLine();

        System.out.println("Informe o email deste Aluno, por favor: ");
        String email = scanner.nextLine();

        System.out.println("Informe por favor, o número de matrícula do Aluno: ");
        String matricula = scanner.nextLine();

        Curso cursoPadrao = new Curso();
        cursoPadrao.setNome("Análise e Desenvolvimento de Sistemas");

        Aluno alunoCadastrado = service.cadastrarAluno(nome, email, matricula, cursoPadrao);

        System.out.println("\nDetalhes do Cadastro: ");
        System.out.println(alunoCadastrado);

        System.out.println("Cadastro finalizado com sucesso, obrigado!");

        scanner.close();

    }
}
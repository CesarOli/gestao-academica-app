package br.com.cesaroli;

import br.com.cesaroli.model.Aluno;
import br.com.cesaroli.model.Curso;
import br.com.cesaroli.model.Professor;
import br.com.cesaroli.service.AcademiaService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AcademiaService service = new AcademiaService();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        Curso cursoPadrao = new Curso();
        cursoPadrao.setNome("Análise e Desenvolvimento de Sistemas");

        System.out.println("#### BEM-VINDO AO SISTEMA DE GESTÃO ACADÊMICA ####");

        while(opcao != 0) {
            System.out.println("\n--MENU PRINCIPAL--");
            System.out.println("1 - Cadastrar Novo Aluno");
            System.out.println("2 - Cadastrar Novo Professor");
            System.out.println("3 - Listar Alunos Cadastrados");
            System.out.println("4 - Listar Professores Cadastrados");
            System.out.println("0 - Sair do Sistema");
            System.out.println("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.println("\n --Cadastro de Novo Aluno --");
                    System.out.println("Nome: ");
                    String nomeAluno = scanner.nextLine();

                    System.out.println("Email: ");
                    String emailAluno = scanner.nextLine();

                    System.out.println("Matrícula: (somente números)");
                    String matricula = scanner.nextLine();

                    service.cadastrarAluno(nomeAluno, emailAluno, matricula, cursoPadrao);
                    break;

                case 2:
                    System.out.println("\n-- Cadastrar Novo Professor --");

                    System.out.println("Nome: ");
                    String nomeProfessor = scanner.nextLine();

                    System.out.println("Email: ");
                    String emailProfessor = scanner.nextLine();

                    System.out.println("Departamento: ");
                    String departamentoProfessor = scanner.nextLine();

                    System.out.println("Salário: ");
                    double salario = scanner.nextDouble();
                    scanner.nextLine();

                    service.cadastrarProfessor(nomeProfessor, emailProfessor, departamentoProfessor, salario);
                    break;

                case 3:
                    System.out.println("\n-- Lista de Alunos --");
                    if (service.getAlunos().isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        for (Aluno aluno : service.getAlunos()) {
                            System.out.println(aluno);
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n-- Lista de Professores --");
                    if (service.getProfessores().isEmpty()) {
                        System.out.println("Nenhum professor cadastrado.");
                    } else {
                        for (Professor professor : service.getProfessores()) {
                            System.out.println(professor);
                        }
                    }
            }
        }
    }
}
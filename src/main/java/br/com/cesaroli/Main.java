package br.com.cesaroli;

import br.com.cesaroli.model.Aluno;
import br.com.cesaroli.model.Curso;
import br.com.cesaroli.model.Disciplina;
import br.com.cesaroli.model.Professor;
import br.com.cesaroli.service.AcademiaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        AcademiaService service = new AcademiaService();

        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        Curso cursoPadrao = new Curso();
        cursoPadrao.setNome("Análise e Desenvolvimento de Sistemas");

        Disciplina programacaoNivelUm = new Disciplina();
        programacaoNivelUm.setNome("Progamação I");

        Disciplina bancoDeDados = new Disciplina();
        bancoDeDados.setNome("Banco de Dados");

        service.getDisciplinas().add(programacaoNivelUm);
        service.getDisciplinas().add(bancoDeDados);


        System.out.println("#### BEM-VINDO AO SISTEMA DE GESTÃO ACADÊMICA ####");

        while(opcao != 0) {
            System.out.println("\n--MENU PRINCIPAL--");
            System.out.println("1 - Cadastrar Novo Aluno");
            System.out.println("2 - Cadastrar Novo Professor");
            System.out.println("3 - Listar Alunos Cadastrados");
            System.out.println("4 - Listar Professores Cadastrados");
            System.out.println("5 - Matricular Aluno em Disciplina");
            System.out.println("6 - Lançar Nota de Aluno");
            System.out.println("7 - Boletin do Aluno");
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
                    break;
                case 5:
                    System.out.println("\n-- Matrícula de Aluno em Disciplina --");

                    if (service.getAlunos().isEmpty()) {
                        System.out.println("ERRO: É preciso cadastrar pelo menos um aluno primeiro. Obrigado!");
                        break;
                    }
                    if (service.getDisciplinas().isEmpty()) {
                        System.out.println("ERRO: Nenhuma disciplina disponível para matrícula.");
                        break;
                    }

                    try {
                        System.out.println("Selecione o aluno (pelo número): ");
                        for (int i = 0; i < service.getAlunos().size(); i++) {
                            System.out.println((i + 1) + " - " + service.getAlunos().get(i).getNome());
                        }
                        System.out.println("Aluno escolhido: ");
                        int indiceAluno = scanner.nextInt() - 1;

                        System.out.println("\nSelecione a disciplina (pelo número): ");
                        for (int i = 0; i < service.getDisciplinas().size(); i++) {
                            System.out.println((i + 1) + " - " + service.getDisciplinas().get(i).getNome());
                        }
                        System.out.println("Disciplina escolhida: ");
                        int indiceDisciplina = scanner.nextInt() - 1;
                        scanner.nextLine();

                        Aluno alunoEscolhido = service.getAlunos().get(indiceAluno);
                        Disciplina disciplinaEscolhida = service.getDisciplinas().get(indiceDisciplina);

                        service.matricularAlunoEmDisciplina(alunoEscolhido, disciplinaEscolhida);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("ERRO: Opção inválida: Você digitou um número que não está na lista.");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("ERRO: Entrada inválida. Por favor, digite apenas números");
                        scanner.nextLine();
                    }
                    break;
                case 6:
                    System.out.println("-- Lançamento de Notas --");
                    if (service.getAlunos().isEmpty()) {
                    System.out.println("ERRO: Nenhum aluno cadastrado para lançar notas.");
                        break;
                    }
                    try {
                        System.out.println("\nSelecione o aluno para lançar a nota: ");
                        for (int i = 0; i < service.getAlunos().size(); i++) {
                            System.out.println((i + 1) + " - " + service.getAlunos().get(i).getNome());
                        }
                        System.out.println("Aluno escolhido: ");
                        int indiceAluno = scanner.nextInt() - 1;
                        Aluno alunoEscolhido = service.getAlunos().get(indiceAluno);

                        if (alunoEscolhido.getNotas().isEmpty()) {
                            System.out.println("ERRO: O aluno " + alunoEscolhido.getNome() + " não está matriculado em nehuma disciplina.");
                            break;
                        }

                        System.out.println("\nSelecione a disciplina para lançar a nota: ");

                        List<Disciplina> disciplinasDoAluno = new ArrayList<>(alunoEscolhido.getNotas().keySet());
                        for (int i = 0; i < disciplinasDoAluno.size(); i++) {
                            System.out.println((i + 1) + " - " + disciplinasDoAluno.get(i).getNome());
                        }
                        System.out.println("Disciplina escolhida: ");
                        int indiceDisciplina = scanner.nextInt() - 1;
                        Disciplina disciplinaEscolhida = disciplinasDoAluno.get(indiceDisciplina);

                        System.out.println("\nDigite a nota ( 0 a 10): ");
                        double nota = scanner.nextDouble();
                        scanner.nextLine();

                        service.lancarNota(alunoEscolhido, disciplinaEscolhida, nota);

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("ERRO: Opçao inválida. Você digitou um número que não está na lista");
                        scanner.nextLine();
                    } catch (Exception e) {
                        System.out.println("ERRO: Entrada inválida.");
                        scanner.nextLine();
                    }
                    break;
                case 7:
                    System.out.println("\n-- Consulta de Boletim do Aluno --");
                    if (service.getAlunos().isEmpty()) {
                        System.out.println("ERRO: Nenhum aluno cadastrado.");
                        break;
                    }

                    try {
                        System.out.println("\nSelecione o aluno para consultar o boletim (pelo número): ");
                        for (int i = 0; i < service.getAlunos().size(); i++) {
                            System.out.println((i + 1) + " - " + service.getAlunos().get(i).getNome());
                            }
                        System.out.println("Aluno escolhido: ");
                        int indiceAluno = Integer.parseInt(scanner.nextLine()) - 1;
                        Aluno alunoEscolhido = service.getAlunos().get(indiceAluno);

                        System.out.println("\n-- BOLETIM DO ALUNO: " + alunoEscolhido.getNome() + " --");

                        if (alunoEscolhido.getNotas().isEmpty()) {
                            System.out.println("ERRO: O aluno " + alunoEscolhido.getNome() + " não está matriculado em nenhuma disciplina.");
                            break;
                        }

                        for (Map.Entry<Disciplina, List<Double>> entry : alunoEscolhido.getNotas().entrySet()) {
                            Disciplina disciplina = entry.getKey();
                            List<Double> notas = entry.getValue();

                            double media = service.calcularMediaDoAlunoNaDisciplina(alunoEscolhido, disciplina);

                            System.out.println("\nDisciplina: " + disciplina.getNome());
                            System.out.println("Notas: " + (notas.isEmpty() ? "Nenhuma nota lançada" : notas.toString()));
                            System.out.printf("Média: %.2f\n", media);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("ERRO: Opção inválida. Você digitou um número que não está na lista");
                    } catch (NumberFormatException e) {
                        System.out.println("ERRO: Entrada inválida. Por favor, digite apenas números");
                    }
                    break;
            }

        }
    }
}


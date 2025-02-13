import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.math.RoundingMode;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"),
                new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente")
        ));

        System.out.println("Lista de Funcionários:\n");

        funcionarios.forEach(System.out::println);

        System.out.println("\nFim da Lista de Funcionários.");
        System.out.println("----------------------------------------------------------------------------------------------\n");

        funcionarios.removeIf(f -> f.getNome().equals("João"));
        System.out.println("Funcionário João foi Removido com sucesso!\n");
        System.out.println("Lista de Funcionários Atualizada:\n");
        funcionarios.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------\n");

        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.10"))));
        System.out.println("Os funcionários receberam 10% de aumento de salário com sucesso!\n");
        System.out.println("Os novos salários dos funcionários são:\n");
        funcionarios.forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------------------\n");

        System.out.println("Lista de Funcionários por função:");
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("\nFunção: " + funcao);
            lista.forEach(System.out::println);
        });

        System.out.println("\nFim da Lista de Funcionários por função.");
        System.out.println("----------------------------------------------------------------------------------------------\n");

        System.out.println("Lista de Funcionários que fazem aniversário nos meses 10 e 12:\n");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        System.out.println("\nFim da Lista de Funcionários que fazem aniversário nos meses 10 e 12.");
        System.out.println("----------------------------------------------------------------------------------------------\n");

        Funcionario maisVelho2 = Collections.max(funcionarios, Comparator.comparing(f -> Period.between(f.getDataNascimento(), LocalDate.now()).getYears()));
        int idadeMaisVelho = Period.between(maisVelho2.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("Funcionário com a maior idade: " + maisVelho2.getNome() + ", Idade: " + idadeMaisVelho);

        System.out.println("----------------------------------------------------------------------------------------------\n");

        System.out.println("Lista de Funcionários por ordem alfabética:\n");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        System.out.println("\nFim da Lista de Funcionários por ordem alfabética.");
        System.out.println("----------------------------------------------------------------------------------------------\n");

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários dos funcionários: " + totalSalarios);
        System.out.println("----------------------------------------------------------------------------------------------\n");

        System.out.println("Lista de quantos salários mínimos ganha cada funcionário:\n");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_EVEN);
            System.out.println(f.getNome() + " ganha " + qtdSalariosMinimos + " salários mínimos.");
        });
        System.out.println("\nFim da Lista de quantos salários mínimos ganha cada funcionário.");
        System.out.println("----------------------------------------------------------------------------------------------\n");
    }
}
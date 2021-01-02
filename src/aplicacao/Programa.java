package aplicacao;

import entities.Empregado;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);//Separador de decimais no padrão americano
        Scanner sc = new Scanner(System.in);

        List<Empregado> list = new ArrayList<>();

        System.out.print("Quantos funcionários serão registrados ?: ");
        int N = sc.nextInt();

        for (int i=0; i<N; i++) {
            System.out.println();
            System.out.print("Funcionário #" + (i + 1) + ":") ;
            System.out.print("Id: ");
            Integer id = sc.nextInt();
            //Verificando Id para cadastro
            while (temId(list, id)) {
                System.out.println("Esse id ja está em uso tente novamente!");
                id = sc.nextInt();
            }
            System.out.print("Nome: ");
            sc.nextLine(); //Limpando o Buffer
            String nome = sc.nextLine();
            System.out.print("Salário: ");
            Double salario = sc.nextDouble();

            Empregado empregado = new Empregado(id,nome,salario);

            list.add(empregado);
        }

        //Parte 2 Atualizando salário do empregado
        System.out.println();
        System.out.println("Informe o ID do funcionário que terá aumento de salário: ");
        int idSalario = sc.nextInt();


       // Empregado empregado = list.stream().filter(x -> x.getId() == idSalario).findFirst().orElse(null);

        Integer pos = posicao(list, idSalario);
        if(pos == null) {
        System.out.println("Esse Id não existe");
        } else {
            System.out.println("Informe a porcentagem de aumento");
            double percent = sc.nextDouble();
            list.get(pos).aumentoSalario(percent);
        }

        System.out.println();
        System.out.println("Lista de funcionários: ");
        for(Empregado empregado : list) {
            System.out.println(empregado);
        }

        sc.close();
    }
    //Verificando se ja tem um Id existente e retornando a posição caso exista um Id
    public static Integer posicao(List<Empregado> list, int id) {
        for (int i= 0; i < list.size(); i++) {
            if(list.get(i).getId() == id) {
                return i;
            }
        }
        return null;
    }

    public static boolean temId(List<Empregado> list, int id) {
        Empregado emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
        return emp != null;
    }
}

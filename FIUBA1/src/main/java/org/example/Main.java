package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        usuario nicor = new usuario("nicor", 110830);
        carrera informatica = new carrera(10, "Ingenieria en Informatica");

        materia intro = new materia("Introduccion al desarollo de software","70.34",4);
        materia fisica = new materia("Fisica","71.41",4);
        materia tda = new materia("TDA","TB024",2);
        informatica.agregar_materia( fisica ,false );
        informatica.agregar_materia( intro, true );
        informatica.agregar_materia( tda ,true );
        informatica.agregar_materia( new materia("Algebra","71.41",2),false );

        sistema fiuba = new sistema();

        fiuba.materia_aprobada(nicor,intro);
        fiuba.materia_aprobada(nicor,fisica);

        System.out.printf("Hola, soy %s y estoy estudiando %s. Me faltan %d creditos para terminar\n", nicor.getNombre(),informatica.getNombre(),fiuba.consultar_carrera(nicor,informatica));

        fiuba.materia_aprobada(nicor,tda);
        System.out.printf("Ahora me faltan %d creditos para terminar\n", fiuba.consultar_carrera(nicor,informatica));

    }
}
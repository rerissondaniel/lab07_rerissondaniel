package main;

import main.controller.LojaControllerImpl;
import main.service.FormatadoraCentralP2Cg;
import util.io.Console;
import util.io.Teclado;
import main.facade.LojaFacade;

import java.util.HashMap;

/**
 * Classe que inicia o sistema.
 * Created by rerissondcsm on 15/02/17.
 */
public class Main {

    public static void main(String[] args) {
        iniciaLoja();
    }

    /**
     * Injeta as depêndencias de {@link LojaFacade} e inicia o sistema.
     */
    private static void iniciaLoja() {
        LojaFacade loja = new LojaFacade(new Teclado(), new Console(),
                new LojaControllerImpl(new HashMap<>(), new FormatadoraCentralP2Cg()));

        loja.iniciaSistema();
    }

}

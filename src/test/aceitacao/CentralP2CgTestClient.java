package test.aceitacao;

import easyaccept.EasyAccept;

/**
 * Created by rerissondcsm on 28/02/17.
 */
public class CentralP2CgTestClient {
    public static void main(String[] args) {
        args = new String[]{"main.facade.LojaFacade",
                "resources/test/acceptance_tests/us1.txt",
                "resources/test/acceptance_tests/us2.txt",
                "resources/test/acceptance_tests/us3.txt"};
        EasyAccept.main(args);
    }
}

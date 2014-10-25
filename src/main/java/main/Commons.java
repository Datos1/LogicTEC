package main;

import data.List;

/**
 * Created by pablo on 15/10/14.
 */
public interface Commons {
    public static final String VENTANA_NOMBRE = "Compuertas Lógicas";
    public static final String DELETE = "borrar";
    public static final String ERROR_INT = "Inserte un Integer mayor a 0 y menor a 10:";
    public static final String MENU_NEW = "Nuevo";
    public static final String SAVE = "Guardar";
    public static final String OPEN = "Abrir";
    public static final String CHECK = "Chequear circuito";
    public static final String ABOUT = "Acerca";
    public static final String MENU_FILE = "Archivo";
    public static final String AND = "Compuerta AND";
    public static final String NAND = "Compuerta NAND";
    public static final String OR = "Compuerta OR";
    public static final String NOR = "Compuerta NOR";
    public static final String NOT = "Compuerta NOT";
    public static final String XOR = "Compuerta XOR";
    public static final String XNOR = "Compuerta XNOR";
    public static final String CUSTOM = "Componente Externo...";
    public static final List<String> COMPONENTS = new List<String>(AND, NAND, OR, NOR, NOT, XOR, XNOR, CUSTOM);
    public static final String FREE_SPACE = "Por favor libere el area para nuevos componentes";
    public static final String AND_PATH = "src/res/image-01.png";
    public static final String NAND_PATH = "src/res/image-06.png";
    public static final String OR_PATH = "src/res/image-03.png";
    public static final String NOR_PATH = "src/res/image-07.png";
    public static final String NOT_PATH = "src/res/image-02.png";
    public static final String XOR_PATH = "src/res/image-04.png";
    public static final String XNOR_PATH = "src/res/image-05.png";
    public static final String CUSTOM_PATH = "src/res/image-08.png";
    public static final List<String> COMPONENTS_PATH = new List<String>(AND_PATH, NAND_PATH, OR_PATH, NOR_PATH,
            NOT_PATH, XOR_PATH, XNOR_PATH, CUSTOM_PATH);

    public static final int IMAGE_SIZE = 100;
    public static final int BORDER = 40;
    public static final int NODO_SIZE = 10;
    public static final int MAX_ENTRADAS = 40;
}



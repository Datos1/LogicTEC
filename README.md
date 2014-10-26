LogicTEC
========

Datos 1 / Proyecto 2 Compuertas logicas
Action Events:
    GUI:
        Nueva conexión:
        new ActionEvent(GUI, id = identificador de componente entrada,
                    "set" + "#" + numero de entrada + "#" + "salida" + "#"
                            + id de componente salida + "#" + numero de salida)
        Nuevo componente Json:
        new ActionEvent(GUI, id = identificador de componente,
                        CUSTOM + "#" + Ruta del archivo)
        Nuevo Componente :
        new ActionEvent(GUI, id = identificador de componente,
                        NOMBRE COMPONENTE(como esta en Commons) + "#" + numero entradas
                        + "#" + numero de salidas)

    Logica:

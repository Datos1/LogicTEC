LogicTEC
========

Datos 1 / Proyecto 2 Compuertas logicas
Action Events:
    GUI:
        Nueva conexión:
        new ActionEvent(GUI, SET,
                    id entrada+ "#" + numero de entrada  + "#"
                            + id de componente salida + "#" + numero de salida)
        Nuevo componente Json:
        new ActionEvent(GUI, ADD,
                        #id+"#"+CUSTOM + "#" + Ruta del archivo)
        Nuevo Componente :
        new ActionEvent(GUI, ADD,
                        #id
                        +"#"+ NOMBRE COMPONENTE(como esta en Commons) + "#" + numero entradas
                        + "#" + numero de salidas)
    Logica:
        Set Salida:
        listener.actionPerformed(new ActionEvent(this, SET, salida.getReference()+"#"+valor));
                
                

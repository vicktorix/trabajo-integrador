package com.argentina.programa;

import com.argentina.programa.proceso.ProcesoAplicacion;
public class MainApp {
    public static void main(String[] args) {

        ProcesoAplicacion proc = new ProcesoAplicacion();

      try {
        proc.iniciarAplicacion();
      } catch (Exception e) {
          System.err.println("No se pudo iniciar la aplicacion");
          System.err.println(e.getMessage());
      }

    }
}

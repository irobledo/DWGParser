package fi.upm.es.dwg.parser;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.Level;

import fi.upm.es.dwg.tools.version1.*;

/**
 * Clase principal del parser DWG. Contiene el método main que lanza toda la ejecución 
 * del proceso. Recibe dos parámetros, el primero debe ser el nombre del fichero
 * DWG a procesar y el segundo es el nombre del fichero de intercambio con el módulo
 * de visualización.
 * 
 * @author	Ignacio Robledo
 * @version	0.1
 */

public class ParserDWG {

	/**
	 * Método principal de la clase. Desencadena todo el proceso de interpretación y extracción
	 * de elementos del fichero DWG.
	 * 
	 * @param args 	Argumentos proporcionados por línea de comandos. Debe recibir siempre dos parámetros.
	 * 				El primero es el nombre del fichero DWH a procesar (ruta absoluta, ruta relativa).
	 * 				El segundo es el nombre del fichero de intercambio donde se volcará toda la información
	 * 				para que el módulo de visualización pueda trabajar con la información del fichero DWG.  
	 */
	
	public static void main(String[] args) {

		// Comprobamos si se reciben los dos argumentos requeridos.
		if (args.length != 2) {
			
			System.out.println("[PARSERDWG] Número de parámetros incorrectos.");
			System.out.println("[PARSERDWG] Uso: ParserDWG fichero1_dwg_origen fichero2_intercambio");
			System.exit(0);
			
		}
		
		Properties prop = new Properties();
		
		// Leemos el fichero de propiedades de configuración.
		try { 
		
			FileInputStream in = new FileInputStream("config/config.properties");
			prop.load(in);
			
		} catch (FileNotFoundException e1) {
			
			System.out.println("[PARSERDWG] Fichero de configuración no encontrado");
			System.exit(-1);			
			
		} catch (IOException e2) {
		
			System.out.println("[PARSERDWG] Fichero de configuración no encontrado");
			System.exit(-2);
		
		}
		
		// Configuramos el sistema de logging.
		try {
			
			LogManager logm = LogManager.getLogManager();
			logm.readConfiguration(new FileInputStream(prop.getProperty("loggerConfiguration")));
			Logger log = Logger.getLogger("fi.upm.es.dwg.parser.main");
			log.log(Level.CONFIG,"Configuramos el log principal de la aplicacion.");
			
		} catch (FileNotFoundException e3) {
			
			System.out.println("[PARSERDWG] Fichero de configuración de logging no encontrado");
			System.exit(-3);
				
		} catch (IOException e4) {
		
			System.out.println("[PARSERDWG] Fichero de configuración de logging no encontrado");
			System.exit(-4);
		}
	
		FileInputStream input_mapa = null;
		DWGStructureParser dwgparser = new DWGStructureParser();
		
		// Leemos el fichero con el mapa.
		try 
		{ 
			input_mapa = new FileInputStream(args[0]);
		
		}
		catch (FileNotFoundException e5) {
			System.out.println("[PARSERDWG] Fichero con el plano no encontrado.");
			System.exit(-5);			
		} 
		
		try
		{
			dwgparser.ParsearHeader(input_mapa);
			System.out.println("[PARSERDWG] Fichero procesado correctamente.");
			System.exit(0);
		}
		catch (Exception e)
		{
			System.out.println("[PARSERDWG] Error parseando el fichero.");
			System.exit(-7);
		}
	}
}

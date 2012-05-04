package fi.upm.es.dwg.tools.interfaces;

import java.io.FileInputStream;

public interface DWGStructureParserInterface {

	/* Vamos a parsear un fichero DWG 2010
	 * Internal version: AC1024
	 */
	
	/* 
	 	The general arrangement of data in an R13/R14/R15 file is as follows:
	 	HEADER
			FILE HEADER
			DWG HEADER VARIABLES
			CRC
		
		CLASS DEFINITIONS
		TEMPLATE (R13 only, optional)
		PADDING (R13C3 AND LATER, 200 bytes, minutes the template section above if present)
		IMAGE DATA (PRE-R13C3)
		OBJECT DATA
			All entities, table entries, dictionary entries, etc. go in this
			section.
		OBJECT MAP
		OBJECT FREE SPACE (optional)
		TEMPLATE (R14-R15, optional)
		SECOND HEADER
		IMAGE DATA (R13C3 AND LATER)
	 */
	
	public void ParsearHeader(FileInputStream in);
}

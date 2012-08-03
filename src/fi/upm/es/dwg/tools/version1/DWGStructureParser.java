package fi.upm.es.dwg.tools.version1;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import fi.upm.es.dwg.tools.interfaces.DWGStructureParserInterface;
import fi.upm.es.dwg.elements.DWGHeader;

public class DWGStructureParser implements DWGStructureParserInterface {
	
	public void ParsearHeader(FileInputStream in) {
		
		/*
		 * 0x00 6 bytes Version String
		 * 0x06 5 bytes 5 0x00
		 * 0x0B 1 byte Maintenance Release Version
		 * 0x0C 1 byte 0x00, 0x01 or 0x03
		 * 0x0D 4 bytes Preview address
		 * 0x11 1 byte Application DWG version
		 * 0x12 1 byte Application Maintentance Release
		 * 0x13 2 byte Codepage
		 * 0x15 3 byte 0x000000
		 * 0x18 4 byte Security flags
		 * 0x1C 4 byte
		 * 0x20 4 byte
		 * 0x24 4 byte
		 * 0x28 4 byte 0x00000080
		 * 0x2C 0x54 bytes 0x00 bytes
		 * 0x80 0x6C bytes Encrypted Data
		 */
		
		Logger log = Logger.getLogger("fi.upm.es.dwg.tools.dwgstructureparser");
		log.log(Level.INFO,"Comienza el analisis del header del fichero");
		DWGBytesReader dwgbytes = new DWGBytesReader();
		DWGManageOutput dwgout = new DWGManageOutput();
		DWGHeader dwgh = new DWGHeader();

		try
		{
			// Version String
			
			byte [] version_string_1 = new byte[4];
			byte [] version_string_2 = new byte[2];
			version_string_1 = dwgbytes.leer4BytesLittleEndian(in);
			version_string_2 = dwgbytes.leer2BytesLittleEndian(in);
			
			dwgh.setVersionString(StringReturn.S(version_string_1) + StringReturn.S(version_string_2));
			
			log.log(Level.INFO,"Version string:" + StringReturn.hexS(version_string_1) + StringReturn.hexS(version_string_2));
			log.log(Level.INFO,"Version string:" + StringReturn.S(version_string_1) + StringReturn.S(version_string_2));
			
			//System.out.println(dwgh.toString());
			
			// 5 bytes 00
			byte [] ceros_1 = new byte[4];
			byte [] ceros_2 = new byte[1];
			
			ceros_1 = dwgbytes.leer4BytesLittleEndian(in);
			ceros_2 = dwgbytes.leerByte(in);
			log.log(Level.INFO,"5 bytes:" + dwgout.bytesToHexString(ceros_1) + dwgout.bytesToHexString(ceros_2));
			
			// Release version
			byte [] release_version = new byte[1];
			release_version = dwgbytes.leerByte(in);
			log.log(Level.INFO,"Release version:" + dwgout.bytesToHexString(release_version));
			
			// 0x0C
			byte [] h0x0C = new byte[1];
			h0x0C = dwgbytes.leerByte(in);
			log.log(Level.INFO,"0x0C:" + dwgout.bytesToHexString(h0x0C));
			
			// Preview address
			byte [] preview_address = new byte[4];
			preview_address = dwgbytes.leer4BytesLittleEndian(in);
			log.log(Level.INFO,"Preview address:" + dwgout.bytesToHexString(preview_address));

			// Application DWG version
			byte [] application_dwg_version = new byte[1];
			application_dwg_version = dwgbytes.leerByte(in);
			log.log(Level.INFO,"Application DWG version:" + dwgout.bytesToHexString(application_dwg_version));

			// Application Maintenance Release
			byte [] application_maintenance_release = new byte[1];
			application_maintenance_release = dwgbytes.leerByte(in);
			log.log(Level.INFO,"Application Maintenance Release:" + dwgout.bytesToHexString(application_maintenance_release));

			// Code Page
			byte [] codepage = new byte[2];
			codepage = dwgbytes.leer2BytesLittleEndian(in);
			log.log(Level.INFO,"CodePage:" + dwgout.bytesToHexString(codepage));

		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"Error parseando header del fichero");
			log.log(Level.SEVERE, e.toString());
			//throw(e);
		}
	}
	
}

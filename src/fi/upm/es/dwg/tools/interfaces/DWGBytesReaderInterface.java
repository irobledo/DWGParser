package fi.upm.es.dwg.tools.interfaces;

import java.io.FileInputStream;

public interface DWGBytesReaderInterface {
	
	/*
	 * boolean - 1 bit
	 * byte
	 * short - 2 bytes
	 * int - 4 bytes
	 * long - 8 bytes
	 * float - 4 bytes
	 * double - 8 bytes
	 * char - 2 bytes
	 */

	public byte[] leerByte (FileInputStream is) throws Exception;
	
	public byte[] leer2BytesBigEndian (FileInputStream is) throws Exception;
	public byte[] leer2BytesLittleEndian (FileInputStream is) throws Exception;

	public byte[] leer4BytesBigEndian (FileInputStream is) throws Exception;
	public byte[] leer4BytesLittleEndian (FileInputStream is) throws Exception;

	public byte[] leer8BytesBigEndian (FileInputStream is) throws Exception;
	public byte[] leer8BytesLittleEndian (FileInputStream is) throws Exception;
	
}

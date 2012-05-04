package fi.upm.es.dwg.tools.version1;

import java.io.FileInputStream;
import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.apache.commons.io.*;

import fi.upm.es.dwg.tools.interfaces.DWGBytesReaderInterface;


public class DWGBytesReader implements DWGBytesReaderInterface {

	/* Tipos a reconocer:
	 * boolean - 1 bit
	 * byte
	 * short - 2 bytes
	 * int - 4 bytes
	 * long - 8 bytes
	 * float - 4 bytes
	 * double - 8 bytes
	 * char - 2 bytes
	 */
	
	private byte[] leerBytesBigEndian (FileInputStream is, int numero) throws Exception
	{
		Logger log = Logger.getLogger("fi.upm.es.dwg.tools.dwgbytesreader");
		log.log(Level.FINE,"Arquitectura del microprocesador " + ByteOrder.nativeOrder().toString());
		int bytes_leidos = 0;
		
		double ret_source_double; //8 bytes
		float ret_source_float; // 4 bytes
		short ret_source_short; //2 bytes
		
		byte [] ret = new byte[numero];
		
		try
		{
			if ((numero == 1) || (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN))
			{
				bytes_leidos = is.read(ret);
				if (bytes_leidos != numero)
				{
					throw new Exception();
				}
			}
			else // Sistema LittleEndian 
			{
				if (numero == 2)
				{
					ret_source_short = EndianUtils.readSwappedShort(is);
					ByteBuffer.wrap(ret).putShort(ret_source_short);
				}
				else if (numero == 4)
				{
					ret_source_float = EndianUtils.readSwappedFloat(is);
					ByteBuffer.wrap(ret).putFloat(ret_source_float);
				}
				else if (numero == 8)
				{
					ret_source_double = EndianUtils.readSwappedDouble(is);
					ByteBuffer.wrap(ret).putDouble(ret_source_double);
				}
				else 
				{
					throw new Exception();
				}
			}			
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"Error leyendo 8 bytes del stream de entrada");
			log.log(Level.SEVERE, e.toString());
			throw(e);
		}
		return ret;
	}
	
	private byte[] leerBytesLittleEndian (FileInputStream is, int numero) throws Exception
	{
		Logger log = Logger.getLogger("fi.upm.es.dwg.tools.dwgbytesreader");
		log.log(Level.FINE,"Arquitectura del microprocesador " + ByteOrder.nativeOrder().toString());
		int bytes_leidos = 0;
		
		double ret_source_double; //8 bytes
		float ret_source_float; // 4 bytes
		short ret_source_short; //2 bytes
		
		byte [] ret = new byte[numero];
		
		try
		{
			if ((numero == 1) || (ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN))
			{
				bytes_leidos = is.read(ret);
				if (bytes_leidos != numero)
				{
					throw new Exception();
				}
			}
			else // Sistema BigEndian
			{
				if (numero == 2)
				{
					ret_source_short = EndianUtils.readSwappedShort(is);
					ByteBuffer.wrap(ret).putShort(ret_source_short);
				}
				else if (numero == 4)
				{
					ret_source_float = EndianUtils.readSwappedFloat(is);
					ByteBuffer.wrap(ret).putFloat(ret_source_float);
				}
				else if (numero == 8)
				{
					ret_source_double = EndianUtils.readSwappedDouble(is);
					ByteBuffer.wrap(ret).putDouble(ret_source_double);
				}
				else 
				{
					throw new Exception();
				}
			}			
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE,"Error leyendo 8 bytes del stream de entrada");
			log.log(Level.SEVERE, e.toString());
			throw(e);
		}
		return ret;
	}

	public byte[] leerByte (FileInputStream is) throws Exception
	{
		return this.leerBytesBigEndian(is, 1);
	}
	
	public byte[] leer2BytesBigEndian (FileInputStream is) throws Exception
	{
		return this.leerBytesBigEndian(is, 2);
	}
	
	public byte[] leer2BytesLittleEndian (FileInputStream is) throws Exception
	{
		return this.leerBytesLittleEndian(is, 2);
	}

	public byte[] leer4BytesBigEndian (FileInputStream is) throws Exception
	{
		return this.leerBytesBigEndian(is, 4);
	}
	
	public byte[] leer4BytesLittleEndian (FileInputStream is) throws Exception
	{
		return this.leerBytesLittleEndian(is, 4);
	}

	public byte[] leer8BytesBigEndian (FileInputStream is) throws Exception
	{
		return this.leerBytesBigEndian(is, 8);
	}
	public byte[] leer8BytesLittleEndian (FileInputStream is) throws Exception
	{
		return this.leerBytesLittleEndian(is, 8);
	}
}

/* 
 * Ejemplo de codigo que debo reutilizar 
 * 
FileInputStream fs = new FileInputStream("myfile.bin");
FileChannel fc = fs.getChannel();
ByteBuffer buf = ByteBuffer.allocate(0x10000);
buf.order(ByteOrder.LITTLE_ENDIAN);
fc.read(buf);
buf.flip();
// here you take data from the buffer by either of getShort(), getInt(), getLong(), getDouble(), or get(byte[], offset, len)
buf.compact();
*/

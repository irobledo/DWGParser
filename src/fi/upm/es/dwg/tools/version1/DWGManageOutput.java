package fi.upm.es.dwg.tools.version1;

import java.lang.StringBuilder;
import java.util.Formatter;

public class DWGManageOutput {
	
	public static String bytesToHexString(byte[] bytes)
	{  
		StringBuilder sb = new StringBuilder(bytes.length * 2);  
		Formatter formatter = new Formatter(sb);  
		for (byte b : bytes)
		{  
			formatter.format("%02x",b);  
		}  
		
		return sb.toString();  
	}
}

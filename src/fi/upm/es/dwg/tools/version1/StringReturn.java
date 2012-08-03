package fi.upm.es.dwg.tools.version1;

// import fi.upm.es.dwg.tools.interfaces.DWGManageOutputInterface;

public class StringReturn {
	
	public static String hexS (byte[] s) {
		return DWGManageOutput.bytesToHexString(s);
	}
	
	public static String S (byte[] s) throws Exception {
		return new String(s,"UTF8");
	}
	
}

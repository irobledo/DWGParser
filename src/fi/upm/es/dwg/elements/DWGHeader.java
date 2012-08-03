package fi.upm.es.dwg.elements;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* @ToString(exclude={"versionString","maintenanceReleaseVersion"}) */
@ToString

public class DWGHeader {

	@Getter @Setter	private String versionString;	
	@Getter @Setter	private String maintenanceReleaseVersion ;
	@Getter @Setter	long previewAddress;	
	@Getter @Setter	private String writeAppDwg;
	@Getter @Setter	private String writeMaintenanceReleaseVersion;
	@Getter @Setter	private String codePage;	
	@Getter @Setter	private String securityFlags;	
	@Getter @Setter	long summaryAddress;
	@Getter @Setter	long vbaAddress;
	
}

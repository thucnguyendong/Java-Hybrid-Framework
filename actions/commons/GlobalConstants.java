package commons;

import java.io.File;

public class GlobalConstants {
	public static final int SHORT_TIMEOUT=5;
	public static final int LONG_TIMEOUT=30;
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FOLDER_PATH = System.getProperty("user.dir")+ File.separator+"uploadFile";
	public static final String DOWNLOAD_FOLDER_PATH = System.getProperty("user.dir")+ File.separator+"downloadFile";
}

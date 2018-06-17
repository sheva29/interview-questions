/**
     * Given a directory with a list of folders create a method that looks for duplicates 
     * and doesn't erase content but creates a symlink of the duplicate file
     * @param dir -directory containing files
     * @return void
*/
	
public void checkFile(String dir) {

	File fdir = new File(dir);
	File[] files = fdir.listFiles();
	Map<String, File> filesRef =  new HashMap<>();   

	for(File f: files){
		String fileHash = hashBytes(f);

		if (files.get(fileString) == null) {
			File fToSave = new File(f.getAbsolutePath(), fileHash, f.length() );
			filesRef.put(fileString, fToSave);  

		} else {
			String srcPath = filesRef.get(fileHash).getPath();
			symlink(srcPath, f.getAbsolutePath());
		}    
	}    
}

// creates symlink
static void symlink(File src, File target) {
	Path srcPath = Paths.get(src.getAbsolutePath());
	Path targetPath = Paths.get(target.getAbsolutePath());
	
	try {
		Files.createSymbolicLink(srcPath, targetPath);
	} catch (IOException e) {
		e.printStackTrace();
	}
}

// returns hash of file
static String hashBytes(File file) {
	InputStream targetStream = new FileInputStream(file);
	// apache commons
	return DigestUtils.md5Hex(targetStream).toUpperCase();
}

/*
class helper to encapsulate file information
*/
@Data// lombok notation for getters and setters
public class File {

	String path;
	String hash;
	long size;

	public File(String path, String hash, long size) {
		this.path = path;
		this.hash = hash;
		this.size = size;
	}
}
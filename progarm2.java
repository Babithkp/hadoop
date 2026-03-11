
public class HDFSManager {

    public static void main(String[] args) throws IOException {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        FileSystem fs = FileSystem.get(conf);

        // 1. Create Directory
        Path dirPath = new Path("/mydata");
        if (!fs.exists(dirPath)) {
            fs.mkdirs(dirPath);
            System.out.println("Directory Created!");
        }

        // 2. Upload File (Add file)
        Path localFile = new Path("sample.txt");
        Path hdfsFile = new Path("/mydata/sample.txt");
        fs.copyFromLocalFile(localFile, hdfsFile);
        System.out.println("File Uploaded!");

        // 3. Read File
        FSDataInputStream inputStream = fs.open(hdfsFile);
        System.out.println("File Content:");
        int byteRead;
        while ((byteRead = inputStream.read()) != -1) {
            System.out.print((char) byteRead);
        }
        inputStream.close();

        // 4. Delete File
        fs.delete(hdfsFile, false);
        System.out.println("\nFile Deleted!");

        fs.close();
    }
}

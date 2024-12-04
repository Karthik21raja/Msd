package comma;

public class MainClass {
	public static void main(String[] args) {
        String csvFilePath = "D:\\Sample\\sample.csv"; // Path to your CSV file
        String query = "SELECT * FROM employee"; // Your query
        String outputFilePath = "D:\\Sample\\sample.txt"; // Output file path

        CSVToDatabase csvLoader = new CSVToDatabase();
        csvLoader.loadCSVToDatabase(csvFilePath);

        QueryToFile queryExecutor = new QueryToFile();
        queryExecutor.executeQueryAndWriteToFile(query, outputFilePath);
    }

}
//explain each class in detail line by line however u can
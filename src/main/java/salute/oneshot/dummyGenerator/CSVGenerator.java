package salute.oneshot.dummyGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVGenerator {


    public static void generate(String tableName,String columns, List<String> dataList) {

        try (FileWriter writer = new FileWriter(tableName+".csv")) {
            writer.append(columns).append("\n");
            for (String data : dataList) {
                writer.append(data).append("\n");
            }
            System.out.println("CSV 파일 생성 완료\n");
            System.out.println("DB 삽입 명령어 : \n");
            System.out.printf("docker cp %s.csv mysql-container:/var/lib/mysql-files/%s.csv\n",tableName,tableName);
            System.out.printf("docker exec -it mysql-container mysql -u root -p\n\n");
            System.out.printf("USE oneshot;\n");
            System.out.printf("TRUNCATE TABLE %s;\n\n",tableName);
            System.out.printf("""
                LOAD DATA INFILE '/var/lib/mysql-files/%s.csv'
                INTO TABLE %s
                FIELDS TERMINATED BY ','
                LINES TERMINATED BY '\\n'
                IGNORE 1 LINES
                (%s)\n
                """,tableName,tableName,columns);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

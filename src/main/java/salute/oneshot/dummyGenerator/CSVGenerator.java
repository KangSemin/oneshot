package salute.oneshot.dummyGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVGenerator {


    public static void generate(String csvFileName,String header, List<String> dataList) {

        try (FileWriter writer = new FileWriter(csvFileName)) {
            writer.append(header).append("\n");
            for (String data : dataList) {
                writer.append(data).append("\n");
            }
            System.out.println("CSV 파일 생성 완료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

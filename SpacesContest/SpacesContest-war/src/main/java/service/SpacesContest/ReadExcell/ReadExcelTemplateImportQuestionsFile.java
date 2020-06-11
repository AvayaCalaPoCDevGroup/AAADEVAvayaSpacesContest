package service.SpacesContest.ReadExcell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import service.SpacesContest.Beans.ImportQuiz;
import service.SpacesContest.Util.ConstantsImport;
import service.SpacesContest.Util.ReadExcelData;


/**
 *
 * @author umansilla
 */
public class ReadExcelTemplateImportQuestionsFile {

    public JSONObject read(String filePath) throws IOException, Exception {
        ReadExcelData readExcelData = new ReadExcelData(filePath);
        List<ImportQuiz> listQuestions = new ArrayList<>();
        for (int i = 0; i < ConstantsImport.QUESTIONS.length; i++) {
            String question = readExcelData.ReadCellData(ConstantsImport.QUESTIONS[i][0][0], ConstantsImport.QUESTIONS[i][0][1]);
            if (!question.isEmpty()) {
                String opcionA = readExcelData.ReadCellData(ConstantsImport.QUESTIONS[i][1][0], ConstantsImport.QUESTIONS[i][1][1]);
                String opcionB = readExcelData.ReadCellData(ConstantsImport.QUESTIONS[i][2][0], ConstantsImport.QUESTIONS[i][2][1]);
                String opcionC = readExcelData.ReadCellData(ConstantsImport.QUESTIONS[i][3][0], ConstantsImport.QUESTIONS[i][3][1]);
                String opcionD = readExcelData.ReadCellData(ConstantsImport.QUESTIONS[i][4][0], ConstantsImport.QUESTIONS[i][4][1]);
                if (!opcionA.isEmpty() && !opcionB.isEmpty() && !opcionC.isEmpty() && !opcionD.isEmpty()) {
                    String responseA = readExcelData.getCheckedBox(ConstantsImport.QUESTIONS[i][5][0], ConstantsImport.QUESTIONS[i][5][1]);
                    String responseB = readExcelData.getCheckedBox(ConstantsImport.QUESTIONS[i][6][0], ConstantsImport.QUESTIONS[i][6][1]);
                    String responseC = readExcelData.getCheckedBox(ConstantsImport.QUESTIONS[i][7][0], ConstantsImport.QUESTIONS[i][7][1]);
                    String responseD = readExcelData.getCheckedBox(ConstantsImport.QUESTIONS[i][8][0], ConstantsImport.QUESTIONS[i][8][1]);
                    String encoede;
                    try {
                        encoede = "data:image/png;base64,"+readExcelData.getB64Image(i + 1).trim();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.toString());
                        encoede = "Home/QuizCreate/assets/img/Transparent.png";
                    }

                    listQuestions
                            .add(new ImportQuiz(question,
                                    opcionA,
                                    opcionB,
                                    opcionC,
                                    opcionD,
                                    Boolean.parseBoolean(responseA),
                                    Boolean.parseBoolean(responseB),
                                    Boolean.parseBoolean(responseC),
                                    Boolean.parseBoolean(responseD),
                                    encoede));
                } else {
                    //System.out.println("OPCIONES EMPTY");
                    continue;
                }
            } else {
                //System.out.println("PREGUNTA EMPTY");
                continue;
            }
        }
        Gson gson = new Gson();
        return new JSONObject().put("quizPreguntas", new JSONArray(gson.toJson(listQuestions)));
    }
}

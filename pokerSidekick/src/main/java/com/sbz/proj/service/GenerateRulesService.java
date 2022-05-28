package com.sbz.proj.service;

import com.sbz.proj.model.ClassificationTemplateModel;
import com.sbz.proj.model.PokerHand;
import com.sbz.proj.model.Rank;
import com.sbz.proj.util.CSVReader;
import org.drools.template.ObjectDataCompiler;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateRulesService {

    public void saveRulesInFile(String drl, String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        writer.write(drl);
        writer.close();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        System.out.println(br.readLine());
    }

    public List<ClassificationTemplateModel> getDataFromCSV(String filePath) {
        List<ClassificationTemplateModel> data = new ArrayList<>();

        if (filePath.equals("")){
            data.add(new ClassificationTemplateModel(PokerHand.STRAIGHT_FLUSH, Rank.ACE, Rank.R2, Rank.R3, Rank.R4, Rank.R5, true));
            data.add(new ClassificationTemplateModel(PokerHand.STRAIGHT_FLUSH, Rank.R2, Rank.R3, Rank.R4, Rank.R5, Rank.R6, true));
        } else {
            data = CSVReader.readTemplateData(filePath);
        }
        return data;
    }

    public String insertDataIntoTemplate(List<ClassificationTemplateModel> data, InputStream template) {
        ObjectDataCompiler converter = new ObjectDataCompiler();
        return converter.compile(data, template);
    }

    public String generateRules(String templateFilePath, String dataFilePath) throws FileNotFoundException {
        InputStream template = new FileInputStream(templateFilePath);

        List<ClassificationTemplateModel> data = getDataFromCSV(dataFilePath);

        String drl = insertDataIntoTemplate(data, template);

        System.out.println(drl);

        return drl;
    }

    public void generateRulesAndSaveThem(String templateFilePath, String dataFilePath, String resultFilePath) throws IOException {
        String drl = generateRules(templateFilePath, dataFilePath);
        saveRulesInFile(drl, resultFilePath);
    }

    private KieSession createKieSessionFromDRL(String drl){
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(drl, ResourceType.DRL);

        Results results = kieHelper.verify();

        if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR)){
            List<Message> messages = results.getMessages(Message.Level.WARNING, Message.Level.ERROR);
            for (Message message : messages) {
                System.out.println("Error: "+message.getText());
            }

            throw new IllegalStateException("Compilation errors were found. Check the logs.");
        }

        return kieHelper.build().newKieSession();
    }
}

package com.csvandexel.service;

import com.csvandexel.entity.CsvEntity;
import com.csvandexel.repository.CsvRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired; // Import the annotation
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

@Service
public class CsvService {

    private final CsvRepository repository; // Add final keyword

    @Autowired // Add this annotation
    public CsvService(CsvRepository repository) {
        this.repository = repository;
    }

    public void processFile(MultipartFile file) throws IOException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                CsvEntity csvData = new CsvEntity();
                csvData.setPeriod(line[0]);
                csvData.setSeriesReference(line[1]);
                csvData.setRegionName(line[2]);
                csvData.setFilledJobs(line[3]);

                repository.save(csvData); // Save each record to the database
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }


    public void processFileExcel(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Assuming there's only one sheet

            for (Row row : sheet) {
                CsvEntity csvData = new CsvEntity();
                Iterator<Cell> cellIterator = row.cellIterator();

                csvData.setPeriod(getStringValueFromCell(cellIterator.next()));
                csvData.setSeriesReference(getStringValueFromCell(cellIterator.next()));
                csvData.setRegionName(getStringValueFromCell(cellIterator.next()));
                csvData.setFilledJobs(getStringValueFromCell(cellIterator.next()));

                repository.save(csvData); // Save each record to the database
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CsvEntity> list() {
        return repository.findAll();
    }

    private String getStringValueFromCell(Cell cell) {
        cell.setCellType(CellType.STRING); // Ensure we read cell content as strings
        return cell.getStringCellValue();
    }
}

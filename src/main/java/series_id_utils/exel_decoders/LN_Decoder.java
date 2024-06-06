package series_id_utils.exel_decoders;

import com.github.pjfanning.xlsx.StreamingReader;
import data_models.BLS_Data_Models.lnSeriesIDData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

public class LN_Decoder {
    public static Map<String, lnSeriesIDData> loadSeriesData(String filePath) {
        Map<String, lnSeriesIDData> seriesDataMap = new ConcurrentHashMap<>();  // Use ConcurrentHashMap for thread safety
        try (InputStream is = new FileInputStream(filePath)) {
            Workbook workbook = StreamingReader.builder()
                    .rowCacheSize(100)
                    .bufferSize(4096)
                    .open(is);
            Sheet sheet = workbook.getSheetAt(0);

            StreamSupport.stream(sheet.spliterator(), true) // Create a parallel stream
                    .filter(r -> r.getCell(0) != null && r.getCell(0).getStringCellValue() != null)
                    .forEach(r -> {
                        String seriesId = r.getCell(0).getStringCellValue().trim();
                        seriesDataMap.put(seriesId, mapRowToLNSeriesIDData(r));
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seriesDataMap;
    }

    public static lnSeriesIDData getSeriesData(String seriesId, Map<String, lnSeriesIDData> seriesDataMap) {
        return seriesDataMap.getOrDefault(seriesId, new lnSeriesIDData());
    }

    private static lnSeriesIDData mapRowToLNSeriesIDData(Row row) {
        lnSeriesIDData data = new lnSeriesIDData();
        //System.out.println("are we getting data?"+ row.getCell(0).getStringCellValue().trim());
        data.setSeriesId(row.getCell(0).getStringCellValue());
        data.setLfstCode(row.getCell(1).getStringCellValue());
        data.setPeriodicityCode(row.getCell(2).getStringCellValue());
        data.setPeriodicityText(row.getCell(3).getStringCellValue());
        data.setSeriesTitle(row.getCell(4).getStringCellValue());
        data.setAbsnCode(row.getCell(5).getStringCellValue());
        data.setAbsnText(row.getCell(6).getStringCellValue());
        data.setActivityCode(row.getCell(7).getStringCellValue());
        data.setActivityText(row.getCell(8).getStringCellValue());
        data.setAgesCode(row.getCell(9).getStringCellValue());
        data.setAgesText(row.getCell(10).getStringCellValue());
        data.setCertCode(row.getCell(11).getStringCellValue());
        data.setCertText(row.getCell(12).getStringCellValue());
        data.setClassCode(row.getCell(13).getStringCellValue());
        data.setClassText(row.getCell(14).getStringCellValue());
        data.setDurationCode(row.getCell(15).getStringCellValue());
        data.setDurationText(row.getCell(16).getStringCellValue());
        data.setEducationCode(row.getCell(17).getStringCellValue());
        data.setEducationText(row.getCell(18).getStringCellValue());
        data.setEntrCode(row.getCell(19).getStringCellValue());
        data.setEntrText(row.getCell(20).getStringCellValue());
        data.setExprCode(row.getCell(21).getStringCellValue());
        data.setExprText(row.getCell(22).getStringCellValue());
        data.setHheaderCode(row.getCell(23).getStringCellValue());
        data.setHheaderText(row.getCell(24).getStringCellValue());
        data.setHourCode(row.getCell(25).getStringCellValue());
        data.setHourText(row.getCell(26).getStringCellValue());
        data.setIndyCode(row.getCell(27).getStringCellValue());
        data.setIndyText(row.getCell(28).getStringCellValue());
        data.setJdesCode(row.getCell(29).getStringCellValue());
        data.setJdesText(row.getCell(30).getStringCellValue());
        data.setLookCode(row.getCell(31).getStringCellValue());
        data.setLookText(row.getCell(32).getStringCellValue());
        data.setMariCode(row.getCell(33).getStringCellValue());
        data.setMariText(row.getCell(34).getStringCellValue());
        data.setMjhsCode(row.getCell(35).getStringCellValue());
        data.setMjhsText(row.getCell(36).getStringCellValue());
        data.setOccupationCode(row.getCell(37).getStringCellValue());
        data.setOccupationText(row.getCell(38).getStringCellValue());
        data.setOrigCode(row.getCell(39).getStringCellValue());
        data.setOrigText(row.getCell(40).getStringCellValue());
        data.setPctsCode(row.getCell(41).getStringCellValue());
        data.setPctsText(row.getCell(42).getStringCellValue());
        data.setRaceCode(row.getCell(43).getStringCellValue());
        data.setRaceText(row.getCell(44).getStringCellValue());
        data.setRjnwCode(row.getCell(45).getStringCellValue());
        data.setRjnwText(row.getCell(46).getStringCellValue());
        data.setRnlfCode(row.getCell(47).getStringCellValue());
        data.setRnlfText(row.getCell(48).getStringCellValue());
        data.setRwnsCode(row.getCell(49).getStringCellValue());
        data.setRwnsText(row.getCell(50).getStringCellValue());
        data.setSeekCode(row.getCell(51).getStringCellValue());
        data.setSeekText(row.getCell(52).getStringCellValue());
        data.setSexsCode(row.getCell(53).getStringCellValue());
        data.setSexsText(row.getCell(54).getStringCellValue());
        data.setTdatCode(row.getCell(55).getStringCellValue());
        data.setTdatText(row.getCell(56).getStringCellValue());
        data.setVetsCode(row.getCell(57).getStringCellValue());
        data.setVetsText(row.getCell(58).getStringCellValue());
        data.setWkstCode(row.getCell(59).getStringCellValue());
        data.setWkstText(row.getCell(60).getStringCellValue());
        data.setBornCode(row.getCell(61).getStringCellValue());
        data.setBornText(row.getCell(62).getStringCellValue());
        data.setChldCode(row.getCell(63).getStringCellValue());
        data.setChldText(row.getCell(64).getStringCellValue());
        data.setDisaCode(row.getCell(65).getStringCellValue());
        data.setDisaText(row.getCell(66).getStringCellValue());
        data.setSeasonal(row.getCell(67).getStringCellValue());
        data.setSeasonalText(row.getCell(68).getStringCellValue());
        data.setFootnoteCodes(row.getCell(69).getStringCellValue());
        data.setBeginYear(row.getCell(70).getStringCellValue());
        data.setBeginPeriod(row.getCell(71).getStringCellValue());
        data.setEndYear(row.getCell(72).getStringCellValue());
        data.setEndPeriod(row.getCell(73).getStringCellValue());
        return data;
    }




}


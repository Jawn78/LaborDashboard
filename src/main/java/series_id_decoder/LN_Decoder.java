package series_id_decoder;

import com.github.pjfanning.xlsx.StreamingReader;
import data_models.LNSeriesIDData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.StreamSupport;

public class LN_Decoder {
    public static Map<String, LNSeriesIDData> loadSeriesData(String filePath) {
        Map<String, LNSeriesIDData> seriesDataMap = new ConcurrentHashMap<>();  // Use ConcurrentHashMap for thread safety
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

    public static LNSeriesIDData getSeriesData(String seriesId, Map<String, LNSeriesIDData> seriesDataMap) {
        return seriesDataMap.getOrDefault(seriesId, new LNSeriesIDData());
    }

    private static LNSeriesIDData mapRowToLNSeriesIDData(Row row) {
        LNSeriesIDData data = new LNSeriesIDData();
        //System.out.println("are we getting data?"+ row.getCell(0).getStringCellValue().trim());
        data.setSeries_id(row.getCell(0).getStringCellValue());
        data.setLfst_code(row.getCell(1).getStringCellValue());
        data.setPeriodicity_code(row.getCell(2).getStringCellValue());
        data.setPeriodicity_text(row.getCell(3).getStringCellValue());
        data.setSeries_title(row.getCell(4).getStringCellValue());
        data.setAbsn_code(row.getCell(5).getStringCellValue());
        data.setAbsn_text(row.getCell(6).getStringCellValue());
        data.setActivity_code(row.getCell(7).getStringCellValue());
        data.setActivity_text(row.getCell(8).getStringCellValue());
        data.setAges_code(row.getCell(9).getStringCellValue());
        data.setAges_text(row.getCell(10).getStringCellValue());
        data.setCert_code(row.getCell(11).getStringCellValue());
        data.setCert_text(row.getCell(12).getStringCellValue());
        data.setClass_code(row.getCell(13).getStringCellValue());
        data.setClass_text(row.getCell(14).getStringCellValue());
        data.setDuration_code(row.getCell(15).getStringCellValue());
        data.setDuration_text(row.getCell(16).getStringCellValue());
        data.setEducation_code(row.getCell(17).getStringCellValue());
        data.setEducation_text(row.getCell(18).getStringCellValue());
        data.setEntr_code(row.getCell(19).getStringCellValue());
        data.setEntr_text(row.getCell(20).getStringCellValue());
        data.setExpr_code(row.getCell(21).getStringCellValue());
        data.setExpr_text(row.getCell(22).getStringCellValue());
        data.setHheader_code(row.getCell(23).getStringCellValue());
        data.setHheader_text(row.getCell(24).getStringCellValue());
        data.setHour_code(row.getCell(25).getStringCellValue());
        data.setHour_text(row.getCell(26).getStringCellValue());
        data.setIndy_code(row.getCell(27).getStringCellValue());
        data.setIndy_text(row.getCell(28).getStringCellValue());
        data.setJdes_code(row.getCell(29).getStringCellValue());
        data.setJdes_text(row.getCell(30).getStringCellValue());
        data.setLook_code(row.getCell(31).getStringCellValue());
        data.setLook_text(row.getCell(32).getStringCellValue());
        data.setMari_code(row.getCell(33).getStringCellValue());
        data.setMari_text(row.getCell(34).getStringCellValue());
        data.setMjhs_code(row.getCell(35).getStringCellValue());
        data.setMjhs_text(row.getCell(36).getStringCellValue());
        data.setOccupation_code(row.getCell(37).getStringCellValue());
        data.setOccupation_text(row.getCell(38).getStringCellValue());
        data.setOrig_code(row.getCell(39).getStringCellValue());
        data.setOrig_text(row.getCell(40).getStringCellValue());
        data.setPcts_code(row.getCell(41).getStringCellValue());
        data.setPcts_text(row.getCell(42).getStringCellValue());
        data.setRace_code(row.getCell(43).getStringCellValue());
        data.setRace_text(row.getCell(44).getStringCellValue());
        data.setRjnw_code(row.getCell(45).getStringCellValue());
        data.setRjnw_text(row.getCell(46).getStringCellValue());
        data.setRnlf_code(row.getCell(47).getStringCellValue());
        data.setRnlf_text(row.getCell(48).getStringCellValue());
        data.setRwns_code(row.getCell(49).getStringCellValue());
        data.setRwns_text(row.getCell(50).getStringCellValue());
        data.setSeek_code(row.getCell(51).getStringCellValue());
        data.setSeek_text(row.getCell(52).getStringCellValue());
        data.setSexs_code(row.getCell(53).getStringCellValue());
        data.setSexs_text(row.getCell(54).getStringCellValue());
        data.setTdat_code(row.getCell(55).getStringCellValue());
        data.setTdat_text(row.getCell(56).getStringCellValue());
        data.setVets_code(row.getCell(57).getStringCellValue());
        data.setVets_text(row.getCell(58).getStringCellValue());
        data.setWkst_code(row.getCell(59).getStringCellValue());
        data.setWkst_text(row.getCell(60).getStringCellValue());
        data.setBorn_code(row.getCell(61).getStringCellValue());
        data.setBorn_text(row.getCell(62).getStringCellValue());
        data.setChld_code(row.getCell(63).getStringCellValue());
        data.setChld_text(row.getCell(64).getStringCellValue());
        data.setDisa_code(row.getCell(65).getStringCellValue());
        data.setDisa_text(row.getCell(66).getStringCellValue());
        data.setSeasonal(row.getCell(67).getStringCellValue());
        data.setSeasonal_text(row.getCell(68).getStringCellValue());
        data.setFootnote_codes(row.getCell(69).getStringCellValue());
        data.setBegin_year(row.getCell(70).getStringCellValue());
        data.setBegin_period(row.getCell(71).getStringCellValue());
        data.setEnd_year(row.getCell(72).getStringCellValue());
        data.setEnd_period(row.getCell(73).getStringCellValue());
        return data;
    }




}


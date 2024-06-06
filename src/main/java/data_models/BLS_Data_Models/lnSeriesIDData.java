package data_models.BLS_Data_Models;

import jakarta.persistence.*;

@Entity
@Table(name = "ln_decoder_file")
public class lnSeriesIDData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This will auto-generate the ID if you use a database that supports auto-increment
    @Column(name = "series_id")
    private String seriesId;

    @Column(name = "lfst_code")
    private String lfstCode;

    @Column(name = "periodicity_code")
    private String periodicityCode;

    @Column(name = "periodicity_text")
    private String periodicityText;

    @Column(name = "series_title")
    private String seriesTitle;

    @Column(name = "absn_code")
    private String absnCode;

    @Column(name = "absn_text")
    private String absnText;

    @Column(name = "activity_code")
    private String activityCode;

    @Column(name = "activity_text")
    private String activityText;

    @Column(name = "ages_code")
    private String agesCode;

    @Column(name = "ages_text")
    private String agesText;

    @Column(name = "cert_code")
    private String certCode;

    @Column(name = "cert_text")
    private String certText;

    @Column(name = "class_code")
    private String classCode;

    @Column(name = "class_text")
    private String classText;

    @Column(name = "duration_code")
    private String durationCode;

    @Column(name = "duration_text")
    private String durationText;

    @Column(name = "education_code")
    private String educationCode;

    @Column(name = "education_text")
    private String educationText;

    @Column(name = "entr_code")
    private String entrCode;

    @Column(name = "entr_text")
    private String entrText;

    @Column(name = "expr_code")
    private String exprCode;

    @Column(name = "expr_text")
    private String exprText;

    @Column(name = "hheader_code")
    private String hheaderCode;

    @Column(name = "hheader_text")
    private String hheaderText;

    @Column(name = "hour_code")
    private String hourCode;

    @Column(name = "hour_text")
    private String hourText;

    @Column(name = "indy_code")
    private String indyCode;

    @Column(name = "indy_text")
    private String indyText;

    @Column(name = "jdes_code")
    private String jdesCode;

    @Column(name = "jdes_text")
    private String jdesText;

    @Column(name = "look_code")
    private String lookCode;

    @Column(name = "look_text")
    private String lookText;

    @Column(name = "mari_code")
    private String mariCode;

    @Column(name = "mari_text")
    private String mariText;

    @Column(name = "mjhs_code")
    private String mjhsCode;

    @Column(name = "mjhs_text")
    private String mjhsText;

    @Column(name = "occupation_code")
    private String occupationCode;

    @Column(name = "occupation_text")
    private String occupationText;

    @Column(name = "orig_code")
    private String origCode;

    @Column(name = "orig_text")
    private String origText;

    @Column(name = "pcts_code")
    private String pctsCode;

    @Column(name = "pcts_text")
    private String pctsText;

    @Column(name = "race_code")
    private String raceCode;

    @Column(name = "race_text")
    private String raceText;

    @Column(name = "rjnw_code")
    private String rjnwCode;

    @Column(name = "rjnw_text")
    private String rjnwText;

    @Column(name = "rnlf_code")
    private String rnlfCode;

    @Column(name = "rnlf_text")
    private String rnlfText;

    @Column(name = "rwns_code")
    private String rwnsCode;

    @Column(name = "rwns_text")
    private String rwnsText;

    @Column(name = "seek_code")
    private String seekCode;

    @Column(name = "seek_text")
    private String seekText;

    @Column(name = "sexs_code")
    private String sexsCode;

    @Column(name = "sexs_text")
    private String sexsText;

    @Column(name = "tdat_code")
    private String tdatCode;

    @Column(name = "tdat_text")
    private String tdatText;

    @Column(name = "vets_code")
    private String vetsCode;

    @Column(name = "vets_text")
    private String vetsText;

    @Column(name = "wkst_code")
    private String wkstCode;

    @Column(name = "wkst_text")
    private String wkstText;

    @Column(name = "born_code")
    private String bornCode;

    @Column(name = "born_text")
    private String bornText;

    @Column(name = "chld_code")
    private String chldCode;

    @Column(name = "chld_text")
    private String chldText;

    @Column(name = "disa_code")
    private String disaCode;

    @Column(name = "disa_text")
    private String disaText;

    @Column(name = "seasonal")
    private String seasonal;

    @Column(name = "seasonal_text")
    private String seasonalText;

    @Column(name = "footnote_codes")
    private String footnoteCodes;

    @Column(name = "begin_year")
    private String beginYear;

    @Column(name = "begin_period")
    private String beginPeriod;

    @Column(name = "end_year")
    private String endYear;

    @Column(name = "end_period")
    private String endPeriod;

    public String getSeriesId() {
        return this.seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getLfstCode() {
        return this.lfstCode;
    }

    public void setLfstCode(String lfstCode) {
        this.lfstCode = lfstCode;
    }

    public String getPeriodicityCode() {
        return this.periodicityCode;
    }

    public void setPeriodicityCode(String periodicityCode) {
        this.periodicityCode = periodicityCode;
    }

    public String getPeriodicityText() {
        return this.periodicityText;
    }

    public void setPeriodicityText(String periodicityText) {
        this.periodicityText = periodicityText;
    }

    public String getSeriesTitle() {
        return this.seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getAbsnCode() {
        return this.absnCode;
    }

    public void setAbsnCode(String absnCode) {
        this.absnCode = absnCode;
    }

    public String getAbsnText() {
        return this.absnText;
    }

    public void setAbsnText(String absnText) {
        this.absnText = absnText;
    }

    public String getActivityCode() {
        return this.activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getActivityText() {
        return this.activityText;
    }

    public void setActivityText(String activityText) {
        this.activityText = activityText;
    }

    public String getAgesCode() {
        return this.agesCode;
    }

    public void setAgesCode(String agesCode) {
        this.agesCode = agesCode;
    }

    public String getAgesText() {
        return this.agesText;
    }

    public void setAgesText(String agesText) {
        this.agesText = agesText;
    }

    public String getCertCode() {
        return this.certCode;
    }

    public void setCertCode(String certCode) {
        this.certCode = certCode;
    }

    public String getCertText() {
        return this.certText;
    }

    public void setCertText(String certText) {
        this.certText = certText;
    }

    public String getClassCode() {
        return this.classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassText() {
        return this.classText;
    }

    public void setClassText(String classText) {
        this.classText = classText;
    }

    public String getDurationCode() {
        return this.durationCode;
    }

    public void setDurationCode(String durationCode) {
        this.durationCode = durationCode;
    }

    public String getDurationText() {
        return this.durationText;
    }

    public void setDurationText(String durationText) {
        this.durationText = durationText;
    }

    public String getEducationCode() {
        return this.educationCode;
    }

    public void setEducationCode(String educationCode) {
        this.educationCode = educationCode;
    }

    public String getEducationText() {
        return this.educationText;
    }

    public void setEducationText(String educationText) {
        this.educationText = educationText;
    }

    public String getEntrCode() {
        return this.entrCode;
    }

    public void setEntrCode(String entrCode) {
        this.entrCode = entrCode;
    }

    public String getEntrText() {
        return this.entrText;
    }

    public void setEntrText(String entrText) {
        this.entrText = entrText;
    }

    public String getExprCode() {
        return this.exprCode;
    }

    public void setExprCode(String exprCode) {
        this.exprCode = exprCode;
    }

    public String getExprText() {
        return this.exprText;
    }

    public void setExprText(String exprText) {
        this.exprText = exprText;
    }

    public String getHheaderCode() {
        return this.hheaderCode;
    }

    public void setHheaderCode(String hheaderCode) {
        this.hheaderCode = hheaderCode;
    }

    public String getHheaderText() {
        return this.hheaderText;
    }

    public void setHheaderText(String hheaderText) {
        this.hheaderText = hheaderText;
    }

    public String getHourCode() {
        return this.hourCode;
    }

    public void setHourCode(String hourCode) {
        this.hourCode = hourCode;
    }

    public String getHourText() {
        return this.hourText;
    }

    public void setHourText(String hourText) {
        this.hourText = hourText;
    }

    public String getIndyCode() {
        return this.indyCode;
    }

    public void setIndyCode(String indyCode) {
        this.indyCode = indyCode;
    }

    public String getIndyText() {
        return this.indyText;
    }

    public void setIndyText(String indyText) {
        this.indyText = indyText;
    }

    public String getJdesCode() {
        return this.jdesCode;
    }

    public void setJdesCode(String jdesCode) {
        this.jdesCode = jdesCode;
    }

    public String getJdesText() {
        return this.jdesText;
    }

    public void setJdesText(String jdesText) {
        this.jdesText = jdesText;
    }

    public String getLookCode() {
        return this.lookCode;
    }

    public void setLookCode(String lookCode) {
        this.lookCode = lookCode;
    }

    public String getLookText() {
        return this.lookText;
    }

    public void setLookText(String lookText) {
        this.lookText = lookText;
    }

    public String getMariCode() {
        return this.mariCode;
    }

    public void setMariCode(String mariCode) {
        this.mariCode = mariCode;
    }

    public String getMariText() {
        return this.mariText;
    }

    public void setMariText(String mariText) {
        this.mariText = mariText;
    }

    public String getMjhsCode() {
        return this.mjhsCode;
    }

    public void setMjhsCode(String mjhsCode) {
        this.mjhsCode = mjhsCode;
    }

    public String getMjhsText() {
        return this.mjhsText;
    }

    public void setMjhsText(String mjhsText) {
        this.mjhsText = mjhsText;
    }

    public String getOccupationCode() {
        return this.occupationCode;
    }

    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    public String getOccupationText() {
        return this.occupationText;
    }

    public void setOccupationText(String occupationText) {
        this.occupationText = occupationText;
    }

    public String getOrigCode() {
        return this.origCode;
    }

    public void setOrigCode(String origCode) {
        this.origCode = origCode;
    }

    public String getOrigText() {
        return this.origText;
    }

    public void setOrigText(String origText) {
        this.origText = origText;
    }

    public String getPctsCode() {
        return this.pctsCode;
    }

    public void setPctsCode(String pctsCode) {
        this.pctsCode = pctsCode;
    }

    public String getPctsText() {
        return this.pctsText;
    }

    public void setPctsText(String pctsText) {
        this.pctsText = pctsText;
    }

    public String getRaceCode() {
        return this.raceCode;
    }

    public void setRaceCode(String raceCode) {
        this.raceCode = raceCode;
    }

    public String getRaceText() {
        return this.raceText;
    }

    public void setRaceText(String raceText) {
        this.raceText = raceText;
    }

    public String getRjnwCode() {
        return this.rjnwCode;
    }

    public void setRjnwCode(String rjnwCode) {
        this.rjnwCode = rjnwCode;
    }

    public String getRjnwText() {
        return this.rjnwText;
    }

    public void setRjnwText(String rjnwText) {
        this.rjnwText = rjnwText;
    }

    public String getRnlfCode() {
        return this.rnlfCode;
    }

    public void setRnlfCode(String rnlfCode) {
        this.rnlfCode = rnlfCode;
    }

    public String getRnlfText() {
        return this.rnlfText;
    }

    public void setRnlfText(String rnlfText) {
        this.rnlfText = rnlfText;
    }

    public String getRwnsCode() {
        return this.rwnsCode;
    }

    public void setRwnsCode(String rwnsCode) {
        this.rwnsCode = rwnsCode;
    }

    public String getRwnsText() {
        return this.rwnsText;
    }

    public void setRwnsText(String rwnsText) {
        this.rwnsText = rwnsText;
    }

    public String getSeekCode() {
        return this.seekCode;
    }

    public void setSeekCode(String seekCode) {
        this.seekCode = seekCode;
    }

    public String getSeekText() {
        return this.seekText;
    }

    public void setSeekText(String seekText) {
        this.seekText = seekText;
    }

    public String getSexsCode() {
        return this.sexsCode;
    }

    public void setSexsCode(String sexsCode) {
        this.sexsCode = sexsCode;
    }

    public String getSexsText() {
        return this.sexsText;
    }

    public void setSexsText(String sexsText) {
        this.sexsText = sexsText;
    }

    public String getTdatCode() {
        return this.tdatCode;
    }

    public void setTdatCode(String tdatCode) {
        this.tdatCode = tdatCode;
    }

    public String getTdatText() {
        return this.tdatText;
    }

    public void setTdatText(String tdatText) {
        this.tdatText = tdatText;
    }

    public String getVetsCode() {
        return this.vetsCode;
    }

    public void setVetsCode(String vetsCode) {
        this.vetsCode = vetsCode;
    }

    public String getVetsText() {
        return this.vetsText;
    }

    public void setVetsText(String vetsText) {
        this.vetsText = vetsText;
    }

    public String getWkstCode() {
        return this.wkstCode;
    }

    public void setWkstCode(String wkstCode) {
        this.wkstCode = wkstCode;
    }

    public String getWkstText() {
        return this.wkstText;
    }

    public void setWkstText(String wkstText) {
        this.wkstText = wkstText;
    }

    public String getBornCode() {
        return this.bornCode;
    }

    public void setBornCode(String bornCode) {
        this.bornCode = bornCode;
    }

    public String getBornText() {
        return this.bornText;
    }

    public void setBornText(String bornText) {
        this.bornText = bornText;
    }

    public String getChldCode() {
        return this.chldCode;
    }

    public void setChldCode(String chldCode) {
        this.chldCode = chldCode;
    }

    public String getChldText() {
        return this.chldText;
    }

    public void setChldText(String chldText) {
        this.chldText = chldText;
    }

    public String getDisaCode() {
        return this.disaCode;
    }

    public void setDisaCode(String disaCode) {
        this.disaCode = disaCode;
    }

    public String getDisaText() {
        return this.disaText;
    }

    public void setDisaText(String disaText) {
        this.disaText = disaText;
    }

    public String getSeasonal() {
        return this.seasonal;
    }

    public void setSeasonal(String seasonal) {
        this.seasonal = seasonal;
    }

    public String getSeasonalText() {
        return this.seasonalText;
    }

    public void setSeasonalText(String seasonalText) {
        this.seasonalText = seasonalText;
    }

    public String getFootnoteCodes() {
        return this.footnoteCodes;
    }

    public void setFootnoteCodes(String footnoteCodes) {
        this.footnoteCodes = footnoteCodes;
    }

    public String getBeginYear() {
        return this.beginYear;
    }

    public void setBeginYear(String beginYear) {
        this.beginYear = beginYear;
    }

    public String getBeginPeriod() {
        return this.beginPeriod;
    }

    public void setBeginPeriod(String beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    public String getEndYear() {
        return this.endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getEndPeriod() {
        return this.endPeriod;
    }

    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }


}

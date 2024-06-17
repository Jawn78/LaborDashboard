package util.config;

import data_models.BLS_Data_Models.labor_data;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import series_id_utils.postgressql_decoders.TableNameUtil;

import java.util.List;

public class LaborDataRepository {

    private EntityManagerFactory entityManagerFactory;

    public LaborDataRepository() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("psql db");
    }

    public List<labor_data> findDataBySeriesIdAndYearRange(String seriesId, int startYear, int endYear) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String tableName = TableNameUtil.getTableName(seriesId);
        String queryString = "SELECT series_id, year, period, period_1, period_2, period_3, period_4, period_5, period_6, period_7, period_8, period_9, period_10, period_11, period_12 "
                + "FROM " + tableName + " "
                + "WHERE TRIM(series_id) = :seriesId AND year BETWEEN :startYear AND :endYear";
        Query query = entityManager.createNativeQuery(queryString, "LaborDataMapping");
        query.setParameter("seriesId", seriesId);
        query.setParameter("startYear", startYear);
        query.setParameter("endYear", endYear);
        System.out.println(queryString);
        List<labor_data> result = query.getResultList();
        entityManager.close();
        return result;
    }

    public void close() {
        entityManagerFactory.close();
    }
}

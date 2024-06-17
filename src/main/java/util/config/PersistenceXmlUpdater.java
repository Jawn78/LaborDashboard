package util.config;

import data_models.Config_Models.ConfigPOJO;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.Jsoup;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class PersistenceXmlUpdater {

    public static void updatePersistenceXml(ConfigPOJO config, String xmlFilePath) throws IOException {
        Document doc;
        File xmlFile = new File(xmlFilePath);

        if (xmlFile.exists()) {
            doc = Jsoup.parse(xmlFile, "UTF-8", "", Parser.xmlParser());
        } else {
            doc = Document.createShell("");
        }

        Element persistenceUnit = doc.selectFirst("persistence-unit");
        if (persistenceUnit == null) {
            persistenceUnit = doc.appendElement("persistence").appendElement("persistence-unit");
            persistenceUnit.attr("name", "your-persistence-unit-name");
            persistenceUnit.appendElement("provider").text("org.hibernate.jpa.HibernatePersistenceProvider");
            persistenceUnit.appendElement("class").text("data_models.BLS_Data_Models.labor_data");
        }

        Element properties = persistenceUnit.selectFirst("properties");
        if (properties == null) {
            properties = persistenceUnit.appendElement("properties");
        } else {
            properties.empty(); // Clear existing properties
        }

        properties.appendElement("property")
                .attr("name", "jakarta.persistence.jdbc.driver")
                .attr("value", "org.postgresql.Driver");
        properties.appendElement("property")
                .attr("name", "jakarta.persistence.jdbc.url")
                .attr("value", config.getPostgreSQLConfig().getUrl());
        properties.appendElement("property")
                .attr("name", "jakarta.persistence.jdbc.user")
                .attr("value", config.getPostgreSQLConfig().getUser());
        properties.appendElement("property")
                .attr("name", "jakarta.persistence.jdbc.password")
                .attr("value", config.getPostgreSQLConfig().getPassword());
        properties.appendElement("property")
                .attr("name", "hibernate.dialect")
                .attr("value", "org.hibernate.dialect.PostgreSQLDialect");
        properties.appendElement("property")
                .attr("name", "hibernate.hbm2ddl.auto")
                .attr("value", "update");

        // Convert the document to a string
        String xmlContent = doc.outerHtml();

        // Write the string content to the file
        Files.write(Paths.get(xmlFilePath), xmlContent.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    }



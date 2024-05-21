package util.Config;

import com.google.gson.Gson;
import data_models.Config_Models.ConfigPOJO;
import data_models.Config_Models.PostgreSQLConfig;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Manager {
    private static ConfigPOJO appConfig;

    public static ConfigPOJO loadConfig(String filePath) {
        System.out.println("Loading config.json file");
        ensureConfigExists();
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            appConfig = gson.fromJson(reader, ConfigPOJO.class);
        } catch (IOException e) {
            System.out.println("Error loading config.json file");
            e.printStackTrace();
        }
        System.out.println("API Key found: " + !appConfig.getBLSApiKey().isEmpty());
        System.out.println("Using PostgreSQL: " + appConfig.getUsePostgreSQL());
        testDatabaseConnection();
        return appConfig;
    }

    public static boolean testDatabaseConnection() {
        if (!appConfig.getUsePostgreSQL()) {
            System.out.println("PostgreSQL usage is disabled.");
            return false;
        }
        try (Connection conn = DriverManager.getConnection(
                appConfig.getPostgreSQLConfig().getUrl(),
                appConfig.getPostgreSQLConfig().getUser(),
                appConfig.getPostgreSQLConfig().getPassword())) {
            System.out.println("Connection to PostgreSQL database successful.");
            return true;
        } catch (Exception e) {
            System.out.println("Failed to connect to the PostgreSQL database.");
            System.out.println("Reverting to use of excel file");
            e.printStackTrace();
            return false;
        }
    }

    public static ConfigPOJO getConfig() {
        return appConfig;
    }

    public static void ensureConfigExists() {
        Path configPath = Paths.get("src/main/java/config.json");
        if (!Files.exists(configPath)) {
            System.out.println("Configuration file does not exist. Let's create one.");
            Scanner scanner = new Scanner(System.in);

            // Gather BLS API Key
            System.out.print("Enter BLS API Key: ");
            String blsApiKey = scanner.nextLine();

            // Confirm use of PostgreSQL
            System.out.print("Using excel files are much slower than using PostgresSQL. If you select no, the program will revert to excel");
            System.out.print("Do you want to use PostgreSQL? (yes/no): ");
            boolean usePostgreSQL = "yes".equalsIgnoreCase(scanner.nextLine().trim());

            // PostgreSQL configuration
            String url = "", user = "", password = "";
            if (usePostgreSQL) {
                System.out.print("Enter PostgreSQL URL: ");
                url = scanner.nextLine();
                System.out.print("Enter PostgreSQL user: ");
                user = scanner.nextLine();
                System.out.print("Enter PostgreSQL password: ");
                password = scanner.nextLine();
            }
            PostgreSQLConfig pgConfig = new PostgreSQLConfig(url, user, password);
            ConfigPOJO config = new ConfigPOJO(blsApiKey, usePostgreSQL, pgConfig);

            // Serialize to JSON and write to file
            try {
                Gson gson = new Gson();
                String json = gson.toJson(config);
                try (FileWriter writer = new FileWriter(configPath.toString())) {
                    writer.write(json);
                }
                System.out.println("Configuration file created successfully.");
            } catch (IOException e) {
                System.out.println("Failed to write the configuration file.");
                e.printStackTrace();
            }

            scanner.close();
        } else {
            System.out.println("Configuration file already exists.");
        }
    }
}

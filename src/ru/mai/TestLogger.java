package ru.mai;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestLogger {

    private static Logger logger = Logger.getLogger("system");

    private static final int NUM_LOG_FILES = 10;

    /**
     * 1 KB Per log file, modify as required.
     */
    private static final int LIMIT = 1024;

    private static final int SIZE = 100;

    private static final int MAGIC_NUMBER = 13;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* Create logger object */
        Logger loggerWords = Logger.getLogger("loggerWords");

        try {
            FileHandler fh1 = new FileHandler("xml.log");
            FileHandler fh = new FileHandler("system.log");
//            FileHandler fh = new FileHandler("system.log", LIMIT, NUM_LOG_FILES, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);

            loggerWords.addHandler(fh1);
//            logger.setLevel(Level.WARNING);

        } catch (SecurityException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за политики безопасности.",
                    e);
        } catch (IOException e) {
            logger.log(Level.SEVERE,
                    "Не удалось создать файл лога из-за ошибки ввода-вывода.",
                    e);
        }

        loggerWords.info("Начинаем эксперименты с логированием");

        loggerWords.warning("Сейчас будем писать много-много данных в системный лог");

        for (int i = 0; i < SIZE; i++) {
            System.out.println("Step - " + i);
            logger.log(Level.INFO, "Запись лога с уровнем INFO (информационная)");
            logger.info("Запись лога с уровнем INFO (информационная)");
            System.out.println("Step - " + i);
            logger.log(Level.WARNING, "Запись лога с уровнем WARNING (Предупреждение)");
            logger.log(Level.SEVERE, "Запись лога с уровнем SEVERE (серъёзная ошибка)");
            if (i == MAGIC_NUMBER) {
                loggerWords.severe("13 - !!!");
            }
        }

        loggerWords.info("Эксперименты с логированием завершены успешно");
    }
}

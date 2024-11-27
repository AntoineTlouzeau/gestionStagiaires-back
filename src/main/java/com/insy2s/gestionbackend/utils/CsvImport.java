package com.insy2s.gestionbackend.utils;

import com.insy2s.gestionbackend.customenum.PresenceType;
import com.insy2s.gestionbackend.model.Intern;

import java.io.BufferedReader;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvImport {

    public static CsvImportResult readCsvFile(Reader reader) throws Exception {
        List<Intern> validInterns = new ArrayList<>();
        List<String> errors = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            int lineCount = 0;

            while ((line = br.readLine()) != null) {
                lineCount++;

                if (lineCount == 1) {
                    continue;
                }

                String[] values = line.split(",");
                List<String> errorsForThisIntern = new ArrayList<>();

                String lastName = values.length > 0 ? values[0] : null;
                String firstName = values.length > 1 ? values[1] : null;
                String email = values.length > 2 ? values[2] : null;
                String presenceType = values.length > 3 ? values[3] : null;
                String isDeleted = values.length > 4 ? values[4] : null;
                String trainings = values.length > 5 ? values[5] : null;
                String urlCv = values.length > 6 ? values[6] : null;
                String phoneNumber = values.length > 7 ? values[7] : null;
                String hiredBy = values.length > 8 ? values[8] : null;
                String hiredAtStr = values.length > 9 ? values[9] : null;

                if (!isValidName(lastName)) {
                    errorsForThisIntern.add("Nom non valide à la ligne : " + lineCount);
                }

                if (!isValidName(firstName)) {
                    errorsForThisIntern.add("Prénom non valide à la ligne : " + lineCount);

                }

                if (!isValidEmail(email)) {
                    errorsForThisIntern.add("Email non valide à la ligne : " + lineCount);
                }

                if (!isValidPresenceType(presenceType)) {
                    errorsForThisIntern.add("Type de présence non valide à la ligne : " + lineCount);
                }

                if (!isValidIsDeleted(isDeleted)) {
                    errorsForThisIntern.add("Archivage non valide à la ligne : " + lineCount);
                }


                if (!isValidTraining(trainings)) {
                    errorsForThisIntern.add("Formation non valide à la ligne : " + lineCount);
                }

                if (!isValidUrlcv(urlCv)) {
                    errorsForThisIntern.add("Chemin de cv non valide à la ligne : " + lineCount);
                }

                if (!isValidPhoneNumber(phoneNumber)) {
                    errorsForThisIntern.add("Numéro de téléphone non valide à la ligne : " + lineCount);
                }

                if (!isValidCompagny(hiredBy)) {
                    errorsForThisIntern.add("Entreprise non valide à la ligne : " + lineCount);
                }

                if (!isValidHireDate(hiredAtStr)) {
                    errorsForThisIntern.add("Date d'embauche non valide à la ligne : " + lineCount);
                }

                if (errorsForThisIntern.isEmpty()) {
                    Intern intern = new Intern();
                    intern.setLastName(lastName);
                    intern.setFirstName(firstName);
                    intern.setEmail(email);
                    intern.setPresenceType(PresenceType.valueOf(presenceType));
                    intern.setIsDeleted(Boolean.parseBoolean(isDeleted));
                    intern.setTrainings(trainings);
                    intern.setUrlCv(urlCv);
                    intern.setPhoneNumber(phoneNumber);
                    intern.setHiredBy(hiredBy);
                    if (hiredAtStr != null) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        Date hiredAtDate = dateFormat.parse(hiredAtStr);
                        intern.setHiredAt(hiredAtDate);
                    }

                    validInterns.add(intern);
                }

                else  {
                    errors.addAll(errorsForThisIntern);
                }
            }
        }

        return new CsvImportResult(validInterns, errors);
    }

    private static boolean isValidName(String name) {
        return name != null && name.matches("^[a-zA-ZéèêëàâäôöùûüçÉÈÊËÀÂÄÔÖÙÛÜÇ\\-\\s]{1,50}$");
    }

    private static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return email != null && matcher.matches();
    }

    private static boolean isValidPresenceType(String presenceType) {
        return presenceType != null && ("DISTANCIEL".equalsIgnoreCase(presenceType) || "HYBRIDE".equalsIgnoreCase(presenceType));
    }

    private static boolean isValidTraining(String training) {
        return training == null || training.length() <= 100;
    }

    private static boolean isValidCompagny(String hiredBy) {
        return hiredBy == null || hiredBy.length() <= 100;
    }

    private static boolean isValidUrlcv(String urlCv) {
        return urlCv == null || urlCv.length() <= 100;
    }

    private static boolean isValidIsDeleted(String isDeleted) {
        return isDeleted != null && ("true".equalsIgnoreCase(isDeleted) || "false".equalsIgnoreCase(isDeleted));
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber == null || phoneNumber.trim().isEmpty() || phoneNumber.matches("^[0-9]{10}$");
    }

    private static boolean isValidHireDate(String date) {
        if (date == null) {
            return true;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}

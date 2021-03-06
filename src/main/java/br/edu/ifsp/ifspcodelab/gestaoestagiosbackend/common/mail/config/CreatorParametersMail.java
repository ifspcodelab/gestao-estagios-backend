package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CreatorParametersMail {

    private CreatorParametersMail() {}

    public static Map<String, String> setParameters(String username, String baseUrl, UUID userId) {
        Map<String, String> map = new HashMap<>();

        map.put("$USERNAME", username);
        map.put("$BASEURL", baseUrl);
        map.put("$IDUSER", userId.toString());

        return map;
    }

    public static Map<String, String> setParametersAdvisorRequestNotify(String username, String studentName, String registration, String internshipType, String expiresAt) {
        Map<String, String> map = new HashMap<>();

        map.put("$USERNAME", username);
        map.put("$STUDENTNAME", studentName);
        map.put("$REGISTRATION", registration);
        map.put("$INTERNSHIPTYPE", internshipType);
        map.put("$EXPIRESAT", expiresAt);

        return map;
    }

    public static Map<String, String> setParametersStudentNotificationExpired(String username,
                                                                              String advisorName,
                                                                              String requestCreatedDate,
                                                                              String link) {
        Map<String, String> map = new HashMap<>();

        map.put("$USERNAME", username);
        map.put("$ADVISORNAME", advisorName);
        map.put("$REQUESTCREATEDATE", requestCreatedDate);
        map.put("$SYSTEMLINK", link);

        return map;
    }

    public static Map<String, String> setParametersRequestAppraisal(String isDeferred, String studentName, String advisorName, String details) {
        Map<String, String> map = new HashMap<>();

        map.put("$ISDEFERRED", isDeferred);
        map.put("$STUDENTNAME", studentName);
        map.put("$ADVISORNAME", advisorName);
        map.put("$DETAILS", details);

        return map;
    }

    public static Map<String, String> setParametersPlanApproved(String studentName, String advisorName, String details) {
        Map<String, String> map = new HashMap<>();

        map.put("$STUDENTNAME", studentName);
        map.put("$ADVISORNAME", advisorName);
        map.put("$DETAILS", details);

        return map;
    }

    public static Map<String, String> setParametersPlanIndeferred(String studentName,
                                                                String advisorName,
                                                                String details,
                                                                String link) {
        Map<String, String> map = new HashMap<>();

        map.put("$STUDENTNAME", studentName);
        map.put("$ADVISORNAME", advisorName);
        map.put("$DETAILS", details);
        map.put("$SYSTEMLINK", link);

        return map;
    }

    public static Map<String, String> setParametersReportSentToStudent(String report, String month) {
        Map<String, String> map = new HashMap<>();

        map.put("$REPORT", report);
        map.put("$MONTH", month);

        return map;
    }

    public static Map<String, String> setParametersReportSentToAdvisor(
        String report,
        String student,
        String month,
        String link
    ) {
        Map<String, String> map = new HashMap<>();

        map.put("$REPORT", report);
        map.put("$STUDENT", student);
        map.put("$MONTH", month);
        map.put("$SYSTEMLINK", link);

        return map;
    }

    public static Map<String, String> setParametersReportApproved(
        String report,
        String student,
        String advisor,
        String month,
        String details
    ) {
        Map<String, String> map = new HashMap<>();

        map.put("$REPORT", report);
        map.put("$STUDENT", student);
        map.put("$ADVISOR", advisor);
        map.put("$MONTH", month);
        map.put("$DETAILS", details);

        return map;
    }

    public static Map<String, String> setParametersReportIndeferred(
        String report,
        String student,
        String advisor,
        String month,
        String details,
        String link
    ) {
        Map<String, String> map = new HashMap<>();

        map.put("$REPORT", report);
        map.put("$STUDENT", student);
        map.put("$ADVISOR", advisor);
        map.put("$MONTH", month);
        map.put("$DETAILS", details);
        map.put("$SYSTEMLINK", link);

        return map;
    }
    public static Map<String, String> setRealizationTermSubmissionStudent(String studentName) {
        Map<String, String> map = new HashMap<>();

        map.put("$STUDENTNAME", studentName);

        return map;
    }

    public static Map<String, String> setRealizationTermSubmissionAdvisor(String advisorName,
                                                                          String studentName,
                                                                          String link) {
        Map<String, String> map = new HashMap<>();

        map.put("$STUDENTNAME", studentName);
        map.put("$ADVISORNAME", advisorName);
        map.put("$SYSTEMLINK", link);

        return map;
    }

}

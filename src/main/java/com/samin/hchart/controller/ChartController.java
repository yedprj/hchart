package com.samin.hchart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping(value = "/chart")
public class ChartController {

    @GetMapping("/covidChart")
    public String covidCahrt() {
        return "chart/covidChart";
    }

    @GetMapping("/covidVacCenter")
    public String covidVacCenter() {
        return "chart/covidVacCenter";
    }

    @GetMapping("/covidYmChart")
    public String covidYmChart() {
        return "chart/covidYmChart";
    }

    @RequestMapping(value = "/covidState", method = RequestMethod.GET, produces = "application/text; charset=utf8")
    @ResponseBody
    public String covidState() throws IOException {

        String urlBuilder = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=cCe0O4QbmO2jUczHa0UUCsTY6y5SFAITKoWay4sLzMN6IP%2FPb8qcJFbLW5Z4Zp2GAnhiyrgRMAg6afTBn7xJhQ%3D%3D" +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("30", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("startCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20220315", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("endCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20220413", StandardCharsets.UTF_8);

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type","application/xml;charset=UTF-8");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());

        return sb.toString();
    }

    @RequestMapping(value = "/centerMarker", method = RequestMethod.GET, produces = "application/text; charset=utf8")
    @ResponseBody
    public String centerMarker() throws IOException{

        String urlBuilder = "https://api.odcloud.kr/api/15077586/v1/centers" + "?" + URLEncoder.encode("page", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("perPage", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("100", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=cCe0O4QbmO2jUczHa0UUCsTY6y5SFAITKoWay4sLzMN6IP%2FPb8qcJFbLW5Z4Zp2GAnhiyrgRMAg6afTBn7xJhQ%3D%3D";

        URL url = new URL(urlBuilder);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml;charset=UTF-8");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();;
        conn.disconnect();

        System.out.println("넘어오냐?");
        System.out.println(sb.toString());

        return sb.toString();
    }

    @RequestMapping(value = "/covidYmState", method = RequestMethod.GET, produces = "application/text; charset=utf8")
    @ResponseBody
    public String covidYmState(String year, String month, String min_day, String max_day) throws IOException {

        String startCreateDt = year+month+min_day;
        String endCreateDt = year+month+max_day;

        String urlBuilder = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=cCe0O4QbmO2jUczHa0UUCsTY6y5SFAITKoWay4sLzMN6IP%2FPb8qcJFbLW5Z4Zp2GAnhiyrgRMAg6afTBn7xJhQ%3D%3D" +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("30", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("startCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(startCreateDt, StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("endCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode(endCreateDt, StandardCharsets.UTF_8);

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml;charset=UTF-8");
        System.out.println("Response Code: " + conn.getResponseCode());

        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());


        return sb.toString();
    }
}

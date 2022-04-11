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

    @RequestMapping(value = "/covidState", method = RequestMethod.GET, produces = "application/text; charset=utf8")
    @ResponseBody
    public String covidState() throws IOException {

        String urlBuilder = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson" + "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=cCe0O4QbmO2jUczHa0UUCsTY6y5SFAITKoWay4sLzMN6IP%2FPb8qcJFbLW5Z4Zp2GAnhiyrgRMAg6afTBn7xJhQ%3D%3D" +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("30", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("startCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20220312", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("endCreateDt", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20220410", StandardCharsets.UTF_8);

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
}

package com.samin.hchart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
@RequestMapping("/api/")
@Log4j2
@RequiredArgsConstructor
public class ApiController {

    @GetMapping("/dgTour")
    public String dgTour(){
        return "api/dgTour";

    }

    @RequestMapping(value = "/dgTourState", method = RequestMethod.GET, produces = "application/text; charset=utf8")
    @ResponseBody
    public String dgTourState() throws IOException {

        String urlBuilder = "http://apis.data.go.kr/3450000/daegu/bukgu/tourCourse/viewTourCourse" +
                "?" + URLEncoder.encode("serviceKey", StandardCharsets.UTF_8) + "=cCe0O4QbmO2jUczHa0UUCsTY6y5SFAITKoWay4sLzMN6IP%2FPb8qcJFbLW5Z4Zp2GAnhiyrgRMAg6afTBn7xJhQ%3D%3D" +
                "&" + URLEncoder.encode("pageNo", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("1", StandardCharsets.UTF_8) +
                "&" + URLEncoder.encode("numOfRows", StandardCharsets.UTF_8) + "=" + URLEncoder.encode("10", StandardCharsets.UTF_8);

        URL url = new URL(urlBuilder);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
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

        return sb.toString();
    }
}

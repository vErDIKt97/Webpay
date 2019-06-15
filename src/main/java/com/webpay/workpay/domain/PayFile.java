package com.webpay.workpay.domain;

import com.webpay.workpay.service.ExcelParser;

import javax.persistence.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "pfl")
public class PayFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private Date timeOfLoad;

    public PayFile () {
        Calendar calendar = Calendar.getInstance();
        timeOfLoad = calendar.getTime();
    }

    public void setTimeOfLoad() {
        Calendar calendar = Calendar.getInstance();
        this.timeOfLoad = calendar.getTime();
    }

    public Date getTimeOfLoad() {
        return timeOfLoad;
    }

    private HashMap<String ,Integer> list = new HashMap<>();

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HashMap<String, Integer> getList() {
        return list;
    }

    public void setList(File file) {
        this.list = ExcelParser.excelParse(file);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void findSells (User user) {
        for (Map.Entry<String, Integer> pair : this.list.entrySet()) {
            if (pair.getKey().contains(user.getName()) && pair.getKey().contains(user.getSurname())) {
                if (timeOfLoad != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date(calendar.getTime().getTime() - timeOfLoad.getTime()-18000000));
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'ч' mm'мин' ss'сек'");
                    user.setSells(pair.getValue().toString() + " руб.  " + "Обновлено: " + simpleDateFormat.format(calendar.getTime()) + " назад");
                } else {
                    user.setSells(pair.getValue().toString());
                }
                break;
            }
        }
    }
}

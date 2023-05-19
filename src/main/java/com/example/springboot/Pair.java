package com.example.springboot;

import org.jsoup.select.Elements;

public class Pair {
    public int id;
    public String title;
    public double cost;
    public double high;
    public double low;
    public double change;
    public double percent;
    public double turnover;
    public String time;
    public static Double parse(String s){
        s = s.replaceAll("\\.", "");
        s = s.replace(',', '.');
        return Double.parseDouble(s);
    }
    public void set_turnover(String s){
        int len = s.length();
        String s1 = s.substring(0, len - 1);
        String c = s.substring(len - 1);
        double t = parse(s1);
        switch (c) {
            case "K" -> this.turnover = t * 1e3;
            case "M" -> this.turnover = t * 1e6;
            case "B" -> this.turnover = t * 1e9;
            case "T" -> this.turnover = t * 1e12;
            default -> this.turnover = t;
        }
    }

    @Override
    public String toString() {
        return "Pair{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", high=" + high +
                ", low=" + low +
                ", change=" + change +
                ", percent=" + percent +
                ", turnover=" + turnover +
                ", time='" + time + '\'' +
                '}';
    }
}

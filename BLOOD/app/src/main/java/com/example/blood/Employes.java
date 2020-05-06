package com.example.blood;

class Employes implements Comparable<Employes>{
    private String name;
    private String blood_g;
    private String donate_date;
    private String age;
    private String mobile;
    private String health_pro;
    private String last_year;
    private String gender;
    private String lt;
    private String lng;
    private float distance;

    public Employes(){

    }


    public Employes(String name, String blood_g, String donate_date, String age, String mobile, String health_pro, String last_year, String gender, String lt, String lng,float distance) {

        this.name = name;
        this.blood_g = blood_g;
        this.donate_date = donate_date;
        this.age = age;
        this.mobile = mobile;
        this.health_pro = health_pro;
        this.last_year = last_year;
        this.gender = gender;
        this.lt = lt;
        this.lng = lng;
        this.distance = distance;
    }


    @Override
    public int compareTo(Employes other) {
        return this.name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlood_g() {
        return blood_g;
    }

    public void setBlood_g(String blood_g) {
        this.blood_g = blood_g;
    }

    public String getDonate_date() {
        return donate_date;
    }

    public void setDonate_date(String donate_date) {
        this.donate_date = donate_date;
    }

    public String getAge() {
        return age;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHealth_pro() {
        return health_pro;
    }

    public void setHealth_pro(String health_pro) {
        this.health_pro = health_pro;
    }

    public String getLast_year() {
        return last_year;
    }

    public void setLast_year(String last_year) {
        this.last_year = last_year;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLatitude() {
        return lt;
    }

    public void setLatitude(String latitude) {
        this.lt = latitude;
    }

    public String getLongitude() {
        return lng;
    }

    public void setLongitude(String longitude) {
        this.lng = longitude;
    }


}
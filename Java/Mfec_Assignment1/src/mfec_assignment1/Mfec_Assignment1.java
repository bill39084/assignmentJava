/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mfec_assignment1;

import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Mfec_Assignment1 {

    public static void main(String[] args) throws IOException, ParseException {
        String content;
        content = new String(Files.readAllBytes(Paths.get("./promotion1.log")));
        String Promotions[] = content.split("\\n"); //split ออกมาเป็นบรรทัดๆ
        ArrayList<Phone> listPhone = new ArrayList<>();  //สร้าง ArrayList มาเพื่อเก็บ Obj
        Phone phone = null;   //สร้าง obj มาเพื่อ Add 
        for (int i = 0; i < Promotions.length; i++) {
            String getValue[] = Promotions[i].split("\\|"); //split ตัวที่คั่นด้วย | 
            phone = new Phone(); //ประกาศ obj
            phone.setDate(getValue[0]); 
            phone.setStartTime(getValue[1]);
            phone.setEndTime(getValue[2]);
            phone.setPhoneNumber(getValue[3]);
            phone.setPromotionId(getValue[4]);
            phone.setAmount(0);    //เอาค่าแต่ละ value ที่ split ด้วย | set ค่าใน class Phone
            listPhone.add(phone); //Add obj ลง ArrayList
        }

        new Mfec_Assignment1().getAmount(listPhone); 

        Gson gson = new Gson();  //ไลบารี่ Gson เพื่อทำเป็น Format แบบ JSON
        String json = gson.toJson(listPhone); 
        byte[] bytetoFile = json.getBytes(); 
        byte data[] = bytetoFile; 
        try {
            FileOutputStream out = new FileOutputStream("./promotionJson.json");
            out.write(data); 
            out.close();   //ขั้นตอนการเขียนไฟล์
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    public void getAmount(ArrayList<Phone> listPhone) throws ParseException { 
        for (int i = 0; i < listPhone.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); //ใช้ dateformat เพื่อจะแปลง เวลาโทรเป็นformat date แล้วเอามาลบกัน
            Date start = sdf.parse(listPhone.get(i).startTime); //แปลง
            Date end = sdf.parse(listPhone.get(i).endTime);  //แปลง
            long millisecond = end.getTime() - start.getTime(); //นำมาลบกัน
            long second = TimeUnit.MILLISECONDS.toSeconds(millisecond); //แปลงเป็นวินาที
            int count = 0; //สร้างcount มานับนาที
            for (long j = second; j > 0; j = j - 60) { //สร้าง loop เอา วินาทีมาวน 60 to 0 
                count++; //ถ้าครบก็ count+1
            }
            float amount = (count - 1) + 3; //คิดราคานาทีแรก 3 บาท โดยลบออก1 เพิ่มไป 3
            listPhone.get(i).setAmount(amount); //set Amount ใน object ของแต่ละตัว
            System.out.println(listPhone.get(i)+" โทร :"+count+" นาที"); //แสดงผล
        }
    }

}

class Phone {

    String date;
    String startTime;
    String endTime;
    String phoneNumber;
    String promotionId;
    float amount;

    public Phone() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return promotionId + " " + date + " " + startTime + " " + endTime + " " + phoneNumber + " " + amount + "฿";
    }

}

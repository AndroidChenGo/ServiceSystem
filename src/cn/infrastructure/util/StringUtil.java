package cn.infrastructure.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class StringUtil {

	public static boolean isEmpty(String str){
		if("".equals(str)|| str==null){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isNotEmpty(String str){
		if(!"".equals(str)&&str!=null){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 将Timestamp转换为前端的datatime-local格式的String
	 * @param time
	 * @return
	 */
	public static String changeTimestampToString(Timestamp time){
		if(time==null)	return "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String startTime = df.format(time);
		startTime = startTime.replace(" ","T");
		return startTime;
	}

	/**
	 * 前端的datatime-local格式的String转为Timestamp
	 * @param time
	 * @return
	 */
	public static Timestamp changeStringToTimestamp(String time){
		if(!StringUtil.isNotEmpty(time))	return null;
		DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		time = time.replaceAll("T"," ");
		if(time.length() < 19){
			StringBuilder str = new StringBuilder(time);
			str.append(":00");
			time = str.toString();
		}
		LocalDateTime localDateTime = LocalDateTime.parse(time,df);
		Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		Timestamp timestamp = new Timestamp(date.getTime());
		return timestamp;
	}

	public static String getRandomNumberString(int length){
		String str="0123456789";
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(10);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	public static String getRandomString(int length){
		String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random=new Random();
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<length;i++){
			int number=random.nextInt(62);
			sb.append(str.charAt(number));
		}
		return sb.toString();
	}

	
}

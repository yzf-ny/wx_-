package com.liu.util;

public class ReturnMessage {

	//回复文本消息

	/**
	 *
	 * @param toUser 到哪一个用户去
	 * @param fromUser 来自哪一个用户
	 * @param msg 返回的信息
	 * @return 文本信息
	 */
	public static String text(String toUser,String fromUser,String msg) {
		String srt="<xml>" + 
				"<ToUserName><![CDATA["+toUser+"]]></ToUserName>" + 
				"<FromUserName><![CDATA["+fromUser+"]]></FromUserName>" + 
				"<CreateTime>"+System.currentTimeMillis()+"</CreateTime>" + 
				"<MsgType><![CDATA[text]]></MsgType>" + 
				"<Content><![CDATA["+msg+"]]></Content>" + 
				"</xml>";
		return srt;
	}

	//返回音乐信息
	public static String memu_music(String toUser,String FromUser,String eventkey,String title,String mus_url,String height_url)
	{
		String str=""
				+"<xml>"
				+"<ToUserName><![CDATA["+toUser+"]]></ToUserName>"
				+"<FromUserName><![CDATA["+FromUser+"]]></FromUserName>"
				+" <CreateTime>"+System.currentTimeMillis()+"</CreateTime>"
				+"<MsgType><![CDATA[music]]></MsgType>"
				+"<Music>"
				+"<Title><![CDATA["+title+"]]></Title>"
				+"<Description><![CDATA[无]]></Description>"
				+"<MusicUrl><![CDATA["+mus_url+"]]></MusicUrl>"
				+"<HQMusicUrl><![CDATA["+height_url+"]]></HQMusicUrl>"
				+"<ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>"
				+" </Music>"
				+"</xml>";
		return str;
	}


}

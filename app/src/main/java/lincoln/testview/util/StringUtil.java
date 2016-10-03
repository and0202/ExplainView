package lincoln.testview.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.TextView;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 判断字符串是否可用
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isUseable(String content) {
		boolean result = false;
		if (content != null) {
			content = replaceBlank(content);
			result = !content.equals("") && !content.equals("null");
		}
		return result;
	}

	/**
	 * 检查邮件地址是否合法
	 * 
	 * @param email
	 *            邮件地址
	 * @return
	 */
	public static boolean isEmail(String email) {
		Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(email);
		return m.find();
	}

	/**
	 * 比较两个字符串是否相等
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean isTheSame(String str1, String str2) {
		boolean result = false;
		if (isUseable(str1) && isUseable(str2) && str1.equals(str2)) {
			result = true;
		}
		return result;
	}

	/**
	 * 把内容显示到TextView上
	 * 
	 * @param string
	 * @param tView
	 */
	public static void SetTextView(Object object, TextView tView) {
		if (object == null) {
			tView.setText("");
			return;
		}
		String string = object+"";
		boolean isUseable = isUseable(string);
		if (tView == null){
			return;
		}
		if (isUseable) {
			string =replaceBlank(string);
			tView.setText(string);
		} else {
			tView.setText("");
		}
	}

	/**
	 * 把内容作为hint显示到TextView上
	 * 
	 * @param string
	 * @param tView
	 */
	public static void SetTextViewHint(Object object, TextView tView) {
		if (object == null) {
			tView.setHint("");
			return;
		}
		String string = object.toString();
		boolean isUseable = isUseable(string);
		if (isUseable) {
			tView.setHint(string);
		} else {
			tView.setHint("");
		}
	}

	// public static void setViewBg(Context context,int colorId,View view){
	// view.setBackgroundColor(context.getResources().getColor(colorId));
	// }

	/**
	 * 截取<img/>中的图片地址
	 * 
	 * 截取前： "<img src="http://mat1.gtimg.com/www/images/qq2012/qqlogo_1x.png
	 * " title="qq " alt="qq " />"
	 * 截取后：http://mat1.gtimg.com/www/images/qq2012/qqlogo_1x.png
	 */
	public static List<String> getImgUrlFromString(String htmlStr) {
		List<String> list = new ArrayList<String>();
		String img = "";
		Pattern p_image;
		Matcher m_image;

		String regEx_img = "<img.*src=(.*?)[^>]*?>"; // 图片链接地址
		p_image = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = img + "," + m_image.group();
			Matcher m = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); // 匹配src
			while (m.find()) {
				list.add(m.group(1));
			}
		}
		return list;
	}

	/**
	 * 删除<img/>标签
	 */
	public static String deleteImgUrlFromString(String htmlStr) {
		while (htmlStr.contains("<")) {
			int start = htmlStr.indexOf("<");
			int end = htmlStr.indexOf(">");
			if (start > end) {
				return htmlStr;
			}
			String subString = htmlStr.substring(start, end + 1);
			htmlStr = htmlStr.replace(subString, "");
		}
		return htmlStr;
	}

	/**
	 * 判断字符串长度是否符合规定
	 */
	public static boolean isComplianceLength(String content, int min, int max) {
		boolean result = false;
		if (isUseable(content)) {
			if (min <= content.length() && content.length() <= max) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * 判断字符串长度是否符合规定
	 */
	public static boolean isComplianceLength(String content, int max) {
		return isComplianceLength(content, 0, max);
	}

	/**
	 * 截取ISO 8601时间格式的年份
	 * 
	 * 2014-02-13T18:47:09+08:00
	 */
	public static String getIso8601Year(String iso8601Date) {
		String result = "";
		if (isUseable(iso8601Date)) {
			String[] array = iso8601Date.split("T");
			if (array.length == 2) {
				result = array[0];
			}
		}
		return result;
	}

	/**
	 * 判读url是否是http://开头得String
	 * 
	 * @return
	 */
	public static boolean isCorrectUrl(String urlString) {
		// Pattern pattern = Pattern.compile("[a-zA-z]+://[^s]*");
		Pattern pattern = Pattern.compile("[http|https]+[://]+[0-9A-Za-z:/[-]_#[?][=][.][&]]*");
		Matcher matcher = pattern.matcher(urlString);
		return matcher.find();
	}

	/**
	 * 判断content是否介于min和max之间
	 * 
	 * @param content
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isComplianceMax(int content, int min, int max) {
		boolean result = false;
		if (min <= content && content <= max) {
			result = true;
		}
		return result;
	}

	/**
	 * 获得省份信息
	 * 
	 * @param city
	 * @return
	 */
	public static String getProvience(String string) {
		String result = null;
		String[] arraysStrings = string.split("-");
		if (arraysStrings != null && arraysStrings.length == 2) {
			result = arraysStrings[0];
		}
		return result;
	}

	/**
	 * 获得城市信息
	 * 
	 * @param city
	 * @return
	 */
	public static String getCity(String string) {
		String result = null;
		String[] arraysStrings = string.split("-");
		if (arraysStrings != null && arraysStrings.length == 2) {
			result = arraysStrings[1];
		}
		return result;
	}

	/**
	 * 跳转到发送短信页面
	 * 
	 * @param activity
	 * @param phoneNumber
	 */
	public static void sendMessage(Activity activity, String phoneNumber, String content) {
		Uri smsUri = Uri.parse("sms:");
		Intent intent = new Intent(Intent.ACTION_VIEW, smsUri);
		intent.putExtra("sms_body", content);
		intent.setType("vnd.android-dir/mms-sms");
		activity.startActivity(intent);
	}

	/**
	 * 粘贴文字到剪贴板
	 */
	public static void copyToClipBoard(Context context, String content) {
		android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
		clipboardManager.setText(content);
		LogUtil.d("粘贴板：" + clipboardManager.getText());
		// }
	}

	// 获得本地String资源
	public static String getResourceString(Context context, int resourceId) {
		return context.getResources().getString(resourceId);
	}

	// 获得本地color资源
	public static int getResourceColor(Context context, int resourceId) {
		return context.getResources().getColor(resourceId);
	}

	// 邮箱隐藏规则:大于4位，隐藏第二到第三位
	public static String EmailReplaceAsterisk(String content) {
		if (!isUseable(content) || !content.contains("@")) {// 替换不符合规则，返回原内容
			return content;
		}
		String[] arrays = content.split("@");
		String replaceOld = arrays[0];
		if (replaceOld.length() > 4) {
			replaceOld = replaceOld.substring(2);
		}
		content = content.replace(replaceOld, "****");
		return content;
	}

	public static String PhoneReplaceAsterisk(String content, int start, int end) {
		if (content.length() < start || content.length() < end) {// 替换不符合规则，返回原内容
			return content;
		}
		String replaceContent = content.substring(start, end);
		content = content.replace(replaceContent, "****");
		return content;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean checkMobileNumber(String mobileNumber) {
		boolean flag = false;
		if (mobileNumber.length() == 11) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 手机号码验证,11位，不知道详细的手机号码段，只是验证开头必须是1和位数
	 * */
	public static boolean checkCellPhone(String cellPhoneNr) {
		String reg = "^[1][\\d]{10}";
		return startCheck(reg, cellPhoneNr);
	}

	public static boolean startCheck(String reg, String string) {
		boolean tem = false;

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);

		tem = matcher.matches();
		return tem;
	}

	/**
	 * MD5处理
	 * 
	 * @param input
	 * @return
	 */
	public static String md5(String input) {
		String result = input;
		if (input != null && !"".equals(input)) {
			MessageDigest md; // or "SHA-1"
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(input.getBytes());
				BigInteger hash = new BigInteger(1, md.digest());
				result = hash.toString(16);
				while (result.length() < 32) {
					result = "0" + result;
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace(); // To change body of catch statement use
										// File | Settings | File Templates.
			}

		}
		return result;
	}

	/**
	 * java去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}


	/**
	 * --------------------------------------New Version----------------------
	 */
	public static String getStringFromResId(Activity activity, int strId){
		String result = "";
		result =  activity.getResources().getString(strId);
		return  result;

	}

	/**
	 * 去掉中括号
	 * @return
     */
	public static String replaceBracket(String origin){
		return  origin.replace("[","").replace("]","").replace("【","").replace("】","");
	}

}

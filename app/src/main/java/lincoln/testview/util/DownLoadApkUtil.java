package lincoln.testview.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 版本更新类
 * 
 * @author lincoln
 * 
 */
public class DownLoadApkUtil {
	private FragmentActivity activity;
	private static final String savePath = "/sdcard/SurveyPrize/";
	private static final String saveFileName = savePath + "UpdateDemoRelease.apk";
	public boolean interceptFlag = false;

	public DownLoadApkUtil(FragmentActivity activity) {
		this.activity = activity;
	}

	/**
	 * 获得版本Code
	 * @param context
	 * @return
	 */
	public static String getVersionCode(Context context) {
		String result = "";
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			result = pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}
	/**
	 * 获得版本Name
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String result = "";
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			result = pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 用户选择更新，下载并安装APK文件
	public void downloadAndInstallApk(String apkFileUrl) {
		try {
			URL url = new URL(apkFileUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			int length = conn.getContentLength();
			InputStream is = conn.getInputStream();

			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdir();
			}
			String apkFile = saveFileName;
			File ApkFile = new File(apkFile);
			FileOutputStream fos = new FileOutputStream(ApkFile);

			int count = 0;
			byte buf[] = new byte[1024];

			do {
				int numread = is.read(buf);
				count += numread;
				LogUtil.d("下载进度：" + (((float) count / length) * 100)); // 更新进度
				if (numread <= 0) {
					// 下载完成通知安装
					installApk();
					break;
				}
				fos.write(buf, 0, numread);
			} while (!interceptFlag);// 点击取消就停止下载.

			fos.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			onFinish();
		}

	}

	// 下载完成后跳转到安装界面
	public void installApk() {
		File apkfile = new File(saveFileName);
		if (!apkfile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		activity.startActivity(i);

	}

	// 异步更新版本任务
	public class UpdateVersionTask extends AsyncTask<String, Void, Void> {
		private String apkFileUrl;

		public UpdateVersionTask(String apkFileUrl) {
			this.apkFileUrl = apkFileUrl;
		}

		@Override
		protected Void doInBackground(String... params) {
			downloadAndInstallApk(apkFileUrl);
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}

	}
	
	public void onFinish(){
		
	}
	
}

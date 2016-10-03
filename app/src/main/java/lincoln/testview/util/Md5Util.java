package lincoln.testview.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Md5Util
{
    protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    protected static MessageDigest messagedigest = null;
    static
    {
        try
        {
            messagedigest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
        }
    }

    public static String getFileMD5String(File file) throws IOException
    {
        FileInputStream in = new FileInputStream(file);
        FileChannel ch = in.getChannel();
        MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
                file.length());
        messagedigest.update(byteBuffer);
        in.close();
        return bufferToHex(messagedigest.digest());
    }

    public static String getMD5String(String s)
    {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes)
    {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[])
    {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n)
    {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++)
        {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer)
    {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

    public static boolean checkPassword(String password, String md5PwdStr)
    {
        String s = getMD5String(password);
        return s.equals(md5PwdStr);
    }

    /**
     * 获取文件的MD5值，android方法
     * @param file
     * @return
     */
    public static String getFileMD5(File file)
    {
        if (!file.isFile())
        {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try
        {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1)
            {
                digest.update(buffer, 0, len);
            }
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
      * 获取文件夹中文件的MD5值
      * 
      * @param file
      * @param listChild
      *            ;true递归子目录中的文件
      * @return
      */
    public static Map<String, String> getDirMD5(File file, boolean listChild)
    {
        if (!file.isDirectory())
        {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        String md5;
        File files[] = file.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File f = files[i];
            if (f.isDirectory() && listChild)
            {
                map.putAll(getDirMD5(f, listChild));
            }
            else
            {
                md5 = getFileMD5(f);
                if (md5 != null)
                {
                    map.put(f.getPath(), md5);
                }
            }
        }
        return map;
    }
}
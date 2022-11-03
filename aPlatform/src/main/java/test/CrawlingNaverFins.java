package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
public class CrawlingNaverFins
{
	/* 실습을 위한 상수형의 Url 주소 : 내 티스토리 포스팅 주소 */
	final static String URL_STRING = "https://uno-kim.tistory.com/215";
	/* 현재 과업(과제)구분을 위한 상수형 */
	final static String TASK_ID = "MyPost";
	/* 파일이 생성될 곳의 주소, 실경로를 설정 */
	final static String SAVE_DIR = "C:\\Users\\zhfld\\downloads";
	/* 이미지파일 확장자 */
	final static String IMG_EXT = ".png";
	/* 파일명 구분자 */
	final static String FILE_SEPARETE = "_";
	/* 버퍼사이즈 맥스로 주고 상수형으로 관리 */
	final static int BUFFER_SIZE = 4096;
	/* 이미지 태그 */
	final static String TAG_IMG = "img";
	/* 소스 속성(attribute) */
	final static String ATTR_SRC = "src";

	public static void main(String[] args) throws IOException
	{
		Document document = Jsoup.connect(URL_STRING).get();
		for (int i = 0; i < document.select(".contents_style").select(TAG_IMG).size(); i++)
		{
			String connImgUrl = document.select(".contents_style").select(TAG_IMG).get(i).attr(ATTR_SRC);
			try
			{
				HttpURLConnection connUrl = (HttpURLConnection) new URL(connImgUrl).openConnection();
				if(connUrl.getResponseCode() == HttpURLConnection.HTTP_OK)
				{
					String fileName = TASK_ID + FILE_SEPARETE + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + FILE_SEPARETE + i
							+ IMG_EXT;
					FileOutputStream fileOutputStream = new FileOutputStream(new File(SAVE_DIR, fileName));
					InputStream inputStream = connUrl.getInputStream();
					byte[] bufferArr = new byte[BUFFER_SIZE];
					int bytesData = 0;
					while ((bytesData = inputStream.read(bufferArr)) != -1)
					{
						fileOutputStream.write(bufferArr, 0, bytesData);
					}
					fileOutputStream.close();
					inputStream.close();
					/* Stream End */
					System.out.println(fileName + " 저장성공");
				}
				else
				{
					System.out.println(i + "번째 이미지 URL 접속 실패");
				}
			}
			catch (Exception e)
			{
				System.out.println(":::::: 오류 발생 ::::::");
				System.out.println(e.getMessage());
			}
		}
		System.out.println("::Test End::");
	}
}
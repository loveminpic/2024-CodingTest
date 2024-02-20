package ssafy_test;
import java.net.*;
import jdk.nashorn.internal.ir.WithNode;
import java.io.*;

public class PlayBallMinji {


	// �г����� ����ڿ� �°� ������ �ּ���.
		static final String NICKNAME = "����09��_�̹���";
		
		// ��Ÿ���� ���α׷��� ���ÿ��� ������ ��� �������� �ʽ��ϴ�.
		static final String HOST = "127.0.0.1";

		// ��Ÿ���� ���α׷��� ����� �� ����ϴ� �ڵ尪���� �������� �ʽ��ϴ�.
		static final int PORT = 1447;
		static final int CODE_SEND = 9901;
		static final int CODE_REQUEST = 9902;
		static final int SIGNAL_ORDER = 9908;
		static final int SIGNAL_CLOSE = 9909;

		// ���� ȯ�濡 ���� ����Դϴ�.
		static final int TABLE_WIDTH = 254;
		static final int TABLE_HEIGHT = 127;
		static final int NUMBER_OF_BALLS = 6;
		static final int[][] HOLES = { { 0, 0 }, { 127, 0 }, { 254, 0 }, { 0, 127 }, { 127, 127 }, { 254, 127 } };

		public static void main(String[] args) {

			Socket socket = null;
			String recv_data = null;
			byte[] bytes = new byte[1024];
			float[][] balls = new float[NUMBER_OF_BALLS][2];
			int order = 0;

			try {
				socket = new Socket();
				System.out.println("Trying Connect: " + HOST + ":" + PORT);
				socket.connect(new InetSocketAddress(HOST, PORT));
				System.out.println("Connected: " + HOST + ":" + PORT);

				InputStream is = socket.getInputStream();
				OutputStream os = socket.getOutputStream();

				String send_data = CODE_SEND + "/" + NICKNAME + "/";
				bytes = send_data.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("Ready to play!\n--------------------");

				while (socket != null) {

					// Receive Data
					bytes = new byte[1024];
					int count_byte = is.read(bytes);
					recv_data = new String(bytes, 0, count_byte, "UTF-8");
					System.out.println("Data Received: " + recv_data);

					// Read Game Data
					String[] split_data = recv_data.split("/");
					int idx = 0;
					try {
						for (int i = 0; i < NUMBER_OF_BALLS; i++) {
							for (int j = 0; j < 2; j++) {
								balls[i][j] = Float.parseFloat(split_data[idx++]);
							}
						}
					} catch (Exception e) {
						bytes = (CODE_REQUEST + "/" + CODE_REQUEST).getBytes("UTF-8");
						os.write(bytes);
						os.flush();
						System.out.println("Received Data has been currupted, Resend Requested.");
						continue;
					}

					// Check Signal for Player Order or Close Connection
					if (balls[0][0] == SIGNAL_ORDER) {
						order = (int)balls[0][1];
						System.out.println("\n* You will be the " + (order == 1 ? "first" : "second") + " player. *\n");
						continue;
					} else if (balls[0][0] == SIGNAL_CLOSE) {
						break;
					}

					// Show Balls' Position
					for (int i = 0; i < NUMBER_OF_BALLS; i++) {
						System.out.println("Ball " + i + ": " + balls[i][0] + ", " + balls[i][1]);
					}

					float angle = 0.0f;
					float power = 0.0f;

					//////////////////////////////
					// �� ���� ��Ÿ���ǿ� ����Ͽ� �����͸� �ְ� �ޱ� ���� �ۼ��� �κ��̹Ƿ� �����ϸ� �ȵ˴ϴ�.
					//
					// ��� ���Ű��� ����, �迭���� Ȯ���� �� �ֽ��ϴ�.
					//   - order: 1�� ��� ����, 2�� ��� �İ��� �ǹ�
					//   - balls[][]: ��Ÿ���� ������ �����ؼ� �� ���� ��ǥ�� �迭�� ����
					//     ��) balls[0][0]: �� ���� X��ǥ
					//         balls[0][1]: �� ���� Y��ǥ
					//         balls[1][0]: 1�� ���� X��ǥ
					//         balls[4][0]: 4�� ���� X��ǥ
					//         balls[5][0]: ������ ��ȣ(8��) ���� X��ǥ
					
					// ���⼭���� �ڵ带 �ۼ��ϼ���.
					// �Ʒ��� �ִ� ���� ���÷� �ۼ��� �ڵ��̹Ƿ� �����Ӱ� ������ �� �ֽ��ϴ�.
					




					float whiteBall_x = balls[0][0];
					float whiteBall_y = balls[0][1];
					// whiteBall_x, whiteBall_y: �� ���� X, Y��ǥ�� ��Ÿ���� ���� ����� ����
		
					
					float targetBall_x = balls[1][0];
					float targetBall_y = balls[1][1];
					// targetBall_x, targetBall_y: �������� X, Y��ǥ�� ��Ÿ���� ���� ����� ����
					out : for(int i = 1; i < balls.length; i++) {
						for(int j = 0; j < 1; j++) {

							if(balls[i][0] >= 0) {
								targetBall_x = balls[i][0];
								targetBall_y = balls[i][1];
								break out;
							}
						}
					}
					

					// width, height: �������� �� ���� X��ǥ ���� �Ÿ�, Y��ǥ ���� �Ÿ�
					float width = Math.abs(targetBall_x - whiteBall_x);
					float height = Math.abs(targetBall_y - whiteBall_y);

					// radian: width�� height�� �� ������ �ϴ� �����ﰢ���� ������ ���� ���
					//   - 1radian = 180 / PI (��)
					//   - 1�� = PI / 180 (radian)
					// angle : ��ũź��Ʈ�� ���� ���� radian�� degree�� ȯ���� ���
					double radian = height > 0? Math.atan(width / height): 0;
					angle = (float) ((180.0 / Math.PI) * radian);
					
					
					// �������� �����¿�� �������� ��ġ���� �� ���� �Է�
					if (whiteBall_x == targetBall_x)
					{
						if (whiteBall_y < targetBall_y)
						{
							angle = 0;
						}
						else
						{
							angle = 180;
						}
					}
					else if (whiteBall_y ==targetBall_y)
					{
						if (whiteBall_x < targetBall_x)
						{
							angle = 90;
						}
						else
						{
							angle = 270;
						}
					}
	
					// 2r 
					double r = 5.73;

					//1��и�
					if (whiteBall_x < targetBall_x && whiteBall_y < targetBall_y)
						{
							// �������� Ÿ�ٰ��� �Ÿ� 
							double width2 = Math.abs(HOLES[5][0] - targetBall_x);
							double height2 = Math.abs(HOLES[5][1] - targetBall_y);
							
							// �������� �Ͼ���� �Ÿ�
							double width3 = Math.abs(HOLES[5][0] - whiteBall_x);
							double height3 =  Math.abs(HOLES[5][1] - whiteBall_y);
							
							double a = Math.sqrt(Math.pow(2, width3)+ Math.pow(2, height3));
							double b =  Math.sqrt(Math.pow(2, width2)+ Math.pow(2, height2));
							double c = Math.sqrt(Math.pow(2, width)+ Math.pow(2, height));
							
							
							double daDegree = Math.toDegrees(Math.acos(((Math.pow(2, a)+Math.pow(2, b)-Math.pow(2, c)) / (2 * a*b))));
							double d = Math.sqrt(Math.pow(2, a) + Math.pow(2, b+r) - 2 *a*(b+r) * Math.cos(daDegree));
							double naDegree = Math.toDegrees(Math.acos(Math.pow(2, a) + Math.pow(2, d) - (Math.pow(2, b+r)) /(2*a*d)));
						
							radian = Math.atan(height / width);
							angle = (float) (((180.0 / Math.PI) * radian) + Math.toDegrees(naDegree));
							System.out.println("1");
							System.out.println(angle);
					}
					// 2��и�
					else if (whiteBall_x > targetBall_x && whiteBall_y < targetBall_y)
					{
						double width2 = Math.abs(HOLES[3][0] - targetBall_x);
						double height2 = Math.abs(HOLES[3][1] - targetBall_y);
						
						// �������� �Ͼ���� �Ÿ�
						double width3 = Math.abs(HOLES[3][0] - whiteBall_x);
						double height3 =  Math.abs(HOLES[3][1] - whiteBall_y);
						
						double a = Math.sqrt(Math.pow(2, width3)+ Math.pow(2, height3));
						double b =  Math.sqrt(Math.pow(2, width2)+ Math.pow(2, height2));
						double c = Math.sqrt(Math.pow(2, width)+ Math.pow(2, height));
						
						
						double daDegree = Math.toDegrees(Math.acos(((Math.pow(2, a)+Math.pow(2, b)-Math.pow(2, c)) / (2 * a*b))));
						double d = Math.sqrt(Math.pow(2, a) + Math.pow(2, b+r) - 2 *a*(b+r) * Math.cos(daDegree));
						double naDegree = Math.toDegrees(Math.acos(Math.pow(2, a) + Math.pow(2, d) - (Math.pow(2, b+r)) /(2*a*d)));
					
						radian = Math.atan(height / width);
						angle = (float) (((180.0 / Math.PI) * radian) + Math.toDegrees(naDegree));
						System.out.println("1");
						System.out.println(angle);
					}
					
					// �������� �� ���� �߽����� 3��и鿡 ��ġ���� �� ������ ����
					else if (whiteBall_x > targetBall_x && whiteBall_y > targetBall_y)
					{
						// �������� Ÿ�ٰ��� �Ÿ� 
						double width2 = Math.abs(HOLES[0][0] - targetBall_x);
						double height2 = Math.abs(HOLES[0][1] - targetBall_y);
						
						// �������� �Ͼ���� �Ÿ�
						double width3 = Math.abs(HOLES[0][0] - whiteBall_x);
						double height3 =  Math.abs(HOLES[0][1] - whiteBall_y);
						
						double a = Math.sqrt(Math.pow(2, width3)+ Math.pow(2, height3));
						double b =  Math.sqrt(Math.pow(2, width2)+ Math.pow(2, height2));
						double c = Math.sqrt(Math.pow(2, width)+ Math.pow(2, height));
						
						
						double daDegree = Math.toDegrees(Math.acos(((Math.pow(2, a)+Math.pow(2, b)-Math.pow(2, c)) / (2 * a*b))));
						double d = Math.sqrt(Math.pow(2, a) + Math.pow(2, b+r) - 2 *a*(b+r) * Math.cos(daDegree));
						double naDegree = Math.toDegrees(Math.acos(Math.pow(2, a) + Math.pow(2, d) - (Math.pow(2, b+r)) /(2*a*d)));
					
					
						radian = Math.atan(height / width);
						angle = (float) (((180.0 / Math.PI) * radian) + 180 +Math.toDegrees(naDegree));

						System.out.println("1");
						System.out.println(angle);
					}

					// �������� �� ���� �߽����� 4��и鿡 ��ġ���� �� ������ ����
					else if (whiteBall_x < targetBall_x && whiteBall_y > targetBall_y)
					{// �������� Ÿ�ٰ��� �Ÿ� 
						double width2 = Math.abs(HOLES[2][0] - targetBall_x);
						double height2 = Math.abs(HOLES[2][1] - targetBall_y);
						
						// �������� �Ͼ���� �Ÿ�
						double width3 = Math.abs(HOLES[2][0] - whiteBall_x);
						double height3 =  Math.abs(HOLES[2][1] - whiteBall_y);
						
						double a = Math.sqrt(Math.pow(2, width3)+ Math.pow(2, height3));
						double b =  Math.sqrt(Math.pow(2, width2)+ Math.pow(2, height2));
						double c = Math.sqrt(Math.pow(2, width)+ Math.pow(2, height));
						
						
						double daDegree = Math.toDegrees(Math.acos(((Math.pow(2, a)+Math.pow(2, b)-Math.pow(2, c)) / (2 * a*b))));
						double d = Math.sqrt(Math.pow(2, a) + Math.pow(2, b+r) - 2 *a*(b+r) * Math.cos(daDegree));
						double naDegree = Math.toDegrees(Math.acos(Math.pow(2, a) + Math.pow(2, d) - (Math.pow(2, b+r)) /(2*a*d)));
					
						radian = Math.atan(height / width);
						angle = (float) (((180.0 / Math.PI) * radian) +270 + Math.toDegrees(naDegree));
						System.out.println("1");
						System.out.println(angle);
					}

			
					
					// distance: �� ��(��ǥ) ������ �Ÿ��� ���
					double distance = Math.sqrt((width * width) + (height * height));

					// power: �Ÿ� distance�� ���� ���� ���⸦ ���
					power = (float) distance;
					System.out.println(power);





					// �־��� ������(���� ��ǥ)�� Ȱ���Ͽ� �� ���� ���� ���� �����ϰ� ����,
					// ������ �ڵ忡�� ��Ÿ���Ƿ� ���� ���� �ڵ����� �÷��̸� �����ϰ� �մϴ�.
					//   - angle: �� ���� ������ ���� ����(����)
					//   - power: �� ���� ���� ���� ����
					// 
					// �� �� ������ ���� power�� 100�� �ʰ��� �� ������,
					// power = 0�� ��� ���� ����(0)�̹Ƿ� �ƹ��� ������ ��Ÿ���� �ʽ��ϴ�.
					//
					// �Ʒ��� ��Ÿ���ǿ� ����ϴ� ������ �κ��̹Ƿ� �����ϸ� �ȵ˴ϴ�.
					//////////////////////////////

					String merged_data = angle + "/" + power + "/";
					bytes = merged_data.getBytes("UTF-8");
					os.write(bytes);
					os.flush();
					System.out.println("Data Sent: " + merged_data);
				}

				os.close();
				is.close();
				socket.close();
				System.out.println("Connection Closed.\n--------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}

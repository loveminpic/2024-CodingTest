package ssafy_test;
import java.net.*;
import java.io.*;

public class A0009_1149381 {

		// �г����� ����ڿ� �°� ������ �ּ���.
		static final String NICKNAME = "����09��_������";

		// ��Ÿ���� ���α׷��� ���ÿ��� ������ ��� �������� �ʽ��ϴ�.
		static final String HOST = "70.12.103.40";

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
		static final int[][] HOLES = { { -2, -2 }, { 129, -2 }, { 256, -2 }, { -2, 129 }, { 129, 129 }, { 256, 129 } };

		static final double R = 5.73; // ����
		//�� �� ������ �Ÿ�
		public static double distance(float a, float b) {
		    return Math.sqrt((a*a)+(b*b));
		}

		public static float radianToAngle (double radian) {
		    return (float) ((180.0 / Math.PI) * radian);
		}

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

		            double radian = 0.0;//



		            // whiteBall_x, whiteBall_y: �� ���� X, Y��ǥ�� ��Ÿ���� ���� ����� ����
		            float whiteBall_x = balls[0][0];
		            float whiteBall_y = balls[0][1];


		            // targetBall_x, targetBall_y: �������� X, Y��ǥ�� ��Ÿ���� ���� ����� ����
		            int num=0;

		            for (int i=1; i<6; i++) {
		                if (order==1) {
		                    if (i==1 || i==3 || i==5)  {
		                        if (balls[i][0]!=-1 && balls[i][1]!=-1) {
		                            num=i;
		                            break;
		                        }
		                    }
		                }
		                else {
		                    if (i==2 || i==4 || i==5) {
		                        if (balls[i][0]!=-1 && balls[i][1]!=-1) { 
		                            num=i;
		                            break;
		                        }
		                    }
		                }
		            }


		            float targetBall_x = balls[num][0];
		            float targetBall_y = balls[num][1];
		            if (targetBall_x==-1 && targetBall_y==-1)
		                continue;

		            int[] hole= new int[2];
		            double minDis = Double.MAX_VALUE;
		            for (int th=0; th<6; th++) {
		                double tmp = distance(targetBall_x-HOLES[th][0], targetBall_y-HOLES[th][1]);
		                if (minDis > tmp) {
		                    minDis = tmp;
		                    hole[0] = HOLES[th][0];
		                    hole[1] = HOLES[th][1];
		                }
		            }

		            System.out.println("num: "+num);
		            System.out.println("wX:"+whiteBall_x+" wY:"+whiteBall_y);
		            System.out.println("tX:"+targetBall_x+" tY"+targetBall_y);
		            System.out.println("����� Ȧ: "+hole[0]+" "+hole[1]);
		            /*
		            // width, height: �������� �� ���� X��ǥ ���� �Ÿ�, Y��ǥ ���� �Ÿ�
		            float width = Math.abs(targetBall_x - whiteBall_x);
		            float height = Math.abs(targetBall_y - whiteBall_y);

		            // radian: width�� height�� �� ������ �ϴ� �����ﰢ���� ������ ���� ���
		            //   - 1radian = 180 / PI (��)
		            //   - 1�� = PI / 180 (radian)
		            // angle : ��ũź��Ʈ�� ���� ���� radian�� degree�� ȯ���� ���
		            double radian = height > 0? Math.atan(width / height): 0;
		            angle = (float) ((180.0 / Math.PI) * radian);
		             */
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


		            double a = distance(whiteBall_x-hole[0],whiteBall_y-hole[1]);
		            double b = distance(hole[0]-targetBall_x, hole[1]-targetBall_y);
		            double c = distance(whiteBall_x-targetBall_x, whiteBall_y-targetBall_y);
		            float a_b_do = (float)Math.acos( ((a*a)+(b*b)-(c*c)) / (2*a*b));
		            double d = Math.sqrt( (a*a)+((b+R)*(b+R))-(2*a*(b+R)*Math.cos(a_b_do)) );
		            float a_d_do =(float) Math.acos( ((a*a)+(d*d)-((b+R)*(b+R)) ) / (2*a*d) );
		            System.out.println("a:"+a+" b:"+b+" c:"+c+" ab_do:"+a_b_do+" d:"+d+" ad_do:"+a_d_do);
		            //�������� �� ���� �߽����� 1��и鿡 ��ġ���� �� ������ ����
		            if (whiteBall_x < targetBall_x && whiteBall_y < targetBall_y)
		            {
		                double atan=0; // ����
		                if (Math.atan((hole[0]-whiteBall_x)/(hole[1]-whiteBall_y)) > Math.atan((targetBall_x-whiteBall_x)/(targetBall_y-whiteBall_y))) {
		                    atan = Math.atan( (hole[1]-whiteBall_y) / (hole[0]-whiteBall_x) );
		                    angle = 90-radianToAngle(atan+a_d_do);
		                    System.out.println("��1��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else {
		                    atan = Math.atan( (hole[0]-whiteBall_x) / (hole[1]-whiteBall_y) );
		                    angle = radianToAngle(atan+a_d_do);
		                    System.out.println("��1��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		            }
		            // 2��и鿡 ��ġ���� �� ������ ����
		            else if (whiteBall_x > targetBall_x && whiteBall_y < targetBall_y)
		            {
		                double atan=0; // ����
		                if (Math.atan((hole[0]-whiteBall_x)/(hole[1]-whiteBall_y)) > Math.atan((whiteBall_x-targetBall_x)/(targetBall_y-whiteBall_y))) {
		                    atan = Math.atan( (hole[1]-whiteBall_y) / (whiteBall_x-hole[0]) );
		                    angle = radianToAngle(atan+a_d_do)+270;
		                    System.out.println("����2��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else {
		                    atan = Math.atan( (whiteBall_x-hole[0]) / (hole[1]-whiteBall_y) );
		                    angle = 360-radianToAngle(atan-a_d_do);
		                    System.out.println("ŭ2��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }

		            }
		            // �������� �� ���� �߽����� 3��и鿡 ��ġ���� �� ������ ����
		            else if (whiteBall_x > targetBall_x && whiteBall_y > targetBall_y)
		            {
		                double atan=0; // ����
		                if (Math.atan((whiteBall_y-hole[1])/(whiteBall_x-hole[0])) > Math.atan((targetBall_y-hole[1])/(targetBall_x-hole[0]))) {
		                    atan = Math.atan( (whiteBall_x-hole[0]) / (whiteBall_y-hole[1]) );
		                    angle = radianToAngle(atan+a_d_do)+180;
		                    System.out.println("��3��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else { //ŭ
		                    atan = Math.atan( (whiteBall_y-hole[1]) / (whiteBall_x-hole[0]) );
		                    angle = radianToAngle(atan+a_d_do)+180;
		                    System.out.println("ŭ3��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		            }

		            // �������� �� ���� �߽����� 4��и鿡 ��ġ���� �� ������ ����
		            else if (whiteBall_x < targetBall_x && whiteBall_y > targetBall_y)
		            {
		                double atan=0; // ����
		                if (Math.atan((whiteBall_y-hole[1])/(hole[0]-whiteBall_x)) > Math.atan((targetBall_y-hole[1])/(hole[0]-targetBall_x))) {
		                    atan = Math.atan((hole[1]-whiteBall_y) / (hole[0]-whiteBall_x));
		                    angle = radianToAngle(atan-a_d_do)+180;
		                    System.out.println("��4��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else { //ŭ
		                    //atan = Math.atan((hole[0]-whiteBall_x) / (hole[1]-whiteBall_y));
		                    atan = Math.atan((hole[1]-whiteBall_y) / (hole[0]-whiteBall_x));
		                    angle = radianToAngle(atan-a_d_do)+180;
		                    System.out.println("ŭ4��и�:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));

		                }

		            }

		            // distance: �� ��(��ǥ) ������ �Ÿ��� ���
		            //double distance = Math.sqrt((width * width) + (height * height));

		            // power: �Ÿ� distance�� ���� ���� ���⸦ ���
		            power = (float) d;
		            //whiteBall_x = targetBall_x;
		            //whiteBall_y = targetBall_y;





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



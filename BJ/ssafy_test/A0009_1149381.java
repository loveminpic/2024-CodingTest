package ssafy_test;
import java.net.*;
import java.io.*;

public class A0009_1149381 {

		// 닉네임을 사용자에 맞게 변경해 주세요.
		static final String NICKNAME = "서울09반_박정의";

		// 일타싸피 프로그램을 로컬에서 실행할 경우 변경하지 않습니다.
		static final String HOST = "70.12.103.40";

		// 일타싸피 프로그램과 통신할 때 사용하는 코드값으로 변경하지 않습니다.
		static final int PORT = 1447;
		static final int CODE_SEND = 9901;
		static final int CODE_REQUEST = 9902;
		static final int SIGNAL_ORDER = 9908;
		static final int SIGNAL_CLOSE = 9909;

		// 게임 환경에 대한 상수입니다.
		static final int TABLE_WIDTH = 254;
		static final int TABLE_HEIGHT = 127;
		static final int NUMBER_OF_BALLS = 6;
		static final int[][] HOLES = { { -2, -2 }, { 129, -2 }, { 256, -2 }, { -2, 129 }, { 129, 129 }, { 256, 129 } };

		static final double R = 5.73; // 지름
		//두 점 사이의 거리
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
		            // 이 위는 일타싸피와 통신하여 데이터를 주고 받기 위해 작성된 부분이므로 수정하면 안됩니다.
		            //
		            // 모든 수신값은 변수, 배열에서 확인할 수 있습니다.
		            //   - order: 1인 경우 선공, 2인 경우 후공을 의미
		            //   - balls[][]: 일타싸피 정보를 수신해서 각 공의 좌표를 배열로 저장
		            //     예) balls[0][0]: 흰 공의 X좌표
		            //         balls[0][1]: 흰 공의 Y좌표
		            //         balls[1][0]: 1번 공의 X좌표
		            //         balls[4][0]: 4번 공의 X좌표
		            //         balls[5][0]: 마지막 번호(8번) 공의 X좌표

		            // 여기서부터 코드를 작성하세요.
		            // 아래에 있는 것은 샘플로 작성된 코드이므로 자유롭게 변경할 수 있습니다.

		            double radian = 0.0;//



		            // whiteBall_x, whiteBall_y: 흰 공의 X, Y좌표를 나타내기 위해 사용한 변수
		            float whiteBall_x = balls[0][0];
		            float whiteBall_y = balls[0][1];


		            // targetBall_x, targetBall_y: 목적구의 X, Y좌표를 나타내기 위해 사용한 변수
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
		            System.out.println("가까운 홀: "+hole[0]+" "+hole[1]);
		            /*
		            // width, height: 목적구와 흰 공의 X좌표 간의 거리, Y좌표 간의 거리
		            float width = Math.abs(targetBall_x - whiteBall_x);
		            float height = Math.abs(targetBall_y - whiteBall_y);

		            // radian: width와 height를 두 변으로 하는 직각삼각형의 각도를 구한 결과
		            //   - 1radian = 180 / PI (도)
		            //   - 1도 = PI / 180 (radian)
		            // angle : 아크탄젠트로 얻은 각도 radian을 degree로 환산한 결과
		            double radian = height > 0? Math.atan(width / height): 0;
		            angle = (float) ((180.0 / Math.PI) * radian);
		             */
		            // 목적구가 상하좌우로 일직선상에 위치했을 때 각도 입력
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
		            //목적구가 흰 공을 중심으로 1사분면에 위치했을 때 각도를 재계산
		            if (whiteBall_x < targetBall_x && whiteBall_y < targetBall_y)
		            {
		                double atan=0; // 작음
		                if (Math.atan((hole[0]-whiteBall_x)/(hole[1]-whiteBall_y)) > Math.atan((targetBall_x-whiteBall_x)/(targetBall_y-whiteBall_y))) {
		                    atan = Math.atan( (hole[1]-whiteBall_y) / (hole[0]-whiteBall_x) );
		                    angle = 90-radianToAngle(atan+a_d_do);
		                    System.out.println("직1사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else {
		                    atan = Math.atan( (hole[0]-whiteBall_x) / (hole[1]-whiteBall_y) );
		                    angle = radianToAngle(atan+a_d_do);
		                    System.out.println("직1사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		            }
		            // 2사분면에 위치했을 때 각도를 재계산
		            else if (whiteBall_x > targetBall_x && whiteBall_y < targetBall_y)
		            {
		                double atan=0; // 작음
		                if (Math.atan((hole[0]-whiteBall_x)/(hole[1]-whiteBall_y)) > Math.atan((whiteBall_x-targetBall_x)/(targetBall_y-whiteBall_y))) {
		                    atan = Math.atan( (hole[1]-whiteBall_y) / (whiteBall_x-hole[0]) );
		                    angle = radianToAngle(atan+a_d_do)+270;
		                    System.out.println("작직2사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else {
		                    atan = Math.atan( (whiteBall_x-hole[0]) / (hole[1]-whiteBall_y) );
		                    angle = 360-radianToAngle(atan-a_d_do);
		                    System.out.println("큼2사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }

		            }
		            // 목적구가 흰 공을 중심으로 3사분면에 위치했을 때 각도를 재계산
		            else if (whiteBall_x > targetBall_x && whiteBall_y > targetBall_y)
		            {
		                double atan=0; // 작음
		                if (Math.atan((whiteBall_y-hole[1])/(whiteBall_x-hole[0])) > Math.atan((targetBall_y-hole[1])/(targetBall_x-hole[0]))) {
		                    atan = Math.atan( (whiteBall_x-hole[0]) / (whiteBall_y-hole[1]) );
		                    angle = radianToAngle(atan+a_d_do)+180;
		                    System.out.println("작3사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else { //큼
		                    atan = Math.atan( (whiteBall_y-hole[1]) / (whiteBall_x-hole[0]) );
		                    angle = radianToAngle(atan+a_d_do)+180;
		                    System.out.println("큼3사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		            }

		            // 목적구가 흰 공을 중심으로 4사분면에 위치했을 때 각도를 재계산
		            else if (whiteBall_x < targetBall_x && whiteBall_y > targetBall_y)
		            {
		                double atan=0; // 작음
		                if (Math.atan((whiteBall_y-hole[1])/(hole[0]-whiteBall_x)) > Math.atan((targetBall_y-hole[1])/(hole[0]-targetBall_x))) {
		                    atan = Math.atan((hole[1]-whiteBall_y) / (hole[0]-whiteBall_x));
		                    angle = radianToAngle(atan-a_d_do)+180;
		                    System.out.println("작4사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));
		                }
		                else { //큼
		                    //atan = Math.atan((hole[0]-whiteBall_x) / (hole[1]-whiteBall_y));
		                    atan = Math.atan((hole[1]-whiteBall_y) / (hole[0]-whiteBall_x));
		                    angle = radianToAngle(atan-a_d_do)+180;
		                    System.out.println("큼4사분면:"+ radianToAngle(atan)+" / "+radianToAngle(a_d_do));

		                }

		            }

		            // distance: 두 점(좌표) 사이의 거리를 계산
		            //double distance = Math.sqrt((width * width) + (height * height));

		            // power: 거리 distance에 따른 힘의 세기를 계산
		            power = (float) d;
		            //whiteBall_x = targetBall_x;
		            //whiteBall_y = targetBall_y;





		            // 주어진 데이터(공의 좌표)를 활용하여 두 개의 값을 최종 결정하고 나면,
		            // 나머지 코드에서 일타싸피로 값을 보내 자동으로 플레이를 진행하게 합니다.
		            //   - angle: 흰 공을 때려서 보낼 방향(각도)
		            //   - power: 흰 공을 때릴 힘의 세기
		            // 
		            // 이 때 주의할 점은 power는 100을 초과할 수 없으며,
		            // power = 0인 경우 힘이 제로(0)이므로 아무런 반응이 나타나지 않습니다.
		            //
		            // 아래는 일타싸피와 통신하는 나머지 부분이므로 수정하면 안됩니다.
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



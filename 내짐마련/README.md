# 내짐마련
<b>디자인 패턴을 이용한 이삿짐 서비스를 관리하고 차량을 추적하여 사용자의 신뢰를 높일 수 있는 프로젝트를 설계하고 개발함. Command 패턴을 이용하여 물품 갯수를 관리할 수 있는 기능을 구현함.</b>

### 프로젝트 목적과 내용
이삿짐 익스프레스 서비스 작업을 고객과 센터 직원의 입장에서 좀 더 편리하게 사용하기 위해 간편한 GUI 화면과 시스템에 적합한 구조로 설계하여 각 사용자의 요구사항이 반영된 이삿짐 정보 관리 시스템(이삿짐센터 정보 확인 프로세스, 짐 정보 관리 프로세스, 이삿짐 배송 조회 프로세스, 분실물 관리 기능을 제공한다)을 제공한다.

### 프로젝트 기간
2018.04.18 ~ 2018.06.07

### 기술 스택
- MySQL
- Smart Git
- Desing Pattern

### 프로젝트
<p>- Command 패턴 적용</br>
</br>
## Center.java

```java
public class Center {
  private Command command; //커맨드를 집어넣음

  //슬롯을 가지고 제어 할 명령을 설정하기 위한
  public void setCommand(Command command) {
    this.command = command;
  }

  //주문을 받으면 이 메소드 호출
  public void takeOrder() {
    command.execute();
  }
}
```
</br>
## Center.java

```java
Center center = new Center(); //center 변수가 인보커
                              //필요한 작업을 요청함

//Itembaggage displayone = new ItemBaggage(); // dis
QuantityBaggage displaytwo = new QuantityBaggage();
ConfirmBaggage displaythree = new ConfirmBaggage();
```
</br>
## BaggageCommand.java

```java
public BaggageCommand (QuantityBaggage quantity, ConfirmBaggage confirm) {
  //this.displayone = Item;
  this.displaytwo = quantity;
  this.displaythree = confirm;
```
</br>
## Check.java

```java
public void Check() throws I0Exception {
  String a;

  BufferedWriter bu = new BufferedWriter(new FileWriter(outFile));

  File inFile = new File("fileW₩BaggageMove.txt");

  Scanner scan = new Scanner(System.in, "euc-kr");

  System.out.print In("물품 확인: ");
  String item;
  for (int i=0; i<arraylist.size); i++) {
    System.out.print(arraylist.get(i) + " ==");
    item = scan.nextLine();
    bw.write(arraylist.get(|) + "==" + item);
    bw.newLine();
    bw.flush();
  }
  Progress();
}
```
</br>
## Progress.java

```java
public void Progress() throws I0Exception {
  BufferedReader br=null; //null로 지정해놨다가

  int count = 0;
  try {
    br = new BufferedReader(new FileReader(outFile));

  } catch (FileNotFoundException ex) {
    Logger.getLogger(ConfirmBaggage.class.getName).log(Level.SEVERE, null, ex);
  }
  String line:
  // line = br.readline()를 읽어들여 X나 x가 나왔을경우 다음단계로 넘어가지 못하게
  while ((line = br.readLine() != null) {
    if (line.contains("X") || line.contains("x")) {
      counttt;
    }
  }
  if (count == 0) { //모든 물품이 0로 처리 되었을경우 count가 0값이 되어 성공하고
    System.out.printIn{"성공하였습니다. 다음단계로 진행합니다.");
  } else {
    System.out .printIn("진행중 입니다....");
  }
}
```
</br>
## QuantityBaggage.java

```java
class QuantityBaggage {

  public void quantityBaggage throws I0Exception {

  ItemBaggage itemnum = new ItemBaggage();

  Scanner scan = new Scanner(System.in);
  int num;

  System. out.print"물품 개수를 입력해 주세요: ");
  num = scan.nextInt();
  itemnum.NumberCheck(num);
  itemnum.itemBaggage();
}
```
</br>
## ConfirmBaggage.java

```java
public void confirmBaggage throws I0Exception {

  BufferedReader br = null;

  try {
    br = new BufferedReader(new FileReader(outFile));

    String line;

    while((line = br.readLine()) |= null) {
      //System.out.printIn(line);
      arraylist.add(line);
    }
    Check();
```
</br>
## QuantityBaggage.java

```java
public void itemBaggage() throws I0Exception {

  BaggageFile(); //BaggageFile(); 로 파일을 생성하고

  Scanner scan = new Scanner(System.in);
  String item;

  System. out.printIn("물품내용을 입력해 주세요 : ");

  File Baggagefile = new File("fileWWBaggageMove.txt");
    BufferedWriter bw = null;//매들 널값으로 만들어주고
  try {
    bw=new BufferedWriter(new Filewriter(Baggageflle)); //bw값을 파일에 저장

  } catch (I0Exception ex) {
    Logger.getLogger(ItemBaggage.class.getName().log(Level.SEVERE, null, ex);
  }
  for(int i =o:isnum:i++)1
    System.out.prInt(i+1+"번째 :"); //물품의 개수를 입력받으면 내용을 입력할 수 있다.
    Item = scan.nextLine();

    bw.write(i+1+"번째 물품:"+Item); //들어온 물품개수만큼 +1번째 물품
    bw.newLine();
    bw.flush();
```
</br>
<p>- 결과화면</br>
<img src="https://github.com/hyeon-bs/Portfolio/blob/fc90db4848bc9596eb064dfa90f08477949cf240/%E1%84%82%E1%85%A2%E1%84%8C%E1%85%B5%E1%86%B7%E1%84%86%E1%85%A1%E1%84%85%E1%85%A7%E1%86%AB/static/%E1%84%80%E1%85%A7%E1%86%AF%E1%84%80%E1%85%AA1.png" width="550" height="400" /></p>
<img src="https://github.com/hyeon-bs/Portfolio/blob/fc90db4848bc9596eb064dfa90f08477949cf240/%E1%84%82%E1%85%A2%E1%84%8C%E1%85%B5%E1%86%B7%E1%84%86%E1%85%A1%E1%84%85%E1%85%A7%E1%86%AB/static/%E1%84%80%E1%85%A7%E1%86%AF%E1%84%80%E1%85%AA2.png" width="650" height="400" /></p>

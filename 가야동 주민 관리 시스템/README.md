# 가야동 주민 관리 시스템
<b>주민 센터에서 주민들의 정보를 조회하여 필요한 정보와 해당되는 복지혜택을 제공 및 관리하는 시스템 설계 및 개발. 개인정보와 장애인 혜택 대상을 조회 할 수 있는 기능 구현과 필요한 테이블 레코드 작성을 설계함.</b>

### 프로젝트 목적과 내용
주민 센터에서 주민들의 정보를 조회하여 필요한 정보와 해당되는 복지혜택을 제공 및 관리하는 시스템 설계 및 개발. 개인정보와 장애인 혜택 대상을 조회 할 수 있는 기능 구현과 필요한 테이블 레코드 작성을 설계함.
    
### 프로젝트 기간
2019.04.11 ~ 2019.06.04

### 기술 스택
- C++</br>
- MySQL</br>

### 프로젝트
<p>▼ main</br>
<p>- 메인화면</br><img src="https://github.com/hyeon-bs/Portfolio/blob/838ab8d72ed4d807fbb56741d234e5f2ef23462b/%E1%84%80%E1%85%A1%E1%84%8B%E1%85%A3%E1%84%83%E1%85%A9%E1%86%BC%20%E1%84%8C%E1%85%AE%E1%84%86%E1%85%B5%E1%86%AB%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7/static/main_%E1%84%92%E1%85%AA%E1%84%86%E1%85%A7%E1%86%AB.jpg" width="650" height="400" /></p>

<p>- 화면표시</br>
<pre>
<code>
void print_screen (char name [l)
{
    FILE *fp;
    char line [100];
    if ( (fp = fopen (fname, "r")) = NULL D{
        printf("file open error\n");
        getch();
        exit (-1);
    }
    while (1)
    {
        if( fgets(line, 99, fp) == NULL){
            break;
        }
        printf("%s", line);
    }
    fclose (fp);
}
</code>
</pre>
</p>

<p>- 데이터베이스 연결</br>
<pre>
<code>
EXEC SQL BEGIN DECLARE SECTION;
    VARCHAR uid [80];
    VARCHAR pwdl201;
EXEC SQL END DECLARE SECTION;
  
#define getch( ) _getch()
 
void main()
{
    DB_connect();
    Show_tuple();
    P_search();
    Update_pro();
    Show_tuple();
    getch();
}
  
void DB_connect()
{
    strcpy((char *)uid.arr,"xxxxxx@//sedb.deu.ac.kr:1521/orcl");
    uid. len = (short) strlen((char *)uid.arr);
    strcpy((char *)pwd.arr,"xxxxxxx") ;
    pwd. len = (short) strlen((char *)pwd.arr);
      
    EXEC SQL CONNECT :uid IDENTIFIED BY :pwd;
       
    // connection이 실패했을경우의 처리부분
    if (salca.sqlcode != O && salca.sqlcode != -1405){
        printf("\7Cconnect error: %s", sqlca.sqlerrm.sqlerrmc);
        getch();
        exit (-1);
    }
    printf("Oracle Connect SUCCESS by %s\n", uid.arr);
}
</code>
</pre>
</p>

<p>▼ information</br>
<p>- 메인화면</br><img src="https://github.com/hyeon-bs/Portfolio/blob/09819a9ca2757b67f419d0ad07ed53dbee30720a/%E1%84%80%E1%85%A1%E1%84%8B%E1%85%A3%E1%84%83%E1%85%A9%E1%86%BC%20%E1%84%8C%E1%85%AE%E1%84%86%E1%85%B5%E1%86%AB%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7/static/information_%E1%84%92%E1%85%AA%E1%84%86%E1%85%A7%E1%86%AB.png" width="650" height="400" /></p>

<p>- 튜플</br>
<pre>
<code>
void Get_tuple()
{
/* ------------------------------------------------------------------------------------ */
    Retrieve the current maximum employee number
/* ------------------------------------------------------------------------------------ */
/* EXEC SQL BEGIN DECLARE SECTION; */
    
/* varchar name[100]; */
struct { unsigned short len; unsigned char arr[100]; } name;
/* varchar social[150]; */
struct { unsigned short len; unsigned char arr[150]; } social;
/* varchar spouse [150]; */
struct { unsigned short len; unsigned char arr[150]; } spouse;
/* varchar sal[150]; */
struct { unsigned short len; unsigned char arr[150]; } sal;
/* varchar fam[100]; */
struct ‹ unsigned short len; unsigned char arr[1001; } fam;
/* varchar protrank[100]; */
struct ‹ unsigned short len; unsigned char arr[100]; & protrank;
/* varchar tel[200]; */
struct { unsigned short len; unsigned char arr[2001; } tel;
/* varchar address[400]; */
struct { unsigned short len; unsigned char arr[400]; } address;
/* varchar child[100]; */
struct { unsigned short len; unsigned char arr[100]; } child;
/* varchar disrank[100]; */
struct { unsigned short len; unsigned char arr[100]; } disrank;
</code>
</pre>
</p>

<p>- 데이터베이스 연결</br>
<pre>
<code>
/* 사용자 입력 */
clrser();
    
print_screen ("scr_select.txt");
    
/* 실행시킬 SQL 문장*/
sprintf(dynstmt, "SELECT J_NAME, J_SOCIAL_NUM, J_SPOUSE_NUM, J_SAL, J_FAM, J_PROT_RANK, J_TEL, J_ADDRESS, J_CHILD, J_DIS_RANK FROM JUMIN") ;
    
/* select 문장이 제대로 구성되어 있는지 화면에 찍어봄 */
//printf("dynstmt:%s\n", dynstmt);
    
/* EXEC SQL PREPARE S2 FROM : dynstmt ; */
</code>
</pre>
</p>

<p>▼ disorder</br>
<p>- 메인화면</br><img src="https://github.com/hyeon-bs/Portfolio/blob/09819a9ca2757b67f419d0ad07ed53dbee30720a/%E1%84%80%E1%85%A1%E1%84%8B%E1%85%A3%E1%84%83%E1%85%A9%E1%86%BC%20%E1%84%8C%E1%85%AE%E1%84%86%E1%85%B5%E1%86%AB%20%E1%84%80%E1%85%AA%E1%86%AB%E1%84%85%E1%85%B5%20%E1%84%89%E1%85%B5%E1%84%89%E1%85%B3%E1%84%90%E1%85%A6%E1%86%B7/static/disorder_%E1%84%8C%E1%85%A9%E1%84%92%E1%85%AC%E1%84%92%E1%85%AA%E1%84%86%E1%85%A7%E1%86%AB.png" width="650" height="400" /></p>

<p>- 튜플</br>
<pre>
<code>
void Show_tuple()
{
/* ------------------------------------------------------------------------------------ */
    Retrieve the current maximum employee number
/* ------------------------------------------------------------------------------------ */
EXEC SQL BEGIN DECLARE SECTION;
    
    varchar name [100]; 
    varchar num[100]; 
    varchar grade [100]; 
    varchar cf [100]; 
    varchar cyn [100]; 
    varchar special[100];
        
    char dynstmt [1000];
EXEC SQL END DECLARE SECTION;
    
    int x, y, count=0, i ;
    /* Register sal_error() as the error handler. */ EXEC SQL WHENEVER SQLERROR DO sal_error("\70RACLE ERROR: \n"') ;
        
    gotoxy (1,1);
    print_screen("scr_select.txt");
    
    /* 실행시킬 SOL 문장*/
        sprintf(dynstmt, "SELECT p_name, P_social_num, P_grade, P_contact_f, P_contact_pa, p_manage FROM probation");
        
    EXEC SQL PREPARE S FROM : dynstmt ;
</code>    
</pre>
</p>

<p>- 데이터베이스 연결</br>
<pre>
<code>
void P_search(){
EXEC SQL BEGIN DECLARE SECTION;
    varchar p_name [100];
    varchar p_num[100];
    varchar p_grade [100];
    varchar p_cf[100];
    varchar p_cyn [100];
    varchar P_special [100];
    
    char dynstmt2 [1000];
EXEC SQL END DECLARE SECTION;
        
    int x, y, count=0, i ;
    /* Register sql_error() as the error handler. */
    EXEC SQL WHENEVER SQLERROR DO sal_error("\ 70RACLE ERROR: \n");
// print_screen("'scr_select.txt");
    
    gotoxy (22,6);
    gets (no_temp2);
    
/* 실행시킬 SOL 문장*/
sprintf(dunstmt2, "SELECT o name, o social num, o arade, o contact f, o contact pa, o manade EROM probation where p name LIKE regeseg'". no temp2):
EXEC SOL PREPARE S FROM : dynstmt2 :
</code>
</pre>
</p>

<p>- 조회</br>
<pre>
<code>
void P_search(){
EXEC SQL BEGIN DECLARE SECTION;
    varchar p_name [100];
    varchar p_num[100];
    varchar p_grade [100];
    varchar p_cf[100];
    varchar p_cyn [100];
    varchar P_special [100];
   
    char dynstmt2 [1000];
EXEC SQL END DECLARE SECTION;
        
    int x, y, count=0, i ;
    /* Register sql_error() as the error handler. */
    EXEC SQL WHENEVER SQLERROR DO sal_error("\ 70RACLE ERROR: \n");
// print_screen("'scr_select.txt");
    
    gotoxy (22,6);
    gets (no_temp2);
    
/* 실행시킬 SOL 문장*/
sprintf(dunstmt2, "SELECT o name, o social num, o arade, o contact f, o contact pa, o manade EROM probation where p name LIKE regeseg'". no temp2):
EXEC SOL PREPARE S FROM : dynstmt2 :
</code>
</pre>
</p>

<p>- 업데이트</br>
<pre>
<code>
for (;;)
{
    if(atoi(temp) ==1){
        sprintf(dynstmt22, "update probation set p_contact_f=p_contact_f+1, P_contact_pa = 'YES' where p_name like '988588' and rownum = 1", no_temp2);
        EXEC SQL EXECUTE IMMEDIATE : dynstmt22;
        EXEC SQL COMMIT WORK ;
       
    stropy (temp, "0");
    }
        
    if(atoi(temp)==2){
    gotoxy (52,23);
        printf ("연락을 취소합니다");
    }
    if( intcontact_f=5)
    {    
    sprintf(dynstmt23, "SELECT P_contact_f FROM probation");
    EXEC SQL EXECUTE IMMEDIATE : dynstmt23;
        
    sprintf(dynstmt24, "update probation set p_manage = 'YES' where P_contact_f >= 5");
    EXEC SQL EXECUTE IMMEDIATE :dynstmt24;
    EXEC SQL COMMIT WORK ;
    
    y = 10 ;
    break;
</code>
</pre>
</p>

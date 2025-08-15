# 간단한 쉘 프로그램 만들기
<b>내가 직접 사용할 나만의 쉘 프로그램을 C언어로 작성하고 손쉽게 운영체제 이용이 가능하도록 프로젝트를 설계하고 개발함. exit명령어, 기본 명령어 및 파일처리 명령어, 쉘 프로그램 통합의 기능을 구현함.</b>

### 프로젝트 목적과 내용
shell은 운영체제의 커널과 사용자의 사이를 연결시켜주는 역할을 하는 것으로 사용자가 명령을 내리면 그 명령을 해석하고 운영체제가 그에 따라 프로그램이 실행되는 역할을 한다. shell의 지시어를 받으면 운영체제는 하드웨어를 위한 지시어로 변경을 시켜준다. 이러한 점을 이용하여 리눅스 안에서 파일을 수정하거나 디렉토리 변경 및 수정, 통신 프로세스등에 대한 컴퓨터 운영체제의 구동이 가능한 것처럼 내가 직접 사용할 나만의 쉘 프로그램을 C언어로 작성하면 손쉽게 운영체제 이용이 가능하도록 할 수 있다. 기존에 없던 새로운 프로그램의 소스를 구현하여 내가 원하는 명령을 수행 시킬 수 있게 생성하는 것이 가능하다. 시그널 처리와, 파이프 기능, 리눅스 기본 명령어 및 파일처리 명령어, 좀비 프로레스 구현, 파일 재 지향, 쉘 내의 프로그램 종료 명령어를 구현하는 하나의 간단한 쉘 프로그램을 만들 수 있다.
    
### 프로젝트 기간
2018.11.12 ~ 2018.11.22

### 기술 스택
- C</br>
- Linux</br>
- Ubuntu</br>
- VMware Workstation Pro</br>
- Bash shell</br>
- GNU 프로그램 개발 도구(gcc, make, gbd 등)</br>

### 프로젝트
<p>▼ exit 명령어</br>
<p>- 메인화면</br><img src="" width="650" height="400" /></p>
<p>- 화면표시</br>
<pre>
<code>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include "common.h"

////////////////////////////////////////////////////////////////////////
//  표준 입/출력을 이용한 리다이렉트 함수
////////////////////////////////////////////////////////////////////////
void redirect(int sourcefd, char *sourcefile, int destfd, char *destfile, BOOLEAN append, BOOLEAN backgrnd)
{
   int flags, fd;

   // 열려있는 소스 파일디스크립터가 없고 백그라운드 실행일 경우
   if (sourcefd == 0 && backgrnd) 
   {
      strcpy(sourcefile, "/dev/null");
      sourcefd = BADFD;
   }

   // 소스 파일 디스크립터가 있을 경우
   if (sourcefd != 0) 
   {
      // 표준입력을 닫는다.
      if (close(0) == ERROR)
      {
         syserr("close");
      }

      if (sourcefd > 0) 
      {
         // 표준입력을 재정의한다.
         if (dup(sourcefd) != 0)
         {
            fatal("dup");
         }
      }

      // 소스파일을 연다.
      else if (open(sourcefile, O_RDONLY, 0) == ERROR)
      {
         fprintf(stderr, "Cannot open %s\n", sourcefile);
         exit(0);
      }
   }
   
   // 출력 파일 디스크립터가 있다면
   if (destfd != 1) 
   {
      // 표준 출력을 닫는다.
      if (close(1) == ERROR)
      {
         syserr("close");
      }

      // 표준 출력을 재정의
      if (destfd > 1) 
      {
         if (dup(destfd) != 1)
         {
            fatal("dup");
         }
      }

      else
      {
         // 파일이 없을 경우에 생성하고, 쓰기도 가능하게 파일을 설정
         flags = O_WRONLY | O_CREAT;
         
         // 추가 모드가 아닐 경우 파일을 비우는 옵션도 추가
         if (!append)
         {
            flags |= O_TRUNC;
         }

         // 파일을 연다.
         if (open(destfile, flags, 0666) == ERROR) 
         {
            fprintf(stderr, "Cannot create %s\n", destfile);
            exit(0);
         }

         // 추가 모드라면 가장 끝을 찾는다.
         if (append)   
         {
            if (lseek(1, 0L, 2) == ERROR) syserr("lseek");
         }
      }
   }

   // 파일디스크립터를 닫는다.
   for (fd =3; fd < MAXFD; fd++)
   {
      close(fd);
   }

   return;
}
</code>
</pre>
</p>
<p>▼ ls, pwd, cd, mkdir, rmdir, ln, cp, rm, mv, cat 명령</br>
<p>- 메인화면</br><img src="" width="650" height="400" /></p>
<p>- 튜플</br>
<pre>
<code>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "common.h"

////////////////////////////////////////////////////////////////////////
//  명령어를 파싱한다.
////////////////////////////////////////////////////////////////////////
SYMBOL parse(int *waitpid, BOOLEAN makepipe, int *pipefdp)
{
   SYMBOL symbol, term;
   int argc, sourcefd, destfd;
   int pid, pipefd[2];
   char *argv[MAXARG+1], sourcefile[MAXFNAME];
   char destfile[MAXFNAME];
   char word[MAXWORD];
   BOOLEAN append;

   argc = 0;         
   sourcefd = 0;      
   destfd = 1;         

   
   while (TRUE) 
   {
      // 하나의 단어씩을 분석한다.
      switch (symbol = getsymbol(word))      
      {
         // 일반 문자일 경우
         case S_WORD :               
            if(argc == MAXARG) 
            {
               fprintf(stderr, "Too many args.\n");
               break;
            }

            // 새로운 인자 배열을 메모리 할당 한다.
            argv[argc] = (char *) malloc(strlen(word)+1);
            
            if(argv[argc] == NULL) 
            {
               fprintf(stderr, "Out of arg memory.\n");
               break;
            }

            // 명령어 복사
            strcpy(argv[argc], word);
            
            // arg 카운터 증가
            argc++;
            continue;

         // < 일 경우
         case S_LT   : 
         
            // 파이프가 열려있다면 오류
            if(makepipe) 
            {
               fprintf(stderr, "Extra <.\n");
               break;
            }

            // 소스파일의 심볼값을 검사한다.
            if(getsymbol(sourcefile) != S_WORD) 
            {
               fprintf(stderr, "Illegal <.\n");
               break;
            }

            sourcefd = BADFD;
            continue;

         // > 혹은 >> 일 경우
         case S_GT   :
         case S_GTGT :

            // 목적 파일이 정의되어있지 않을 경우 에러
            if(destfd != 1) 
            {
               fprintf(stderr, "Extra > or >>.\n");
               break;
            }

            // 목적어 파일의 심볼타임이 문자열이 아니면 에러
            if(getsymbol(destfile) != S_WORD) 
            {
               fprintf(stderr, "Illegal > or >>.\n");
               break;
            }

            // GTGT일 경우 추가 모드로
            destfd = BADFD;
            append = (symbol == S_GTGT);
            continue;

         // |, &, ;, 줄바꿈 문자일 경우 - 하나의 명령어 단위
         case S_BAR  :
         case S_AMP  :
         case S_SEMI :
         case S_NL   :
         
            argv[argc] = NULL;
            // 심볼이 파이프(|) 일 경우
            if(symbol == S_BAR) 
            {
               if(destfd != 1) 
               {
                  fprintf(stderr, "> or >> conflicts with |.\n");
                  break;
               }
               
               // 현재의 표준출력 디스크립터를 함께 넘겨 명령어를 분석한다.
               term = parse(waitpid, TRUE, &destfd);
            }
            
            // 종료 문자 세팅
            else
            {
               term = symbol;
            }

            // 파이프가 열려있을 경우 파이프를 연결한다.
            if (makepipe) 
            {
               if (pipe(pipefd) == ERROR)
               {
                  syserr("pipe");
               }
               *pipefdp = pipefd[1];
               sourcefd = pipefd[0];
            }

            // 명령을 수행한다.
            pid = execute(argc, argv, sourcefd, sourcefile,
                        destfd, destfile,append, term == S_AMP);

            // 파이프가 아닐 경우 기다릴 PID를 설정
            if (symbol != S_BAR)
            {
               *waitpid = pid;
            }

            // 인자값을 없을 경우
            if (argc == 0 && (symbol != S_NL || sourcefd > 1))
            {
               fprintf(stderr, "Missing command.\n");
            }

            // 인자로 입력된 값들의 메모리 해제
            while (--argc >= 0)
            {
               free(argv[argc]);
            }

            return term;

         // 명령이 잘못되었을 경우 종료
         case S_EOF : 
            exit(0);
      } 
   }    
}
</code>
</pre>
</p>
